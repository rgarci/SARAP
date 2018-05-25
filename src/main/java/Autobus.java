import java.io.Serializable;

/**
 * Clase que modela un autobús
 * @author rosaura & hilda
 */
public class Autobus implements Serializable{
    /**
     * marca del autobús
     */
    private String marca;
    /**
     * modelo del autobús
     */
    private String modelo;
    /**
     * placa del autobús
     */
    private String placa;
    /**
     * número de asientos del autobús
     */
    private int noAsientos;

    /**
     * Constructor
     * @param marca
     * @param modelo
     * @param placa
     * @param noAsientos
     */
    public Autobus(String marca, String modelo, String placa, int noAsientos){
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.noAsientos = noAsientos;
    }

    /**
     * getter marca
     * @return marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * getter modelo
     * @return modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * getter placa
     * @return placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * tostring
     * @return  versión de impresión del objeto
     */
    @Override
    public String toString() {
        return "Marca: " + getMarca() + "\n\t Modelo: " + getModelo() + "\n\t Placa: " + getPlaca();
    }
}
