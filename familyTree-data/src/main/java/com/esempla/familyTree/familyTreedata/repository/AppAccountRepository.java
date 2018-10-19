package com.esempla.familyTree.familyTreedata.repository;

import com.esempla.familyTree.familyTreedata.domain.AppAccount;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "familyTree", path = "app_account")
public interface AppAccountRepository extends JpaRepository<AppAccount, Long> {
//    @Query("select a from app_account a where a.person_id = ?1 and a.type_id = ?2")
    //public AppAccount getAppAccountBy(long personId, long typeId);
}
