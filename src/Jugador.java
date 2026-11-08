public class Jugador extends Thread{
    private static int turno=Ruleta.getTurno();
    private static int ultimoNumeroGanador=Ruleta.getUltimoNumGanador();
    private static int contador = 0;
    private int id;
    boolean levantarse = false;
    private String tipoJugador;
    private String colorApostado;
    private Double apuesta = 10.00;
    private Double dineroJugador = 1000.00;
    private String[] color = {"negro","rojo"};
    private int numApostado=-1;
    private String colorGanador = null;
    public Jugador(){
        contador++;
        this.id = contador;
    }
    public Jugador(String tipo){
        contador++;
        this.id = contador;
        this.tipoJugador=tipo;
    }
    public  void realizarApuestas(){
        if (tipoJugador == null){
            colorApostado = null;
            numApostado = (int)(Math.random()*36+1);
            System.out.println("El jugador "+ this.id + "ha hecho una apuesta normal");
        }
        else if (tipoJugador.equals("color")){
            numApostado = -1;
            colorApostado=color[(int)(Math.random()*2)];
            System.out.println("El jugador "+ this.id + "ha hecho una apuesta a color");

        }
        else if (tipoJugador.equals("martin")){
            colorApostado = null;
            numApostado = (int)(Math.random()*36+1);
            System.out.println("El jugador "+ this.id + "ha hecho una apuesta Martin");

        }
        this.turno++;
    }

    public static int getTurno() {
        return turno;
    }

    public static int getUltimoNumeroGanador() {
        return ultimoNumeroGanador;
    }

    public static int getContador() {
        return contador;
    }


    public String getTipoJugador() {
        return tipoJugador;
    }

    public String getColorApostado() {
        return colorApostado;
    }

    public Double getApuesta() {
        return apuesta;
    }

    public Double getDineroJugador() {
        return dineroJugador;
    }

    public String[] getColor() {
        return color;
    }

    public int getNumApostado() {
        return numApostado;
    }

    public String getColorGanador() {
        return colorGanador;
    }

    public int devolverId() {
        return id;
    }

    public void setDineroJugador(Double dineroJugador) {
        this.dineroJugador = dineroJugador;
    }

    @Override
    public void run() {
        ultimoNumeroGanador = Ruleta.getUltimoNumGanador();
        if (ultimoNumeroGanador==-1 || (numApostado == -1 && colorApostado == null)) {
            realizarApuestas();
        }
        else{
            if (tipoJugador==null){
                if (ultimoNumeroGanador == numApostado){
                    System.out.println("El jugador ha ganado una apuesta a número apostando al: " + numApostado);
                    this.dineroJugador = Monitor.bancaPagaJugador(this.dineroJugador,this.apuesta, null,this.id);
                }
                else{
                    System.out.println("El jugador " + this.id + " ha perdido una apuesta a numero apostando a " + numApostado);
                    this.dineroJugador = Monitor.jugadorPagaBanca(this.dineroJugador,this.apuesta,this.id);
                }
                if (this.apuesta<=this.dineroJugador){
                    realizarApuestas();//Una vez termina de comprobar el turno anterior realiza otra apuesta
                }
                else{
                    System.out.println("El jugador no puede apostar");
                    levantarse = true;
                }
            }
            else if (tipoJugador.equals("color")){
                if (ultimoNumeroGanador == 0){
                    colorGanador = "verde";
                }
                else if(ultimoNumeroGanador%2==0){
                    colorGanador = "rojo";
                }
                else{
                    colorGanador = "negro";
                }
                if (colorGanador.equals(colorApostado)){
                    System.out.println("El jugador " + this.id + " ha ganado una apuesta a color apostando a " + colorApostado);
                    this.dineroJugador = Monitor.bancaPagaJugador(this.dineroJugador,this.apuesta,this.tipoJugador,this.id);
                }
                else{
                    System.out.println("El jugador " + this.id + " ha perdido una apuesta a color apostando a " + colorApostado);
                    this.dineroJugador = Monitor.jugadorPagaBanca(this.dineroJugador,this.apuesta,this.id);
                }
                if (this.apuesta<=this.dineroJugador){
                    realizarApuestas();//Una vez termina de comprobar el turno anterior realiza otra apuesta
                }
                else{
                    System.out.println("El jugador no puede apostar");
                    levantarse=true;
                }

            }
            else if (tipoJugador.equals("martin")){
                if (ultimoNumeroGanador == numApostado){
                    System.out.println("El jugador ha ganado una apuesta a número apostando al: " + numApostado);
                    this.dineroJugador = Monitor.bancaPagaJugador(this.dineroJugador,this.apuesta, null,this.id);
                    this.apuesta = 10.00;
                }
                else{
                    System.out.println("El jugador " + this.id + " ha perdido una apuesta a numero apostando a " + numApostado);
                    this.dineroJugador = Monitor.jugadorPagaBanca(this.dineroJugador,this.apuesta,this.id);
                    this.apuesta = this.apuesta*2;
                }
                if (this.apuesta<=this.dineroJugador){
                    realizarApuestas();//Una vez termina de comprobar el turno anterior realiza otra apuesta
                }
                else{
                    System.out.println("El jugador no puede apostar");
                    levantarse = true;
                }
            }

        }
    }


}
