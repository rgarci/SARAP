import java.io.*;
import java.util.*;

/**
 * Clase que modela las acciones que se pueden realizar con una reservación
 */
public class ModReservacion {
    /**
     * crea una nueva reservación y la almacena en el archivo reservaciones.txt
     * @param reservacion   nueva reservacion
     * @param correo    usuario que hizo la reservacion
     * @return  true si se pudo hacer el registro correctamente
     * @throws Exception
     */
    public static boolean reservar(Reservacion reservacion, String correo) throws Exception{

        File file = new File("reservaciones.txt");
        try {
            Hashtable<String, LinkedList<Reservacion>> reservaciones;
            FileInputStream fis = new FileInputStream(file);

            ObjectInputStream ois = new ObjectInputStream(fis);
            reservaciones = (Hashtable<String, LinkedList<Reservacion>>) ois.readObject();
            ois.close();
            if (reservaciones.containsKey(correo)) {
                LinkedList<Reservacion> reservacions = reservaciones.get(correo);
                Iterator iterator = reservacions.iterator();
                Boolean flag = true;
                while(iterator.hasNext()){
                    Reservacion reservacionAux = (Reservacion) iterator.next();
                    if (reservacionAux.getClaveReservacion().equals(reservacion.getClaveReservacion())){
                        flag=false;
                        break;
                    }
                }

                if (flag){
                    reservacions.add(reservacion);
                    reservaciones.put(correo, reservacions);
                    FileOutputStream fs = new FileOutputStream(file, false);
                    ObjectOutputStream ob = new ObjectOutputStream(fs);
                    ob.writeObject(reservaciones);
                    ob.close();
                    return true;
                }else{
                    return false;
                }

            }else{
                LinkedList<Reservacion> reservacions = new LinkedList<Reservacion>();
                reservacions.add(reservacion);
                reservaciones.put(correo, reservacions);
                FileOutputStream fs = new FileOutputStream(file, false);
                ObjectOutputStream ob = new ObjectOutputStream(fs);
                ob.writeObject(reservaciones);
                ob.close();
                return true;
            }

        }catch(EOFException e){
            Hashtable<String, LinkedList<Reservacion>> reservaciones = new Hashtable<String, LinkedList<Reservacion>>();
            LinkedList<Reservacion> reservacions = new LinkedList<Reservacion>();
            reservacions.add(reservacion);
            reservaciones.put(correo, reservacions);
            FileOutputStream fs = new FileOutputStream(file, false);
            ObjectOutputStream ob = new ObjectOutputStream(fs);
            ob.writeObject(reservaciones);
            return true;
        }catch(IOException e1){
            e1.printStackTrace();
        }
        return false;
    }

    /**
     * Encuentra las reservaciones hechas por un usuario
     * @param correo    correo del usuario para encontrar sus reservaciones
     * @return  un string que concatena las reservaciones del usuario
     * @throws Exception
     */
    public static String consultarReservacion(String correo) throws Exception{
        String st = "";
        Hashtable<String, LinkedList<Reservacion>> reservaciones;
        FileInputStream fis = new FileInputStream("reservaciones.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        try {
            reservaciones = (Hashtable<String, LinkedList<Reservacion>>) ois.readObject();
            ois.close();
            if (reservaciones.containsKey(correo)){
                LinkedList<Reservacion> reservacions =reservaciones.get(correo);
                if (reservacions.size()>0){
                    for (int i = 0; i < reservacions.size(); i++){
                        st += "Destino: " + reservacions.get(i).getHorario().getRuta().getCiudadDestino() + "\n Origen: " + reservacions.get(i).getHorario().getRuta().getCiudadOrigen() + "\nHorario salida: " + reservacions.get(i).getHorario().getHoraSalida() + "\nHora llegada: " + reservacions.get(i).getHorario().getHoraLlegada() + "\nClave de reservación: " + reservacions.get(i).getClaveReservacion() + "\n\n";
                    }
                }else{
                    st += "El usuario no tiene ninguna reservación";
                }
            }else{
                st += "El usuario no tiene ninguna reservación";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return st;
    }

    public static Reservacion encontrarReservacion(String clave, String correo) throws Exception {
        Hashtable<String, LinkedList<Reservacion>> reservaciones;
        FileInputStream fis = new FileInputStream("reservaciones.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        try {
            reservaciones = (Hashtable<String, LinkedList<Reservacion>>) ois.readObject();
            ois.close();
            if (reservaciones.containsKey(correo)){
                LinkedList<Reservacion> reservacions =reservaciones.get(correo);
                Iterator<Reservacion> iterator = reservacions.iterator();
                while(iterator.hasNext()){
                    Reservacion next = iterator.next();
                    if(next.getClaveReservacion().equals(clave)){
                        return next;
                    }
                }
            }else{
                System.out.println("El usuario no tiene ninguna reservación");
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
