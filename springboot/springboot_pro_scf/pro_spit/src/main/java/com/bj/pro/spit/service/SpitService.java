package com.bj.pro.spit.service;

import com.bj.pro.spit.entity.Spit;
import com.bj.pro.spit.repository.SpitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import utils.IdWorker;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SpitService {
    @Autowired
    private SpitRepository spitRepository;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    //-----------------------------------基本操作--------------------------------------------------
    public List<Spit> findAll(){
        return spitRepository.findAll();
    }
    public Spit findById(String id){
        return spitRepository.findById(id).get();
    }
    public void save(Spit spit){
        spit.set_id(idWorker.nextId() + "");
        //初始化
        spit.setPublishtime(new Date());//发布日期

        //更新父节点的回复数
        if(!StringUtils.isEmpty(spit.getParentid())){
            update(1,spit.getParentid(), "content");
        }
        spitRepository.save(spit);

    }
    public void update(Spit spit){
        spitRepository.save(spit);
    }
    public void deleteAll(){
        spitRepository.deleteAll();
    }
    public void delete(String id){
        spitRepository.deleteById(id);
    }
    //-----------------------------------条件查询操作--------------------------------------------------
    public Page<Spit> findByPartentid(String parentid, Pageable pageable) {
        return spitRepository.findByParentid(parentid,pageable);
    }

    /**
     *
     * @param num +num
     * @param id 要改变的id
     * @param key   要修改的属性
     */
    public void update(int num, String id, String key){
//        方式一、效率低
//        Spit spit = spitRepository.findById(id).get();
//        spit.setThumbup(spit.getThumbup() + num);
//        spitRepository.save(spit);
//        方式二、使用MongoDB原生的自增命令来实现
//        db.spit.update({"_id":"2"},{$inc:{"thumbup": NumberInt(1)}})
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc(key, num);
        mongoTemplate.updateFirst(query, update, "spit");
    }
}
