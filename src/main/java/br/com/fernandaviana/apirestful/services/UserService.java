package br.com.fernandaviana.apirestful.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandaviana.apirestful.entities.User;
import br.com.fernandaviana.apirestful.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	
	public User buscar(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElse(null);
	}
	
}
