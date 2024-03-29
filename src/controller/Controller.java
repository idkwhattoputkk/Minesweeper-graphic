package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
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
			bs = new Buscaminas(bs.PRINCIPIANTE);
			printMatrixAndPlay();
		} catch (NivelNoExtisteException e) {
			e.printStackTrace();
		}
	}
	public void intermMethod(ActionEvent event){
			
			try {
				bs = new Buscaminas(bs.INTERMEDIO);
				printMatrixAndPlay();
			} catch (NivelNoExtisteException e) {
				e.printStackTrace();
			}
		}
	public void hardMethod(ActionEvent event){
		
		try {
			bs = new Buscaminas(bs.EXPERTO);
			printMatrixAndPlay();
		} catch (NivelNoExtisteException e) {
			e.printStackTrace();
		}
	}
	private void printMatrixAndPlay() {
			grd1= new GridPane();
		for (int i = 0; i < bs.darCasillas().length; i++) {
			for (int j = 0; j < bs.darCasillas()[0].length; j++) {
				int columns=i;
				int rows=j;
				bs.darCasillas()[i][j].modificarValor(bs.cantidadMinasAlrededor(i, j));
				Button b = new Button();
				b.setText(bs.darCasillas()[i][j].mostrarValorCasilla());
				b.setOnAction(e->{
					openBoxes(b,columns, rows);
				});
				b.setPrefWidth(10);
				grd1.add(b, j,i);
			}
		}
		grd1.setAlignment(Pos.CENTER);
		ac.setCenter(grd1);
	}
	private void openBoxes(Button b, int columns, int rows) {
		bs.abrirCasilla(columns, rows);
		checkLost();
		checkWin();
		b.setText(bs.darCasillas()[columns][rows].mostrarValorCasilla());
	}
	public void givePista(ActionEvent event) {
		try{
			bs.darPista();
			printMatrixAndPlay();
			checkWin();
		}catch(Exception e) {
			Alert x = new Alert(Alert.AlertType.WARNING);
			x.setContentText("No has escogido el nivel aun");
			x.showAndWait();
		}
	}
	private void checkLost() {
		if(bs.darPerdio()==true) {
			Alert x = new Alert(Alert.AlertType.INFORMATION);
			x.setTitle("You loose");
			x.setContentText("X_X Perdiste ");
			x.showAndWait();
			if(bs.darCasillas().length==bs.COLUMNAS_PRINCIPIANTE) {
				easyMethod(null);
			}else if(bs.darCasillas()[0].length==bs.FILAS_EXPERTO) {
				intermMethod(null);
			}else {
				hardMethod(null);
			}
		}
	}
	private void checkWin() {
		if(bs.gano()==true) {
			Alert x = new Alert(Alert.AlertType.INFORMATION);
			x.setTitle("You Win");
			x.setContentText("Felicitaciones Ganaste!!!!!!!");
			x.showAndWait();
			if(bs.darCasillas().length==bs.COLUMNAS_PRINCIPIANTE) {
				easyMethod(null);
			}else if(bs.darCasillas()[0].length==bs.FILAS_EXPERTO) {
				intermMethod(null);
			}else {
				hardMethod(null);
			}
		}
	}
	public void solveGame(ActionEvent e) {
		try{
		bs.resolver();
		printMatrixAndPlay();
		checkLost();
	}catch(Exception ex) {
		Alert x = new Alert(Alert.AlertType.WARNING);
		x.setContentText("No has escogido el nivel aun");
		x.showAndWait();
	}
	}
}
