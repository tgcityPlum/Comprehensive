# 指定代码的压缩级别
-optimizationpasses 5
# 是否使用大小写混合
-dontusemixedcaseclassnames
#不做预校验，preverify是proguard的四个步骤之一，Android不需要preverify，去掉这一步能够加快混淆速度。
-dontpreverify
# 混淆时是否记录日志
-verbose
#不进行优化
-dontshrink
#不压缩
-dontoptimize
#抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
# 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#混淆字典参考：https://mp.weixin.qq.com/s/ya0RiLuHfIBrPLkl2lTbaA
# 外部模糊字典
#-obfuscationdictionary proguardDictionary/proguard-o0O.txt
# class模糊字典
#-classobfuscationdictionary proguardDictionary/proguard-o0O.txt
# package模糊字典
#-packageobfuscationdictionary proguardDictionary/proguard-o0O.txt

#是否忽略检测
-ignorewarnings

#忽略主工程警告
-dontwarn com.tgcity.demo.**
#忽略谷歌警告
-dontwarn com.google.**
#忽略V4包警告
-dontwarn android.support.v4.**
#忽略WebView警告
-dontwarn android.webkit.WebView

# 保持必须的系统类不被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * extends android.view.View
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**

 -keep class android.support.v8.renderscript.** { *; }
 -dontwarn android.support.v8.renderscript.**
 -keep public class android.support.v8.renderscript.** { *; }

-keep public class javax.**
-keep public class android.webkit.**
-keep class com.google.**{*;}

#实体类不混淆
#-keep class com.eagersoft.youzy.youzy.bean.**{*;}
#-keep class com.chad.library.adapter.base.entity.**{*;}
#-keep class com.eagersoft.youzy.youzy.data.greendao.model.**{*;}
#-keep class com.eagersoft.youzy.youzy.third.share.bean.**{*;}
#保持注解库不被混淆
#-keep class com.eagersoft.youzy.annotation.** {*;}

 # 保持 native 方法不被混淆
-keepclasseswithmembernames class * {
   native <methods>;
}
-keepclassmembers class * {
    native <methods>;
}

# 保持自定义控件类不被混淆
-keepclasseswithmembers class * {
   public <init>(android.content.Context, android.util.AttributeSet);
   public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 保持Click事件不被混淆
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# 保持枚举 enum 类不被混淆
-keepclassmembers enum * {
   public static **[] values();
   public static ** valueOf(java.lang.String);
}

#保持Parcelable不被混淆
-keep class * implements android.os.Parcelable {
   public static final android.os.Parcelable$Creator *;
}
# 保持序列化不被混淆
-keep public class * implements java.io.Serializable {*;}
-keepclassmembers class * implements java.io.Serializable {
   static final long serialVersionUID;
   private static final java.io.ObjectStreamField[]   serialPersistentFields;
   private void writeObject(java.io.ObjectOutputStream);
   private void readObject(java.io.ObjectInputStream);
   java.lang.Object writeReplace();
   java.lang.Object readResolve();
}

#保持JSON不被混淆
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

#保持资源文件不被混淆
-keepclassmembers class **.R$* {
    public static <fields>;
}
#反射相关的不混淆 防止找不到
#x:\...\app\src\main\java\com\eagersoft\youzy\youzy\data\cache\stategy
#-keep class com.eagersoft.youzy.youzy.data.cache.stategy.**{*;}
#-keep class com.eagersoft.youzy.youzy.route.**{*;}
#-keep class com.eagersoft.youzy.youzy.RouteManage{*;}


#webview开始
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}
#-keepclassmembers class com.eagersoft.youzy.youzy.dao.AndroidInterface {#js交互接口
#    <methods>;
#}
#webview结束

# Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod

#-------------------------------------------------第三方库专区-------------------------------------------------
#忽略java扩展包中的注释警告
-dontwarn javax.annotation.**
-dontwarn javax.inject.**

# OkHttp3
-dontwarn okhttp3.**
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-keep class okhttp3.**{*;}
-dontwarn okio.**
# OkHttp3结束

# Retrofit开始
-keep class com.squareup.**{ *; }
-dontwarn com.squareup.**
-keepattributes Signature
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
# Retrofit结束

# RxJava RxAndroid开始
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
# RxJava RxAndroid结束

#glide开始
-keep class com.bumptech.glide.Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
#glide结束