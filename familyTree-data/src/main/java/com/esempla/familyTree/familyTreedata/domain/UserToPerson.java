package com.esempla.familyTree.familyTreedata.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_to_person")
public class UserToPerson implements Serializable {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "person_id")
    private Long personId;
}
