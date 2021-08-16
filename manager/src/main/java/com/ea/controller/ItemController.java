package com.ea.controller;

import com.ea.service.IItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//  http://localhost:8001/swagger-ui.html
@Api(value = "商品管理", protocols = "http")
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private IItemService itemService;

    @GetMapping("/all")
    @ApiOperation(value = "获取所有商品", notes = "查询商品列表")
    public Object getAll() {
        return itemService.getItemList();
    }

    @GetMapping("/getOne")
    @ApiOperation(value = "查询商品详细信息", notes = "根据url的id来获取商品详细信息")
    public Object getItemById(@RequestParam Long itemId) {
        return itemService.getItemById(itemId);
    }

    @GetMapping("/item-list/{page}/{limit}")
    public Object getItemList(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit){
        return itemService.getItemList(page, limit);
    }
}
