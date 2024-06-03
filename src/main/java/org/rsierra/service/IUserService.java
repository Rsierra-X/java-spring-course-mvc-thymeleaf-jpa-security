package org.rsierra.service;

import org.rsierra.models.User;

import java.util.List;

public interface IUserService {
  void save(User user);
    void delete(Integer idUser);
    List<User> getAllUsers();
    List<User> searchRegisterUser();
    User searchById(Integer idUser);
    User findByUsername(String username);
   /* int bloquear(int idUsuario);
    int activar(int idUsuario);*/
}
