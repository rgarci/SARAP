package Mod;

import java.util.Date;

public class Chofer {

    private int noEmpleado;
    private String nombre;
    private Date fechaNacimiento;

    public Chofer(int noEmpleado, String nombre, Date fechaNacimiento){
        this.nombre = nombre;
        this.noEmpleado = noEmpleado;
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNoEmpleado(int noEmpleado) {
        this.noEmpleado = noEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getNoEmpleado() {
        return noEmpleado;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
