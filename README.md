# MagicBuriedPoint
自动埋点框架

现阶段做到自动埋点 捕获所有点击事件和页面的显示隐藏

```
MagicBuriedPoint.init(new BuriedPointCallBack() {
            @Override
            public void onClick(String pageClassName, String viewIdName) {
                Log.e(TAG, "onClick: " + pageClassName + "-" + viewIdName);
            }

            @Override
            public void onPageResume(String pageClassName, Object pageObject) {
                Log.e(TAG, "onPageOpen: " + pageClassName);
            }

            @Override
            public void onPagePause(String pageClassName, Object pageObject) {
                Log.e(TAG, "onPageClose: " + pageClassName);

            }
        });
```

### 注意事项：

> Activity 和 Fragment 必须重写 onPause() 和 onResume() 方法，由父类重写也可以；         
> Fragment 还必须重写 setUserVisibleHint 方法；由父类重写也可以！         
> 必须要重写以上方法，否则埋点无法生效！        

### 依赖方法:

#### 1.在全局build里面添加下面github仓库地址

```
buildscript {
    ...
    dependencies {
	...
            classpath 'cn.leo.plugin:magic-plugin:1.0.0' //java 用这个
            classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.0' //kotlin 用这个
    }
}
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
google()和jcenter()这两个仓库一般是默认的，如果没有请加上

#### 2.在app的build里面添加插件和依赖
```
apply plugin: 'cn.leo.plugin.magic' //java 用这个
apply plugin: 'android-aspectjx'  //kotlin 用这个，编译速度会慢点
...
dependencies {
	...
	implementation 'com.github.jarryleo:MagicBuriedPoint:v2.1'
}
```


> 用于支持kotlin的插件用的是 [aspectjx](https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx)   
> 感谢插件作者    
> 因为编织所有二进制文件的问题导致编译速度慢的问题，请查看原作者提供的解决方案
