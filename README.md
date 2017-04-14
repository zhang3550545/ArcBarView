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
    
    <!-- 开始的渐变色 -->
    <attr name="startColor" format="color" />
    
    <!--中间的渐变色-->
    <attr name="centerColor" format="color" />
    
    <!-- 最后的渐变色 -->
    <attr name="endColor" format="color" />
    
    <!--   注意：使用渐变色最少有2个色值   -->
    
</declare-styleable>
```

#### ArcBarView的使用
```
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.yundoku.arcbarview.MainActivity">

    <com.yundoku.arcbarview.view.ArcBarView 
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:arcBarHightToArcPercentage="0.2"
        app:arcBarHightToScreenPercentage="0.4"
        app:arcBarWidthToArcPercentage="0.1"
        app:centerColor="@color/colorPrimary"
        app:endColor="@color/colorPrimaryDark"
        app:startColor="@color/colorAccent" />

</RelativeLayout>
```

效果图：

![image](http://oe9ggtbcb.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720170414173234.png)

