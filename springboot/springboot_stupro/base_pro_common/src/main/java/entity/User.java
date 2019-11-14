package entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String username;//账户名称
    private String token;   //账户token
    private Integer user_status;    //账号状态
    private Date create_time;   //创建时间
    private Date update_time;   //更新时间
}
class Plan implements Serializable {
    private String user_id;//用户id
    private String plan_name;   //推广计划名称
    private String plan_status;   //推广计划状态
    private Date start_date;    //推广计划开始时间
    private Date end_date;    //推广计划截止时间
    private Date create_time;   //创建时间
    private Date update_time;   //更新时间
}