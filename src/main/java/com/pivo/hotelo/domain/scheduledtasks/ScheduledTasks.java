package com.pivo.hotelo.domain.scheduledtasks;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pivo.hotelo.domain.model.Rent;
import com.pivo.hotelo.domain.model.StatusType;
import com.pivo.hotelo.domain.service.RentService;

import jakarta.transaction.Transactional;

@Component
public class ScheduledTasks {

	@Autowired
	private RentService rentService;
	
	@Scheduled(cron = "0 0 8 * * ?")
	@Transactional
	public void taks() {
		List<Rent> list = rentService.findAll();
	
		Stream<Rent> reservedList = list.stream().filter(reserved-> reserved.getQuarto().getStatus().equals(StatusType.RESERVADO));
		
		reservedList.forEach(object ->{
			object.releaseRoom();
		});
	}
}
