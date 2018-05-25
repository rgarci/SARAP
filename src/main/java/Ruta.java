import java.io.Serializable;
import java.util.ArrayList;

/**
 * clase que modela una ruta
 */
public class Ruta implements Serializable{
    /**
     * ciudad de partida
     */
    private String ciudadOrigen;
    /**
     * ciudad de destino
     */
    private String ciudadDestino;
    /**
     * paradas intermedias
     */
    private ArrayList<String> rutasIntermedias;

    /**
     * Constructor
     * @param ciudadOrigen
     * @param ciudadDestino
     */
    public Ruta(String ciudadOrigen, String ciudadDestino){
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
    }

    /**
     * getter ciudad origen
     * @return ciudadOrigen
     */
    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    /**
     * getter ciudad destino
     * @return ciudadDestino
     */
    public String getCiudadDestino() {
        return ciudadDestino;
    }

    /**
     * getter rutas intermedias
     * @return rutasIntermedias
     */
    public ArrayList<String> getRutasIntermedias() {
        return rutasIntermedias;
    }

    /**
     * toString
     * @return versión de impresión del objeto
     */
    @Override
    public String toString() {
        return super.toString();
    }

    public void addRutaIntermedia(String s) {
        if (rutasIntermedias==null){
            rutasIntermedias = new ArrayList<>();
        }
        rutasIntermedias.add(s);
    }
}
