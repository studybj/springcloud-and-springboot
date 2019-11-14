package com.bj.stu.controller;

import com.bj.stu.entity.Menu;
import com.bj.stu.entity.Result;
import com.bj.stu.service.MenuService;
import com.bj.stu.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public Result findAll(){
        return ResultUtil.success("查询成功", menuService.findAll());
    }
    @GetMapping("/{menuId}")
    public Result findById(@PathVariable("menuId") long id){
        return ResultUtil.success("查询成功", menuService.findById(id));
    }

    @PostMapping
    public Result save(@RequestBody Menu menu){
        menuService.save(menu);
        return ResultUtil.success("添加成功");
    }

    @DeleteMapping
    public Result deleteAll(){
        menuService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{menuId}")
    public Result delete(@PathVariable long menuId){
        menuService.delete(menuId);
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{menuId}")
    public Result update(@PathVariable long menuId, @RequestBody Menu menu){
        menu.setId(menuId);
        menuService.update(menu);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody Menu menu){
        return ResultUtil.success("查询成功", menuService.search(menu));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody Menu menu, @PathVariable int page, @PathVariable int size){
//        PageRequest pageRequest = PageRequest.of(page - 1,size);
//        Page<Menu> pageinfo = menuService.searchPage(menu, pageRequest);
//        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
        return null;
    }

}
