package org.rsierra.service;

import org.rsierra.models.User;

public interface IUserService {
  /*  void guardar(Usuario usuario);
    void eliminar(Integer idUsuario);
    List<Usuario> buscarTodos();
    List<Usuario> buscarRegistrados();*/
    /*Usuario buscarPorId(Integer idUsuario);*/
    User findByUsername(String username);
   /* int bloquear(int idUsuario);
    int activar(int idUsuario);*/
}
