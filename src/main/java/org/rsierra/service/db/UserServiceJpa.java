package org.rsierra.service.db;

import org.rsierra.models.User;
import org.rsierra.repository.UserRepository;
import org.rsierra.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceJpa implements IUserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public void delete(Integer idUser) {
        userRepo.deleteById(idUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<User> searchRegisterUser() {
        return List.of();
    }

    @Override
    public User searchById(Integer idUser) {
        return userRepo.findById(idUser).get();
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
