package br.com.agapesistemas.demo.service;

import br.com.agapesistemas.demo.database.entities.UserEntity;
import br.com.agapesistemas.demo.database.repositories.UserRepo;
import br.com.agapesistemas.demo.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByLogin(username);
        if(user == null){
            throw new UsernameNotFoundException("could not found user..!!");
        }

        return new CustomUserDetails(user);
    }
}