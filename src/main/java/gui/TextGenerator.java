package gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.stage.FileChooser;
import parse.Markov;
import parse.MarkovTextParser;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TextGenerator extends Application {

	private Markov<String> mc;
	private MarkovTextParser parser;
	private String suggested;
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/gui.fxml"));
		final Scene scene = new Scene(fxml.load(), 800, 600);
		mc = null;
		parser = new MarkovTextParser();
		suggested = null;
		
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
			
			if (mc == null) {
				mc = new Markov<String>((int) orderSlider.getValue());
				parser.setMarkov(mc);
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
			mc = null;
			parser.setMarkov(null);
			stats.clear();
			orderSlider.setDisable(false);		
			reset.setDisable(true);
		});
		
		display.textProperty().addListener((observable, oldValue, newValue) -> {
			String[] words = newValue.split(" ");
			
			List<String> prefix = new ArrayList<String>();
			int length = Math.min((int) orderSlider.getValue(), words.length); 
			for (int i = length; i > 0; i--) {
				prefix.add(words[words.length - i]);
			}
			
			suggested = mc.getNext(prefix);
			
		});
		
		display.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
			if (key.getCode() == KeyCode.TAB && suggested != null && reset.isDisable() == false) {
				display.textProperty().set(display.textProperty().get() + " " + suggested);
				display.positionCaret(display.textProperty().length().intValue());
				key.consume();
			}
		});
		
		
		
		
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
