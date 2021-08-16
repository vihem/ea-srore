package com.ea.web;

import com.ea.client.ItemClientFeign;
import com.ea.service.ItemService;
import com.ea.vo.ItemVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@Api(value = "商品管理web", protocols = "http")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ApiOperation(value = "查询所有商品", notes = "查询所有商品列表")
    public List<ItemVO> getItemList(){
        return itemService.getItemList();
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询商品详细信息", notes = "根据url的id来获取商品详细信息")
    public ItemVO getItemById(@RequestParam Long itemId){
        return itemService.getItemById(itemId);
    }

    @RequestMapping(value = "/item-list/{page}/{limit}", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取商品列表")
    public List<ItemVO> getItemList(@PathVariable Integer page, @PathVariable Integer limit){
        return itemService.getItemList(page, limit);
    }
}
