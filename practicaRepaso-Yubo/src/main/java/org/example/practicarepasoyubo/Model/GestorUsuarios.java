package org.example.practicarepasoyubo.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: GestorUsuarios
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author Yubo
 * @Create 20/09/2025 15:40
 * @Version 1.0
 */
public class GestorUsuarios implements GestionUsuarios {
    private ArrayList<Usuario> usuarios;

    public GestorUsuarios() {
        usuarios = new ArrayList<>();
    }


    @Override
    public void agregarUsuario(Usuario usuario) throws Exception {

        for( Usuario u : usuarios ) {
            if (u.getId() == usuario.getId()) {
                throw new Exception("El usuario ya existe");
            }
        }
        usuarios.add(usuario);
        System.out.println("Usuario agregado correctamente");
    }

    @Override
    public void eliminarUsuario(int idUsuario) throws Exception {
        boolean encontrado = false;
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.getId() == idUsuario) {
                usuarios.remove(i);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new Exception("No existe el usuario");
        }
       }


    @Override
    public ArrayList<String> listarUsuarios(int tipoUsuario) {
        ArrayList<String> resultado = new ArrayList<>();
            for(Usuario u : usuarios){
                if (tipoUsuario == 0 && u instanceof Estudiante) {
                    resultado.add(u.toString());
                }else if (tipoUsuario == 1 && u instanceof Profesor) {
                    resultado.add(u.toString());
                }
            }
            return resultado;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }


}
