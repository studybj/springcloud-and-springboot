package com.bj.pro.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
@FeignClient("pro-user")
public interface UserClient {
    @PutMapping("/user/{userid}/{friendid}/{num}")
    public void updateFanscountAndFollowcount(@PathVariable("userid") String userid, @PathVariable("friendid") String friendid, @PathVariable("num") int num);
}
