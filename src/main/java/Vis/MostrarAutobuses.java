package Vis;

import Con.Con;
import Mod.Autobus;
import Mod.ShellSort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class MostrarAutobuses extends JFrame{
    private JCheckBox cbMarca;
    private JCheckBox cbModelo;
    private JCheckBox cbPlaca;
    private JButton btnOrdenar;
    private JList listAutobuses;
    private JButton btnRegresar;
    private JPanel panel1;
    private Con con;

    public MostrarAutobuses(final Autobus[] autobuses) {
        this.setLocationRelativeTo(null);
        con = Con.getSingletonInstance();

        btnOrdenar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] autobusesLista;
                if (cbMarca.isSelected() || cbModelo.isSelected() || cbPlaca.isSelected()){
                    if (cbMarca.isSelected()){
                        autobusesLista = ShellSort.sort(0, autobuses);
                        listAutobuses.setListData(autobusesLista);
                    }else if(cbModelo.isSelected()){
                        autobusesLista = ShellSort.sort(1, autobuses);
                        listAutobuses.setListData(autobusesLista);
                    }else if(cbPlaca.isSelected()){
                        autobusesLista = ShellSort.sort(2, autobuses);
                        listAutobuses.setListData(autobusesLista);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado un criterio");
                }
                cbModelo.setEnabled(true);
                cbModelo.setSelected(false);
                cbMarca.setEnabled(true);
                cbMarca.setSelected(false);
                cbPlaca.setEnabled(true);
                cbPlaca.setSelected(false);
            }
        });
        cbMarca.addMouseListener(new MouseAdapter() {
        });
        cbMarca.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cbModelo.setEnabled(false);
                cbPlaca.setEnabled(false);
            }
        });
        cbModelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cbMarca.setEnabled(false);
                cbPlaca.setEnabled(false);
            }
        });
        cbPlaca.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cbMarca.setEnabled(false);
                cbModelo.setEnabled(false);
            }
        });
        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                con.terminarMostrarAutobuses();
            }
        });
    }

    public Component getter() {
        return this.panel1;
    }
}
