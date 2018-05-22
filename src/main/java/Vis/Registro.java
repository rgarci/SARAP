package Vis;

import Con.Con;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registro extends JFrame{
    private JTextField txfNombre;
    private JTextField txfCorreo;
    private JButton btnCrear;
    private JPasswordField pswContrasena;
    private JPasswordField pswContrasenaConf;
    private JPanel panel1;
    private Con con;

    public Registro() {
        this.setLocationRelativeTo(null);
        con = Con.getSingletonInstance();

        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String nombre = txfNombre.getText();
                    String correo = txfCorreo.getText();
                    String passw = String.valueOf(pswContrasena.getPassword());
                    String passwConfirmation = String.valueOf(pswContrasenaConf.getPassword());
                    if (!nombre.equals("") && !correo.equals("") && !passw.equals("")) {
                        if (passw.equals(passwConfirmation)) {
                            con.registrar(nombre, passw, correo);
                            JOptionPane.showMessageDialog(null, "El registro se realizó correctamente");
                            con.terminarRegistro();
                        } else {
                            JOptionPane.showMessageDialog(null, "Las contraseñas no son iguales");
                        }
                    }else{
                        throw new Exception();
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null, "Los campos no se llenaron correctamente");
                }
            }
        });
    }

    public Component getter() {
        return this.panel1;
    }
}
