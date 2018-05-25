import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que modela un chofer
 */
public class Chofer implements Serializable {
    /**
     * numero de empleado del chofer
     */
    private int noEmpleado;
    /**
     * nombre del chofer
     */
    private String nombre;
    /**
     * fecha de nacimiento del chofer
     */
    private Date fechaNacimiento;

    /**
     * Constructor
     * @param noEmpleado
     * @param nombre
     * @param fechaNacimiento
     */
    public Chofer(int noEmpleado, String nombre, Date fechaNacimiento){
        this.nombre = nombre;
        this.noEmpleado = noEmpleado;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * getter nombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * getter fecha de nacimiento
     * @return fechaNacimiento
     */
    public String getFechaNacimiento() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaNacimientoS = simpleDateFormat.format(fechaNacimiento);
        return fechaNacimientoS;
    }

    /**
     * getter noEmpleado
     * @return noEmpleado
     */
    public int getNoEmpleado() {
        return noEmpleado;
    }

    /**
     * toString
     * @return versión de impresión del objeto
     */
    @Override
    public String toString() {
        return "Nombre: " + getNombre() + " Número: " + getNoEmpleado() + " Fecha de nacimiento: " + getFechaNacimiento();
    }
}
