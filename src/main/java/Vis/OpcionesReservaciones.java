package Vis;

import Mod.Horario;
import Con.Con;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpcionesReservaciones extends JFrame{
    private JButton btnSiguiente;
    private JList listOpciones;
    private JPanel panel1;
    private Con con;

    public OpcionesReservaciones(Horario[] horarios) {
        con = Con.getSingletonInstance();
        this.setLocationRelativeTo(null);
        listOpciones.setListData(horarios);
        btnSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listOpciones.getSelectedValue()!=null){
                    try {
                        Horario horario = (Horario) listOpciones.getSelectedValue();
                        String mensaje = "Se reservará el siguiente viaje:\n";
                        mensaje += horario;
                        mensaje += "\nDuración: " + horario.getDuracion() + " horas";
                        mensaje += "\nParadas intermedias:\n";
                        for (int i = 0; i < horario.getRuta().getRutasIntermedias().size(); i++) {
                            mensaje += horario.getRuta().getRutasIntermedias().get(i) + "\n";
                        }
                        JOptionPane.showMessageDialog(null, mensaje);
                        if (con.reservar(horario)){
                            JOptionPane.showMessageDialog(null, "El registro se realizó correctamente");
                            con.terminarOpcionesReservaciones();
                        }else {
                            JOptionPane.showMessageDialog(null, "Hubo un error al hacer la reservación");
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }


    public Component getter() {
        return this.panel1;
    }
}
