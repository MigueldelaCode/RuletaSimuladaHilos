public class Monitor {
    private static Double dineroBanca = 50000.00;
    public synchronized static Double bancaPagaJugador(Double dineroJugador, Double apuesta, String tipoJugador,int id){
        if (tipoJugador == null ){
            if (dineroBanca >= apuesta*35){
                dineroBanca = dineroBanca - apuesta*35;
                System.out.println("Se ha pagado al jugador " +id +" "+ apuesta*35 + " € correctamente.");
                return dineroJugador + apuesta*35;
            }
            else{
                Double dineroQuiebra = dineroBanca;
                dineroBanca = 0.00;
                System.out.println("La banca a quebrado solo se le pueden pasar al jugador "+ id+" " + dineroQuiebra + " € cuando se le deberían haber pasado " + apuesta*35 + " €");
                return dineroJugador + dineroQuiebra;
            }
        }
        else{
            if (dineroBanca >= apuesta){
                dineroBanca = dineroBanca - apuesta;
                System.out.println("Se ha pagado al jugador " +id +" "+ apuesta + " € correctamente.");
                return dineroJugador + apuesta;
            }
            else{
                Double dineroQuiebra = dineroBanca;
                dineroBanca = 0.00;
                System.out.println("La banca a quebrado solo se le pueden pasar al jugador "+ id+" " + dineroQuiebra + " € cuando se le deberían haber pasado " + apuesta + " €");
                return dineroJugador + dineroQuiebra;
            }

        }

    }
    public synchronized static Double jugadorPagaBanca(Double dineroJugador,Double apuesta,int id){
        dineroBanca = dineroBanca + apuesta;//Se comprobara que el jugador puede seguir pagando en el main despues de actualizar apuesta y asi de paso se le hecha de la lista de participantes
        System.out.println("El jugador con id: " + id + " ha pagado correctamente " + apuesta +" €");
        return dineroJugador - apuesta;
    }

    public static Double getDineroBanca() {
        return dineroBanca;
    }

}
