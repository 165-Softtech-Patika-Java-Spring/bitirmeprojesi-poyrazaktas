package com.poyrazaktas.bitirme.usr.dao;

import com.poyrazaktas.bitirme.usr.entity.UsrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsrUserDao extends JpaRepository<UsrUser, Long> {
    Optional<UsrUser> findUserByUserName(String userName);
}
