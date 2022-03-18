package com.poyrazaktas.bitirme.sec.security;

import com.poyrazaktas.bitirme.usr.entity.UsrUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JwtUserDetails implements UserDetails {
    private Long id;
    private String userName;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public static JwtUserDetails create(UsrUser user) {
        Long id = user.getId();
        String userName = user.getUserName();
        String password = user.getPassword();

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("user"));

        return new JwtUserDetails(id, userName, password, grantedAuthorityList);

    }

    private JwtUserDetails(Long id, String userName, String password, List<GrantedAuthority> grantedAuthorityList) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.authorities = grantedAuthorityList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
