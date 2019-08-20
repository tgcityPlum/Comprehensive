##-------------------------------- 第三方库 ---------------

#----------------------------- 实体类 ----------------
-keep class com.tgcity.base.network.bean.**{*;}
-keep class com.tgcity.base.network.cache.**{*;}

#----------------------------- butterknife ----------------
# PS 针对butterknife7.0及以上的
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#----------------------------- glide ----------------------
-keep class com.bumptech.glide.Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#----------------------------- RxJava RxAndroid -----------
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

#----------------------------- ImmersionBar -----------
 -keep class com.gyf.immersionbar.* {*;}
 -dontwarn com.gyf.immersionbar.**

#----------------------------- autoSize -----------
 -keep class me.jessyan.autosize.** { *; }
 -keep interface me.jessyan.autosize.** { *; }

#----------------------------- RxLifeCycle2 -----------
-keep class com.trello.rxlifecycle2.** { *; }
-keep interface com.trello.rxlifecycle2.** { *; }
-dontwarn com.trello.rxlifecycle2.**

-keep class com.github.mikephil.charting.** { *; }
-dontwarn com.github.mikephil.charting.data.realm.**