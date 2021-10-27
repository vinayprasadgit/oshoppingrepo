package com.techwave.cartRelation.controller;

import com.techwave.cartRelation.config.JwtTokenUtil;
import com.techwave.cartRelation.entity.User;
import com.techwave.cartRelation.model.JwtRequest;
import com.techwave.cartRelation.model.JwtResponse;
import com.techwave.cartRelation.model.UserDto;
import com.techwave.cartRelation.service.JwtUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/user")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@PostMapping(value = "/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {



		authenticate(authenticationRequest.getUsername(),authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final int isAdmin = userDetailsService.loadisAdminUserByUsername(authenticationRequest.getUsername());
	       final String token = jwtTokenUtil.generateToken(userDetails,isAdmin);
		logger.debug("User Logged in : "+userDetails.getUsername());
	       return ResponseEntity.ok(new JwtResponse(token));
	}

	@PostMapping(value = "/register")
	public  ResponseEntity<?> saveUser(@RequestBody UserDto user) throws EntityNotFoundException{
		logger.info("User Registered  : "+user.getUsername());
		return ResponseEntity.ok(userDetailsService.save(user));
	}



	@GetMapping(value = "/all")
	public List<User> getAllUsers() throws EntityNotFoundException {
		logger.info("Showing All Users");
		return userDetailsService.getAllusers();
	}


	@GetMapping(value = "/{id}")
	public User getUserById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return userDetailsService.getUserById(id);
	}

	@GetMapping(value = "/email/{email}")
	public User getUserByEmail(@PathVariable("email") String email) throws EntityNotFoundException
	{
		return userDetailsService.getUserByEmail(email);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity deleteUser(@PathVariable("id") Long id) throws EntityNotFoundException
	{
		return userDetailsService.deleteUser(id);
	}

	@GetMapping(value = "/profile")
	public User getUserById(HttpServletRequest request) throws EntityNotFoundException {
		String jwtToken = null;
		final String requestTokenHeader = request.getHeader("Authorization");
		jwtToken = requestTokenHeader.substring(7);
		return userDetailsService.getUserByToken(jwtToken);
	}


	@DeleteMapping(value = "/profile/delete")
	public ResponseEntity deleteCurrentUser(HttpServletRequest request) throws EntityNotFoundException {
		String jwtToken = null;
		final String requestTokenHeader = request.getHeader("Authorization");
		jwtToken = requestTokenHeader.substring(7);
		User user = userDetailsService.getUserByToken(jwtToken);
		return userDetailsService.deleteUser(user.getId());
	}

	private void authenticate(String username, String password) throws Exception
	{
		try{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		}
		catch (DisabledException e)
		{
			throw new Exception("USER_DISABLED",e);
		}
		catch (BadCredentialsException e)
		{
			throw new Exception("INVALID_CREDENTIALS");
		}
	}

}
