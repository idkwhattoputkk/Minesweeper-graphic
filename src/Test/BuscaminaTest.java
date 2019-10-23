package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import modelo.Buscaminas;
import modelo.NivelNoExtisteException;

public class BuscaminaTest {

	private Buscaminas buscaminas;
	
	//sceneries
	public void setupScenery1() throws NivelNoExtisteException {
		 buscaminas = new Buscaminas(3);
		 buscaminas.inicializarCasillasLibres();
	}
	public void setupScenery2() throws NivelNoExtisteException {
		buscaminas = new Buscaminas(0);
	}
	
	// test cases
	
	@Test
	public void testInicializarCasillasLibre1() {
		try {
			setupScenery1();
			for(int i=0;i<buscaminas.darCasillas().length;i++) {
				for(int j=0;j<buscaminas.darCasillas()[i].length;j++) {
					assertTrue(!buscaminas.darCasillas()[i][j].esMina());
				}
			}
		} catch (NivelNoExtisteException e) {
				fail(e.getMessage());
		}
	}
	@Test
	public void testInicializarCasillasLibre2() {
		try {
			setupScenery1();
			for(int i=0;i<buscaminas.darCasillas().length;i++) {
				for(int j=0;j<buscaminas.darCasillas()[0].length;j++) {
					assertEquals(buscaminas.darCasillas()[i][j].mostrarValorCasilla(), "-");
					}
				}
		}catch(Exception e) {
			fail(e.getMessage());
		}
		}
	@Test
	public void testInicializarCasillasLibre3() {
		try {
			setupScenery1();
			for(int i=0;i<buscaminas.darCasillas().length;i++) {
				for(int j=0;j<buscaminas.darCasillas()[0].length;j++) {
					assertFalse(buscaminas.darCasillas()[i][j].darSeleccionada());
					}
				}
			}catch(Exception e) {
				fail(e.getMessage());
			}
	}
	@Test
	public void testConstructor() {
		try {
			setupScenery2();
			 fail("Should throw NivelNoExtisteException");
		} catch (NivelNoExtisteException e) {
			assertEquals("digite un nivel correcto", e.getMessage());
		}
		
		}
}
