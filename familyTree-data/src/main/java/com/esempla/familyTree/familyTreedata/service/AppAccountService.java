package com.esempla.familyTree.familyTreedata.service;


import com.esempla.familyTree.familyTreedata.domain.AppAccount;
import com.esempla.familyTree.familyTreedata.repository.AppAccountRepository;
import com.esempla.familyTree.familyTreedata.service.intf.AppAccountServiceIntf;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppAccountService implements AppAccountServiceIntf {

    private final AppAccountRepository appAccountRepository;

    @Override
    public List<AppAccount> listAll() {
        return appAccountRepository.findAll();
    }

    @Override
    public boolean existEntry(Long id) {
        return appAccountRepository.existsById(id);
    }

    @Override
    public AppAccount getById(Long id) {
        return appAccountRepository.getOne(id);
    }

    @Override
    public AppAccount saveOrUpdate(AppAccount appAccount) {
        return appAccountRepository.save(appAccount);
    }

    @Override
    public void delete(Long id) {
        appAccountRepository.deleteById(id);
    }

    public AppAccount getAppAccountBy(long personId, long typeId){
        return null;//appAccountRepository.getAppAccountBy(personId, typeId);
    }
}
