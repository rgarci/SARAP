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
    private Con con;

    public Administrador() throws HeadlessException {
        this.setLocationRelativeTo(null);
        con = Con.getSingletonInstance();
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                con.terminarAdministrador(0);
            }
        });
        btnAgregarAutobus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                con.terminarAdministrador(1);
            }
        });
        btnAgregarHorario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                con.terminarAdministrador(2);
            }
        });
    }

    public Component getter() {
        return this.panel1;
    }
}
