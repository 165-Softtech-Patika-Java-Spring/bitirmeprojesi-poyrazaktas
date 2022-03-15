package com.poyrazaktas.bitirme.gen.service;

import com.poyrazaktas.bitirme.gen.enums.GenErrorMessage;
import com.poyrazaktas.bitirme.gen.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class BaseEntityService<E, D extends JpaRepository<E,Long>> {
    private final D dao;

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
        return dao.save(entity);
    }

    public void delete(E entity) {
        dao.delete(entity);
    }

    public D getDao() {
        return dao;
    }
}
