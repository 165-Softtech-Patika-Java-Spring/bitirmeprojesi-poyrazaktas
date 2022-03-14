package com.poyrazaktas.bitirme.prd.dao;

import com.poyrazaktas.bitirme.prd.entity.PrdProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdProductDao extends JpaRepository<PrdProduct, Long> {
}
