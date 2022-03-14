package com.poyrazaktas.bitirme.usr.dao;

import com.poyrazaktas.bitirme.usr.entity.UsrUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrUserDao extends JpaRepository<UsrUser,Long> {
}
