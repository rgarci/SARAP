import com.github.javafaker.Faker;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

/**
 * Clase main
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Con con = Con.getSingletonInstance();
        /*File filec = new File("ciudades.txt");
        filec.delete();
        filec.createNewFile();
        File file = new File("horarios.txt");
        file.delete();
        file.createNewFile();
        File filea = new File("autobuses.txt");
        filea.delete();
        filea.createNewFile();


        String origen[] = new String[20];
        String destino[] = new String[20];
        LinkedList<Horario> hs = new LinkedList<Horario>();
        Faker faker = new Faker(new Locale("es", "MX"));
        for (int i = 0; i < 20; i++) {

            origen[i] = faker.address().cityName();
            destino[i] = origen[i];
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 20; j++) {
                for (int k = 0; k < 20; k++) {
                    if (j!=k) {
                        hs.add(con.generarHorario(origen[j], destino[k]));
                    }
                }
            }
        }
        FileOutputStream fsc = new FileOutputStream(filec, false);
        ObjectOutputStream obc = new ObjectOutputStream(fsc);
        obc.writeObject(origen);
        obc.close();

        FileOutputStream fs = new FileOutputStream(file, false);
        ObjectOutputStream ob = new ObjectOutputStream(fs);
        ob.writeObject(hs);
        ob.close();
        LinkedList<Autobus> autobuses = new LinkedList<Autobus>();
        faker = new Faker();
        for (int i = 0; i < 100; i++) {

            Autobus autobus = new Autobus(faker.name().lastName(), faker.number().digits(3), faker.number().digits(5), 50);
            autobuses.add(autobus);
        }

        FileOutputStream fsa = new FileOutputStream(filea, false);
        ObjectOutputStream oba = new ObjectOutputStream(fsa);
        oba.writeObject(autobuses);
        oba.close();
        try
        {
            FileInputStream fisc = new FileInputStream(filec);
            ObjectInputStream oisc = new ObjectInputStream(fisc);
            origen = (String[]) oisc.readObject();
            System.out.println(origen.length);
            oisc.close();
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            hs = (LinkedList<Horario>) ois.readObject();
            System.out.println(hs.size());
            ois.close();
            FileInputStream fisa = new FileInputStream(filea);
            ObjectInputStream oisa = new ObjectInputStream(fisa);
            autobuses = (LinkedList<Autobus>) oisa.readObject();
            System.out.println(autobuses.size());
            oisa.close();

        }
        catch (EOFException e) {
            System.out.println("no hay nada we");
        }

        File filech = new File("choferes.txt");
        filech.delete();
        filech.createNewFile();

        LinkedList<Chofer> chofers = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, faker.number().numberBetween(-50, -30));
            calendar.add(Calendar.DAY_OF_YEAR, faker.number().numberBetween(0, 365));
            Date date = calendar.getTime();
            Chofer chofer = new Chofer(faker.number().numberBetween(0, 120), faker.name().name(), date);
            chofers.add(chofer);
        }

        FileOutputStream fsac = new FileOutputStream(filech, false);
        ObjectOutputStream obac = new ObjectOutputStream(fsac);
        obac.writeObject(chofers);
        obac.close();

        FileInputStream fiscc = new FileInputStream("choferes.txt");
        ObjectInputStream oiscc = new ObjectInputStream(fiscc);
        chofers = (LinkedList<Chofer>) oiscc.readObject();
        System.out.println(chofers.size());
        oiscc.close();*/

        con.iniciar();
    }
}
