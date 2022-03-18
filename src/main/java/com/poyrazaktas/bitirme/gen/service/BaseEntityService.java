package com.poyrazaktas.bitirme.gen.service;

import com.poyrazaktas.bitirme.gen.entity.BaseAdditionalFields;
import com.poyrazaktas.bitirme.gen.entity.BaseEntity;
import com.poyrazaktas.bitirme.gen.enums.GenErrorMessage;
import com.poyrazaktas.bitirme.gen.exception.ItemNotFoundException;
import com.poyrazaktas.bitirme.sec.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity, D extends JpaRepository<E, Long>> {
    private final D dao;

    private AuthenticationService authenticationService;

    @Autowired
    public void setAuthenticationService(@Lazy AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public List<E> findAll() {
        return dao.findAll();
    }

    public Optional<E> findById(Long id) {
        return dao.findById(id);
    }

    public E getByIdWithControl(Long id) {
        return findById(id).orElseThrow(() -> new ItemNotFoundException(GenErrorMessage.ITEM_NOT_FOUND));
    }

    public boolean existsById(Long id) {
        return dao.existsById(id);
    }

    public E save(E entity) {
        setAdditionalFields(entity);
        return dao.save(entity);
    }

    public void delete(E entity) {
        dao.delete(entity);
    }

    public D getDao() {
        return dao;
    }

    private void setAdditionalFields(E entity) {
        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();

        Long currentUserId = getCurrentUserId();

        if (baseAdditionalFields == null) {
            baseAdditionalFields = new BaseAdditionalFields();
            entity.setBaseAdditionalFields(baseAdditionalFields);
        }

        if (entity.getId() == null) {
            baseAdditionalFields.setCreateDate(new Date());
            baseAdditionalFields.setCreatedBy(currentUserId);
        }

        baseAdditionalFields.setUpdateDate(new Date());
        baseAdditionalFields.setUpdatedBy(currentUserId);
    }

    public Long getCurrentUserId() {
        Long userId = authenticationService.getCurrentUserId();
        return userId;
    }

    public String getCurrentUserName() {
        String userName = authenticationService.getCurrentUserName();
        return userName;
    }
}
