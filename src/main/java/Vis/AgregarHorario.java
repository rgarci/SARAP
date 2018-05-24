package Vis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Con.Con;
import Mod.Horario;
import Mod.Ruta;

public class AgregarHorario extends JFrame{
    private JComboBox cbOrigen;
    private JComboBox cbDestino;
    private JTextField txfHora;
    private JButton btnAgregar;
    private JPanel panel1;
    private JButton btnRegresar;
    private Con con;

    public AgregarHorario(String[] ciudades) {
        this.setLocationRelativeTo(null);
        con = Con.getSingletonInstance();
        for (int i = 0; i < ciudades.length; i++) {
            cbOrigen.addItem(ciudades[i]);
            cbDestino.addItem(ciudades[i]);
        }
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
                Date horaSalida;
                String origen = (String) cbOrigen.getSelectedItem();
                String destino  = (String) cbDestino.getSelectedItem();
                try {
                    horaSalida = simpleDateFormat.parse(txfHora.getText());
                    con.agregarHorario(origen, destino, horaSalida);
                    JOptionPane.showMessageDialog(null, "El registro se realizó con éxito");
                } catch (ParseException e1) {
                    JOptionPane.showMessageDialog(null, "La hora no tiene el formato correcto");
                }
            }
        });
        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                con.terminarAgregarHorario();
            }
        });
    }

    public Component getter() {
        return this.panel1;
    }
}
