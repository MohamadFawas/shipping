package com.imara.shipping.service;

import com.imara.shipping.model.User;
import com.imara.shipping.model.UserRole;
import com.imara.shipping.repository.UserRepository;
import com.imara.shipping.repository.core.AbstractRepository;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class UserService extends AbstractService<User> implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    protected AbstractRepository getRepository() {
        return userRepository;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    public User save(User theUser) {
        if (!this.userRepository.findByUsernameAndSkipId(theUser.getUsername(), theUser.getId()).isEmpty()) {
            throw new RuntimeException("This username already used");
        }
        //
        if (!StringUtils.isEmpty(theUser.getPasswordN())) {
            theUser.setPassword(encoder.encode(theUser.getPasswordN()));
        }
        //
        return super.save(theUser);
    }

    public List<User> findAllByUserRole(UserRole userRole) {
        return userRepository.findAllByUserRole(userRole);
    }

    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    public void updateUserActive(long id, boolean active, boolean checkForAdminUsers) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
//        if (checkForAdminUsers && !(user.isAdmin() || user.isMasterAdmin())) {
//            throw new RuntimeException("Admin and Master Admin can use this method to activate / deactivate.");
//        }
        userRepository.updateUserActive(id, active);
    }

    public User findByRefId(long refId) {
        return userRepository.findByRefId(refId);
    }

}
