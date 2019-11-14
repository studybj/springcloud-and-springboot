package com.justtide.tms.controller;

import com.justtide.tms.common.PageResult;
import com.justtide.tms.common.Result;
import com.justtide.tms.entity.Menu;
import com.justtide.tms.service.MenuService;
import com.justtide.tms.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public Result findAll(){
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", menuService.findAll());
    }
    @GetMapping("/{menuId}")
    public Result findById(@PathVariable("menuId") String id){
        return ResultUtil.success("查询成功", menuService.findById(Integer.valueOf(id)));
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
    public Result delete(@PathVariable String menuId){
        menuService.delete(Integer.valueOf(menuId));
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{menuId}")
    public Result update(@PathVariable String menuId, @RequestBody Menu menu){
        menu.setId(Integer.valueOf(menuId));
        menuService.update(menu);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody Menu menu){
        return ResultUtil.success("查询成功", menuService.search(menu));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody Menu menu, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<Menu> pageinfo = menuService.searchPage(menu, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }
}
