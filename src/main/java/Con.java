import com.github.javafaker.Faker;

import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Clase Controlador, contiene los métodos que enlazan la vista y el modelo
 * @author rosaura & hilda
 * @version 1.0
 */
public class Con {
    /**
     * Con variable a la que se le genera una sola instancia
     */
    private static Con con;
    /**
     * correo con el que se accesó al sistema
     */
    private String usuario;
    /**
     * GUI Login
     */
    private Login loginF;
    /**
     * GUI Resgistro
     */
    private Registro resgitroF;
    /**
     * GUI administrar Horarios
     */
    private AdmHorarios adHorariosF;
    /**
     * GUI Reservaciones
     */
    private Reservaciones reservacionesF;
    /**
     * GUI Cancelaciones
     */
    private Cancelaciones cancelacionesF;
    /**
     * GUI Confirmar cancelaciones
     */
    private ConfirmCancelaciones confCancelacionesF;
    /**
     * Faker, generador de datos aleatorios
     */
    private  Faker faker = new Faker(new Locale("es", "MX"));
    /**
     * GUI Opciones Reservaciones
     */
    private OpcionesReservaciones opcionesReservacionesF;
    /**
     * boleano que indica si la persona que ingresó es administrador
     */
    private boolean admin = false;
    /**
     * GUI de Administrador
     */
    private Administrador administradorF;
    /**
     * GUI mostrara autobuses
     */
    private MostrarAutobuses mostrarAutobusesF;
    /**
     * GUI Agregar Autobus
     */
    private AgregarAutobus agregarAutobusF;
    /**
     * GUI Agregar Horario
     */
    private AgregarHorario agregarHorarioF;
    /**
     * GUI agregar chofer
     */
    private AgregarChofer agregarChoferF;

    /**
     * Constructor privado para no hacer nuevas instancias
     */
    private Con() {
    }

    /**
     * Método que regresa la única instancia creada de la clase Con
     * @return  con
     */
    public static Con getSingletonInstance() {
        if (con == null){
            con = new Con();
        }
        return con;
    }

    /**
     * Inicializa el programa, abre la ventana de inicio
     * @throws IOException
     */
    public void iniciar() throws IOException {
        File fileUsuarios = new File("usuarios.txt");
        //fileUsuarios.delete();
        fileUsuarios.createNewFile();
        File fileReservaciones = new File("reservaciones.txt");
        //fileReservaciones.delete();
        fileReservaciones.createNewFile();
        abrirVentanaInicio();
    }

    /**
     * Abre la GUI de la ventana de inicio
     */
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

    /**
     * Abre la GUI de la ventana de Registro
     */
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
    /**
     * Abre la GUI de la ventana de Administrar Horarios
     */
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

