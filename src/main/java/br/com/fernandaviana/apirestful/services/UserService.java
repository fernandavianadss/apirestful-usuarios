package br.com.fernandaviana.apirestful.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fernandaviana.apirestful.dto.UserDTO;
import br.com.fernandaviana.apirestful.dto.UserNewDTO;
import br.com.fernandaviana.apirestful.entities.Phone;
import br.com.fernandaviana.apirestful.entities.User;
import br.com.fernandaviana.apirestful.interfaces.PhoneRepository;
import br.com.fernandaviana.apirestful.interfaces.UserRepository;
import br.com.fernandaviana.apirestful.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@Autowired
	private JWTService jwtService;
	
	public User findById(Long id) {
		
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! Id: " + id));
	}
	
	@Transactional
	public User insert(User obj) {
		obj.setId(null);
		obj = userRepository.save(obj);
		phoneRepository.saveAll(obj.getPhones());
		return obj;
	}

	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return userRepository.save(newObj);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail(), objDto.getPassword());
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	public User fromDTO(UserNewDTO objDto) {
				
		User user = new User(null, objDto.getName(), objDto.getEmail(), objDto.getPassword(), new Date(), new Date(), new Date());
		user.setToken(jwtService.generateToken(user));	
		
		Phone phone = new Phone(null, objDto.getNumber(), objDto.getDdd(), user);
		user.getPhones().add(phone);
		
		return user;
		
	}

}
