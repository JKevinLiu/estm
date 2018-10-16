package com.yucheng.estm.config;

import com.yucheng.estm.entity.Catalog;
import com.yucheng.estm.service.CatalogService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InitCommonContext implements InitializingBean {

    private static Catalog root;
    private static Map<Integer, String> catalogNameMap = new HashMap<>();
    private static Map<Integer, String> busTypeMap = new HashMap<>();

    @Autowired
    private CatalogService catalogService;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Catalog> catalogList = catalogService.getCatalogListByParentId(-1);
        if(catalogList != null && catalogList.size() > 0){
            root = catalogList.get(0);
        }else{
            root = new Catalog();
        }
        initCatalogMap(root, "");

        System.out.println();

    }

    public void initCatalogMap(Catalog cat, String baseName){
        if(cat.getChildList() == null){
            return;
        }

        for(Catalog child : cat.getChildList()){
            int id = child.getId();

            if(child.getBusCode() != null && !"".equalsIgnoreCase(child.getBusCode())){
                busTypeMap.put(id,child.getBusCode());
            }

            String newBaseName = "";
            if("".equalsIgnoreCase(baseName)){
                newBaseName = child.getName();
            }else{
                newBaseName = baseName + ">>" + child.getName();

            }
            catalogNameMap.put(id, newBaseName);
            initCatalogMap(child, newBaseName);
        }


    }

    public static Catalog getRoot() {
        return root;
    }

    public static Map<Integer, String> getCatalogNameMap() {
        return catalogNameMap;
    }

    public static Map<Integer, String> getBusTypeMap() {
        return busTypeMap;
    }
}