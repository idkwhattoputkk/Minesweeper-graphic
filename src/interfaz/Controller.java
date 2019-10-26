package interfaz;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import modelo.Buscaminas;

public class Controller {
	Buscaminas bs;
	
	@FXML
	private TextField txt1;
	
	
	
	public void initialize(ActionEvent event){
		try {
		int num = Integer.parseInt(txt1.getText());
		bs = new Buscaminas(num);
		printMatrix();
		} catch(Exception ex) {
			String msg = ex.getMessage();
			JOptionPane.showMessageDialog(null, msg);
		}
	}



	private void printMatrix() {
		GridPane grd1 = new GridPane();

		for (int i = 0; i < bs.darCasillas().length; i++) {
			for (int j = 0; j < bs.darCasillas()[i].length; j++) {
				Button b = new Button();
				b.setText("" + bs.darCasillas()[i][j] + "");
				b.setPrefWidth(50);
				grd1.add(b, i, j);
			}
		}
	}
}
