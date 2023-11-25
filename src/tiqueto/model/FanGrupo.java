package tiqueto.model;

import tiqueto.EjemploTicketMaster;

/**
 * @version 1.0
 * @author Alfredo Rafael Maldonado Pertuz
 * @since 20/11/2023
 * Clase FanGrupo: Es la clase consumidora del programa
 */
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

    /**
     * Método run: El fan comprará entradas siempre y cuando haya disponibilidad de entradas
     */
    @Override
    public void run() {
        mensajeFan("¡Estoy emocionado por el concierto!");

        // Intentamos comprar entradas hasta que se cierre la venta o alcancemos el límite
        while (!webCompra.isVentaCerrada) {
            if (entradasCompradas != EjemploTicketMaster.MAX_ENTRADAS_POR_FAN) {
                mensajeFan("Voy a intentar comprar una entrada... ¡Espero que no me la quiten!");

                // If cuando se compran entradas
                if (webCompra.comprarEntrada()) {
                    entradasCompradas++;
                    mensajeFan("¡Entrada comprada! Total entradas que llevo: " + entradasCompradas);

                    // Simulamos un tiempo de sueño entre 1 y 3 segundos
                    int tiempoSueño = 1000 + (int) (Math.random() * 2000);
                    try {
                        sleep(tiempoSueño);
                        System.out.println("Fan durmiendo de 1 a 3 segundos: " + tiempoSueño + "ms en total. ");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("El hilo ha sido interrumpido: " + e);
                    }
                }
            }
        }
        mensajeFan("¡Terminé mi compra de entradas!");
    }

    /**
     * Método para saber cuantas entradas a conseguido cada fan.
     * El mensaje aparecerá una vez que haya finalizado
     */
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
