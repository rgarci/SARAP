package Mod;

import java.io.Serializable;
import java.util.ArrayList;

public class Ruta implements Serializable{
    private String ciudadOrigen;
    private String ciudadDestino;
    private ArrayList<String> rutasIntermedias;

    public Ruta(String ciudadOrigen, String ciudadDestino){
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public void addRutaIntermedia(String ciudad){
        if (rutasIntermedias==null){
            rutasIntermedias = new ArrayList<String>();
        }
        rutasIntermedias.add(ciudad);
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public ArrayList<String> getRutasIntermedias() {
        return rutasIntermedias;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
