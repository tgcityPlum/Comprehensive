# 搭建项目
## 说明
>项目主基调是模块化开发，分离出business,common和function,基本上将app架空，MVP

## 模块结构
![avatar](/picture/comprehensive.png)

## 遗留问题
1\. 在library中，butterKnife在MVP模式中无法兼容，找不到R2文件

2\. MVPPlugin插件重构代码，因为自动生成后无法关联自定义文件

3\. 关于Application的继承，app直接继承与BaseApplication，但是NetApplication没有封装进去，暂未想到合适的解决方案
初步想法是在mode的index类中进行初始化 XXXRetrofitUtils