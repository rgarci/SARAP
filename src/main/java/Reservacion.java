import java.io.Serializable;
import java.util.Date;

/**
 * Clase que modela una reservación
 */
public class Reservacion implements Serializable{
    /**
     * Horario de la reservacion
     */
    private Horario horario;
    /**
     * Clave de la reservación
     */
    private String claveReservacion;
    /**
     * Precio de la reservación
     */
    private double precio;
    /**
     * fecha del viaje que se reservó
     */
    private Date fecha;

    /**
     * constructor
     * @param horario
     * @param claveReservacion
     * @param fecha
     */
    public Reservacion(Horario horario, String claveReservacion, Date fecha){
        this.claveReservacion = claveReservacion;
        this.horario = horario;
        precio = horario.getDuracion()*250;
        this.fecha = fecha;
    }

    /**
     * getter horario
     * @return horario
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * getter clave de reservacion
     * @return claveReservacion
     */
    public String getClaveReservacion() {
        return claveReservacion;
    }

    /**
     * getter fecha
     * @return fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * getter precio
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * toString
     * @return versión de impresión del objeto
     */
    @Override
    public String toString() {
        return getHorario().toString() +"\n Clave de reservación: " + getClaveReservacion() + "\n Fecha: " + getFecha() + "\n precio: " + getPrecio();
    }
}
