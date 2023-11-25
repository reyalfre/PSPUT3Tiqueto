package tiqueto.model;

import java.util.concurrent.ThreadLocalRandom;

import static tiqueto.EjemploTicketMaster.REPOSICION_ENTRADAS;

/**
 * @version 1.0
 * @author Alfredo Rafael Maldonado Pertuz
 * @since 20/11/2023
 * PromotoraConciertos: Es la clase productora del programa
 */
public class PromotoraConciertos extends Thread {

    final WebCompraConciertos webCompra;

    public PromotoraConciertos(WebCompraConciertos webCompra) {
        super();
        this.webCompra = webCompra;
    }

    /**
     * Run: Método que sirve para reponer entradas cada vez que se agoten.
     */
    @Override
    public void run() {
        mensajePromotor("¡Inicio de la reposición de entradas!");

        // Espera hasta que no haya más entradas disponibles
        while (webCompra.entradasRestantes() != 0 && !webCompra.hayEntradas()) {
            if (!webCompra.hayEntradas()) {
                mensajePromotor("Hay que reponer entradas porque se han vendido todas");
                webCompra.reponerEntradas(REPOSICION_ENTRADAS);

                try {
                    // Simulamos un tiempo de sueño entre 3 y 8 segundos
                    Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 8000));
                    System.out.println("Promotor en espera ");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("El hilo ha sido interrumpido: " + e);
                }
            }
        }
        webCompra.cerrarVenta();
    }

    /**
     * Método a usar para cada impresión por pantalla
     *
     * @param mensaje Mensaje que se quiere lanzar por pantalla
     */
    private void mensajePromotor(String mensaje) {
        System.out.println(System.currentTimeMillis() + "| Promotora: " + mensaje);

    }
}
