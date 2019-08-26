# news mode
## 项目结构
--module                                //application下的包
----AndroidManifest.xml                 //application下的AndroidManifest

--java                                  
----com.tgcity.mode.news
------base                              //基本的Application
------mvp                               //使用MVPPlugin插件必备的父类
------testRefreshView                   //测试类

----debug                               //application下的包
------NewsLauncherActivity              //application下的启动类