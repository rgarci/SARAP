package Vis;

import Con.Con;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Reservaciones extends JFrame {
    private JComboBox cbOrigen;
    private JComboBox cbDestino;
    private JButton btnSiguiente;
    private JPanel panel1;
    private JTextField txfFecha;
    private Con con;

    public Reservaciones(String[] ciudades) {
        con = Con.getSingletonInstance();
        this.setLocationRelativeTo(null);
        for (int i = 0; i < ciudades.length; i++){
            cbOrigen.addItem(ciudades[i]);
            cbDestino.addItem(ciudades[i]);
        }
        btnSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try{
                    String origen = String.valueOf(cbOrigen.getSelectedItem());
                    String destino = String.valueOf(cbDestino.getSelectedItem());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha = null;
                    fecha = simpleDateFormat.parse(txfFecha.getText());
                    Calendar calendar = Calendar.getInstance();
                    Date today = calendar.getTime();
                    if (fecha.before(today)){
                        throw new RuntimeException();
                    }
                    con.abrirVentanaOpcionesReservaciones(origen, destino, fecha);
                }catch(ParseException pe){
                    pe.printStackTrace();
                }catch(RuntimeException rt){
                    JOptionPane.showMessageDialog(null, "La fecha es incorrecta o anterior a la actual");
                }

            }
        });
    }

    public Component getter() {
        return this.panel1;
    }


}
