package br.com.fernandaviana.apirestful.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandaviana.apirestful.entities.User;
import br.com.fernandaviana.apirestful.repository.UserRepository;
import br.com.fernandaviana.apirestful.services.exception.ObjectNotFoundException;


@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	
	public User find(Long id) {
		
		Optional<User> obj = repository.findById(id);
			
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Usuário não encontrado! Id: " + id));
		
	}
	
	/*
	public User findLogin(Long id) {
		
		User user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
	}
	
	*/
	
	
}
