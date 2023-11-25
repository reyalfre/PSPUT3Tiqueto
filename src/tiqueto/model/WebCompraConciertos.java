package tiqueto.model;

import tiqueto.EjemploTicketMaster;
import tiqueto.IOperacionesWeb;

/**
 * @version 1.0
 * @author Alfredo Rafael Maldonado Pertuz
 * @since 20/11/2023
 * WebCompraConciertos: Es el monitor del programa
 */
public class WebCompraConciertos implements IOperacionesWeb {
    private int entradasDisponibles = 0;
    private int entradasVendidas = 0;

    public boolean isVentaCerrada = false;

    public WebCompraConciertos() {
        super();
    }

    /**
     * ComprarEntrada: Método para comprar entradas disponible. Sino hay se espera con wait.
     *
     * @return Devuelve un booleano si la entrada es comprada.
     */
    @Override
    public synchronized boolean comprarEntrada() {
        if (hayEntradas()) {
            // Lógica para realizar la compra de la entrada
            entradasVendidas++;
            entradasDisponibles--;
            mensajeWeb("¡Entrada comprada! Quedan: " + entradasRestantes() + " entradas totales por vender.");
            return true;
        } else {
            try {
                mensajeWeb("No hay entradas disponibles para comprar. ");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted: " + e);
            }
            return false;
        }

    }

    /**
     * ReponerEntradas: Método que sirve para reponer las entradas disponibles hasta el límite del total de entradas
     *
     * @param numeroEntradas Número de entradas a reponer
     * @return
     */
    @Override
    public synchronized int reponerEntradas(int numeroEntradas) {
        //Lógica para reponer las entradas
        notifyAll();
        if (entradasVendidas + entradasDisponibles + numeroEntradas > EjemploTicketMaster.TOTAL_ENTRADAS) {
            mensajeWeb("Reponiendo " + numeroEntradas + " entradas. Entradas totales disponibles: " + entradasDisponibles);
            return entradasDisponibles += EjemploTicketMaster.TOTAL_ENTRADAS - (entradasDisponibles + entradasVendidas);

        } else {
            return this.entradasDisponibles += numeroEntradas;
        }
    }

    /**
     * CerrarVenta: Método que sirve para cerrar la venta de entradas
     */
    @Override
    public synchronized void cerrarVenta() {
        isVentaCerrada = true;
        notifyAll();
        mensajeWeb("Venta cerrada. No se pueden comprar más entradas.");
    }

    /**
     * HayEntradas: Método para saber si hay entradas disponibles
     * @return devuelve las entradas disponibles
     */
    @Override
    public synchronized boolean hayEntradas() {
        //return ventaAbierta && entradasDisponibles > 0;
        return entradasDisponibles > 0;
    }

    /**
     * EntradasRestantes: Método para saber las entradas restantes
     * @return devuelve las entradas restantes
     */
    @Override
    public int entradasRestantes() {
        //return entradasDisponibles;
        return EjemploTicketMaster.TOTAL_ENTRADAS - entradasVendidas - entradasDisponibles;
    }


    /**
     * Método a usar para cada impresión por pantalla
     *
     * @param mensaje Mensaje que se quiere lanzar por pantalla
     */
    private void mensajeWeb(String mensaje) {
        System.out.println(System.currentTimeMillis() + "| WebCompra: " + mensaje);

    }

}
