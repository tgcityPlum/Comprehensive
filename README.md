# 搭建项目
## 说明
>项目主基调是模块化开发，分离出business,common和function,基本上将app架空，模块之间通过ARoute进行通信，整个项目分为app层，业务层，控件层，功能层  
1\. app层不处理核心业务，只负责打包时的配置功能
2\. 业务层处理项目的核心业务，比如 launch,login,home,news,mine等核心业务，每个业务完全解耦，通过ARoute进行通信
3\. 控件层处理一些主要的公共控件，比如 refreshview,webview,network,mvp等，每个控件相对独立，引用功能层资源
4\. 功能层处理一些基本的功能，比如 baseactivity，baseapplication，dp，color等
5\. 模块化开启在gradle.properties进行配置
## 模块结构
![avatar](/picture/comprehensive.png)

## 使用说明
1\. 在根目录下的build.gradle文件中添加
```aidl
allprojects {
    repositories {
       
        maven { url 'https://www.jitpack.io' }
    }
}
```
2\. 在app目录下的build.gradle文件中添加
```aidl
dependencies {
    implementation 'com.github.tgcityPlum:Comprehensive:1.1.0'
}
```

3\. 当前项目中**minSdkVersion = 16 targetSdkVersion = 28**如果出现不兼容问题可以**new issues**

## 遗留问题
1\. 在library中，butterKnife在MVP模式中无法兼容，找不到R2文件

2\. MVPPlugin插件重构代码，因为自动生成后无法关联自定义文件

3\. 关于Application的继承，app直接继承与BaseApplication，但是NetApplication没有封装进去，暂未想到合适的解决方案
初步想法是在mode的index类中进行初始化 XXXRetrofitUtils