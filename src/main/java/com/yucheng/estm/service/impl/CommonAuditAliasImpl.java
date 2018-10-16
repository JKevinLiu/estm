package com.yucheng.estm.service.impl;

import com.yucheng.estm.constants.CommonContant;
import com.yucheng.estm.dto.ItemDto;
import com.yucheng.estm.entity.AuditItem;
import com.yucheng.estm.service.AuditAliasStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("common")
public class CommonAuditAliasImpl implements AuditAliasStrategy {
    @Override
    public List<List<ItemDto>> MergeItemForAlias(List<AuditItem> itemList) {

        List<List<ItemDto>> retList = new ArrayList<>();

        List<ItemDto> reqList = new ArrayList<>();//不动产申请书
        List<ItemDto> marrList = new ArrayList<>();//婚姻状况证明
        List<ItemDto> recogList = new ArrayList<>();//具结保证书
        List<ItemDto> regResiList = new ArrayList<>();//户口本
        List<ItemDto> cardList = new ArrayList<>();//身份证
        List<ItemDto> contractList = new ArrayList<>();//合同

        retList.add(reqList);
        retList.add(marrList);
        retList.add(recogList);
        retList.add(regResiList);
        retList.add(cardList);
        retList.add(contractList);

        for(AuditItem item : itemList){
            int itemType = item.getItemType();

            int fileType = item.getFileType();
            String fileTypeStr = "";

            switch (fileType){
                case CommonContant.FT_WORD:
                    fileTypeStr = CommonContant.FT_WORD_DESC;
                    break;
                case CommonContant.FT_IMG:
                    fileTypeStr = CommonContant.FT_IMG_DESC;
                    break;
            }

            String[] fileNames = item.getFileName().split(";");
            for(String fileName : fileNames){
                ItemDto itemDto = new ItemDto();
                itemDto.setFilePath(item.getPath() + fileName);
                itemDto.setFileType(fileTypeStr);

                switch (itemType){
                    case CommonContant.IT_REQ_CERT:
                        reqList.add(itemDto);
                        break;
                    case CommonContant.IT_MARRIAGE:
                        marrList.add(itemDto);
                        break;
                    case CommonContant.IT_RECOG:
                        recogList.add(itemDto);
                        break;
                    case CommonContant.IT_CARD:
                        regResiList.add(itemDto);
                        break;
                    case CommonContant.IT_REG_RESIDENCE:
                        cardList.add(itemDto);
                        break;
                    case CommonContant.IT_CONTRACT:
                        contractList.add(itemDto);
                        break;
                }
            }
        }


        return retList;
    }
}