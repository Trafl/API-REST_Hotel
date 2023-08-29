package com.pivo.hotelo.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pivo.hotelo.api.DTO.input.UserInput;
import com.pivo.hotelo.api.DTO.output.UserOutput;
import com.pivo.hotelo.domain.model.User;

@Component
public class UserMapper {

	@Autowired
	private ModelMapper modelMapper;


	public User toDomainModel(UserInput clientInput) {
		return modelMapper.map(clientInput, User.class);
	}

	public List<User> toDomainCollectionModel(List<UserInput> list) {
		return list.stream().map((models) -> toDomainModel(models)).collect(Collectors.toList());
	}

	// ---------------------------------------------------------
	public UserOutput toModel(User user) {

		return modelMapper.map(user, UserOutput.class);
	}

	// ----------------------------------------------------------
	public void copyToDomain(UserInput userInput, User client) {
		modelMapper.map(userInput, client);
	}
}
