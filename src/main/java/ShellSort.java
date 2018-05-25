/**
 * Clase que ordena una lista de autobuses según un vector criterio
 */
public class ShellSort {
    /**
     * ordena lista de autobuses
     * @param criterio  vector que se ordena para ordenar la lista de autobuses
     * @param list  lista de autobuses para ordenar
     * @return  lista de autobuses ordenada
     */
    public static String[] sort(int criterio, Autobus[] list) {
        String[] crit = new String[list.length];
        switch (criterio){
            case 0: //marca
                for (int i = 0; i < list.length; i++) {
                    crit[i] = list[i].getMarca();
                }
                break;
            case 1: //modelo
                for (int i = 0; i < list.length; i++) {
                    crit[i] = list[i].getModelo();
                }
                break;
            case 2: //placa
                for (int i = 0; i < list.length; i++) {
                    crit[i] = list[i].getPlaca();
                }
                break;
        }
        int inner, outer;
        String temp;
        Autobus tempAl;
        int h = 1;
        while (h <= crit.length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for (outer = h; outer < crit.length; outer++) {
                temp = crit[outer];
                tempAl = list[outer];
                inner = outer;
                while (inner > h - 1 && crit[inner - h].compareTo(temp) >= 0) {
                    crit[inner] = crit[inner - h];
                    list[inner] = list[inner - h];
                    inner -= h;
                }
                crit[inner] = temp;
                list[inner] = tempAl;
            }
            h = (h - 1) / 3;
        }
        for (int i = 0; i < list.length; i++) {
            crit[i] = "Autobus no." + i + " " + list[i].toString();
        }
        return crit;
    }
}
