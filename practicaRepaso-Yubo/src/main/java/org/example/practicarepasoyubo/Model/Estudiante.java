package org.example.practicarepasoyubo.Model;

/**
 * ClassName: Estudiante
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author Yubo
 * @Create 16/09/2025 21:07
 * @Version 1.0
 */
public class Estudiante extends Usuario {
    private String curso;

    public Estudiante(int id, String nombre, String email, String password, String curso) {
        super(id, nombre, email, password);
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", curso='" + curso + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
