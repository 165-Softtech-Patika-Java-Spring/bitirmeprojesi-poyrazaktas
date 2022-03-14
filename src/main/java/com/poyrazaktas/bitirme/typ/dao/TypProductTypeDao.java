package com.poyrazaktas.bitirme.typ.dao;

import com.poyrazaktas.bitirme.typ.entity.TypProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypProductTypeDao extends JpaRepository<TypProductType,Long> {
}
