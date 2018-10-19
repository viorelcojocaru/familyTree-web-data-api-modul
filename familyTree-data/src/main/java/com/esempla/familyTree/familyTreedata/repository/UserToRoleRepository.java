package com.esempla.familyTree.familyTreedata.repository;


import com.esempla.familyTree.familyTreedata.domain.UserToRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "familyTree", path = "user_to_role")
public interface UserToRoleRepository extends JpaRepository<UserToRole, Long> {


}
