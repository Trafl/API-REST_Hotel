package com.algaworks.algafood.domain.scheduledtasks;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.RentRoom;
import com.algaworks.algafood.domain.model.StatusType;
import com.algaworks.algafood.domain.service.RentRoomService;

import jakarta.transaction.Transactional;

@Component
public class ScheduledTasks {

	@Autowired
	private RentRoomService rentService;
	
	@Scheduled(cron = "0 0 8 * * ?")
	@Transactional
	public void taks() {
		List<RentRoom> list = rentService.findAll();
	
		Stream<RentRoom> reservedList = list.stream().filter(reserved-> reserved.getQuarto().getStatus().equals(StatusType.RESERVADO));
		
		reservedList.forEach(object ->{
			object.liberarQuarto();
		});
	}
}
