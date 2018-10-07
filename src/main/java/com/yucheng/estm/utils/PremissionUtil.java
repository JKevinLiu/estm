package com.yucheng.estm.utils;

import com.yucheng.estm.entity.ItemNavinfo;
import com.yucheng.estm.entity.Navinfo;
import com.yucheng.estm.entity.SecondNavinfo;

import java.util.ArrayList;
import java.util.List;

public class PremissionUtil {
    public static List<Navinfo> getNavList() {
        List<Navinfo> navList = new ArrayList<>();
        Navinfo nav1 = new Navinfo();
        nav1.setTitle("资料管理");

        navList.add(nav1);

        List<SecondNavinfo> nav1SecondMenuList = new ArrayList<>();
        nav1.setSecondMenuList(nav1SecondMenuList);

        SecondNavinfo secondMenu = new SecondNavinfo();
        nav1SecondMenuList.add(secondMenu);

        List<ItemNavinfo> nav1ItemList = new ArrayList<>();
        secondMenu.setItemList(nav1ItemList);

        ItemNavinfo item1 = new ItemNavinfo();
        item1.setManuName("待审材料");
        item1.setUrl("wait_audit");

        ItemNavinfo item2 = new ItemNavinfo();
        item2.setManuName("已审材料");
        item2.setUrl("finished_audit");

        ItemNavinfo item3 = new ItemNavinfo();
        item3.setManuName("打回记录");
        item3.setUrl("back_audit");

        nav1ItemList.add(item1);
        nav1ItemList.add(item2);
        nav1ItemList.add(item3);


        Navinfo nav2 = new Navinfo();
        nav2.setTitle("推送管理");

        navList.add(nav2);

        Navinfo nav3 = new Navinfo();
        nav3.setTitle("权限管理");

        navList.add(nav3);
        return navList;
    }
}