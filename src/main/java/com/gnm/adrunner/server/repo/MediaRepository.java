package com.gnm.adrunner.server.repo;

import java.util.List;

import javax.transaction.Transactional;

import com.gnm.adrunner.server.entity.Media;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MediaRepository extends CrudRepository<Media, Integer>{

    @Query(value="select id from media where name=?1", nativeQuery = true)
    public Integer getIdByName(String name);

    @Query(value="select media_key from media where name=?1", nativeQuery = true)
    public String getKeyByName(String name);

    @Query(value="select * from media where is_delete=false", nativeQuery = true)
    public List<Media> listAll();

    @Query(value="select id from media where media_key=?1", nativeQuery = true)
    public Integer findByMediaKey(String mediaKey);
    
    @Query(value="select * from media where id=?1", nativeQuery = true)
    public Media findByID(Integer id);

    @Transactional
    @Modifying
    @Query(value="update media set media_key=?1 where id=?2", nativeQuery = true)
    public void updateMediaKey(String mediaKey, Integer mediaId);

    @Transactional
    @Modifying
    @Query(value="update media set is_delete=true where id=?1", nativeQuery = true)
    public void deleteMedia(Integer mediaId);

}
