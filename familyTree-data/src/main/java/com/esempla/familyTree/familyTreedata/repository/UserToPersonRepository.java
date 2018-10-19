package com.esempla.familyTree.familyTreedata.repository;

import com.esempla.familyTree.familyTreedata.domain.UserToPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "familyTree", path = "user_to_person")
public interface UserToPersonRepository extends JpaRepository<UserToPerson, Long> {

    //@Query("SELECT utp FROM user_to_person utp WHERE utp.person_id = ?1")
    public UserToPerson getUserToPersonByPersonId(Long personId);

    //@Query("SELECT CASE  WHEN count(utp)> 0 THEN true ELSE false END FROM user_to_person utp WHERE utp.personId = ?1")
    public boolean existsUserToPersonByPersonId(Long personId);


}
