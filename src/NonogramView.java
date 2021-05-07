import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

public class NonogramView extends BorderPane implements View
{
	private RowCluesView left;
	private ColCluesView top;
	private CellGridView center;
	
	public NonogramView(int[][] rowClues, int[][] colClues, int cellLength)
	{
		int rowWidth = calcRowWidth(rowClues);
		int colHeight = calcColHeight(colClues);
		
		left = new RowCluesView(rowClues, cellLength, rowWidth);
		top = new ColCluesView(colClues, cellLength, colHeight);
		center = new CellGridView(rowClues.length, colClues.length, cellLength);
		
		this.setCenter(center);
		this.setLeft(left);
		this.setTop(top);
		top.setAlignment(Pos.CENTER_RIGHT);
		
		this.setStyle("nonogram-view");
	}

	private int calcRowWidth(int[][] rowClues) 
	{
		//Takes the array with the largest length
		int rowWidth = 0;
		for(int i = 0; i < rowClues.length; i++)
		{
			if(rowClues[i].length > rowWidth)
			{
				rowWidth = rowClues[i].length;
			}
		}
		return rowWidth;
	}
	
	private int calcColHeight(int[][] colClues) 
	{
		//Takes the array with the largest length
		int colHeight = 0;
		for(int i = 0; i < colClues.length; i++)
		{
			if(colClues[i].length > colHeight)
			{
				colHeight = colClues[i].length;
			}
		}
		return colHeight;
	}
	
	@Override
	public void setCellState(int rowIdx, int colIdx, CellState state) 
	{
		center.setCellState(rowIdx, colIdx, state);
	}
	
	@Override
	public void setRowClueState(int rowIdx, boolean solved) 
	{
		left.setRowState(rowIdx, solved);
	}
	
	@Override
	public void setColClueState(int colIdx, boolean solved) 
	{
		top.setColState(colIdx, solved);
	}
	
	@Override
	public void setPuzzleState(boolean solved) 
	{
		if(solved)
		{
			this.getStyleClass().add("nonogram-view-solved");
		}
		else
		{
			this.getStyleClass().remove("nonogram-view-solved");
		}
	}
	
	@Override
	public void register(Presenter presenter) 
	{
		center.register(presenter);
	}
}
