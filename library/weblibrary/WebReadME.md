# Web模块简介

## 一、AgentWeb库说明
本库基于AgentWeb（https://github.com/Justson/AgentWeb)上做了个二次封装，因为原有框架中的一堆混乱参数，使得开发者不知道该设置哪些参数，为了避免这个现象故而诞生了本库，
本库中的抽象类WebViewConfig强制实现的方法中提供的参数足已满足日常大部分使用需求，开发者可以实现WebViewConfig之后定制业务需求，
之后仅仅需要调用 webViewLayout.with(this).loadUrl("http://news.qq.com/")即可使用，如果开发者觉得还不满足，可以继续扩展本库的WebViewConfig。

## 二、类介绍
WebViewConfig：功能抽象类，实现该类定制业务
WebViewManager：WebView参数构造，仅仅是为开发者构造出来的WebViewConfig提供一个载体
WebViewLayout：核心功能，在此类中基于AgentWeb定制的各项WebView的功能，也是开发者需要添加到XML中的
WebViewLayoutPosition：WebView页面布局的显示方式，由此可控制头部View是否显示，开发者可以根据此类暂时的效果来扩展
OnWebViewOperationCallBack：WebView控制接口，为WebViewConfig与WebViewLayout起到了一个桥梁的作用，用来实现与WebView的各种交互控制，目前仅仅提供了返回与截图两种常用功能，需要其他什么功能可以在此定制

注意！强烈建议在Activity或Fragment生命周期中调用onPause()、onResume()和onDestroy()三个方法

## 三、使用方式
1\. 添加布局
```
    <com.tgcity.web.WebViewLayout
        android:id="@+id/webview"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </com.tgcity.web.WebViewLayout>
```
2\. 关联控件
```
    private WebViewLayout webViewLayout;
    webViewLayout= (WebViewLayout) findViewById(R.id.webview);
```
3\. 实现WebViewConfig抽象类  
>类似于WebViewInit文件  

4\. 如果涉及到Activity的业务操作的话那就在WebView所在的Activity中初始化一下，
如果单单就一个WebView没有其他乱七八糟的定制的话那就推荐在Application做成全局通用即可，看使用者需求。
```
    WebViewManager.getInstance()
       .build(new WebViewManager.Builder()
       .setWebViewConfig(new WebViewInit(this)))
       //这里的this是Activity，因为例子中用到了Activity.finish(),
       //如果没有这项业务的话推荐这段初始化放在Application中;
```
5\. 使用
```
    webViewLayout
    .loadUrl("http://xxx/")
    .with(this)//绑定Activity或Fragment，这步操作必做！
    .show();
```
6\. 项目需要加入混淆 ， 请加入如下配置
```
-keep class com.just.agentweb.** {
    *;
}

-dontwarn com.just.agentweb.**

#Java 注入类不要混淆 ， 例如 sample 里面的 AndroidInterface 类 ， 需要 Keep 。
-keepclassmembers class com.tgcity.web.interfaces.AndroidInterface{ *; }
```

     
   