package org.example.practicarepasoyubo.Model;

import java.util.ArrayList;

/**
 * ClassName: GestionUsuarios
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author Yubo
 * @Create 20/09/2025 15:41
 * @Version 1.0
 */
public interface GestionUsuarios {
    // agregar usuario
    void agregarUsuario(Usuario usuario) throws Exception;
    // eliminar usuario
    void eliminarUsuario(int idUsuario) throws Exception;

    // busca usuario
    ArrayList<String> listarUsuarios(int tipoUsuario);
}

