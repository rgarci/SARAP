package Mod;

import java.io.*;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

public class ModCancelaciones {
    public static boolean cancelar(String claveRservacion, String correo) throws Exception{

        File file = new File("reservaciones.txt");
        try {
            Hashtable<String, LinkedList<String>> reservaciones;
            FileInputStream fis = new FileInputStream(file);

            ObjectInputStream ois = new ObjectInputStream(fis);
            reservaciones = (Hashtable<String, LinkedList<String>>) ois.readObject();
            ois.close();
            if (reservaciones.containsKey(correo)) {
                LinkedList<String> claves = reservaciones.get(correo);
                Iterator iterator = claves.iterator();
                Boolean flag = false;
                Reservacion reservacionAux = null;
                while(iterator.hasNext()){
                    reservacionAux = (Reservacion) iterator.next();
                    if (reservacionAux.getClaveReservacion().equals(claveRservacion)){
                        flag=true;
                        break;
                    }
                }

                if (flag){
                    claves.remove(reservacionAux);
                    reservaciones.remove(correo);
                    reservaciones.put(correo, claves);
                    FileOutputStream fs = new FileOutputStream(file, false);
                    ObjectOutputStream ob = new ObjectOutputStream(fs);
                    ob.writeObject(reservaciones);
                    ob.close();
                    System.out.println("Se eliminó la reservación");
                    return true;

                }else{
                    System.out.println("El usuario no tiene esa reservación");
                }

            }else{
                System.out.println("no existen reservaciones a ese nombre");
            }

        }catch(EOFException e){
            System.out.println("no existen reservaciones a ese nombre");
            System.out.println("El archivo estaba vacío");
        }catch(IOException e1){
            e1.printStackTrace();
        }
        return false;
    }

}
