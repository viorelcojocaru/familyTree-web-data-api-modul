package com.esempla.familyTree.familyTreedata.service;

import com.esempla.familyTree.familyTreedata.domain.Role;
import com.esempla.familyTree.familyTreedata.repository.RoleRepository;
import com.esempla.familyTree.familyTreedata.service.intf.RoleServiceIntf;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements RoleServiceIntf {

    private RoleRepository roleRepository;

    @Override
    public List<Role> listAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Role getById(Long id) {
        return roleRepository.getOne(id);
    }

    @Override
    public Role saveOrUpdate(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    public  String getRoleName( int roleId) {
        return listAll().stream().filter(role -> role.getId().intValue()==roleId).findAny().orElse(null).getName();
    }
    @Override
    public boolean existEntry(Long id) {
        return roleRepository.existsById(id);
    }
}
