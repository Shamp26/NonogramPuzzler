import java.util.LinkedList;
import java.util.List;
import javafx.scene.layout.HBox;

public class ColCluesView extends HBox
{
	private List<ColClueView> colClueViews;
	
	public ColCluesView(int[][] colClues, int cellLength, int height)
	{
		colClueViews = new LinkedList<>();
		this.setHeight(height);
		
		for(int i = 0; i < colClues.length; i++)
		{
			ColClueView colClue = new ColClueView(colClues[i], cellLength, height);
			colClueViews.add(colClue);
			this.getChildren().add(colClue);
		}
	}
	
	public void setColState(int colIdx, boolean solved)
	{
		colClueViews.get(colIdx).setState(solved);
	}
}