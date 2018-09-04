package cn.leo.buried.point;

/**
 * @author : Jarry Leo
 * @date : 2018/9/4 13:34
 */
public class MagicBuriedPoint {

    private static BuriedPointCallBack buriedPointCallBack;

    private MagicBuriedPoint() {
    }

    public static void init(BuriedPointCallBack callBack) {
        buriedPointCallBack = callBack;
    }

    static void onClick(String pageClassName, String viewIdName) {
        if (buriedPointCallBack == null) {
            return;
        }
        buriedPointCallBack.onClick(pageClassName, viewIdName);
    }

    static void onPageOpen(String pageClassName) {
        if (buriedPointCallBack == null) {
            return;
        }
        buriedPointCallBack.onPageOpen(pageClassName);
    }

    static void onPageClose(String pageClassName) {
        if (buriedPointCallBack == null) {
            return;
        }
        buriedPointCallBack.onPageClose(pageClassName);
    }
}
