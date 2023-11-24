package tiqueto.model;

import static tiqueto.EjemploTicketMaster.REPOSICION_ENTRADAS;
import static tiqueto.EjemploTicketMaster.TOTAL_ENTRADAS;

public class PromotoraConciertos extends Thread {

    final WebCompraConciertos webCompra;

    //agregado private int precioEntrada;
    private int precioEntrada;

    public PromotoraConciertos(WebCompraConciertos webCompra) {
        super();
        this.webCompra = webCompra;
    }


   @Override
   public void run() {
       mensajePromotor("¡Inicio de la reposición de entradas!");

       // Espera hasta que no haya más entradas disponibles
       while (webCompra.entradasRestantes() > 0) {
           try {
               sleep(1000); // Puedes ajustar este valor según tus necesidades
               System.out.println("Esperando promotor ");
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
       webCompra.cerrarVenta();
//(webCompra.entradasRestantes() > 0 && webCompra.entradasRestantes() < TOTAL_ENTRADAS)
       // Repone entradas hasta que ya no hay más por reponer o se alcanza el total deseado
       //while (webCompra.entradasRestantes() < REPOSICION_ENTRADAS) {
       while (webCompra.entradasRestantes()==0) {
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
       mensajePromotor("¡Reposición de entradas completa! Venta cerrada.");
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
