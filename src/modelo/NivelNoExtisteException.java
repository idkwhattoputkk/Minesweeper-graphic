package modelo;

/**
 * NivelNoExtisteException
 * Excepcion que maneja el caso que el nivel dado no exista.
 * @author emanuelumana
 *
 */
public class NivelNoExtisteException extends Exception {
	/**
	 * permite inicializar la excepcion
	 * @param msj
	 */
	public NivelNoExtisteException(String msj) {
		super(msj);
	}
}
