package ru.ftc.android.shifttemple.features;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract <T extends MvpView> MvpPresenter<T> getPresenter();

    protected abstract <T extends MvpView> T getMvpView();

    private MvpPresenter<MvpView> presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(presenter == null)return;//TODO заглушка для MainActivity
        presenter.attachView(getMvpView());
    }

    @Override
    protected void onStop() {
        super.onStop();
        //TODO заглушка для MainActivity
        if(presenter == null)return;
        presenter.detachView();
    }

}