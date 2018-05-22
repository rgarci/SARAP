package Mod;

import java.io.Serializable;

public class Autobus implements Serializable{
    private String marca;
    private String modelo;
    private String placa;
    private int noAsientos;

    public Autobus(String marca, String modelo, String placa, int noAsientos){
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.noAsientos = noAsientos;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setNoAsientos(int noAsientos) {
        this.noAsientos = noAsientos;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public int getNoAsientos() {
        return noAsientos;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }
}
