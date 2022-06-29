package com.gnm.adrunner.server;

import com.gnm.adrunner.config.MemoryData;
import com.gnm.adrunner.config.RedisConfig;
import com.gnm.adrunner.server.repo.AdsMediaRepository;
import com.gnm.adrunner.server.repo.AdsRepository;
import com.gnm.adrunner.server.repo.AffRepository;
import com.gnm.adrunner.server.repo.AffParamRepository;
import com.gnm.adrunner.server.repo.MediaRepository;
import com.gnm.adrunner.server.repo.ServerInstanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Application {


	@Autowired
	AffParamRepository 			affParamRepository;
	
	@Autowired
	AdsRepository				adsRepository;

	@Autowired
	AffRepository				affRepository;

	@Autowired
	MediaRepository				mediaRepository;

	@Autowired
	ServerInstanceRepository	serverInstanceRepository;

	@Autowired
	AdsMediaRepository			adsMediaRepository;

	public static void main(String[] args) {
		
		//REDIS 시작
		RedisConfig.init();
		
		SpringApplication.run(Application.class, args);
	}

 
	@EventListener(ApplicationReadyEvent.class)
	public void startup() {
		// 메모리 변수 : 제휴사 파라미터 초기화
		MemoryData.affParamList 		= affParamRepository.listAll();
		// 메모리 변수 : 광고 목록 초기화
		MemoryData.adsList				= adsRepository.listAll();
		// 메모리 변수 : 매체사 목록 초기화
		MemoryData.mediaList			= mediaRepository.listAll();
		// 메모리 변수 : 광고 - 연동된 매체사 목록 초기화
		MemoryData.adsMediaList			= adsMediaRepository.listAll();
		// 메모리 변수 : 제휴사 목록 초기화
		MemoryData.affList				= affRepository.listAll();
		
	}


}
