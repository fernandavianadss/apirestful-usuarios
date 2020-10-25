package br.com.fernandaviana.apirestful.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fernandaviana.apirestful.dto.UserNewDTO;
import br.com.fernandaviana.apirestful.entities.User;
import br.com.fernandaviana.apirestful.interfaces.UserRepository;
import br.com.fernandaviana.apirestful.resources.exception.FieldMessage;

public class UserInsertValidator implements ConstraintValidator<UserInsert, UserNewDTO> {
	
	@Autowired
	private UserRepository repository;

	@Override
	public void initialize(UserInsert ann) {
	}

	@Override
	public boolean isValid(UserNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		User obj = repository.findByEmail(objDto.getEmail());
		
		if (obj != null) {
			list.add(new FieldMessage("Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
