/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad Icesi (Cali - Colombia)
 * Propuesta de soluci�n laboratorio Unidad 5
 * @author Camilo Barrios - camilo.barrios@correo.icesi.edu.co
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */


package modelo;

import java.util.Random;

public class Buscaminas {


	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel principiante
	 */
	public static final int FILAS_PRINCIPIANTE = 8;

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel intermedio
	 */
	public static final int FILAS_INTERMEDIO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel experto
	 */
	public static final int FILAS_EXPERTO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel principiante
	 */
	public static final int COLUMNAS_PRINCIPIANTE = 8;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel intermedio
	 */
	public static final int COLUMNAS_INTERMEDIO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel experto
	 */
	public static final int COLUMNAS_EXPERTO = 30;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el nivel principiante
	 */
	public static final int PRINCIPIANTE = 1;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el nivel intermedio
	 */
	public static final int  INTERMEDIO = 2;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el nivel experto
	 */
	public static final int EXPERTO = 3;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel principiante
	 */
	public static final int CANTIDAD_MINAS_PRINCIPANTE = 10;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel 
	 */
	public static final int CANTIDAD_MINAS_INTERMEDIO= 40;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel experto
	 */
	public static final int CANTIDAD_MINAS_EXPERTO = 99;

	// -----------------------------------------------------------------
	// Atributos y relaciones
	// -----------------------------------------------------------------

	/**
	 * Relacion que tiene la matriz de casillas
	 */
	private Casilla[][] casillas;

	/**
	 * Atributo que representa el nivel del juego <Solo puede tomar valores PRINCIPIANTE, , EXPERTO>
	 */
	private int nivel;

	/**
	 * Atributo que tiene la cantidad de minas en el tablero
	 */
	private int cantidadMinas;

	/**
	 * Atributo que representa si el usuario perdio al abrir una mina
	 */
	private boolean perdio;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructo de la clase Buscaminas
	 * @param nivel - el nivel seleccionado por el usuario
	 */
	public Buscaminas(int nivel) throws NivelNoExtisteException{
		if(nivel<1 || nivel>3) {
			throw new NivelNoExtisteException("digite un nivel correcto");
		}else {
			perdio = false;
			this.nivel=nivel;
			inicializarPartida();
		}

	}


	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	/**
	 * Se encarga de inicializar los atributos y relaciones de la clase buscaminas a partir del nivel elegido por el usuario
	 */
	private void inicializarPartida() {

		if(nivel == PRINCIPIANTE) {
			casillas = new Casilla[FILAS_PRINCIPIANTE][COLUMNAS_PRINCIPIANTE];
			cantidadMinas = CANTIDAD_MINAS_PRINCIPANTE;
		}
		else if (nivel==INTERMEDIO) {
			casillas = new Casilla[FILAS_INTERMEDIO][COLUMNAS_INTERMEDIO];
			cantidadMinas = CANTIDAD_MINAS_INTERMEDIO;
		}
		else if(nivel==EXPERTO) {
			casillas = new Casilla[FILAS_EXPERTO][COLUMNAS_EXPERTO];
			cantidadMinas = CANTIDAD_MINAS_EXPERTO;
		}
		inicializarCasillasLibres();
		generarMinas();
	}


	/**
	 * Metodo que se encarga de inicializar todas las casillas que no son minas
	 */
	public void inicializarCasillasLibres() {
		for(int i=0;i<casillas.length;i++) {
			for(int j=0;j<casillas[i].length;j++) {
				Casilla c = new Casilla(50);
				casillas[i][j]=c;
			}
		}

	}


	/**
	 * Metodo que permite contar la cantidad de minas que tiene alrededor una casillas
	 * @param i - La fila de la matriz
	 * @param j - la columna de la matriz
	 * @return int - La cantidad de minas que tiene alrededor la casilla [i][j]
	 */
	public int cantidadMinasAlrededor(int i, int j) {
		int count = 0;

        for (int x = -1; x <= 1; ++x) {
            int xIndex = x + i;
            if (xIndex < 0 || xIndex >= casillas.length) {
                continue; //bad coding techniques sorry, also this is for checking if out of bounds.
            }

            for (int y = -1; y <= 1; ++y) {
                int yIndex = y + j;
                if (yIndex < 0 || yIndex >= casillas[0].length) {
                    continue; //bad coding techniques sorry, also this is for checking if out of bounds.
                }

                if (x == 0 && y == 0) {
                    continue; //bad coding techniques sorry, also this is for checking if out of bounds.
                }

                if (casillas[xIndex][yIndex].esMina()) {
                    count++;
                }
            }
        }
        return count;
	}
	/**
	 * M�todo que se encarga de generar aleatoriomente las minas
	 */
	public void generarMinas() {
		int minesPlaced = 0;
	    Random random = new Random();
	    while(minesPlaced < cantidadMinas) {
	    	int x = random.nextInt(casillas.length);
	    	int y = random.nextInt(casillas[0].length);
	    	Casilla ca = new Casilla(100);
	    	if(!casillas[x][y].esMina()){
	    		casillas[x][y]=ca;
		        minesPlaced ++;
	    	}
	    }
	}


