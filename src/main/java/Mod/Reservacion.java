package Mod;

import java.io.Serializable;

public class Reservacion implements Serializable{
    private Horario horario;
    private String claveReservacion;
    private double precio;

    public Reservacion(Horario horario, String claveReservacion){
        this.claveReservacion = claveReservacion;
        this.horario = horario;
        precio = horario.getDuracion()*250;
    }

    public Horario getHorario() {
        return horario;
    }

    public String getClaveReservacion() {
        return claveReservacion;
    }

    public void setClaveReservacion(String claveReservacion) {
        this.claveReservacion = claveReservacion;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return getHorario().toString() +"\n Clave de reservación: " + getClaveReservacion();
    }
}
