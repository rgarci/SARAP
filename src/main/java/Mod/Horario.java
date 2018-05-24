package Mod;

import java.io.Serializable;

public class Horario implements Serializable{
    private Ruta ruta;
    private String horaSalida;
    private float duracion;
    private String horaLlegada;
    private int disponibilidad;

    public  Horario(Ruta ruta, String horaSalida, float duracion, String horaLlegada){
        this.ruta = ruta;
        this.horaSalida = horaSalida;
        this.duracion = duracion;
        this.horaLlegada = horaLlegada;
    }


    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public float getDuracion() {
        return duracion;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }


    @Override
    public String toString() {
        return "Origen: " + getRuta().getCiudadOrigen() + " \nDestino: " + getRuta().getCiudadDestino() + " \nHorario salida: " + getHoraSalida() + " \nHora llegada: " + getHoraLlegada() + " \nDisponibilidad " + getDisponibilidad() + " asientos";
    }
}
