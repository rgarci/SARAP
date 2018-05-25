import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Hashtable;

/**
 * Clase que modela el login del programa
 */
public class ModLogin {
    /**
     * valida que el correo y la contraseña ingresadas correspondan a un usuario registrado
     * @param correo    correo del usuario
     * @param contrasena    contraseña del usuario
     * @return  entero de control para manipular el acceso al sistema
     * @throws Exception
     */
    public static int ingresar(String correo, String contrasena) throws Exception {
        Hashtable<String, Usuario> usuarios;
        try {
            FileInputStream fis = new FileInputStream("usuarios.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            usuarios = (Hashtable<String, Usuario>) ois.readObject();

            Usuario usuario = (Usuario) usuarios.get(correo);
            if (usuario!=null){
                if (usuario.getContrasena().equals(contrasena)){
                     return 0;
                }else{
                    return 1;
                }
            }else{
                return 2;
            }
        }catch(Exception e){
            return 3;
        }

    }

}
