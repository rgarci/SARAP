package Mod;

import java.io.Serializable;

public class Horario implements Serializable{
    private Ruta ruta;
    private String horaSalida;
    private float duracion;
    private Autobus autobus;
    private String horaLlegada;
    private String fecha;
    private int disponibilidad;

    public  Horario(Ruta ruta, String horaSalida, float duracion, Autobus autobus, String horaLlegada, String fecha){
        this.ruta = ruta;
        this.horaSalida = horaSalida;
        this.duracion = duracion;
        this.autobus = autobus;
        this.horaLlegada = horaLlegada;
        this.fecha = fecha;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setAutobus(Autobus autobus) {
        this.autobus = autobus;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public void setHora(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public Autobus getAutobus() {
        return autobus;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getFecha() {
        return fecha;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    @Override
    public String toString() {
        return "Origen: " + getRuta().getCiudadOrigen() + " \nDestino: " + getRuta().getCiudadDestino() + " \nHorario salida: " + getHoraSalida() + " \nHora llegada: " + getHoraLlegada() + " \nDisponibilidad " + getDisponibilidad() + " asientos";
    }
}
