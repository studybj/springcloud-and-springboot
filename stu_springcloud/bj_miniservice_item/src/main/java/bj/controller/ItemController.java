package bj.controller;

import bj.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import bj.servoce.ItemService;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;
    @GetMapping("/item/{id}")
    public Item queryItemById(@PathVariable("id") Long id){
        return itemService.queryItemById(id);
    }
}
