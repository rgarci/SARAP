package Mod;

import java.io.Serializable;
import java.util.Date;

public class Reservacion implements Serializable{
    private Horario horario;
    private String claveReservacion;
    private double precio;
    private Date fecha;

    public Reservacion(Horario horario, String claveReservacion, Date fecha){
        this.claveReservacion = claveReservacion;
        this.horario = horario;
        precio = horario.getDuracion()*250;
        this.fecha = fecha;
    }

    public Horario getHorario() {
        return horario;
    }

    public String getClaveReservacion() {
        return claveReservacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setClaveReservacion(String claveReservacion) {
        this.claveReservacion = claveReservacion;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return getHorario().toString() +"\n Clave de reservación: " + getClaveReservacion() + "\n Fecha: " + getFecha();
    }
}
