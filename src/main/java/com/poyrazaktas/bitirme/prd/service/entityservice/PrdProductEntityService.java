package com.poyrazaktas.bitirme.prd.service.entityservice;

import com.poyrazaktas.bitirme.gen.service.BaseEntityService;
import com.poyrazaktas.bitirme.prd.dao.PrdProductDao;
import com.poyrazaktas.bitirme.prd.entity.PrdProduct;
import org.springframework.stereotype.Service;

@Service
public class PrdProductEntityService extends BaseEntityService<PrdProduct, PrdProductDao> {
    public PrdProductEntityService(PrdProductDao dao) {
        super(dao);
    }
}
