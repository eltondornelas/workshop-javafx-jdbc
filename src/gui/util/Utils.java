package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	public static Stage currentStage(ActionEvent event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
		//getsource � de Obj,ou seja, muito gen�rico, o Node � um downcast e o WIndow � a superclasse do Stage, por isso tbm precisa do downcast.
	}
	
	public static Integer tryParseToInt(String str) {
		try {
			return Integer.parseInt(str);
			//retorna int na caixa que � string, converte para inteiro
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
	
}