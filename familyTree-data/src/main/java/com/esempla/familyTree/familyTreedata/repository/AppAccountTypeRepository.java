package com.esempla.familyTree.familyTreedata.repository;

import com.esempla.familyTree.familyTreedata.domain.AppAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "familyTree", path = "app_account_type")
public interface AppAccountTypeRepository extends JpaRepository<AppAccountType, Long> {


}
