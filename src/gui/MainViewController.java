package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;
import model.services.SellerService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		loadView("/gui/SellerList.fxml", (SellerListController controller) -> {
			controller.setSellerService(new SellerService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
		//carrega a tela de departamentos
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x -> {});
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
		
	}
	
	//o sincronado é para garantir que a função não será interrompida. Funções de interface são multi thread.
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		//função para abrir outra tela
		//absoluteName porque vai passar o caminho completo gui/about.fxml
				
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName)); //objeto que carrega a tela
			VBox newVBox = loader.load();
			
			//mostrar essa view dentro da janela principal, precisa pegar uma referência da cena (scene) que foi declarada no Main.java (mainScene)
			Scene mainScene = Main.getMainScene();
			
			//os filhos (children) da tela about tem que ficar dentro dos filhos da tela principal (MainView)
			//precisa pegar a referência do vbox da tela principal
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent(); //pega o primeiro elemento da view que é o ScrollPane. precisa fazer o UpCast
			
			Node mainMenu = mainVBox.getChildren().get(0); //primeiro filho do VBox da janela principal = mainMenu
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			//para ativar o parâmetro Consumer<T>
			T controller = loader.getController();
			initializingAction.accept(controller);
			//vão executar os comandos da função lambda que foi passado como argumento
			
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
			//mostra o alerta na tela			
		}
	}	
}
