package Mod;

import com.github.javafaker.Faker;

import java.io.File;
import Vis.*;
import Con.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        /*Faker faker = new Faker();
        File file = new File("reservaciones.txt");
        file.delete();
        file.createNewFile();
        Usuario usuario = new Usuario("Rosi", "chuchis", "rgarci@gmails.com");
        DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, faker.number().numberBetween(0, 24));
        calendar.add(Calendar.MINUTE, faker.number().numberBetween(0, 60));
        Date date = calendar.getTime();
        String hora =dateFormat.format(date);
        float duracion = (float) 14.5;
        int minutos = (int)((duracion - (int) duracion)*60);
        calendar.add(Calendar.HOUR_OF_DAY, (int) duracion);
        calendar.add(Calendar.MINUTE, minutos);
        date = calendar.getTime();
        String horaLlegada = dateFormat.format(date);

        Horario horario = new Horario(new Ruta("Mérida", "Cancún"), hora, duracion, new Autobus("Mazda", "Mazda3", "123yuc", 35), horaLlegada, "10/04/2018");

        String claveReservacion = String.valueOf(faker.number().digits(7));
        Reservacion reservacion  new Reservacion(horario, claveReservacion);

        ModReservacion.reservar(reservacion, usuario);
        ModReservacion.consultarReservacion(usuario);
        ModCancelaciones.cancelar(claveReservacion, usuario);
        ModReservacion.consultarReservacion(usuario);
*/
        /*File file = new File("usuarios.txt");
        file.delete();
        file.createNewFile();
        Usuario usuario = new Usuario("Rosi", "chuchis", "rgarci@gmails.com");
        RegistroCliente.registrar(usuario);
        RegistroCliente.registrar(usuario);
        ModLogin.ingresar("rgarci@gmails.com", "chuchis");
        ModLogin.ingresar("rgarci@gmail.com", "chuchis");*/

        Con con = Con.getSingletonInstance();
        con.iniciar();
    }
}
