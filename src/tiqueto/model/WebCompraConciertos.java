package tiqueto.model;

import tiqueto.IOperacionesWeb;

public class WebCompraConciertos implements IOperacionesWeb{
	private int REPOSICION_ENTRADAS = 0;
	private int entradasDisponibles;
	private boolean ventaAbierta;

	
	public WebCompraConciertos(int totalEntradas) {
		this.entradasDisponibles = totalEntradas;
		this.ventaAbierta = true;

		//super();
	}


	@Override
	public synchronized boolean comprarEntrada() {
		if(ventaAbierta && entradasDisponibles > 0){
			// Lógica para realizar la compra de la entrada
			// ...
			entradasDisponibles--;
			mensajeWeb("¡Entrada comprada! Entradas disponible: "+entradasDisponibles);
			return true;
		}else {
			mensajeWeb("No hay entradas disponibles para comprar. ");
		}
		return false;
	}


	@Override
	public synchronized int reponerEntradas(int numeroEntradas) {
		//Lógica para reponer las entradas
		// ...
		int entradasReponer = Math.min(numeroEntradas, REPOSICION_ENTRADAS);
		entradasDisponibles += numeroEntradas;
		mensajeWeb("Reponiendo "+numeroEntradas+ " entradas. Entradas totales disponibles: "+entradasDisponibles);
		//return 0;
		//return numeroEntradas;
		return entradasReponer;
	}


	@Override
	public synchronized void cerrarVenta() {
		ventaAbierta = false;
		mensajeWeb("Venta cerrada. No se pueden comprar más entradas.");
	}
	//Creado por mí
	public synchronized void abrirVenta() {
		ventaAbierta = true;
		mensajeWeb("Venta abierta. Se pueden comprar más entradas.");
	}


	@Override
	public synchronized boolean hayEntradas() {
		return ventaAbierta && entradasDisponibles > 0;
	}


	@Override
	public int entradasRestantes() {
		return entradasDisponibles;
	}


	/**
	 * Método a usar para cada impresión por pantalla
	 * @param mensaje Mensaje que se quiere lanzar por pantalla
	 */
	private void mensajeWeb(String mensaje) {
		System.out.println(System.currentTimeMillis() + "| WebCompra: " + mensaje);
		
	}

}
