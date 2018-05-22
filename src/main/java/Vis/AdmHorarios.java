package Vis;

import Con.Con;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdmHorarios extends JFrame{
    private JButton btnReservar;
    private JButton btnCancelar;
    private JPanel panel1;
    private JList listHorarios;
    private Con con;

    public AdmHorarios(String[] horarios) {
        con = Con.getSingletonInstance();
        this.setLocationRelativeTo(null);
        listHorarios.setListData(horarios);
        btnReservar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                con.abrirVentanaReservaciones();
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                con.abrirVentanaCancelaciones();
            }
        });
    }

    public Component getter() {
        return this.panel1;
    }
}
