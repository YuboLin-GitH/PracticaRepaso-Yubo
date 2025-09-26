package org.example.practicarepasoyubo.Model;

import java.util.ArrayList;

/**
 * ClassName: Usuario
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author Yubo
 * @Create 16/09/2025 21:00
 * @Version 1.0
 */
public abstract class Usuario {
    protected int id;
    protected String nombre;
    protected String email;
    protected String password;

    public Usuario(int id, String nombre, String email , String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}








