package org.rsierra.service.db;

import org.rsierra.models.User;
import org.rsierra.repository.UserRepository;
import org.rsierra.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceJpa implements IUserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
