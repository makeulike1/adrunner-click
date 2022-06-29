package com.gnm.adrunner.server.service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnm.adrunner.server.entity.Media;
import com.gnm.adrunner.server.repo.MediaRepository;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
@Service("MediaService")
public class MediaService {

        
    @PersistenceContext
	private EntityManager entityManager;

 

    @Autowired
    MediaRepository mediaRepository;
 
    @Transactional
    public Integer saveMedia(Media media){
        entityManager.persist(media);
        return (int)(long)media.getId();
    }


    public void updateMediaKey(String mediaKey, Integer mediaId){
        mediaRepository.updateMediaKey(mediaKey.toUpperCase(), mediaId);
    }
    
}
