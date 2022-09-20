package challenge.controller;

import javax.validation.Valid;

import challenge.security.SecUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import challenge.model.User;
import challenge.repository.UserRepository;
import challenge.security.IAuthenticationFacade;

@RestController
public class UserController {

	final SecUserDetailsService secUserDetailsService;

	public UserController(SecUserDetailsService secUserDetailsService) {
		this.secUserDetailsService = secUserDetailsService;
	}
	@Autowired
	private UserRepository repository;
	
	@Autowired
    IAuthenticationFacade authenticationFacade;
	
	@PostMapping("/users")
	public User newUser(@Valid @RequestBody User user) {
		return repository.save(user);
		
	}

	@PostMapping("/users/")
	public ResponseEntity<Object> saveUser(@RequestBody @Valid User user) {
		if (repository.findByEmail(user.getEmail())){
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Email is already in use.");
		}
	}

	@GetMapping("/users/logged")
	public User loggedUser() {
		return authenticationFacade.getUser();
	
	}
}
