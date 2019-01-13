package com.example.vadim.testappforonetrak.presentation.presenter;

import android.content.SharedPreferences;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.vadim.testappforonetrak.Utils.ApiUtils;
import com.example.vadim.testappforonetrak.presentation.view.DataView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class DataPresenter extends MvpPresenter<DataView> {

    public static final String AIM_VALUE_KEY = "AIM VALUE KEY";

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private SharedPreferences mSharedPreferences;
    private int mAimSteps;

    public DataPresenter (SharedPreferences sharedPreferences){
        mSharedPreferences = sharedPreferences;
    }


    public void updateData() {

        mCompositeDisposable.add(
                ApiUtils.getApi().getData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> getViewState().setRefreshing(true))
                        .doFinally(() -> getViewState().setRefreshing(false))
                        .subscribe(dates -> getViewState().showData(dates, getAimSteps()),
                                throwable -> getViewState().showDownloadErrorMessage())
        );
    }

    public int getAimSteps(){
        return mAimSteps = mSharedPreferences.getInt(AIM_VALUE_KEY, 4000);
    }

    public void saveAimSteps(String aimString){
        try {
            int aimSteps = Integer.parseInt(aimString);
            SharedPreferences.Editor editor = mSharedPreferences.edit()
                    .putInt(AIM_VALUE_KEY, aimSteps);
            editor.commit();
            getViewState().updateAimSteps(aimSteps);
        } catch (Exception e){
            e.printStackTrace();
            getViewState().showIncorrectValueErrorMessage();
        }
    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.clear();
    }
}
