package br.com.fernandaviana.apirestful.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fernandaviana.apirestful.dto.UserDTO;
import br.com.fernandaviana.apirestful.dto.UserResponseDTO;
import br.com.fernandaviana.apirestful.entities.User;
import br.com.fernandaviana.apirestful.services.UserService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/profile")
public class ProfileResource {
	

	@Autowired
	private UserService service;
	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Obtem um perfil único")
	public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
		
		User obj = service.findById(id);
		return ResponseEntity.ok().body(UserResponseDTO.toDTO(obj));
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	@ApiOperation(value = "Obtem uma lista de perfis")
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	

}
