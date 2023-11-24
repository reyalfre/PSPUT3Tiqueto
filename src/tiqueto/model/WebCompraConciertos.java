package tiqueto.model;

import tiqueto.EjemploTicketMaster;
import tiqueto.IOperacionesWeb;

public class WebCompraConciertos implements IOperacionesWeb {
    //La tengo que borrar
    // private int REPOSICION_ENTRADAS = 0;
    private int entradasDisponibles = 0;
    private int entradasVendidas = 0;

    // private boolean ventaAbierta;
    public boolean isVentaCerrada = false;


    /*public WebCompraConciertos(int totalEntradas) {
        this.entradasDisponibles = totalEntradas;
        this.ventaAbierta = true;

        //super();
    }*/

    public WebCompraConciertos() {
        super();
    }

    @Override
    public synchronized boolean comprarEntrada() {
        if (hayEntradas()) {
            // Lógica para realizar la compra de la entrada
            // ...
            entradasVendidas++;
            entradasDisponibles--;
            //He cambiado entrdasDisponibles por entradasRestantes()
            mensajeWeb("¡Entrada comprada! Quedan: " + entradasRestantes()+" entradas totales por vender.");
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


    @Override
    public synchronized int reponerEntradas(int numeroEntradas) {
        //Lógica para reponer las entradas
        // ...
		/*int entradasReponer = Math.min(numeroEntradas, REPOSICION_ENTRADAS);
		entradasDisponibles += numeroEntradas;
		mensajeWeb("Reponiendo "+numeroEntradas+ " entradas. Entradas totales disponibles: "+entradasDisponibles);
		return entradasReponer;*/
        notifyAll();
        if (entradasVendidas + entradasDisponibles + numeroEntradas > EjemploTicketMaster.TOTAL_ENTRADAS) {
          //  mensajeWeb("Reponiendo " + numeroEntradas + " entradas. Entradas totales disponibles: " + entradasDisponibles);
            return entradasDisponibles += EjemploTicketMaster.TOTAL_ENTRADAS - (entradasDisponibles + entradasVendidas);

        } else {
            return this.entradasDisponibles += numeroEntradas;
        }


        //return 0;
        //return numeroEntradas;
    }


    @Override
    public synchronized void cerrarVenta() {
        isVentaCerrada = true;
        notifyAll();
        mensajeWeb("Venta cerrada. No se pueden comprar más entradas.");
    }
/*
    //Creado por mí
    public synchronized void abrirVenta() {
        ventaAbierta = true;
        mensajeWeb("Venta abierta. Se pueden comprar más entradas.");
    }*/


    @Override
    public synchronized boolean hayEntradas() {
        //return ventaAbierta && entradasDisponibles > 0;
        return entradasDisponibles > 0;
    }


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
