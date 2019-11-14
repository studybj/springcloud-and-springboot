package com.bj.pro.qa.service;

import com.bj.pro.qa.entity.Problem;
import com.bj.pro.qa.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisTemplate redisTemplate;

    //-----------------------------------基本操作--------------------------------------------------
    public List<Problem> findAll(){
        return problemRepository.findAll();
    }
    public Problem findById(String id){
        //先从缓存中读取，若没有则查询数据库
        Problem problem = (Problem) redisTemplate.opsForValue().get("problem_" + id);
        if(problem == null){
            problem = problemRepository.findById(id).get();
            redisTemplate.opsForValue().set("problem_" + id, problem);
        }
        return problem;
    }
    public void save(Problem problem){
        String token = (String) request.getAttribute("claims_admin");
        if (token.isEmpty()){
            throw new RuntimeException("权限不足");
        }
        problem.setId(idWorker.nextId() + "");
        problemRepository.save(problem);
    }
    public void update(Problem problem){
        redisTemplate.delete("problem_" + problem.getId());
        problemRepository.save(problem);
    }
    public void deleteAll(){
        redisTemplate.delete("problem_*");
        problemRepository.deleteAll();
    }
    public void delete(String id){
        redisTemplate.delete("problem_" + id);
        problemRepository.deleteById(id);
    }
    //-----------------------------------条件查询操作--------------------------------------------------
    public List<Problem> search(Problem problem){
        return problemRepository.findAll(paramOperate(problem));
    }

    public Page<Problem> searchPage(Problem problem, Pageable pageable) {
        return problemRepository.findAll(paramOperate(problem), pageable);
    }
    public Page<Problem> newPage(String labelid, Pageable pageable) {
        return problemRepository.newlist(labelid, pageable);
    }
    public Page<Problem> hotPage(String labelid, Pageable pageable) {
        return problemRepository.hotlist(labelid, pageable);
    }
    public Page<Problem> waitPage(String labelid, Pageable pageable) {
        return problemRepository.waitlist(labelid, pageable);
    }

    //-----------------------------------私有操作--------------------------------------------------
    private Specification<Problem> paramOperate(Problem problem) {
        Specification<Problem> spec = new Specification<Problem>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<Problem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(problem != null){
                    if(problem.getTitle() != null && !"".equals(problem.getTitle())){
                        Predicate titlePredicate = cb.like(root.get("title"), "%" + problem.getTitle() + "%");
                        predicatesList.add(titlePredicate);
                    }
                    if(problem.getNickname() != null && !"".equals(problem.getNickname())){
                        Predicate nicknamePredicate = cb.equal(root.get("nickname"), problem.getNickname());
                        predicatesList.add(nicknamePredicate);
                    }
//                    if(userInfo.getSubscribe_scene() != null && !"".equals(userInfo.getSubscribe_scene())){
//                        Expression<Integer> exp = root.get("subscribe_scene");
//                        predicatesList.add(exp.in(userInfo.getSubscribe_scene())); // 往in中添加所有id 实现in 查询
//                    }
//                    if(userInfo.getTagid_list() != null && !"".equals(userInfo.getTagid_list())){//TODO
//                        //Predicate nicknamePredicate = cb.like(root.get("nickname"), "%" + userInfo.getNickname() + "%");
//                        //predicatesList.add(nicknamePredicate);
//                        List<String> useridlist = tagService.findByParam(userInfo.getTagid_list());
//                        if(useridlist != null && useridlist.size() != 0) {
//                            Expression<String> exp = root.get("id");
//                            predicatesList.add(exp.in(useridlist)); // 往in中添加所有id 实现in 查询
//                        }
//                    }
//                    if(time != null && !"".equals(time)){
//                        String[] times = time.split("到");
//                        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        try{
//                            Predicate timestartPredicate = cb.greaterThanOrEqualTo   (root.get("subscribe_time").as(Date.class), simple.parse(times[0]));
//                            Predicate timeendPredicate = cb.lessThanOrEqualTo(root.get("subscribe_time").as(Date.class), simple.parse(times[1]));
//                            predicatesList.add(timestartPredicate);
//                            predicatesList.add(timeendPredicate);
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
                }
                if (predicatesList.size() != 0) {
                    Predicate[] p = new Predicate[predicatesList.size()];
                    return cb.and(predicatesList.toArray(p));
                }
                return null;
            }
        };
        return spec;
    }
}
