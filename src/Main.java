import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
	public static final String TITLE = "Nonogram Player";
	public static final boolean RESIZABLE = false;
	public static final String STYLE_SHEET = "style.css";
	public Stage primaryStage;
	
	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		this.primaryStage = primaryStage;
		PuzzleLoader puzzleLoader = new PuzzleLoader(this);
		Scene loaderScene = new Scene(puzzleLoader);
		
		this.primaryStage.setScene(loaderScene);
		this.primaryStage.setTitle(TITLE);
		this.primaryStage.setResizable(RESIZABLE);
		this.primaryStage.show();
	}
	
	public void startNonogramPlayer(Model model, int cellLength)
	{	
		int[][] rowClues = new int[model.getNumRows()][]; 
		int[][] colClues = new int[model.getNumCols()][];
		
		for(int i = 0; i < model.getNumRows(); i++) //Builds rowClues by each row
		{
			rowClues[i] = model.getRowClue(i);
		}
		for(int i = 0; i < model.getNumCols(); i++) //Builds colClues by each column
		{
			colClues[i] = model.getColClue(i);
		}
		
		NonogramView view = new NonogramView(rowClues, colClues, cellLength);
		
		Presenter presenter = new Presenter(model, view);
		view.register(presenter);
		
		Scene viewScene = new Scene(view);
		viewScene.getStylesheets().add(STYLE_SHEET);
		
		primaryStage.setScene(viewScene);
	}
}
