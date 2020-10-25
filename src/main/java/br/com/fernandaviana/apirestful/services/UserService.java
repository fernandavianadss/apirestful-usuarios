package br.com.fernandaviana.apirestful.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fernandaviana.apirestful.dto.UserDTO;
import br.com.fernandaviana.apirestful.dto.UserNewDTO;
import br.com.fernandaviana.apirestful.entities.Phone;
import br.com.fernandaviana.apirestful.entities.Profile;
import br.com.fernandaviana.apirestful.entities.User;
import br.com.fernandaviana.apirestful.entities.enums.ProfileEnum;
import br.com.fernandaviana.apirestful.interfaces.PhoneRepository;
import br.com.fernandaviana.apirestful.interfaces.ProfileRepository;
import br.com.fernandaviana.apirestful.interfaces.UserRepository;
import br.com.fernandaviana.apirestful.security.UserSecurity;
import br.com.fernandaviana.apirestful.services.exception.AuthorizationException;
import br.com.fernandaviana.apirestful.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private BCryptPasswordEncoder pw;
	
	

	public User findById(Long id) {
		
		UserSecurity user = LoginService.authenticated();
		
		if(user == null || !user.hasRole(ProfileEnum.ADMIN) && id.equals(user.getId())) {
			throw new AuthorizationException("Acesso não autorizado");
		}
		
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! Id: " + id));
		
		
	}
	
	@Transactional
	public User insert(User obj) {
		obj.setId(null);
		obj = repository.save(obj);
		profileRepository.save(obj.getProfile());
		phoneRepository.saveAll(obj.getPhones());
		return obj;
	}

	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail(), objDto.getPassword());
	}
	
	public User fromDTO(UserNewDTO objDto) {
		Profile profile = new Profile(null, new Date(), new Date(), new Date(), "Token gerado");
		
		User user = new User(null, objDto.getName(), objDto.getEmail(), pw.encode(objDto.getPassword()), profile);
		
		profile.setUser(user);
		
		Phone phone = new Phone(null, objDto.getNumber(), objDto.getDdd(), user);
		
		user.getPhones().add(phone);
		
		return user;
		
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

}
