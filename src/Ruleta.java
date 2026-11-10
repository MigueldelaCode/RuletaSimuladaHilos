public class Ruleta extends Thread{
    private static int turno = 0;
    private static int ultimoNumGanador=-1;

    public static void sortearNumero(){
        ultimoNumGanador = (int)(Math.random()*37);
        System.out.println("El número ganador es " + ultimoNumGanador);
        turno++;

    }
    public static int getTurno() {
        return turno;
    }

    public static int getUltimoNumGanador() {
        return ultimoNumGanador;
    }
}
