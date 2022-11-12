package com.wodongso.wodongso.service;

import java.util.HashSet;
import java.util.Set;

//import com.wodongso.wodongso.entity.UserRole;
//import com.wodongso.wodongso.repository.RoleRepository;
import com.wodongso.wodongso.repository.UserRepository;
//import com.wodongso.wodongso.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String id) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        com.wodongso.wodongso.entity.User user = userRepository.findByIdContaining(id);
        
        if (user != null) {
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
            return new User(user.getId(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("can not find User : " + id);
        }
    }

}
