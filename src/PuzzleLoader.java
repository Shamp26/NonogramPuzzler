import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

public class PuzzleLoader extends GridPane
{
	public static final int MIN_CELL_LENGTH = 20;
	public static final int MAX_CELL_LENGTH = 50;
	public static final int DEFAULT_CELL_LENGTH = 30;
	public static final int SPACING = 10;
	//will insert others
	
	//put name in title bar of puzzle loader
	//screenshot save to project folder
	
	public PuzzleLoader(Main main)
	{
		Label fileLabel = new Label("Nonogram file:");
		Label cellLabel = new Label("Cell side length (px):");
		Spinner<Integer> cellSpinner = new Spinner<Integer>(MIN_CELL_LENGTH, MAX_CELL_LENGTH, DEFAULT_CELL_LENGTH);
		TextField fileEntry = new TextField();
		fileEntry.setPromptText("Enter file name");
		Button button = new Button("Load Puzzle");
		
		this.add(fileLabel, 0, 0);
		this.add(fileEntry, 1, 0);
		this.add(cellLabel, 0, 1);
		this.add(cellSpinner, 1, 1);
		this.add(button, 1, 2);
		
		setAlignment();
		
		button.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					NonogramModel model = new NonogramModel(fileEntry.getText());
					main.startNonogramPlayer(model, cellSpinner.getValue());
					
					//Creates model from file
				}
				catch(FileNotFoundException e)
				{
					Alert error = new Alert(AlertType.ERROR, "File could not be read");
					error.show();
				}
			}
	
		});
		
	}
	private void setAlignment() {
		this.setHgap(SPACING);
		this.setVgap(SPACING);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(SPACING, SPACING, SPACING, SPACING));
	}
	
	
}
