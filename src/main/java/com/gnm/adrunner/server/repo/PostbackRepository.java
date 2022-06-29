package com.gnm.adrunner.server.repo;

import com.gnm.adrunner.server.entity.Postback;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostbackRepository extends CrudRepository<Postback, Integer> {

    @Query(value="select id from postback where click_key= ?1", nativeQuery = true)
    public Integer findIdByClickKey(String click_key);
    
}
