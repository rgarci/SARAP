package Con;

import Mod.*;
import com.github.javafaker.Faker;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import Vis.*;

import javax.swing.*;

public class Con {
    private static Con con;
    private String usuario;
    private Login loginF;
    private Registro resgitroF;
    private AdmHorarios adHorariosF;
    private Reservaciones reservacionesF;
    private Cancelaciones cancelacionesF;
    private ConfirmCancelaciones confCancelacionesF;
    private Faker faker = new Faker(new Locale("es", "MX"));
    private OpcionesReservaciones opcionesReservacionesF;
    private Con() {
    }

    public static Con getSingletonInstance() {
        if (con == null){
            con = new Con();
        }
        return con;
    }
    public void iniciar() throws IOException {
        File fileUsuarios = new File("usuarios.txt");
        //fileUsuarios.delete();
        fileUsuarios.createNewFile();
        File fileReservaciones = new File("reservaciones.txt");
        fileReservaciones.delete();
        fileReservaciones.createNewFile();
        abrirVentanaInicio();
    }

    public void abrirVentanaInicio(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                loginF = new Login("");
                loginF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                loginF.getContentPane().add(new Login("Login").getter());
                loginF.pack();
                loginF.setVisible(true);
            }
        });
    }

    public void abrirVentanaRegistro(){
        loginF.dispose();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                resgitroF = new Registro();
                resgitroF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                resgitroF.getContentPane().add(new Registro().getter());
                resgitroF.pack();
                resgitroF.setVisible(true);
            }
        });
    }

    public void abrirVentanaAdHorarios(){
        loginF.dispose();
        final String[] horarios = mostrarHorarios();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                adHorariosF = new AdmHorarios(horarios);
                adHorariosF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                adHorariosF.getContentPane().add(new AdmHorarios(horarios).getter());
                adHorariosF.pack();
                adHorariosF.setVisible(true);
            }
        });
    }

    public void abrirVentanaReservaciones(){
        adHorariosF.dispose();
        int noCiudades = faker.number().numberBetween(20, 30);
        final String[] ciudades = new String[noCiudades];
        for (int i = 0; i < noCiudades; i++){
            ciudades[i] = faker.address().cityName();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                reservacionesF = new Reservaciones(ciudades);
                reservacionesF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                reservacionesF.getContentPane().add(new Reservaciones(ciudades).getter());
                reservacionesF.pack();
                reservacionesF.setVisible(true);
            }
        });
    }

    public void abrirVentanaCancelaciones(){
        adHorariosF.dispose();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    cancelacionesF = new Cancelaciones(mostrarCancelaciones());
                    cancelacionesF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    cancelacionesF.getContentPane().add(new Cancelaciones(mostrarCancelaciones()).getter());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cancelacionesF.pack();
                cancelacionesF.setVisible(true);

            }
        });
    }

    public void abrirVentanaConfCancelacion(final Reservacion reservacion){
        cancelacionesF.dispose();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                confCancelacionesF = new ConfirmCancelaciones(reservacion);
                confCancelacionesF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                confCancelacionesF.getContentPane().add(new ConfirmCancelaciones(reservacion).getter());
                confCancelacionesF.pack();
                confCancelacionesF.setVisible(true);
            }
        });
        String reservacionString = "";
        reservacionString += "Fecha: " + reservacion.getHorario().getFecha();
        reservacionString += "Cuidad origen: " + reservacion.getHorario().getRuta().getCiudadOrigen();
        reservacionString += "\nCuidad destino: " + reservacion.getHorario().getRuta().getCiudadDestino();
        reservacionString += "\nSalida: " + reservacion.getHorario().getHoraSalida();
        reservacionString += "\nLlegada: " + reservacion.getHorario().getHoraLlegada();
    }
    public void abrirVentanaOpcionesReservaciones(String ciudadOrigen, String ciudadDestino, Date fecha){
        reservacionesF.dispose();
        int noOpciones = faker.number().numberBetween(5,10);
        final Horario st[] = new Horario[noOpciones];

        String placaAutobus = faker.idNumber().valid();
        int noAsientos = faker.number().numberBetween(20, 50);
        Autobus autobus = new Autobus("Mercedes Benz", faker.regexify("Modelo[a-z]*[0-9]*"), placaAutobus, noAsientos);

        float duracion = (float) faker.number().randomDouble(2, 1, 30);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaS = dateFormat.format(fecha);
        for (int i = 0; i < noOpciones; i++) {
            Ruta ruta = new Ruta(ciudadOrigen, ciudadDestino);
            DateFormat hourFormat = new SimpleDateFormat("hh:mm aa");
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR_OF_DAY, faker.number().numberBetween(0, 24));
            calendar.add(Calendar.MINUTE, faker.number().numberBetween(0, 60));
            Date date = calendar.getTime();
            String horaSalida =hourFormat.format(date);

            int minutos = (int)((duracion - (int) duracion) * 60);
            calendar.add(Calendar.HOUR_OF_DAY, (int) duracion);
            calendar.add(Calendar.MINUTE, minutos);
            date = calendar.getTime();
            String horaLlegada = hourFormat.format(date);

            for (int j = 0; j < faker.number().numberBetween(2, 5); j++) {
                ruta.addRutaIntermedia(faker.address().cityName());
            }

            Horario horario = new Horario(ruta, horaSalida, duracion, autobus, horaLlegada, fechaS);
            horario.setDisponibilidad(noAsientos - faker.number().numberBetween(0, noAsientos));

            st[i] = horario;
        }


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                opcionesReservacionesF = new OpcionesReservaciones(st);
                opcionesReservacionesF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                opcionesReservacionesF.getContentPane().add(new OpcionesReservaciones(st).getter());
                opcionesReservacionesF.pack();
                opcionesReservacionesF.setVisible(true);
            }
        });
    }

    public void registrar(String nombre, String contrasena, String correo) throws Exception {
        Usuario user = new Usuario(nombre, contrasena, correo);
        RegistroCliente.registrar(user);
    }

    public void terminarRegistro(){
        resgitroF.dispose();
        abrirVentanaInicio();
    }

    public void terminarCancelaciones(){
        cancelacionesF.dispose();
        abrirVentanaAdHorarios();
    }

    public void terminarOpcionesReservaciones(){
        opcionesReservacionesF.dispose();
        abrirVentanaAdHorarios();
    }

    public void terminarConfCancelaciones(){
        confCancelacionesF.dispose();
        abrirVentanaAdHorarios();
    }

    public void login(String correo, String contrasena) throws Exception {
        usuario = correo;
        int flag = ModLogin.ingresar(correo, contrasena);
        switch (flag){
            case 0:
                loginF.dispose();
                abrirVentanaAdHorarios();
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "La contraseña es incorrecta");
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "El usuario es incorrecto");
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                break;
        }
        System.out.println(usuario);
        //cerrar login
        //abrir reservaciones
    }

    public boolean reservar(Horario horario) throws Exception {
        Reservacion reservacion = new Reservacion(horario, faker.crypto().md5());
        return ModReservacion.reservar(reservacion, usuario);
    }

    public void cancelar(String claveReservacion) throws Exception {
        ModCancelaciones.cancelar(claveReservacion, usuario);
    }
    public String[] mostrarHorarios(){
        String st[] = new String[faker.number().numberBetween(15, 20)];
        st[0] = "Viajes más populares\n";
        for (int i = 1; i < st.length; i++){
            st[i] = generarHorario().toString() ;
        }
        return st;
        //mostrar horarios y opciones de reservacion y cancelación
    }


    public String mostrarCancelaciones() throws Exception {
        try {
            String can = "Usted puede cancelar las siguientes reservaciones:\n";
            can += ModReservacion.consultarReservacion(usuario);
            return can;
        }catch(EOFException np){
            return null;
        }
    }

    public Reservacion encontrarReservacion(String clave) throws Exception{
        return ModReservacion.encontrarReservacion(clave, usuario);
    }

    public boolean comprobarContrasena(String contrasena) throws Exception {
        return RegistroCliente.validarContrasena(usuario, contrasena);
    }

    private Horario generarHorario(){
        String ciudadOrigen = faker.address().cityName();
        String ciudadDestino = faker.address().cityName();
        Ruta ruta = new Ruta(ciudadOrigen, ciudadDestino);

        String placaAutobus = faker.idNumber().valid();
        int noAsientos = faker.number().numberBetween(20, 50);
        Autobus autobus = new Autobus("Mercedes Benz", faker.regexify("Modelo[a-z]*[0-9]*"), placaAutobus, noAsientos);

        DateFormat hourFormat = new SimpleDateFormat("hh:mm aa");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, faker.number().numberBetween(0, 24));
        calendar.add(Calendar.MINUTE, faker.number().numberBetween(0, 60));
        Date date = calendar.getTime();
        String horaSalida =hourFormat.format(date);

        float duracion = (float) faker.number().randomDouble(2, 1, 40);

        int minutos = (int)((duracion - (int) duracion) * 60);
        calendar.add(Calendar.HOUR_OF_DAY, (int) duracion);
        calendar.add(Calendar.MINUTE, minutos);
        date = calendar.getTime();
        String horaLlegada = hourFormat.format(date);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        calendar.add(Calendar.DAY_OF_YEAR, faker.number().numberBetween(0, 60));
        date = calendar.getTime();
        String fecha = dateFormat.format(date);

        Horario horario = new Horario(ruta, horaSalida, duracion, autobus, horaLlegada, fecha);
        horario.setDisponibilidad(noAsientos - faker.number().numberBetween(0, noAsientos));
        return horario;
    }



}
