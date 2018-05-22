package Mod;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Hashtable;

public class ModLogin {

    private static Usuario user;
    public static int ingresar(String correo, String contrasena) throws Exception {
        Hashtable<String, Usuario> usuarios;
        try {
            FileInputStream fis = new FileInputStream("usuarios.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            usuarios = (Hashtable<String, Usuario>) ois.readObject();

            Usuario usuario = (Usuario) usuarios.get(correo);
            if (usuario!=null){
                if (usuario.getContrasena().equals(contrasena)){
                    user = usuario;
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

    public static Usuario getUser() {
        return user;
    }
}
