package cn.leo.buried.point;

/**
 * @author : Jarry Leo
 * @date : 2018/9/4 11:30
 */
public interface BuriedPointCallBack {

    void onClick(String pageClassName, String viewIdName);

    void onPageOpen(String pageClassName);

    void onPageClose(String pageClassName);
}
