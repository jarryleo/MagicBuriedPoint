# MagicBuriedPoint
自动埋点框架

现阶段做到自动埋点 点击事件和页面的开启关闭
fragment 没有做可见性处理,还不完善;

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
### 依赖方法:
#### To get a Git project into your build:
#### Step 1. Add the JitPack repository to your build file
1.在全局build里面添加下面github仓库地址
Add it in your root build.gradle at the end of repositories:
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

#### Step 2. Add the dependency
2.在app的build里面添加插件和依赖
```
apply plugin: 'cn.leo.plugin.magic' //java 用这个
apply plugin: 'android-aspectjx'  //kotlin 用这个，编译速度会慢点
...
dependencies {
	...
	implementation 'com.github.jarryleo:MagicBuriedPoint:v1.0'
}
```


> 用于支持kotlin的插件用的是 [aspectjx](https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx)   
> 感谢插件作者    
> 因为编织所有二进制文件的问题导致编译速度慢的问题，请查看原作者提供的解决方案
