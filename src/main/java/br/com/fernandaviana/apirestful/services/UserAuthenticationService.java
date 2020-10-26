package br.com.fernandaviana.apirestful.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandaviana.apirestful.dto.UserCredentialsDTO;
import br.com.fernandaviana.apirestful.entities.User;
import br.com.fernandaviana.apirestful.interfaces.UserRepository;
import br.com.fernandaviana.apirestful.services.exception.DataIntegrityException;
import br.com.fernandaviana.apirestful.services.exception.ExpiredTokenException;
import br.com.fernandaviana.apirestful.services.exception.ObjectNotFoundException;
import io.jsonwebtoken.Claims;

@Service
public class UserAuthenticationService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JWTService jwtService;
	
	public User userAuthenticate(UserCredentialsDTO userCredentials, String token) {
		User user = userRepository.findByEmail(userCredentials.getEmail());
		
		if(user == null) {
			throw new DataIntegrityException("Usuário e/ou senha inválidos");
		}
		
		if(userCredentials.getPassword().equals(user.getPassword()) && !token.isEmpty() && validateToken(token)) {
			return user;
		}else {
			throw new ObjectNotFoundException("Usuário e/ou senha inválidos");
		}
		
	}
	
	public boolean validateToken(String token) {
		Claims claims = jwtService.decodeToken(token);
		if(claims != null) {
			String userName = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			
			if(userName != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}else {
				throw new ExpiredTokenException("Não autorizado");
			}
		}
		
		return false;
		
	}
}
