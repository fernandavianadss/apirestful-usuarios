package br.com.fernandaviana.apirestful.resources;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fernandaviana.apirestful.dto.UserCredentialsDTO;
import br.com.fernandaviana.apirestful.dto.UserResponseDTO;
import br.com.fernandaviana.apirestful.entities.User;
import br.com.fernandaviana.apirestful.services.UserAuthenticationService;
import br.com.fernandaviana.apirestful.services.UserService;

@RestController
@RequestMapping(value = "/login")
public class LoginResource {
	
	@Autowired
	private UserAuthenticationService userService;
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<UserResponseDTO> insert(@Valid @RequestBody UserCredentialsDTO userCredentials, @RequestHeader String Authorization) {
		User obj = userService.userAuthenticate(userCredentials, Authorization);
		obj.setLast_login(new Date());
		obj = service.update(obj);
		return new ResponseEntity<UserResponseDTO>(UserResponseDTO.toDTO(obj), HttpStatus.ACCEPTED);
	}

}
