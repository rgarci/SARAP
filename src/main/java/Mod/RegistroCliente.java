package Mod;

import javax.swing.*;
import java.io.*;
import java.util.Hashtable;
import java.util.Iterator;

public class RegistroCliente {
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
            System.out.println("el usuario no existía");
        }else{
            System.out.println("el usuario existía");
        }

        FileOutputStream fs = new FileOutputStream(file, false);
        ObjectOutputStream ob = new ObjectOutputStream(fs);
        ob.writeObject(usuarios);
        ob.close();
    }

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
