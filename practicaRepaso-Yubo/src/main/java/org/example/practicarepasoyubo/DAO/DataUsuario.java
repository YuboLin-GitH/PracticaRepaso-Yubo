package org.example.practicarepasoyubo.DAO;

import java.util.ArrayList;
import java.util.List;
/**
 * ClassName: DataUsuario
 * Package: org.example.practicarepasoyubo.DAO
 * Description:
 *
 * @Author Yubo
 * @Create 26/09/2025 22:03
 * @Version 1.0
 */
class Usuario {
    private String email;
    private String password;

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
}

public class DataUsuario {

    private List<Usuario> usuarios = new ArrayList<>();
    public DataUsuario() {
        usuarios.add(new Usuario("1@1.com", "1"));
        usuarios.add(new Usuario("2@2.com", "2"));
    }

    public boolean validarUsuario(String email, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


}
