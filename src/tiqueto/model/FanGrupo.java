package tiqueto.model;

import tiqueto.EjemploTicketMaster;

public class FanGrupo extends Thread {

    final WebCompraConciertos webCompra;
    int numeroFan;
    public String tabuladores = "\t\t\t\t";
    int entradasCompradas = 0;

    public FanGrupo(WebCompraConciertos web, int numeroFan) {
        super();
        this.numeroFan = numeroFan;
        this.webCompra = web;
    }

    @Override
    public void run() {
        mensajeFan("¡Estoy emocionado por el concierto!");

        // Intentamos comprar entradas hasta que se cierre la venta o alcancemos el límite
        while (!webCompra.isVentaCerrada) {
            if (entradasCompradas != EjemploTicketMaster.MAX_ENTRADAS_POR_FAN) {
                mensajeFan("Voy a intentar comprar una entrada... ¡Espero que no me la quiten!");

                // Intentamos comprar una entrada
                if (webCompra.comprarEntrada()) {
                    entradasCompradas++;
                    mensajeFan("¡Entrada comprada! Total entradas que llevo: " + entradasCompradas);

                    // Simulamos un tiempo de sueño entre 1 y 3 segundos
                    int tiempoSueño = 1000 + (int) (Math.random() * 2000);
                    try {
                        sleep(tiempoSueño);
                        System.out.println("Fan durmiendo de 1 a 3 segundos " + tiempoSueño);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        // e.printStackTrace();
                        System.out.println("El hilo ha sido interrumpido: " + e);
                    }
                } /*else {
                    // Si no se pudo comprar, salimos del bucle
                    mensajeFan("No pude comprar entrada. Venta cerrada o no hay entradas disponibles.");
                    break;
                }*/
            }
        }
        mensajeFan("¡Terminé mi compra de entradas!");
    }

    public void dimeEntradasCompradas() {
        mensajeFan("Sólo he conseguido: " + entradasCompradas);
    }

    /**
     * Método a usar para cada impresión por pantalla
     *
     * @param mensaje Mensaje que se quiere lanzar por pantalla
     */
    private void mensajeFan(String mensaje) {
        System.out.println(System.currentTimeMillis() + "|" + tabuladores + " Fan " + this.numeroFan + ": " + mensaje);
    }
}
