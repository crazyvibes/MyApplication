package com.ajayam.myapplication.viewmodels;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ajayam.myapplication.data.ApiManager;
import com.ajayam.myapplication.data.Repository;
import com.ajayam.myapplication.model.Collections;
import com.ajayam.myapplication.model.Example;
import com.ajayam.myapplication.utils.CommonMethods;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Retrofit;

public class MainViewModel extends AndroidViewModel {
    private Repository repository;
    private final CompositeDisposable disposable = new CompositeDisposable();
    public final MutableLiveData<Example> mutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();
    private ApiManager apiManager;
    Context context;
    ArrayList<Collections> collectionsList = new ArrayList<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        Retrofit retrofit = CommonMethods.getRetrofit(application);
        apiManager = retrofit.create(ApiManager.class);
        context = application.getApplicationContext();
        repository = new Repository(apiManager);
    }

    public LiveData<Example> getData() {
        getCourseData();
        return mutableLiveData;
    }

    private void getCourseData() {
        try {
            isLoading.setValue(true);
            disposable.add(repository.getCoursesData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<Example>() {
                        @Override
                        public void onSuccess(Example response) {
                            Log.e("TAG", "getDataResponse: "+new Gson().toJson(response));
                            isLoading.setValue(false);

                            mutableLiveData.setValue(response);

                        }

                        @Override
                        public void onError(Throwable e) {
                            if (e instanceof HttpException) {
                                try {
                                    isLoading.setValue(false);
                                    JSONObject jObjError = new JSONObject(((HttpException) e).response().errorBody().string());
                                    error.setValue(jObjError.toString());
                                    Log.e("TAG", "getDataResponse: "+"error" );
                                } catch (IOException | JSONException er) {
                                    er.printStackTrace();
                                }
                            }

                        }

                    }));
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();

    }
}
