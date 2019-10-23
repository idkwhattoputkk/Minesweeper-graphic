package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import modelo.Casilla;

public class CasillaTest {
	Casilla casillas;
	// Setup sceneries

	public void setupScenery1() {
		casillas = new Casilla(50);
	}

	public void setupScenery2() {
		casillas = new Casilla(100);
	}

	
	//Testing cases
	@Test
	public void mostrarValorCasillasTest() {
		setupScenery2();
		assertEquals(casillas.mostrarValorCasilla(), "-");

	}
	@Test
	public void mostrarValorCasillasTest2() {
		setupScenery2();
		casillas.destapar();
		assertEquals(casillas.mostrarValorCasilla(), "*");

	}

	@Test
	public void destaparTest() {
		setupScenery1();
		assertFalse(casillas.darSeleccionada());
	}

	@Test
	public void destaparTest2() {
		setupScenery2();
		casillas.destapar();
		assertTrue(casillas.darSeleccionada());

	}
}
