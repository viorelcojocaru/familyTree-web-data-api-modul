package com.esempla.familyTree.familyTreedata.service.intf;

import com.esempla.familyTree.familyTreedata.domain.User;

public interface UserServiceIntf extends CRUDService<User> {
    public User findUserByUserName(String userName);

}