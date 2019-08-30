# home mode
## project structure
--module                                //package below application
----AndroidManifest.xml                 //AndroidManifest in module used by release

--java                                  
----com.tgcity.mode.web
------index                              //web common activity

----debug                               //package below java used by release
------activity                            
--------HomeLauncherActivity            //home launcher activity
------base                              
--------HomeApplication                 //home application

## project function
the main function is to show home fragment and child page. the mode provide home fragment and support expand!