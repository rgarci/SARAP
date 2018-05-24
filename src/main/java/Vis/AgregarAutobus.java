package Vis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Con.Con;
import Mod.Autobus;

public class AgregarAutobus extends JFrame{
    private JTextField txfMarca;
    private JTextField txfModelo;
    private JTextField txfPlaca;
    private JButton btnAgregar;
    private JButton btnRegresar;
    private JPanel panel1;
    private Con con;

    public AgregarAutobus() {
        this.setLocationRelativeTo(null);
        con = Con.getSingletonInstance();
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String marca = txfMarca.getText();
                String modelo = txfModelo.getText();
                String placa = txfPlaca.getText();

                if (!marca.equals("") && !modelo.equals("") && !placa.equals("")){
                    Autobus autobus = new Autobus(marca, modelo, placa, 50);
                    con.agregarAutobus(autobus);
                    txfMarca.setText("");
                    txfModelo.setText("");
                    txfPlaca.setText("");
                    JOptionPane.showMessageDialog(null, "El registro se realizó con éxito");
                }else{
                    JOptionPane.showMessageDialog(null, "No se han llenado todos los campos");
                }
            }
        });
        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                con.terminarAgregarAutobus();
            }
        });
    }

    public Component getter() {
        return this.panel1;
    }
}
