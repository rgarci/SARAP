package Con;

import Mod.*;
import Vis.*;
import com.github.javafaker.Faker;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Con {
    private static Con con;
    private String usuario;
    private Login loginF;
    private Registro resgitroF;
    private AdmHorarios adHorariosF;
    private Reservaciones reservacionesF;
    private Cancelaciones cancelacionesF;
    private ConfirmCancelaciones confCancelacionesF;
    private  Faker faker = new Faker(new Locale("es", "MX"));
    private OpcionesReservaciones opcionesReservacionesF;
    private boolean admin = false;
    private Administrador administradorF;
    private MostrarAutobuses mostrarAutobusesF;
    private AgregarAutobus agregarAutobusF;
    private AgregarHorario agregarHorarioF;

    private String[] ciudades;
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

    public void abrirVentanaAdHorarios() throws Exception {
        final String[] horarios = leerHorarios();
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

    public void abrirVentanaAdministrador(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                administradorF = new Administrador();
                administradorF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                administradorF.getContentPane().add(new Administrador().getter());
                administradorF.pack();
                administradorF.setVisible(true);
            }
        });
    }

    public void abrirVentanaReservaciones(){

        final String[] ciudades = leerCiudades();
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
    }
    public void abrirVentanaOpcionesReservaciones(String ciudadOrigen, String ciudadDestino, final Date fecha) throws Exception{
        reservacionesF.dispose();
        final Horario st[] = new Horario[3];
        LinkedList<Horario> horarios;
        try
        {
            FileInputStream fis = new FileInputStream("horarios.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            horarios = (LinkedList<Horario>) ois.readObject();
            Iterator it = horarios.iterator();
            int i = 0;
            while(it.hasNext()){
                Horario horario = (Horario) it.next();
                if (horario.getRuta().getCiudadOrigen().equals(ciudadOrigen) && horario.getRuta().getCiudadDestino().equals(ciudadDestino)){
                    st[i++] = horario;
                }
            }
            ois.close();

        }
        catch (EOFException e) {
            System.out.println("no hay nada we");
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                opcionesReservacionesF = new OpcionesReservaciones(st, fecha);
                opcionesReservacionesF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                opcionesReservacionesF.getContentPane().add(new OpcionesReservaciones(st, fecha).getter());
                opcionesReservacionesF.pack();
                opcionesReservacionesF.setVisible(true);
            }
        });
    }

    public void abrirVentanaMostrarAutobuses(){
        Autobus[] st = null;
        LinkedList<Autobus> autobuses;
        try
        {
            FileInputStream fis = new FileInputStream("autobuses.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            autobuses = (LinkedList<Autobus>) ois.readObject();
            st = new Autobus[autobuses.size()];
            Iterator it = autobuses.iterator();
            for (int i = 0; i < autobuses.size(); i++) {
                st[i] = (Autobus) it.next();
            }
            ois.close();

        }
        catch (EOFException e) {
            System.out.println("no hay nada we");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        administradorF.dispose();
        final Autobus[] finalSt = st;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mostrarAutobusesF = new MostrarAutobuses(finalSt);
                mostrarAutobusesF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mostrarAutobusesF.getContentPane().add(new MostrarAutobuses(finalSt).getter());
                mostrarAutobusesF.pack();
                mostrarAutobusesF.setVisible(true);
            }
        });
    }

    public void abrirVentanaAgregarAutobus(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                agregarAutobusF = new AgregarAutobus();
                agregarAutobusF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                agregarAutobusF.getContentPane().add(new AgregarAutobus().getter());
                agregarAutobusF.pack();
                agregarAutobusF.setVisible(true);
            }
        });
    }

    public void abrirVentanaAgregarHorario(){
        final String[] ciudades = leerCiudades();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                agregarHorarioF = new AgregarHorario(ciudades);
                agregarHorarioF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                agregarHorarioF.getContentPane().add(new AgregarHorario(ciudades).getter());
                agregarHorarioF.pack();
                agregarHorarioF.setVisible(true);
            }
        });
    }

    public void registrar(String nombre, String contrasena, String correo) throws Exception {
        Usuario user = new Usuario(nombre, contrasena, correo);
        RegistroCliente.registrar(user);
    }

    public void terminarLogin() throws Exception {
        loginF.dispose();
        if (admin){
            abrirVentanaAdministrador();
        }else {
            abrirVentanaAdHorarios();
        }
    }

    public void terminarRegistro(){
        resgitroF.dispose();
        abrirVentanaInicio();
    }

    public void terminarAdHorarios(boolean res){
        administradorF.dispose();
        if (res){
            abrirVentanaReservaciones();
        }else{
            abrirVentanaCancelaciones();
        }
    }

    public void terminarAdministrador(int flag){
        administradorF.dispose();
        switch (flag){
            case 0: //mostrar autobuses
                abrirVentanaMostrarAutobuses();
                break;
            case 1: //agregar autobus
                abrirVentanaAgregarAutobus();
                break;
            case 2: //agregar horaio
                abrirVentanaAgregarHorario();
                break;
        }
    }

    public void terminarCancelaciones() throws Exception {
        cancelacionesF.dispose();
        abrirVentanaAdHorarios();
    }

    public void terminarOpcionesReservaciones() throws Exception {
        opcionesReservacionesF.dispose();
        abrirVentanaAdHorarios();
    }

    public void terminarConfCancelaciones() throws Exception {
        confCancelacionesF.dispose();
        abrirVentanaAdHorarios();
    }

    public void terminarMostrarAutobuses(){
        mostrarAutobusesF.dispose();
        abrirVentanaAdministrador();
    }

    public void terminarAgregarAutobus(){
        agregarAutobusF.dispose();
        abrirVentanaAdministrador();
    }

    public void terminarAgregarHorario(){
        agregarHorarioF.dispose();
        abrirVentanaAdministrador();
    }

    public void login(String correo, String contrasena) throws Exception {
        usuario = correo;
        if (correo.equals("admin")){
            admin = true;
        }
        int flag = ModLogin.ingresar(correo, contrasena);
        switch (flag){
            case 0:
                terminarLogin();
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

    public boolean reservar(Horario horario, Date fecha) throws Exception {
        Reservacion reservacion = new Reservacion(horario, faker.crypto().md5(), fecha);
        return ModReservacion.reservar(reservacion, usuario);
    }

    public void cancelar(String claveReservacion) throws Exception {
        ModCancelaciones.cancelar(claveReservacion, usuario);
    }

    public String[] leerHorarios() throws Exception{
        String st[] = null;
        LinkedList<Horario> horarios;
        try
        {
            FileInputStream fis = new FileInputStream("horarios.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            horarios = (LinkedList<Horario>) ois.readObject();
            Iterator it = horarios.iterator();
            st = new String[horarios.size()+1];
            int i = 1;
            st[0] = "Horarios disponibles:";
            while(it.hasNext()){
                st[i++] = it.next().toString();
            };
            ois.close();

        }
        catch (EOFException e) {
            System.out.println("no hay nada we");
        }
        return st;
        //mostrar horarios y opciones de reservacion y cancelación
    }

    public String[] leerCiudades(){
        String st[] = null;
        try
        {
            FileInputStream fis = new FileInputStream("ciudades.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            st = (String[]) ois.readObject();
            ois.close();

        }
        catch (EOFException e) {
            System.out.println("no hay nada we");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return st;
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

    public void agregarAutobus(Autobus autobus){

        LinkedList<Autobus> autobuses;
        try
        {
            FileInputStream fis = new FileInputStream("autobuses.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            autobuses = (LinkedList<Autobus>) ois.readObject();
            autobuses.add(autobus);
            ois.close();

            FileOutputStream fs = new FileOutputStream("autobuses.txt", false);
            ObjectOutputStream ob = new ObjectOutputStream(fs);
            ob.writeObject(autobuses);
            ob.close();
        }
        catch (EOFException e) {
            System.out.println("no hay nada we");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void agregarHorario(String origen, String destino, Date horaSalida){

        float duracion = (float) faker.number().randomDouble(2, 1, 30);

        int minutos = (int)((duracion - (int) duracion)*60);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        String horaS = simpleDateFormat.format(horaSalida);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, faker.number().numberBetween(0, 24));
        calendar.add(Calendar.MINUTE, faker.number().numberBetween(0, 60));
        horaSalida = calendar.getTime();
        String horaL = simpleDateFormat.format(horaSalida);

        Horario horario = new Horario(new Ruta(origen, destino), horaS, duracion, horaL);
        LinkedList<Horario> horarios;
        try
        {
            FileInputStream fis = new FileInputStream("horarios.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            horarios = (LinkedList<Horario>) ois.readObject();
            horarios.add(horario);
            ois.close();

            FileOutputStream fs = new FileOutputStream("horarios.txt", false);
            ObjectOutputStream ob = new ObjectOutputStream(fs);
            ob.writeObject(horario);
            ob.close();
        }
        catch (EOFException e) {
            System.out.println("no hay nada we");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
