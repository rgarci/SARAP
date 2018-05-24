package Vis;
import Con.Con;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Administrador extends JFrame{
    private JButton btnConsultar;
    private JButton btnAgregarAutobus;
    private JButton btnAgregarHorario;
    private JPanel panel1;
    private JButton btnUsuario;
    private Con con;

    public Administrador() throws HeadlessException {
        this.setLocationRelativeTo(null);
        con = Con.getSingletonInstance();
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    con.terminarAdministrador(0);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnAgregarAutobus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    con.terminarAdministrador(1);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnAgregarHorario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    con.terminarAdministrador(2);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    con.terminarAdministrador(3);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public Component getter() {
        return this.panel1;
    }
}
