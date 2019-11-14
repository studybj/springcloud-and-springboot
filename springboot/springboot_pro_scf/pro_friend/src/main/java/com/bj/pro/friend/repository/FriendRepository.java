package com.bj.pro.friend.repository;

import com.bj.pro.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, String> {

    Friend findByUseridAndFriendid(String userid, String friendid);

}
