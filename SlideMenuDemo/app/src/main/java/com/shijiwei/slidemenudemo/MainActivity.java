package com.shijiwei.slidemenudemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shijiwei.slidemenu.widget.slidemenu.interfaces.Source;
import com.shijiwei.slidemenu.widget.slidemenu.modle.SlideMenuItem;
import com.shijiwei.slidemenu.widget.slidemenu.widget.PagerManager;
import com.shijiwei.slidemenu.widget.slidemenu.widget.SlideMenuComponent;
import com.shijiwei.slidemenudemo.base.ArticleFragment;

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
        pagerManager = (PagerManager) findViewById(R.id.pager_manager);

        List<Source> list = new ArrayList<>();
        list.add(new SlideMenuItem("德玛西亚", R.mipmap.icn_1, NumberFragment.newInstance("1")));
        list.add(new SlideMenuItem("大盖伦", R.mipmap.icn_2, NumberFragment.newInstance("2")));
        list.add(new SlideMenuItem("大宝剑", R.mipmap.icn_3, NumberFragment.newInstance("3")));
        list.add(new SlideMenuItem("菊花信", R.mipmap.icn_4, NumberFragment.newInstance("4")));
        list.add(new SlideMenuItem("信爷", R.mipmap.icn_5, NumberFragment.newInstance("5")));
        list.add(new SlideMenuItem("诡术妖姬", R.mipmap.icn_6, NumberFragment.newInstance("6")));
        list.add(new SlideMenuItem("辛德拉", R.mipmap.icn_7, NumberFragment.newInstance("7")));

        list.add(new SlideMenuItem("巴德", R.mipmap.icn_1, NumberFragment.newInstance("8")));
        list.add(new SlideMenuItem("人头狗", R.mipmap.icn_2, NumberFragment.newInstance("9")));
        list.add(new SlideMenuItem("抢人头", R.mipmap.icn_3, NumberFragment.newInstance("10")));
        list.add(new SlideMenuItem("我的五杀", R.mipmap.icn_4, NumberFragment.newInstance("11")));
        list.add(new SlideMenuItem("哈儿", R.mipmap.icn_5, NumberFragment.newInstance("12")));
        list.add(new SlideMenuItem("瓜娃子", R.mipmap.icn_6, NumberFragment.newInstance("13")));
        list.add(new SlideMenuItem("你他妈居然看完了", R.mipmap.icn_7, ArticleFragment.newInstance(getResources().getString(R.string.article))));

        slideMenu.addSlideMenuItem(list);
        pagerManager.bind(getSupportFragmentManager(), slideMenu);


    }
}
