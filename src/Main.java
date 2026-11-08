public class Main {
    public static void main(String[] args) throws InterruptedException {
        Jugador[] jugadores = new Jugador[12];
        Thread[] hilos = new Thread[12];
        for (int i = 0; i < jugadores.length; i++){
            if(i<4){
                jugadores[i] = new Jugador();
            }
            else if(i<8){
                jugadores[i] = new Jugador("color");

            }
            else{
                jugadores[i] = new Jugador("martin");

            }
            hilos[i] = new Thread(jugadores[i]);

        }
        while(Monitor.getDineroBanca()!=0 && Ruleta.getTurno()<10000){//Con 10000 al tener poco dinero la banca y no tenere limite de apuestas quiebra la mitad de las veces
            for (int i = 0; i < jugadores.length; i++){
                if (jugadores[i].levantarse){
                    if (i<4){
                        jugadores[i] = new Jugador();
                    }
                    else if (i<8){
                        jugadores[i] = new Jugador("color");
                    }
                    else {
                        jugadores[i] = new Jugador("martin");
                    }
                }
                hilos[i] = new Thread(jugadores[i]);


            }
            for (Thread hilo:hilos){
                hilo.start();


            }
            for (Thread hilo: hilos){
                hilo.join();
            }
            Ruleta.sortearNumero();

            for (Jugador jugador:jugadores){
                System.out.println("ID: " + jugador.devolverId());
                System.out.println("Dinero jugador: " + jugador.getDineroJugador());
                System.out.println("Apuesta actual: " + jugador.getApuesta());
            }
        }
        System.out.println("El dinero final de la banca es " + Monitor.getDineroBanca());
    }
}
