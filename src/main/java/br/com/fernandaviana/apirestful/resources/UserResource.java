package br.com.fernandaviana.apirestful.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fernandaviana.apirestful.dto.UserDTO;
import br.com.fernandaviana.apirestful.dto.UserNewDTO;
import br.com.fernandaviana.apirestful.dto.UserResponseDTO;
import br.com.fernandaviana.apirestful.entities.User;
import br.com.fernandaviana.apirestful.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/user")
@Api(value = "API RESTFULL Usuários")
@CrossOrigin(origins = "*")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Obtem um usuário único")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value = "Cria um usuário")
	public ResponseEntity<UserResponseDTO> insert(@Valid @RequestBody UserNewDTO objDto) {
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		return new ResponseEntity<UserResponseDTO>(UserResponseDTO.toDTO(obj), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value="/{id}")
	@ApiOperation(value = "Deleta um usuário")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ApiOperation(value = "Atualiza um usuário")
	public ResponseEntity<Void> update(@Valid @RequestBody UserDTO objDto, @PathVariable Long id) {
		User obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ApiOperation(value = "Obtem uma lista de usuários")
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}

}
