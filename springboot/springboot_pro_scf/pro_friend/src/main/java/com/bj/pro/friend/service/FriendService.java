package com.bj.pro.friend.service;

import com.bj.pro.friend.entity.Friend;
import com.bj.pro.friend.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService {
    @Autowired
    private FriendRepository friendRepository;

    public Friend findById(String userid, String friendid) {
        return friendRepository.findByUseridAndFriendid(userid, friendid);
    }

    public void save(String userid, String friendid, String islike) {
        Friend friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike(islike);
        friendRepository.save(friend);
    }

    public void update(String userid, String friendid, String islike) {
        save(userid, friendid, islike);
    }

    public void delete(String userid, String friendid) {
        Friend friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friendRepository.delete(friend);
    }
}
