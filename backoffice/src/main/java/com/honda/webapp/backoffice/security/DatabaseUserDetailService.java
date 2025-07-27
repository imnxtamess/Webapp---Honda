package com.honda.webapp.backoffice.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.honda.webapp.backoffice.model.User;
import com.honda.webapp.backoffice.repository.UserRepository;

@Service
public class DatabaseUserDetailService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<User> userAttempt = userRepository.findByUsername(username);

    if (userAttempt.isEmpty()) {
      throw new UsernameNotFoundException("There's no username available with username" + username);
    }

    return new DatabaseUserDetails(userAttempt.get());
  }

}
