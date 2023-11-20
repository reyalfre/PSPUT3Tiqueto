package tiqueto.model;

import static tiqueto.EjemploTicketMaster.MAX_ENTRADAS_POR_FAN;

public class FanGrupo extends Thread {

    final WebCompraConciertos webCompra;
    int numeroFan;
    public String tabuladores = "\t\t\t\t";
    int entradasCompradas = 0;

    //Agregado: private int preciEntrada;
    private int precioEntrada;

    public FanGrupo(WebCompraConciertos web, int numeroFan) {
        super();
        this.numeroFan = numeroFan;
        this.webCompra = web;
    }

    /*@Override
    public void run() {

        // Comprobamos si hay entradas disponibles
        if (webCompra.hayEntradas()) {

            // Compramos una entrada
            boolean entradasCompradas = webCompra.comprarEntrada();

            // Almacenamos el precio de la entrada
            precioEntrada = webCompra.precioEntrada();

            // Imprimimos el número de entradas compradas y el precio
            mensajeFan("He comprado " + entradasCompradas + " entradas a un precio de " + precioEntrada);

            // Nos quedamos dormidos
            try {
                Thread.sleep((int) (Math.random() * 3 + 1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {

            // Imprimimos que no hay entradas disponibles
            mensajeFan("No hay entradas disponibles");
        }

    }*/
    @Override
    public void run() {
        mensajeFan("¡Estoy emocionado por el concierto!");

        // Intentamos comprar entradas hasta que se cierre la venta o alcancemos el límite
        while (webCompra.hayEntradas() && (entradasCompradas < MAX_ENTRADAS_POR_FAN || webCompra.entradasRestantes() > 0)) {
            // Intentamos comprar una entrada
            if (webCompra.comprarEntrada()) {
                entradasCompradas++;
                mensajeFan("¡Entrada comprada! Total entradas: " + entradasCompradas);

                // Simulamos un tiempo de sueño entre 1 y 3 segundos
                int tiempoSueño = 1000 + (int) (Math.random() * 2000);
                try {
                    sleep(tiempoSueño);
                    System.out.println("Esperando fan "+tiempoSueño);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // Si no se pudo comprar, salimos del bucle
                mensajeFan("No pude comprar entrada. Venta cerrada o no hay entradas disponibles.");
                break;
            }
        }
        mensajeFan("¡Terminé mi compra de entradas!");
    }
        public void dimeEntradasCompradas () {
            mensajeFan("Sólo he conseguido: " + entradasCompradas);
        }

        /**
         * Método a usar para cada impresión por pantalla
         * @param mensaje Mensaje que se quiere lanzar por pantalla
         */
        private void mensajeFan (String mensaje){
            System.out.println(System.currentTimeMillis() + "|" + tabuladores + " Fan " + this.numeroFan + ": " + mensaje);
        }
    }
