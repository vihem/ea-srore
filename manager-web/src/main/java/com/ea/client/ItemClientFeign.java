package com.ea.client;

import com.ea.vo.ItemVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "manager")
public interface ItemClientFeign {
    @RequestMapping(value = "/item/all", method = RequestMethod.POST)
    List<ItemVO> getItemList();

    @RequestMapping(value = "/item/getOne", method = RequestMethod.GET)
    ItemVO getItemById(@RequestParam Long itemId);

    @RequestMapping(value = "/item/item-list/{page}/{limit}", method = RequestMethod.GET)
    List<ItemVO> getItemList(@PathVariable Integer page, @PathVariable Integer limit);
}
