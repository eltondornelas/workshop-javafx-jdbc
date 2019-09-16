package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	public static Stage currentStage(ActionEvent event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
		//getsource é de Obj,ou seja, muito genérico, o Node é um downcast e o WIndow é a superclasse do Stage, por isso tbm precisa do downcast. 
		
	}
}
