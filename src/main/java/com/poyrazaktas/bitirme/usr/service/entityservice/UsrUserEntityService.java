package com.poyrazaktas.bitirme.usr.service.entityservice;

import com.poyrazaktas.bitirme.gen.service.BaseEntityService;
import com.poyrazaktas.bitirme.usr.dao.UsrUserDao;
import com.poyrazaktas.bitirme.usr.entity.UsrUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsrUserEntityService extends BaseEntityService<UsrUser, UsrUserDao> {
    public UsrUserEntityService(UsrUserDao dao) {
        super(dao);
    }

    public Optional<UsrUser> findUserByUserName(String userName) {
        return this.getDao().findUserByUserName(userName);
    }
}