	/**
	 * Metodo que se encarga de convertir el tablero a un String para poder verlo en pantalla
	 * @return String - El tablero en formato String
	 */
	public String mostrarTablero() {
		String print ="";
		for(int i=0;i<casillas[0].length;i++) {
			if(i==0) {
				print="   ";
			}
			if(i<9)
				print+=" "+(i+1)+" ";
			else
				print+=(i+1)+" ";
		}
		for(int i=0;i<casillas.length;i++) {
			print+="\n";
			if(i<9)
				print+= " "+(i+1)+" ";
			else
				print+=(i+1)+" ";
			for(int j=0;j<casillas[0].length;j++) {
				casillas[i][j].modificarValor(cantidadMinasAlrededor(i,j));
				print+=" "+casillas[i][j].mostrarValorCasilla()+" ";
			}
		}
		return print;
	}


	/**
	 * Metodo que se encarga de marcar todas las casillas como destapadas
	 */
	public void resolver() {

		for(int i=0;i<casillas.length;i++) {
			for(int j=0;j<casillas[i].length;j++) {
				if(!casillas[i][j].esMina())
					casillas[i][j].destapar();
			}
		}

	}

	/**
	 * Metodo dar del atributo casillas
	 * @return la relacion casillas
	 */
	public Casilla[][] darCasillas(){
		return casillas;
	}


	/**
	 * Este metodo se encargaa de abrir una casilla
	 * Si se abre una casilla de tipo Mina, se marca que el jugador perdio el juego.
	 * @param i - la fila donde esta la casilla 
	 * @param j - la columna donde esta la casilla
	 * @return boolean - true si fue posible destaparla, false en caso contrario
	 */
	public boolean abrirCasilla(int i, int j) {
		boolean flag = false;
		if(casillas[i][j].esMina()) {
			casillas[i][j].destapar();
			perdio = true;
			flag= true;
		}else if(!casillas[i][j].esMina() && !casillas[i][j].darSeleccionada()){
			casillas[i][j].destapar();
			flag=true;
		}
		return flag;
	}


	/**
	 * Metodo que se encarga de revisar si el jugador gano el juego
	 * @return boolean - true si gano el juego, false en caso contrario
	 */
	public boolean gano() {
		boolean flag=false;
		int cont=0;
		for (int i=0;i<casillas.length;i++) {
			for(int j=0;j<casillas[i].length;j++) {
				if(casillas[i][j].darSeleccionada() && !casillas[i][j].esMina()) {
					cont++;
			}
		}
			if(cont==(casillas.length*casillas[i].length)-cantidadMinas) {
				flag=true;
			}else
				flag=false;
		}
		return flag;
	}


	/**
	 * Metodo que se encarga de abrir la primera casilla que no sea una Mina y cuyo valor sea Mayor que 0
	 * @return String, Mensaje de la Casilla que marco abierta, En caso de no haber casillas posibles para dar una pista, retorna el mensaje no hay pistas para dar
	 */
	public String darPista() {
		String nhull="";
		boolean stop=false;
		for (int i=0;i<casillas.length && stop==false;i++) {
			for(int j=0;j<casillas[i].length&&stop==false;j++) {
				if(!casillas[i][j].esMina() && !casillas[i][j].darSeleccionada() ) {
					casillas[i][j].destapar();
					nhull=""+i+" e "+j+" ";
					stop=true;
				}else {
					nhull="no hay pistas para dar";
			}
			}
		}
		return nhull;
	}
	
	/***
	 * Metodo dar del atributo perdio
	 * @return boolean el atributo
	 */
	public boolean darPerdio(){
		return perdio;
	}

}