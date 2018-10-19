package com.esempla.familyTree.familyTreedata.service.intf;

import java.util.List;

public interface CRUDService<T> {
    List<?> listAll();

    boolean existEntry(Long id);

    T getById(Long id);

    T saveOrUpdate(T domainObject);

    void delete(Long id);
}
