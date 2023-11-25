package tiqueto.model;

import java.util.concurrent.ThreadLocalRandom;

import static tiqueto.EjemploTicketMaster.REPOSICION_ENTRADAS;

/**
 * PromotoraConciertos: Es la clase productora del programa
 */
public class PromotoraConciertos extends Thread {

    final WebCompraConciertos webCompra;

    public PromotoraConciertos(WebCompraConciertos webCompra) {
        super();
        this.webCompra = webCompra;
    }


    @Override
    public void run() {
        mensajePromotor("¡Inicio de la reposición de entradas!");

        // Espera hasta que no haya más entradas disponibles
        while (webCompra.entradasRestantes() != 0 && !webCompra.hayEntradas()) {
            if (!webCompra.hayEntradas()) {
                mensajePromotor("Hay que reponer entradas porque se han vendido todas");
                webCompra.reponerEntradas(REPOSICION_ENTRADAS);

                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 8000)); // Simulamos un tiempo de sueño entre 3 y 8 segundos
                    System.out.println("Promotor en espera ");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("El hilo ha sido interrumpido: " + e);
                }
            }
        }
        webCompra.cerrarVenta();
        /*webCompra.cerrarVenta();
//(webCompra.entradasRestantes() > 0 && webCompra.entradasRestantes() < TOTAL_ENTRADAS)
        // Repone entradas hasta que ya no hay más por reponer o se alcanza el total deseado
        //while (webCompra.entradasRestantes() < REPOSICION_ENTRADAS) {
        while (webCompra.entradasRestantes() == 0) {
            int entradasReponer = Math.min(REPOSICION_ENTRADAS, TOTAL_ENTRADAS - webCompra.entradasRestantes());
            int entradasReponidas = webCompra.reponerEntradas(entradasReponer);

            mensajePromotor("Reponiendo " + entradasReponidas + " entradas. Entradas totales disponibles: " + webCompra.entradasRestantes());

            // Aumenta el precio o realiza otras acciones según sea necesario
            // ...

            // Simulamos un tiempo de sueño entre 3 y 8 segundos
            int tiempoSueño = 3000 + (int) (Math.random() * 5000);
            try {
                sleep(tiempoSueño);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Cierra la venta después de reponer todas las entradas o alcanzar el total deseado
        // webCompra.cerrarVenta();
        webCompra.abrirVenta();
        mensajePromotor("¡Reposición de entradas completa! Venta cerrada.");*/
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
