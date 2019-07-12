package ru.ftc.android.shifttemple;

import android.app.Application;
import android.content.Context;
/*import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;*/

import ru.ftc.android.shifttemple.network.RetrofitProvider;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:48
 */


//TODO переход на активность звонка
//регистрация
//изображения
//app logo
//при обновлении учитывать запрос в поиске?
//MultiDExApplication
public final class App extends Application {

    private RetrofitProvider retrofitProvider;

    public static RetrofitProvider getRetrofitProvider(Context context) {
        return getApp(context).retrofitProvider;
    }

    /*@Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }*/

    private static App getApp(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitProvider = RetrofitFactory.createRetrofitProvider(this);
    }
}