package com.bj.wechatserver.controller;

import com.bj.eat.utils.ResultVoUtil;
import com.bj.eat.vo.ResultVo;
import com.bj.wechatserver.entity.UserTag;
import com.bj.wechatserver.entity.menu.Menu;
import com.bj.wechatserver.entity.menu.PersonalityMenu;
import com.bj.wechatserver.enums.LanguageEnum;
import com.bj.wechatserver.enums.MenuTypeEnum;
import com.bj.wechatserver.service.PersonalityMenuService;
import com.bj.wechatserver.service.UserTagService;
import com.bj.wechatserver.service.WXMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wxmenu")
@Slf4j
public class WXMenuController {
    @Autowired
    private WXMenuService menuService;
    @Autowired
    private UserTagService tagService;
    @Autowired
    private PersonalityMenuService plityMenuService;
    @RequestMapping("/list")
    public ResultVo list(){
        return ResultVoUtil.success(menuService.findAll());
    }

    @RequestMapping("/listByParam")
    public ResultVo list(Menu menu){
        return ResultVoUtil.success(menuService.findByParam(menu,0));
    }

    @RequestMapping("/save")
    public ResultVo save(Menu menu) throws Exception {
        menuService.save(menu);
        return ResultVoUtil.success();
    }
    @RequestMapping("/batch_save")
    public ResultVo batchSave(@RequestParam String list, Integer id) throws IOException {
        /*//jackson对象
        ObjectMapper mapper = new ObjectMapper();
        //使用jackson将json转为List<User>
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, UserTag.class);
        List<UserTag> lists =  (List<UserTag>)mapper.readValue(list, jt);

        if(id != null && !"".equals(id)){
            UserTag info = lists.get(0);
            info.setId(id);
            menuService.save(info);
        }else{
            menuService.save(lists);
        }*/
        return ResultVoUtil.success();
    }

    @RequestMapping("/delete")
    public ResultVo delete(@RequestParam Integer[] id){
        menuService.delete(id);
        return ResultVoUtil.success();
    }
    /**
     * @param updateType 更新类型  1 更改菜单状态，2 生成普通菜单，3 删除普通菜单
     * @return
     */
    @RequestMapping("/update")
    public @ResponseBody ResultVo update(String[] ids, int updateType) throws Exception {
        List<Integer> lString = new ArrayList<Integer>();
        for(String id : ids){
            lString.add(Integer.parseInt(id));
        }
        Map result = menuService.update(lString.toArray(new Integer[lString.size()]),updateType);
        return  ResultVoUtil.result(result);
    }
    @RequestMapping("/baseinfo")
    public @ResponseBody ResultVo baseinfo() {
        //MenuTypeEnum.
        Menu menu = new Menu();
        menu.setPid(-1);
        menu.setStatus(1);
        List<Menu> list = menuService.findByParam(menu,1);
        Map map = new HashMap();
        for(MenuTypeEnum typeEnum : MenuTypeEnum.values()){
            map.put(typeEnum.getCode(),typeEnum.getMessage());
        }
        Map resmap = new HashMap();
        resmap.put("list",list);
        resmap.put("map", map);
        return ResultVoUtil.success(resmap);
    }
    @RequestMapping("/validate")
    public @ResponseBody ResultVo validate(Menu menu,int level) {
        //MenuTypeEnum.
        Menu param = null;
        boolean flag = false;
        if(level == 1){
            param = new Menu();
            param.setStatus(1);
            param.setPid(-1);
            param.setId(menu.getId());
            long count = menuService.count(param);
            if(menu.getId() != null && !"".equals(menu.getId())){
                flag = (count > 2);
            }else{
                flag = (count >= 3);
            }
            if(flag){
                return ResultVoUtil.error(-1,"已启用的一级菜单已有三条，不能再次添加");
            }
        }else if(level == 2){
            param = new Menu();
            param.setStatus(1);
            param.setPid(menu.getPid());//设置为0，即判定不为-1的记录条数
            param.setId(menu.getId());
            long count = menuService.count(param);
            if(menu.getId() != null && !"".equals(menu.getId())){
                flag = (count > 4);
            }else{
                flag = (count >= 5);
            }
            if(flag){
                return ResultVoUtil.error(-1,"该一级菜单下已启用的二级菜单已有五条，不能再次添加");
            }
        }
        if(menu.getKey() != null && !"".equals(menu.getKey())){
            param = new Menu();
            param.setStatus(1);
            param.setKey(menu.getKey());
            param.setId(menu.getId());
            int size = menuService.findByParam(param,1).size();
            String message = null;
            if(menu.getId() != null && !"".equals(menu.getId())){
                message = size > 1 ? "已有该键值对应的启用菜单" : null;
            }else {
                message = size > 0 ? "已有该键值对应的启用菜单" : null;
            }
            if(message != null){
                return ResultVoUtil.error(-1,message);
            }
        }
        if(menu.getName() != null && !"".equals(menu.getName())){
            param = new Menu();
            param.setStatus(1);
            param.setName(menu.getName());
            param.setId(menu.getId());
            int size = menuService.findByParam(param,1).size();
            String message = null;
            if(menu.getId() != null && !"".equals(menu.getId())){
                message = size > 1 ? "已有该名称对应的启用菜单" : null;
            }else {
                message = size > 0 ? "已有该名称对应的启用菜单" : null;
            }
            if(message != null){
                return ResultVoUtil.error(-1,message);
            }
        }
        return ResultVoUtil.success();
    }

    @RequestMapping("/plitylist")
    public ResultVo plityMenu(){
        return ResultVoUtil.success(plityMenuService.findAll());
    }
    @RequestMapping("/plitysave")
    public ResultVo plityMenu(PersonalityMenu plityMenu) throws Exception {
        plityMenuService.save(plityMenu);
        return ResultVoUtil.success();
    }




    @RequestMapping("/staticinfo")
    public @ResponseBody ResultVo staticinfo() {
        UserTag tag = new UserTag();
        tag.setStatus(1);
        List<UserTag> list = tagService.findByParam(tag);
        Map devicemap = new HashMap();
        devicemap.put(1,"IOS");
        devicemap.put(2,"Android");
        devicemap.put(3,"Others");
        Map languagemap = new HashMap();
        for(LanguageEnum languageEnum : LanguageEnum.values()){
            languagemap.put(languageEnum.getCode(),languageEnum.getMessage());
        }
        Map resmap = new HashMap();
        resmap.put("list",list);
        resmap.put("devicemap", devicemap);
        resmap.put("languagemap", languagemap);
        return ResultVoUtil.success(resmap);
    }
}

