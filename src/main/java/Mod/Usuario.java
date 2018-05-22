package Mod;

import java.io.Serializable;

public class Usuario implements Serializable{
    private String nombre;
    private String contrasena;
    private String correo;

    public Usuario(String nombre, String contrasena, String correo){
        this.contrasena = contrasena;
        this.correo = correo;
        this.nombre = nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getCorreo() {
        return correo;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
