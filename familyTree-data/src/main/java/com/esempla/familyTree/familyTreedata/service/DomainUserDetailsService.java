package com.esempla.familyTree.familyTreedata.service;

import com.esempla.familyTree.familyTreedata.domain.User;
import com.esempla.familyTree.familyTreedata.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        Optional<User> userFromDatabase = userRepository.findOneWithRoleByUserName(lowercaseLogin);
        UserDetails userDetails = userFromDatabase.map(user ->
                getUserDetails(user, lowercaseLogin)).orElseThrow(() ->
                new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the " +
                        "database"));
        return userDetails;
    }

    private org.springframework.security.core.userdetails.User getUserDetails(User user, String lowercaseLogin) {
        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(lowercaseLogin, user.getPassword(), grantedAuthorities);

    }

}
