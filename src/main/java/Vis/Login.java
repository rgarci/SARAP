package Vis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Con.Con;

public class Login extends JFrame{
    private JPanel LoginPanel;
    private JPanel westPanel;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel eastPanel;
    private JLabel lblMail;
    private JLabel lblPassword;
    private JTextField txfMail;
    private JButton btnSubmit;
    private JLabel lblRegistro;
    private JPasswordField pswPassword;
    private JButton btnRegistro;
    private Con con;

    public Login(String title) {
        super(title);
        this.setLocationRelativeTo(null);
        con = Con.getSingletonInstance();
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //enviar datos a con para ingresar
            }
        });
        btnRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //mostrar gui form de registro
            }
        });
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    con.login(txfMail.getText(), String.valueOf(pswPassword.getPassword()));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                con.abrirVentanaRegistro();
            }
        });
    }
    public Component getter() {
        return this.LoginPanel;
    }
}
