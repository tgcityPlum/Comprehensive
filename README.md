# 尝试搭建项目
## 说明
>项目主基调是MVP

## 遗留问题
1\. 在library中，butterKnife在MVP模式中无法兼容，找不到R2文件

2\. MVPPlugin插件重构代码，因为自动生成后无法关联自定义文件

3\. 关于Application的继承，app直接继承与BaseApplication，但是NetApplication没有封装进去，暂未想到合适的解决方案