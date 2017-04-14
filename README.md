## 自定义ArcBarView

#### ArcBarView介绍
ArcBarView是一个底部具有弧线形的一个自定义View。
可以通过属性设置ArcBarView的高度，弧度（采用控制椭圆高和宽），
高度和弧度均采用的是屏幕的比例来控制。同时还支持渐变色效果。

#### ArcBarView支持的属性：
```
 <declare-styleable name="ArcBarView">
 
        <!-- arcBarHightToScreenPercentage：ArcBarView的高度和屏幕的高度比例 -->
        <attr name="arcBarHightToScreenPercentage" format="float" />

        <!-- arcBarHightToArcPercentage:椭圆的高度一半与ArcBarView的高度比例 -->
        <attr name="arcBarHightToArcPercentage" format="float" />

        <!-- arcBarWidthToArcPercentage:椭圆宽度超出屏幕的总距离的一半与屏幕宽度的比例 -->
        <attr name="arcBarWidthToArcPercentage" format="float" />

        <!-- ArcBarView的color颜色 -->
        <attr name="arcBarColor" format="color" />

        <!--是否显示渐变色，如果设置为true，那么arcBarColor属性无效，而且至少使用2种渐变色-->
        <attr name="arcBarShowGradientColor" format="boolean" />
        
        <!-- 开始的渐变色 -->
        <attr name="arcBarStartGradientColor" format="color" />
        <!--中间的渐变色-->
        <attr name="arcBarCenterGradientColor" format="color" />
        <!-- 最后的渐变色 -->
        <attr name="arcBarEndGradientColor" format="color" />
    </declare-styleable>
```

#### ArcBarView使用渐变色
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.yundoku.arcbarview.MainActivity">

    <com.yundoku.arcbarview.view.ArcBarView xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:arcBarCenterGradientColor="@color/colorPrimary"
        app:arcBarEndGradientColor="@color/colorPrimaryDark"
        app:arcBarHightToArcPercentage="0.2"
        app:arcBarHightToScreenPercentage="0.4"
        app:arcBarShowGradientColor="true"
        app:arcBarStartGradientColor="@color/colorAccent"
        app:arcBarWidthToArcPercentage="0.1" />

</RelativeLayout>
```

效果图：

![image](http://oe9ggtbcb.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720170414173234.png)

#### ArcBarView使用arcBarView
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.yundoku.arcbarview.MainActivity">

    <com.yundoku.arcbarview.view.ArcBarView xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:arcBarColor="@color/colorPrimary"
        app:arcBarHightToArcPercentage="0.2"
        app:arcBarHightToScreenPercentage="0.4"
        app:arcBarWidthToArcPercentage="0.1" />

</RelativeLayout>
```

效果图：

![image](http://oe9ggtbcb.bkt.clouddn.com/wwww.png)