# web mode
## project structure
--module                                //package below application
----AndroidManifest.xml                 //AndroidManifest in module used by release

--java                                  
----com.tgcity.mode.web
------index                              //web common activity

----debug                               //package below java
------WebLauncherActivity              //web launcher activity used by release

## project function
the main function is to show web page. the mode provide common web activity and support expand!