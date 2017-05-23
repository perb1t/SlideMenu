
# SlideMenu
## 侧滑菜单<br>
之前在博客上面看见有人写了一份类似功能的开源项目，我马上star一波看看源码，发现并不是很好用，但是效果很炫，于是我决定自己重新写一个这样的开源控件。欢迎大家star，一起学习，一起优化这个项目！！<br>
### 主要功能介绍<br>
侧滑菜单对Fragment的切换管理，使用了炫酷的揭露动画<br><br><br>

![image](https://github.com/perb1t/SlideMenu/raw/master/effect/effect.gif)
### 使用方法<br>
主要调用类：SlideMenuComponent，PagerManager<br>
步骤一<br>
让你的BaseFragment继承SlideMenuPager（用于View截图与获取）<br>

步骤二<br> 
xml文件调用<br>
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ui.activity.MainActivity">

    <com.shijiwei.slidemenu.widget.slidemenu.widget.PagerManager
        android:id="@+id/peger_manager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

    <com.shijiwei.slidemenu.widget.slidemenu.widget.SlideMenuComponent
        android:id="@+id/slide_menu"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        />


</RelativeLayout>
步骤三<br>
```
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        slideMenu = (SlideMenuComponent) findViewById(R.id.slide_menu);
        pagerManager = (PagerManager) findViewById(R.id.peger_manager);

        List<Source> list = new ArrayList<>();
        list.add(new SlideMenuItem("德玛西亚", R.mipmap.icn_1, FragmentOne.newInstance("1")));
        list.add(new SlideMenuItem("大盖伦", R.mipmap.icn_2, FragmentOne.newInstance("2")));
        list.add(new SlideMenuItem("大宝剑", R.mipmap.icn_3, FragmentOne.newInstance("3")));
        list.add(new SlideMenuItem("菊花信", R.mipmap.icn_4, FragmentOne.newInstance("4")));
        list.add(new SlideMenuItem("信爷", R.mipmap.icn_5, FragmentOne.newInstance("5")));
        list.add(new SlideMenuItem("诡术妖姬", R.mipmap.icn_6, FragmentOne.newInstance("6")));
        list.add(new SlideMenuItem("辛德拉", R.mipmap.icn_7, FragmentOne.newInstance("7")));

        slideMenu.addSlideMenuItem(list);
        pagerManager.bind(getSupportFragmentManager(),slideMenu);


    }
```
