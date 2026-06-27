//Eugenio Cortés - ICCI
//Marcelo Osandon - ICCI
//Lucas Gonzalez - ICCI
package Logica;

import java.util.ArrayList;
import java.util.List;

public class App {
	public static void main(String[] args) {
		int n = 4;
		List<int[]> soluciones = new ArrayList<int[]>();
		Estado e = new Estado(new int[n], -1, 0, n, soluciones);
		funcion(e);
		
	}
	
	private static void funcion(Estado estado) {
		if(esEstadoObjetivo(estado)) {
			///printeo solucion
			return;
		}
		
		for(Estado sucesor : obtenerSucesores(estado)) {
			if(!haSidoExplorado()) {
				funcion(sucesor);
			}
		}
		
	}

	private static boolean haSidoExplorado() {
		return false;//Por el momento que retorne false para acceder a todas las posibles soluciones.
	}

	private static List<Estado> obtenerSucesores(Estado estado) {
		return null;
	}

	private static boolean esEstadoObjetivo(Estado estado) {
		return estado.contadorReinas == estado.n;//Deberia ser el estadoObjetivo si es que ya pusimos las n reinas en el tablero
	}

	public static class Estado{
		int[] matrizN; // Por que int[] solo puede haber una reina por columna asi que mejor procesar asi la matriz.
		int actualF;
		int contadorReinas;
		int n;
		List<int[]> soluciones;
		
		public Estado(int[] matrizN, int actualF, int contadorReinas, int n, List<int[]> soluciones) {
			this.matrizN = matrizN;
			this.actualF = actualF;
			this.contadorReinas = contadorReinas;
			this.n = n;
			this.soluciones = soluciones;
		}
		
		
		
		
		
		
	}

}
