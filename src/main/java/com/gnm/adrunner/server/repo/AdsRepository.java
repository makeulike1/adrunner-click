package com.gnm.adrunner.server.repo;

import java.util.List;

import com.gnm.adrunner.server.entity.Ads;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

public interface AdsRepository extends CrudRepository<Ads, Integer> {

    @Query(value="select * from ads where is_delete=false order by id desc limit ?1 offset ?2", nativeQuery = true)
    public Iterable<Ads> listAds(Integer pageSize, Integer offset);

    @Query(value="select * from ads where status=?1", nativeQuery = true)
    public Iterable<Ads> listByStatus(Integer status);

    @Query(value="select * from ads where ads_key = ?1", nativeQuery = true)
    public Ads findByAdsKey(String adsKey);

    @Query(value="select * from ads where id = ?1", nativeQuery = true)
    public Ads findByID(Integer adsId);

    @Query(value="select id from ads", nativeQuery = true)
    public Iterable<Integer> listAllId();

    @Query(value="select type from ads where ads_key = ?1", nativeQuery = true)
    public Integer getAdType(String adsKey);

    @Query(value="select * from ads where is_delete=false and status != 2", nativeQuery = true)
    public List<Ads> listAll();

    @Query(value="select * from ads where is_delete=false and status != 2", nativeQuery = true)
    public Iterable<Ads> listForScheduler();

    @Transactional
    @Modifying
    @Query(value="update ads set status=?2 where id=?1", nativeQuery = true)
    public void updateAdsByStatus(Integer adsId, Integer status);

}