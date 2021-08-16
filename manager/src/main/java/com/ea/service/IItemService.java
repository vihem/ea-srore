package com.ea.service;

import com.ea.entity.Item;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IItemService {
    List<Item> getItemList();
    Item getItemById(Long itemId);
    PageInfo<Item> getItemList(Integer page, Integer limit);
}
