package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView2("/gui/DepartmentList.fxml");
		//carrega a tela de departamentos
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
		
	}
	
	//o sincronado é para garantir que a função não será interrompida. Funções de interface são multi thread.
	private synchronized void loadView(String absoluteName) {
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
			
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
			//mostra o alerta na tela			
		}
	}
	
	private synchronized void loadView2(String absoluteName) {
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
			
			DepartmentListController controller = loader.getController();
			//loader é o objeto que carrega a view, com ele você pode tanto acessar a view como pegar o controller
			
			controller.serDepartmentService(new DepartmentService());
			controller.UpdateTableView();
			
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
			//mostra o alerta na tela			
		}
	}

	
}
