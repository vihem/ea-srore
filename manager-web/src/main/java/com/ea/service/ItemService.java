package com.ea.service;

import com.ea.client.ItemClientFeign;
import com.ea.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemClientFeign itemClientFeign;

    public List<ItemVO> getItemList(){
        return itemClientFeign.getItemList();
    }

    public ItemVO getItemById(Long itemId){
        return itemClientFeign.getItemById(itemId);
    }

    public List<ItemVO> getItemList(Integer page, Integer limit){
        return itemClientFeign.getItemList(page, limit);
    }
}
