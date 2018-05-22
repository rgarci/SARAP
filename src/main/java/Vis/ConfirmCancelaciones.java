package Vis;

import Con.Con;
import Mod.Reservacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmCancelaciones extends JFrame{
    private JTextArea txtaViaje;
    private JButton btnConfirmar;
    private JPasswordField pswContrasena;
    private JPanel panel1;
    private Con con;

    public ConfirmCancelaciones(final Reservacion reservacion) {
        this.setLocationRelativeTo(null);
        con = Con.getSingletonInstance();
        txtaViaje.setText(reservacion.toString());
        txtaViaje.setEditable(false);
        btnConfirmar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    if (con.comprobarContrasena(String.valueOf(pswContrasena.getPassword()))) {
                        con.cancelar(reservacion.getClaveReservacion());
                        JOptionPane.showMessageDialog(null, "El registro se eliminó con éxito");
                        con.terminarConfCancelaciones();
                    } else {
                        JOptionPane.showMessageDialog(null, "La contraseña es incorrecta");
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null, "La contraseña es incorrecta");
                }
            }
        });
    }


    public Component getter() {
        return this.panel1;
    }
}
