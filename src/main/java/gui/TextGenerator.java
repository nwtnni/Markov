package gui;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.stage.FileChooser;
import parse.MarkovTextParser;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class TextGenerator extends Application {

	private MarkovTextParser parser;
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/gui.fxml"));
		final Scene scene = new Scene(fxml.load(), 800, 600);
		parser = null;
		
		Button load = (Button) fxml.getNamespace().get("load");
		Button reset = (Button) fxml.getNamespace().get("reset");
		Slider orderSlider = (Slider) fxml.getNamespace().get("orderSlider");
		Label orderLabel = (Label) fxml.getNamespace().get("orderLabel");
		TextArea display = (TextArea) fxml.getNamespace().get("display");
		TextArea stats = (TextArea) fxml.getNamespace().get("stats");
		

		
		orderSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			orderLabel.setText(((Integer) newValue.intValue()).toString());
		});
		
		load.setOnMouseClicked(click -> {
			
			if (parser == null) {
				parser = new MarkovTextParser((int) orderSlider.getValue());
				orderSlider.setDisable(true);
			}
			
			FileChooser fc = new FileChooser();
			fc.setTitle("Select Text File");
			
			Stage popup = new Stage();
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.initOwner(primaryStage);
			
			File f = fc.showOpenDialog(popup);
			try {
				parser.parse(f);
				reset.setDisable(false);
			} catch (Exception e) {
				stats.setText("File failed to load.");
				e.printStackTrace();
			}
		});
		
		reset.setOnMouseClicked(click -> {
			parser = null;
			stats.clear();
			orderSlider.setDisable(false);		
			reset.setDisable(true);
		});
		
		display.textProperty().addListener((observable, oldValue, newValue) -> {
			newValue.split(" ");
		});
		
		
		
		
		
		
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
