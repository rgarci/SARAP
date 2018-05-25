import java.io.*;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Clase que modela las acciones que se pueden realizar con una cancelación
 */
public class ModCancelaciones {
    /**
     * Cancela una reservación existente
     * @param claveRservacion   clave de la reservación que se va a cancelar
     * @param correo    del usuario que está usando el sistema
     * @return  true si la cancelación se realizó con éxito
     * @throws Exception
     */
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
                    return true;

                }

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
