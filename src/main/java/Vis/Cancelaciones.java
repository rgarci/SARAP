package Vis;

import Con.Con;
import Mod.Reservacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Cancelaciones extends JFrame{
    private JTextField txfClave;
    private JButton btnSiguiente;
    private JPanel panel1;
    private JTextArea taCancelaciones;
    private JButton btnRegresar;
    private Con con;

    public Cancelaciones(String can) {
        this.setLocationRelativeTo(null);
        con = Con.getSingletonInstance();
        if (can!=null) {
            taCancelaciones.setText(can);
        }else{
            taCancelaciones.setText("El usuario no tiene ninguna reservación");
        }
        btnSiguiente.setEnabled(false);
        taCancelaciones.setEditable(false);
        btnSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txfClave.equals("")) {
                        Reservacion reservacion = con.encontrarReservacion(txfClave.getText());
                        if (reservacion != null) {
                            con.abrirVentanaConfCancelacion(reservacion);
                        }else{
                            JOptionPane.showMessageDialog(null, "La clave de reservación no existe");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Ingrese una clave de reservación");
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "La clave de reservación es incorrecta");
                }
            }
        });
        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                con.terminarCancelaciones();
            }
        });
        txfClave.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                btnSiguiente.setEnabled(true);
            }
        });
    }



    public Component getter() {
        return this.panel1;
    }
}
