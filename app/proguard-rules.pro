# 代码混淆压缩比，在0~7之间，默认为5，一般不做修改
-optimizationpasses 5

# 混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames

# 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclasses

# 这句话能够使我们的项目混淆后产生映射文件
# 包含有类名->混淆后类名的映射关系
-verbose

# 指定不去忽略非公共库的类成员
-dontskipnonpubliclibraryclassmembers

# 不做预校验，preverify是proguard的四个步骤之一，Android不需要preverify，去掉这一步能够加快混淆速度。
-dontpreverify
-dontoptimize

# 混淆时是否记录日志
-verbose
-ignorewarnings

# 保留Annotation不混淆
-keepattributes *Annotation*

# 避免混淆泛型
-keepattributes Signature
-keepattributes Exceptions,InnerClasses

-dontnote com.google.vending.licensing.ILicensingService
-dontnote com.android.vending.licensing.ILicensingService

# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
-keepattributes Deprecated,Synthetic,EnclosingMethod

# 重命名抛出异常时的文件名称
-renamesourcefileattribute SourceFile

# 指定混淆是采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不做更改
-optimizations !code/simplification/cast,!field/*,!class/merging/*
# OkHttp3
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**
#网络请求等与外界通信不能混淆
-keep class com.xxxxx.function.**.net.** { *; }
-keep class com.xxxxx.function.**.bean.** { *; }
-keep class com.xxxxx.common.net.** { *; }
-keep class com.xxxxx.common.bean.** { *; }
#加密不能混淆
-keep class com.xxxxx.crypt.** {*;}

#数据库实体类不能混淆
-keep class com.xxxxxx.function.**.dao.** { *; }
#工具类不混淆
-keep class com.xxxxx.common.utils.** { *; }
#greenDAO 3
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
-dontwarn org.greenrobot.greendao.database.**
-dontwarn rx.**
#greenDAO 3
#webview
-dontwarn  com.tencent.**
#eventbus不能混淆
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
#如果项目中倒入了de.greenrobot.even的beta1版本，需要添加此行代码
-keepclassmembers class ** {
    public void onEvent*(**);
}

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
   @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
   @butterknife.* <methods>;
}
# rx
-dontwarn rx.**
-keepclassmembers class rx.** { *; }
# retrolambda
-dontwarn java.lang.invoke.*

# Glide specific rules #
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}

-keep class jjr.com.playandroids.beans.fivelistbean.* {*;}
-keep class jjr.com.playandroids.beans.fourlistbean.* {*;}
-keep class jjr.com.playandroids.beans.knowbean.* {*;}
-keep class jjr.com.playandroids.beans.one.* {*;}
-keep class jjr.com.playandroids.beans.setting.* {*;}
-keep class jjr.com.playandroids.beans.sixlistbean.* {*;}
-keep class jjr.com.playandroids.beans.collect.* {*;}
-keep class jjr.com.playandroids.beans.wechat.* {*;}
-keep class jjr.com.playandroids.utils.* {*;}
-keep class jjr.com.playandroids.beans.* {*;}