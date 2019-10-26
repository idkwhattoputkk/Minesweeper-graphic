package interfaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import modelo.Buscaminas;
import modelo.NivelNoExtisteException;

public class Controller {
	Buscaminas bs;
	
	@FXML
	private BorderPane ac;
	
	private GridPane grd1;
	
	
	
	
	public void easyMethod(ActionEvent event){
		
		try {
			bs = new Buscaminas(1);
			printMatrix();
		} catch (NivelNoExtisteException e) {
			e.printStackTrace();
		}
	}
	public void intermMethod(ActionEvent event){
			
			try {
				bs = new Buscaminas(2);
				printMatrix();
			} catch (NivelNoExtisteException e) {
				e.printStackTrace();
			}
		}
	public void hardMethod(ActionEvent event){
		
		try {
			bs = new Buscaminas(3);
			printMatrix();
		} catch (NivelNoExtisteException e) {
			e.printStackTrace();
		}
	}
	private void printMatrix() {
			grd1= new GridPane();

		for (int i = 0; i < bs.darCasillas().length; i++) {
			for (int j = 0; j < bs.darCasillas()[0].length; j++) {
				bs.darCasillas()[i][j].modificarValor(bs.cantidadMinasAlrededor(i, j));
				Button b = new Button();
				b.setText(bs.darCasillas()[i][j].mostrarValorCasilla());
				b.setPrefWidth(10);
				grd1.add(b, i, j);
			}
		}
		grd1.setAlignment(Pos.CENTER);
		ac.setCenter(grd1);
	}
	
}
