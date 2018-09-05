# MagicBuriedPoint
自动埋点框架

现阶段做到自动埋点 点击事件和页面的开启关闭
fragment 没有做可见性处理,还不晚上;

```
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
```
