package com.ea.service.impl;

import com.ea.entity.Item;
import com.ea.mapper.ItemMapper;
import com.ea.service.IItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements IItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Item> getItemList() {
        return itemMapper.getItemList();
    }

    @Override
    public Item getItemById(Long itemId) {
        return itemMapper.getItemById(itemId);
    }

    @Override
    public PageInfo<Item> getItemList(Integer page, Integer limit){
        PageHelper.startPage(page, limit);
        List<Item> itemList = itemMapper.getItemList();
        return new PageInfo<>(itemList);
    }
}
