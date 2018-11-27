package cn.leo.magicburiedpoint;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import cn.leo.buried.point.BuriedPointCallBack;
import cn.leo.buried.point.MagicBuriedPoint;

/**
 * @author : Jarry Leo
 * @date : 2018/9/4 13:42
 */
public class MainApp extends Application {
    private static final String TAG = "Application";

    @Override
    public void onCreate() {
        super.onCreate();
        MagicBuriedPoint.init(new BuriedPointCallBack() {
            @Override
            public void onClick(String pageClassName, String viewIdName) {
                Log.e(TAG, "onClick: " + pageClassName + "-" + viewIdName);
            }

            @Override
            public void onPageResume(String pageClassName, Object pageObject) {
                Log.e(TAG, "onPageOpen: " + pageClassName);
                if (pageObject instanceof Activity) {
                    Log.e(TAG, "onPageResume: " + pageObject);
                }
            }

            @Override
            public void onPagePause(String pageClassName, Object pageObject) {
                Log.e(TAG, "onPageClose: " + pageClassName);

            }
        });
    }
}