    /**
     * Abre la GUI de la ventana de Administrador
     */
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
    /**
     * Abre la GUI de la ventana de Reservaciones
     */
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
    /**
     * Abre la GUI de la ventana de Cancelaciones
     */
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
    /**
     * Abre la GUI de la ventana de Confirmar Cancelación
     * @param reservacion reservación que será cancelada
     */
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
    /**
     * Abre la GUI de la ventana de Opciones de reservaciones
     * @param ciudadOrigen ciudad origen de la ruta elegida.
     * @param ciudadDestino ciudad destino de la ruta elegida
     * @param fecha fecha del viaje
     */
    public void abrirVentanaOpcionesReservaciones(String ciudadOrigen, String ciudadDestino, final Date fecha, boolean redondo) throws Exception{
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
            System.out.println("archivo vacío");
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                opcionesReservacionesF = new OpcionesReservaciones(st, fecha, redondo);
                opcionesReservacionesF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                opcionesReservacionesF.getContentPane().add(new OpcionesReservaciones(st, fecha, redondo).getter());
                opcionesReservacionesF.pack();
                opcionesReservacionesF.setVisible(true);
            }
        });
    }
    /**
     * Abre la GUI de la ventana de Mostrar autobuses
     * Lee el objeto del archivo autobuses.txt y los muestra
     */
    public void abrirVentanaMostrarAutobuses(){

        administradorF.dispose();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    mostrarAutobusesF = new MostrarAutobuses(leerAutobuses());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mostrarAutobusesF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                try {
                    mostrarAutobusesF.getContentPane().add(new MostrarAutobuses(leerAutobuses()).getter());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mostrarAutobusesF.pack();
                mostrarAutobusesF.setVisible(true);
            }
        });
    }

    /**
     * Abre la GUI de la ventana de agregar chofer
     * Lee el objeto del archivo choferes.txt y los muestra
     */
    public void abrirVentanaAgregarChofer(){
        administradorF.dispose();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                agregarChoferF = new AgregarChofer(leerChoferes());
                agregarChoferF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                agregarChoferF.getContentPane().add(new AgregarChofer(leerChoferes()).getter());
                agregarChoferF.pack();
                agregarChoferF.setVisible(true);
            }
        });
    }
    /**
     * Abre la GUI de la ventana de Agregar autobús
     */
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
    /**
     * Abre la GUI de la ventana de Agregar horario
     */
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
    /**
     * Envía un usuario a registrarse en el método registrar(user) de RegistroCliente donde se realizan las validaciones del registro
     * @param nombre nombre del usuario
     * @param contrasena constraseña del usuario
     * @param correo correo del usuario
     */
    public void registrar(String nombre, String contrasena, String correo) throws Exception {
        Usuario user = new Usuario(nombre, contrasena, correo);
        RegistroCliente.registrar(user);
    }

    /**
     * cierra la ventana Login y dependiendo del usar abre la ventana administrador o usuario
     * @throws Exception
     */
    public void terminarLogin() throws Exception {
        loginF.dispose();
        if (admin){
            abrirVentanaAdministrador();
        }else {
            abrirVentanaAdHorarios();
        }
    }
    /**
     * cierra la ventana Registro y abre la ventana inicio
     * @throws Exception
     */
    public void terminarRegistro(){
        resgitroF.dispose();
        abrirVentanaInicio();
    }
    /**
     * cierra la ventana adHorarios y abre la ventana reservaciones o cancelaciones según elija el usuario
     * @throws Exception
     */
    public void terminarAdHorarios(boolean res){
        adHorariosF.dispose();
        if (res){
            abrirVentanaReservaciones();
        }else{
            abrirVentanaCancelaciones();
        }
    }
    /**
     * cierra la ventana Administrador y abre la ventana indicada por el usuario
     * @throws Exception
     */
    public void terminarAdministrador(int flag) throws Exception {
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
            case 3: //usuario
                abrirVentanaAdHorarios();
                break;
            case 4:
                abrirVentanaAgregarChofer();
                break;
        }
    }
    /**
     * cierra la ventana Cancelaciones y abre la ventana adHorarios
     * @throws Exception
     */
    public void terminarCancelaciones() throws Exception {
        cancelacionesF.dispose();
        abrirVentanaAdHorarios();
    }
    /**
     * cierra la ventana Opciones reservaciones y abre la ventana adHorarios
     * @throws Exception
     */
    public void terminarOpcionesReservaciones(boolean redondo) throws Exception {

        if (!redondo) {
            opcionesReservacionesF.dispose();
            abrirVentanaAdHorarios();
        }else{
            opcionesReservacionesF.dispose();
        }
    }
    /**
     * cierra la ventana Confirmar cancelaciones y abre la ventana adHorarios
     * @throws Exception
     */
    public void terminarConfCancelaciones() throws Exception {
        confCancelacionesF.dispose();
        abrirVentanaAdHorarios();
    }
    /**
     * cierra la ventana mostrar autobuses y abre la ventana administrador
     */
    public void terminarMostrarAutobuses(){
        mostrarAutobusesF.dispose();
        abrirVentanaAdministrador();
    }
    /**
     * cierra la ventana agregar chofer y abre la ventana administrador
     */
    public void terminarAgregarChofer(){
        agregarChoferF.dispose();
        abrirVentanaAdministrador();
    }
    /**
     * cierra la ventana agregar autobús y abre la ventana administrador
     */
    public void terminarAgregarAutobus(){
        agregarAutobusF.dispose();
        abrirVentanaAdministrador();
    }

    /**
     * cierra la ventana agregar horario y abre la ventana administrador
     */
    public void terminarAgregarHorario(){
        agregarHorarioF.dispose();
        abrirVentanaAdministrador();
    }
    /**
     * hace las validaciones de un usuario para que ingrese al sistema, declara el valor de usuario
     * @param correo correo del usuario que quiere ingresar
     * @param contrasena contraseña del usuario que quiere ingresar
     * @throws Exception
     */
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
        //cerrar login
        //abrir reservaciones
    }

    /**
     * agrega una reservación al archivo reservaciones.txt
     * @param horario   horario del viaje a reservar
     * @param fecha fecha del viaje a reservar
     * @return  si se pudo o no hacer la reservación
     * @throws Exception
     */
    public boolean reservar(Horario horario, Date fecha) throws Exception {
        Reservacion reservacion = new Reservacion(horario, faker.crypto().md5(), fecha);
        return ModReservacion.reservar(reservacion, usuario);
    }

    /**
     * cancela una reservación eliminándola del archivo reservaciones.txt, solo puede ser del usuario actual
     * @param claveReservacion  clave de reservación del viaje
     * @throws Exception
     */
    public void cancelar(String claveReservacion) throws Exception {
        ModCancelaciones.cancelar(claveReservacion, usuario);
    }

    /**
     * lee los horarios del archivo horarios.txt
     * @return  un vector de String con todos los toString de los horarios
     * @throws Exception
     */
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
            System.out.println("archivo vacío");
        }
        return st;
    }

    /**
     * lee los horarios del archivo horarios.txt
     * @return  un vector de String con todos los toString de los horarios
     * @throws Exception
     */
    public Autobus[] leerAutobuses() throws Exception{
        Autobus st[] = null;
        LinkedList<Autobus> autobuses;
        try
        {
            FileInputStream fis = new FileInputStream("autobuses.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            autobuses = (LinkedList<Autobus>) ois.readObject();
            st = new Autobus[autobuses.size()];
            for (int i = 0; i < autobuses.size(); i++) {
                st[i] = autobuses.get(i);
            }
            ois.close();

        }
        catch (EOFException e) {
            System.out.println("archivo vacío");
        }
        return st;
        //mostrar autobuses
    }
    /**
     * Lee las ciudades del archivo ciudades.txt
     * @return  un vector con los nombres de las ciudades
     */
    public String[] leerCiudades(){
        String st[] = null;
        try
        {
            FileInputStream fis = new FileInputStream("ciudades.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            st = (String[]) ois.readObject();
            System.out.println(st.length);
            ois.close();

        }
        catch (EOFException e) {
            System.out.println("archivo vacío");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return st;
    }

    /**
     * Lee los choferes del archivo choferes.txt
     * @return  un vector con los nombres de las ciudades
     */
    public String[] leerChoferes(){
        String st[] = null;
        LinkedList<Chofer> chofers;
        try
        {
            FileInputStream fis = new FileInputStream("choferes.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            chofers = (LinkedList<Chofer>) ois.readObject();
            st = new String[chofers.size()];
            for (int i = 0; i < chofers.size(); i++) {
                st[i] = chofers.get(i).toString();
            }
            ois.close();

        }
        catch (EOFException e) {
            System.out.println("archivo vacío");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return st;
    }

    /**
     * crea una cadena con las reservaciones hechas por el usuario que se pueden cancelar
     * @return  cadena de reservaciones hechas por el usuario
     * @throws Exception
     */
    public String mostrarCancelaciones() throws Exception {
        try {
            String can = "Usted puede cancelar las siguientes reservaciones:\n";
            can += ModReservacion.consultarReservacion(usuario);
            return can;
        }catch(EOFException np){
            return null;
        }
    }

    /**
     * determina si una reservación existe dada una clave de reservación
     * @param clave clave de la reservación por encontrar
     * @return  booleano que determina si la reservación existe
     * @throws Exception
     */
    public Reservacion encontrarReservacion(String clave) throws Exception{
        return ModReservacion.encontrarReservacion(clave, usuario);
    }

    /**
     * Determina si el usuario ingresado tiene la contraseña ingresada
     * @param contrasena
     * @return  boleano que indica si la contraseña es válida para el usuario
     * @throws Exception
     */
    public boolean comprobarContrasena(String contrasena) throws Exception {
        return RegistroCliente.validarContrasena(usuario, contrasena);
    }

    /**
     * Agrega un autobus al archivo autobuses.txt
     * @param autobus   nuevo autobús para agregar al registro
     */
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
            System.out.println("archivo vacío");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * agrega un horario al archivo horarios.txt
     * @param origen    ciudad de origen de la ruta del horario
     * @param destino   ciudad destino de la ruta
     * @param horaSalida    hora de salida del autobús
     */
    public void agregarHorario(String origen, String destino, Date horaSalida){

        float duracion = (float) faker.number().randomDouble(2, 1, 30);

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
            ob.writeObject(horarios);
            ob.close();
        }
        catch (EOFException e) {
            System.out.println("archivo vacío");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * agrega un nuevo chofer al archivo choferes.txt
     * @param chofer    chofer a agregar
     */
    public void agregarChofer(Chofer chofer){
        LinkedList<Chofer> chofers;
        try
        {
            FileInputStream fis = new FileInputStream("choferes.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            chofers = (LinkedList<Chofer>) ois.readObject();
            chofers.add(chofer);
            ois.close();

            FileOutputStream fs = new FileOutputStream("choferes.txt", false);
            ObjectOutputStream ob = new ObjectOutputStream(fs);
            ob.writeObject(chofers);
            ob.close();
        }
        catch (EOFException e) {
            System.out.println("archivo vacío");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Horario generarHorario(String ciudadOrigen, String ciudadDestino){
        Ruta ruta = new Ruta(ciudadOrigen, ciudadDestino);

        for (int i = 0; i < faker.number().numberBetween(1, 5); i++){
            ruta.addRutaIntermedia(faker.address().cityName());
        }
        String placaAutobus = faker.idNumber().valid();
        int noAsientos = faker.number().numberBetween(20, 50);

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

        Horario horario = new Horario(ruta, horaSalida, duracion, horaLlegada);
        horario.setDisponibilidad(noAsientos - faker.number().numberBetween(0, noAsientos));
        return horario;
    }
}
