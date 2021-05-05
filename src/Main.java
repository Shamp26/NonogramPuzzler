import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

	public static final String TITLE = "Grant Vess";
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
		
		NonogramView view = new NonogramView();
		Presenter presenter = new Presenter(model, view);
		Scene viewScene = new Scene(view);
		viewScene.getStylesheets().add(STYLE_SHEET);
		primaryStage.setScene(viewScene);
		
	}
	

}
