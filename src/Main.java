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
        while(Monitor.getDineroBanca()!=0 && Ruleta.getTurno()<1000){//Con 10000 al tener poco dinero la banca y no tenere limite de apuestas quiebra la mitad de las veces

            for (Thread hilo:hilos){//Inicializo hilos
                hilo.start();


            }
            for (Thread hilo: hilos){//Termino ejecución hilos
                hilo.join();
            }
            //El sleep tiene que simular 3000 ms en los que se sientan los jugadores revisar en casa

            Ruleta.sortearNumero();
            //Se comprueban los premios
            for(int i=0; i<jugadores.length; i++){
                jugadores[i].setActualizarJugador(true);
                hilos[i] =  new Thread(jugadores[i]);
                hilos[i].start();
            }
            for(Thread hilo: hilos){
                hilo.join();
            }
            for (int i = 0; i < jugadores.length; i++){
                if (jugadores[i].levantarse){
                    if (i<4){
                        jugadores[i] = new Jugador();
                        //System.out.println("NUEVO JUGADOR");
                    }
                    else if (i<8){
                        jugadores[i] = new Jugador("color");
                        //System.out.println("NUEVO JUGADOR");
                    }
                    else {
                        jugadores[i] = new Jugador("martin");
                        //System.out.println("NUEVO JUGADOR");
                    }
                }
                hilos[i] = new Thread(jugadores[i]);


            }
            for (Jugador jugador:jugadores){
                System.out.println("ID: " + jugador.devolverId());
                System.out.println("Dinero jugador: " + jugador.getDineroJugador());
                System.out.println("Apuesta actual: " + jugador.getApuesta());
            }
        }
        System.out.println("El dinero final de la banca es " + Monitor.getDineroBanca());
    }
}
