# base library
## 版本号
1.0.0
## 项目结构
--activity                  //Activity包
----BaseCommonActivity      //项目中常规的Activity都继承此类
----BaseLauncherTimeActivity//处理Activity的启动时间
----BaseImmersionBarActivity//处理Activity的沉浸式状态栏
----BaseEventLogicActivity  //处理Activity的事件逻辑
----BaseBindViewActivity    //绑定view层
----BaseBindRouterActivity  //绑定路由
----BaseOrientationActivity //管理Activity的屏幕方向
----BaseLifecycleActivity   //管理Activity的生命周期
----BaseMemoryActivity      //监听系统内存的状况
----BaseLoadingActivity     //处理加载指示器

--application               //application包
--constant                  //constant包
--mvp                       //mvp包：提取mvp基础文件
--network                   //network包：提取network基础文件
--utils                     //utils包：自定义工具类
--widget                    //widget包：自定义控件