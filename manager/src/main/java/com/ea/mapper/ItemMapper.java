package com.ea.mapper;

import com.ea.entity.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ItemMapper {
    List<Item> getItemList();
    Item getItemById(Long itemId);

}

