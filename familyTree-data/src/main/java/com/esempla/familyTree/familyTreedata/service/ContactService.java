package com.esempla.familyTree.familyTreedata.service;

import com.esempla.familyTree.familyTreedata.domain.Contact;
import com.esempla.familyTree.familyTreedata.repository.ContactRepository;
import com.esempla.familyTree.familyTreedata.service.intf.ContactServiceIntf;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ContactService implements ContactServiceIntf {

    private final ContactRepository contactRepository;

    @Override
    public List<?> listAll() {
        return null;
    }

    @Override
    public Contact getById(Long id) {
        return contactRepository.getOne(id);
    }

    @Override
    public Contact saveOrUpdate(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void delete(Long id) {

    }
    @Override
    public boolean existEntry(Long id) {
        return contactRepository.existsById(id);
    }
}
