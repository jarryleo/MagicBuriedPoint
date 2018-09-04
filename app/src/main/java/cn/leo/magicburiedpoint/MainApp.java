package cn.leo.magicburiedpoint;

import android.app.Application;
import android.util.Log;

import cn.leo.buried.point.MagicBuriedPoint;
import cn.leo.buried.point.BuriedPointCallBack;

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
            public void onPageOpen(String pageClassName) {
                Log.e(TAG, "onPageOpen: " + pageClassName);
            }

            @Override
            public void onPageClose(String pageClassName) {
                Log.e(TAG, "onPageClose: " + pageClassName);

            }
        });
    }
}
