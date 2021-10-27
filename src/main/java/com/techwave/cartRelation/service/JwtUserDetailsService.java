package com.techwave.cartRelation.service;

import com.techwave.cartRelation.EntityNotFoundException;
import com.techwave.cartRelation.config.ApiResponse;
import com.techwave.cartRelation.config.JwtTokenUtil;
import com.techwave.cartRelation.entity.User;
import com.techwave.cartRelation.model.UserDto;
import com.techwave.cartRelation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder bcryptedEncoder;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                new ArrayList<>());
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws  UsernameNotFoundException{
        User user = userRepository.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("User not found with username "+username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());
    }

    public int loadisAdminUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user.getAdmin();
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User getUserByHeaderToken(HttpServletRequest request)
    {
        String jwtToken;
        final String requestTokenHeader = request.getHeader("Authorization");
        jwtToken = requestTokenHeader.substring(7);
        String currentUsername = jwtTokenUtil.getUsernameFromToken(jwtToken);
        return this.getUserByUsername(currentUsername);
    }

    public List<User> getAllusers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User save(UserDto user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptedEncoder.encode(user.getPassword()));
        newUser.setFullName(user.getFullName());
        newUser.setEmail(user.getEmail());
        newUser.setMobile(user.getMobile());
        newUser.setAdmin(0);
        return  userRepository.save(newUser);
    }

    public ResponseEntity<ApiResponse> deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", "record deleted successfully"), HttpStatus.OK);
        }
        catch (EmptyResultDataAccessException ex)
        {
            throw new EntityNotFoundException(User.class,"id",id.toString());
        }
    }

    public User getUserByToken(String jwtToken) {
        String currentUsername = jwtTokenUtil.getUsernameFromToken(jwtToken);
        return this.getUserByUsername(currentUsername);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }



}
