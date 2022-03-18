package com.poyrazaktas.bitirme.sec.security;

import com.poyrazaktas.bitirme.usr.entity.UsrUser;
import com.poyrazaktas.bitirme.usr.service.entityservice.UsrUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UsrUserEntityService userEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsrUser user = userEntityService.getUserByUserName(username);

        return JwtUserDetails.create(user);
    }


    public UserDetails loadUserByUserId(Long id) {
        UsrUser user = userEntityService.getByIdWithControl(id);

        return JwtUserDetails.create(user);
    }

}
