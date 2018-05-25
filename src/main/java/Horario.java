import java.io.Serializable;

/**
 * Clase que modela un horario, los horarios son los mismos todos los días
 * @author rosaura & hilda
 * @version 1
 */
public class Horario implements Serializable{
    /**
     * ruta que se sigue en el horario
     */
    private Ruta ruta;
    /**
     * hora de salida
     */
    private String horaSalida;
    /**
     * duración del viaje
     */
    private float duracion;
    /**
     * hora de llegada
     */
    private String horaLlegada;
    /**
     * disponibilidad de asientos en el autobús
     */
    private int disponibilidad;

    /**
     * constructor
     * @param ruta
     * @param horaSalida
     * @param duracion
     * @param horaLlegada
     */
    public  Horario(Ruta ruta, String horaSalida, float duracion, String horaLlegada){
        this.ruta = ruta;
        this.horaSalida = horaSalida;
        this.duracion = duracion;
        this.horaLlegada = horaLlegada;
    }

    /**
     * getter hora de llegada
     * @return horaLlegada
     */
    public String getHoraLlegada() {
        return horaLlegada;
    }

    /**
     * getter duracion
     * @return duracion
     */
    public float getDuracion() {
        return duracion;
    }

    /**
     * getter ruta
     * @return ruta
     */
    public Ruta getRuta() {
        return ruta;
    }

    /**
     * getter hora de salida
     * @return horaSalida
     */
    public String getHoraSalida() {
        return horaSalida;
    }

    /**
     * getter disponibilidad
     * @return disponibilidad
     */
    public int getDisponibilidad() {
        return disponibilidad;
    }

    /**
     * setter ruta
     * @param ruta  nueva ruta
     */
    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    /**
     * toString
     * @return versión de
     */
    @Override
    public String toString() {
        return "Origen: " + getRuta().getCiudadOrigen() + " \nDestino: " + getRuta().getCiudadDestino() + " \nHorario salida: " + getHoraSalida() + " \nHora llegada: " + getHoraLlegada() + " \nDisponibilidad " + getDisponibilidad() + " asientos";
    }

    public void setDisponibilidad(int i) {
        this.disponibilidad = i;
    }
}
