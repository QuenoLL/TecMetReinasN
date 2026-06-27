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
		System.out.println("Total de soluciones encontradas: " + soluciones.size());
	}
	
	private static void funcion(Estado estado) {
		if(esEstadoObjetivo(estado)) {
			//printeo solucion
			estado.soluciones.add(estado.matrizN.clone());
			imprimirTablero(estado); //me falta el imprimir tablero
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
		List<Estado> sucesores = new ArrayList<>();
		int proximaFila = estado.actualF + 1; // La fila en la que intentaremos poner la reina
		
		// Probamos colocar la reina en cada una de las columnas (de 0 a n-1)
		for (int col = 0; col < estado.n; col++) {
			if (esPosicionValida(estado.matrizN, proximaFila, col)) {
				int[] nuevaMatriz = estado.matrizN.clone();
				nuevaMatriz[proximaFila] = col; // Colocamos la reina (queen)
				
				// Creamos el nuevo estado sucesor
				Estado sucesor = new Estado(
					nuevaMatriz, 
					proximaFila, 
					estado.contadorReinas + 1, 
					estado.n, 
					estado.soluciones
				);
				sucesores.add(sucesor);
			}
		}
		return sucesores;
	}

	private static boolean esPosicionValida(int[] matrizN, int filaActual, int colActual) {
		// Revisamos todas las filas que ya tienen reinas colocadas atrás
		for (int i = 0; i < filaActual; i++) {
			int colReinaPasada = matrizN[i];
			
			// 1. Validar misma columna
			if (colReinaPasada == colActual) {
				return false;
			}
			
			// 2. Validar diagonales
			// Si la distancia en filas es igual a la distancia en columnas, están en la misma diagonal
			// Valor absoluto (abs)
			if (Math.abs(i - filaActual) == Math.abs(colReinaPasada - colActual)) {
				return false;
			}
		}
		//retorna verdadero si es valida la posicion
		return true;
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
