package com.wodongso.wodongso.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wodongso.wodongso.entity.Role;
//import com.wodongso.wodongso.entity.UserRole;
//import com.wodongso.wodongso.repository.RoleRepository;
import com.wodongso.wodongso.repository.UserRepository;
//import com.wodongso.wodongso.repository.UserRoleRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final UserRoleRepository userRoleRepository;

    public String getRole(String id) {
//        com.wodongso.wodongso.entity.User user = userRepository.findByIdContaining(id);
//        UserRole userRole = userRoleRepository.findByRoleIdContaining(id);
//        Role role = roleRepository.findByIdContaining(userRole.getRoleId());
//        return role.getName();

//        com.wodongso.wodongso.entity.User user = userRepository.findByIdContaining(id);
//        List<Role> roleList = user.getRoles();
//        System.out.println("roleList >> " + roleList);

        
        return "ROLE_";
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String id) {
        System.out.println("loadUserByUsername id >> " + id);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        com.wodongso.wodongso.entity.User user = userRepository.findByIdContaining(id);


        if (user != null) {
            grantedAuthorities.add(new SimpleGrantedAuthority(getRole(user.getId())));
//            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
            return new User(user.getId(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("can not find User : " + id);
        }
    }

}
