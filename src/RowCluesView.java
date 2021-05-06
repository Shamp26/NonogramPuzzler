import java.util.LinkedList;
import java.util.List;

import javafx.scene.layout.VBox;


//./puzzles/pig.txt

public class RowCluesView extends VBox
{
	private List<RowClueView> rowClueViews;
	
	public RowCluesView(int[][] rowClues, int cellLength, int width)
	{
		rowClueViews = new LinkedList<>();
		this.setWidth(width);
		
		for(int i = 0; i < rowClues.length; i++)
		{
			RowClueView rowClue = new RowClueView(rowClues[i], cellLength, width);
			rowClueViews.add(rowClue);
			this.getChildren().add(rowClue);
		}
		
	}
	
	public void setRowState(int rowIdx, boolean solved)
	{
		rowClueViews.get(rowIdx).setState(solved);
	}
}
