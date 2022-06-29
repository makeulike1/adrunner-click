package com.gnm.adrunner.server.repo;

import com.gnm.adrunner.server.entity.ViewAdsMedia;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ViewAdsMediaRepository extends CrudRepository<ViewAdsMedia, Integer> {
 
    @Query(value="select * from view_ads_media where ads_key=?1", nativeQuery = true)
    public Iterable<ViewAdsMedia> findByAdsKey(String adsKey);
}
