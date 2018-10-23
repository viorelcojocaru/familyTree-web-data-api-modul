package com.esempla.familyTree.familyTreewebstarter.service;

import com.esempla.familyTree.familyTreecommonutils.constants.Roles;
import com.esempla.familyTree.familyTreedata.domain.Role;
import com.esempla.familyTree.familyTreedata.domain.User;
import com.esempla.familyTree.familyTreedata.repository.RoleRepository;
import com.esempla.familyTree.familyTreedata.repository.UserRepository;
import com.esempla.familyTree.familyTreedata.service.intf.UserServiceIntf;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserServiceIntf {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public List<User> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public boolean existEntry(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    @Transactional
    public User saveOrUpdate(User user) {
        if (user.getPassword() != null) {
            user.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setPassword(user.getEncryptedPassword());
        }
        Role role = roleRepository.getOne((long) Roles.USER);
        user.addRole(role);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }
}
