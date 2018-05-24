package Mod;

import com.github.javafaker.Faker;

import java.io.*;

import Vis.*;
import Con.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Con con = Con.getSingletonInstance();
        File filec = new File("ciudades.txt");
        filec.delete();
        filec.createNewFile();
        File file = new File("horarios.txt");
        file.delete();
        file.createNewFile();
        File filea = new File("autobuses.txt");
        filea.delete();
        filea.createNewFile();
        Faker faker = new Faker(new Locale("es", "MX"));

        String origen[] = new String[20];
        String destino[] = new String[20];
        LinkedList<Horario> hs = new LinkedList<Horario>();
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
            System.out.println(autobuses);
            oisa.close();

        }
        catch (EOFException e) {
            System.out.println("no hay nada we");
        }

        con.iniciar();
    }
}
