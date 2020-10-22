package br.com.fernandaviana.apirestful.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserEndpoint {
	
	@RequestMapping(method = RequestMethod.GET)
	public String listar() {
		return "Rest testado";
	}
}
