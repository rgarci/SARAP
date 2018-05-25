import java.io.Serializable;

/**
 * clase que modela a un usuario del sistema
 */
public class Usuario implements Serializable{
    /**
     * nombre del usuario
     */
    private String nombre;
    /**
     * constraseña asociada al usuario
     */
    private String contrasena;
    /**
     * correo del usuario
     */
    private String correo;

    /**
     * constructor
     * @param nombre
     * @param contrasena
     * @param correo
     */
    public Usuario(String nombre, String contrasena, String correo){
        this.contrasena = contrasena;
        this.correo = correo;
        this.nombre = nombre;
    }

    /**
     * getter contraseña
     * @return contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * getter correo
     * @return correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * toString
     * @return versión de impresión del objeto
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
