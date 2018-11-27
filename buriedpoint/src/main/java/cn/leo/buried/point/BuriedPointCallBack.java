package cn.leo.buried.point;

/**
 * @author : Jarry Leo
 * @date : 2018/9/4 11:30
 */
public interface BuriedPointCallBack {
    /**
     * 返回所有点击事件
     *
     * @param pageClassName 点击事件所在页面全类名
     * @param viewIdName    点击的view的id名称
     */
    void onClick(String pageClassName, String viewIdName);

    /**
     * 页面展示
     *
     * @param pageClassName 页面全类名
     * @param pageObject    页面对象
     */
    void onPageResume(String pageClassName, Object pageObject);

    /**
     * 页面隐藏
     *
     * @param pageClassName 页面全类名
     * @param pageObject    页面对象
     */
    void onPagePause(String pageClassName, Object pageObject);

}
