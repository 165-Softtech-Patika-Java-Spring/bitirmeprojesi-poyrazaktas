package com.poyrazaktas.bitirme.typ.service.entityservice;

import com.poyrazaktas.bitirme.gen.service.BaseEntityService;
import com.poyrazaktas.bitirme.typ.dao.TypProductTypeDao;
import com.poyrazaktas.bitirme.typ.entity.TypProductType;
import org.springframework.stereotype.Service;

@Service
public class TypProductTypeEntityService extends BaseEntityService<TypProductType, TypProductTypeDao> {
    public TypProductTypeEntityService(TypProductTypeDao dao) {
        super(dao);
    }
}
