package com.shijiwei.slidemenu.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shijiwei.slidemenu.R;
import com.shijiwei.slidemenu.ui.fragment.FragmentOne;
import com.shijiwei.slidemenu.widget.slidemenu.interfaces.Source;
import com.shijiwei.slidemenu.widget.slidemenu.modle.SlideMenuItem;
import com.shijiwei.slidemenu.widget.slidemenu.widget.PagerManager;
import com.shijiwei.slidemenu.widget.slidemenu.widget.SlideMenuComponent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SlideMenuComponent slideMenu;
    private PagerManager pagerManager;

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

        list.add(new SlideMenuItem("巴德", R.mipmap.icn_1, FragmentOne.newInstance("8")));
        list.add(new SlideMenuItem("人头狗", R.mipmap.icn_2, FragmentOne.newInstance("9")));
        list.add(new SlideMenuItem("抢人头", R.mipmap.icn_3, FragmentOne.newInstance("10")));
        list.add(new SlideMenuItem("我的五杀", R.mipmap.icn_4, FragmentOne.newInstance("11")));
        list.add(new SlideMenuItem("哈儿", R.mipmap.icn_5, FragmentOne.newInstance("12")));
        list.add(new SlideMenuItem("瓜娃子", R.mipmap.icn_6, FragmentOne.newInstance("13")));
        list.add(new SlideMenuItem("你他妈居然看完了", R.mipmap.icn_7, FragmentOne.newInstance("14")));

        slideMenu.addSlideMenuItem(list);
        pagerManager.bind(getSupportFragmentManager(),slideMenu);


    }



}
