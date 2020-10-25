package br.com.fernandaviana.apirestful.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandaviana.apirestful.entities.User;
import br.com.fernandaviana.apirestful.interfaces.UserRepository;
import br.com.fernandaviana.apirestful.services.exception.ObjectNotFoundException;

@Service
public class ProfileService {

	@Autowired
	private UserRepository repository;

	public User findById(Long id) {

		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! Id: " + id));

	}

}
