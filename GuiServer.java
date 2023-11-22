
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GuiServer extends Application{

	TextField userInput;
	Button ONButton, OFFButton;
	HashMap<String, Scene> sceneMap;
	Server serverConnection;
	Label askPortNum;
	ListView<String> listItems;

	int portNumber = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("The Networked Client/Server GUI Example");

		setUp();

		setOnAction(primaryStage);

		primaryStage.setScene(this.sceneMap.get(("start")));
		primaryStage.show();
		
	}

	public void setUp(){
		//On Button
		this.ONButton = new Button("ON");
		this.ONButton.setPrefWidth(60);
		this.ONButton.setPrefHeight(500);
		this.ONButton.setStyle("-fx-font: normal 16 Langdon; "
				+ "-fx-base: #3d3d3d; "
				+ "-fx-text-fill: orange;");

		//Off Button
		this.OFFButton = new Button("OFF");
		this.OFFButton.setPrefWidth(60);
		this.OFFButton.setPrefHeight(500);
		this.OFFButton.setStyle("-fx-font: normal 16 Langdon; "
				+ "-fx-base: #3d3d3d; "
				+ "-fx-text-fill: orange;");

		//start scene
		this.askPortNum = new Label("      Please Input which port number\nyou would like to use and press Enter:");
		this.userInput = new TextField("5555");

		//list view data
		listItems = new ListView<String>();

		//create hash map for scenes
		sceneMap = new HashMap<String, Scene>();
		sceneMap.put("server",  createServerGui());
		sceneMap.put("start", createStartScene());


	}

	public void setOnAction(Stage primaryStage){
		this.ONButton.setOnAction(e->{
			this.ONButton.setDisable(true);
			serverConnection = new Server(data -> {
				Platform.runLater(()->{
					listItems.getItems().add(data.toString());
				});
			}, portNumber);
		});

		this.OFFButton.setOnAction(e->{
			listItems.getItems().add("Server is off");
			serverConnection.closeServer();
			OFFButton.setDisable(true);
			ONButton.setDisable(false);
		});

		this.userInput.setOnKeyPressed( event -> {
			if( event.getCode() == KeyCode.ENTER ) {
				try {
					portNumber = Integer.parseInt(userInput.getText());
					primaryStage.setScene(sceneMap.get("server"));
					primaryStage.setTitle("This is the Server");
				}
				catch(NumberFormatException e){
					userInput.setText("Invalid Input");
				}
			}
		} );

	}

	public Scene createServerGui() {
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-background-color: orange;");
		
		pane.setCenter(listItems);

		return new Scene(new HBox(ONButton,pane,OFFButton), 500,500);
		
	}

	public Scene createStartScene(){

		Button hint = new Button();
		hint.setText("Forgot Port Number?");
		Tooltip tt = new Tooltip();
		tt.setText("It is '5555'");
		tt.setStyle("-fx-font: normal 16 Langdon; "
				+ "-fx-base: #AE3522; "
				+ "-fx-text-fill: orange;"
		);

		hint.setTooltip(tt);

		askPortNum.setStyle("-fx-font: normal 24 Langdon; "
				+ "-fx-text-fill: orange;");


		userInput.setMaxWidth(80);
		VBox v = new VBox(30, askPortNum, userInput, hint);
		v.setAlignment(Pos.CENTER);

		Scene scene = new Scene(v, 500, 500);
		v.setStyle("-fx-base: #3d3d3d; ");

		return scene;
	}

}
