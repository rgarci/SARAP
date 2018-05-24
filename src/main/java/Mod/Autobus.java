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

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }

    @Override
    public String toString() {
        return "Marca: " + getMarca() + "\n\t Modelo: " + getModelo() + "\n\t Placa: " + getPlaca();
    }
}
