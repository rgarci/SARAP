import javax.swing.*;
import java.io.*;
import java.util.Hashtable;

/**
 * Clase que permite registrar un nuevo usuario en el sistema
 */
public class RegistroCliente {
    /**
     * crea un nuevo registro de usuario en el archivo usuarios.txt
     * @param newUsuario    usuario a registrar
     * @throws Exception
     */
    public static void registrar(Usuario newUsuario) throws Exception{
        Hashtable<String, Usuario> usuarios;
        File file = new File("usuarios.txt");
        try
        {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            usuarios = (Hashtable<String, Usuario>) ois.readObject();
            ois.close();

        }
        catch (EOFException e) {
            usuarios = new Hashtable<String, Usuario>();
        }
        if (usuarios.get(newUsuario.getCorreo())==null) {
            usuarios.put(newUsuario.getCorreo(), newUsuario);
        }

        FileOutputStream fs = new FileOutputStream(file, false);
        ObjectOutputStream ob = new ObjectOutputStream(fs);
        ob.writeObject(usuarios);
        ob.close();
    }

    /**
     * Valida si la contraseña ingresada por el usuario es correcta
     * @param correo    correo del usuario que está usando el sistema
     * @param contrasena    contraseña que se quiere validar
     * @return  true si la contraseña corresponde al usuario
     * @throws Exception
     */
    public static boolean validarContrasena(String correo, String contrasena) throws Exception{
        Hashtable<String, Usuario> usuarios;
        File file = new File("usuarios.txt");

        try
        {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            usuarios = (Hashtable<String, Usuario>) ois.readObject();
            ois.close();
            if (usuarios.get(correo).getContrasena()!=null){
                if (usuarios.get(correo).getContrasena().equals(contrasena)){
                    return true;
                }
            }
        }
        catch (EOFException e) {
            JOptionPane.showMessageDialog(null, "No existe un usuario con esa contraseña");
        }
        return false;
    }
}
