package com.poyrazaktas.bitirme.sec.security;

import com.poyrazaktas.bitirme.gen.exception.ItemNotFoundException;
import com.poyrazaktas.bitirme.usr.entity.UsrUser;
import com.poyrazaktas.bitirme.usr.enums.UsrUserErrorMessage;
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
        UsrUser user = userEntityService.findUserByUserName(username)
                .orElseThrow(() -> new ItemNotFoundException(UsrUserErrorMessage.ITEM_NOT_FOUND));

        return JwtUserDetails.create(user);
    }


    public UserDetails loadUserByUserId(Long id) {
        UsrUser user = userEntityService.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(UsrUserErrorMessage.ITEM_NOT_FOUND));

        return JwtUserDetails.create(user);
    }

}
