import javafx.scene.layout.GridPane;

public class CellGridView extends GridPane
{
	private CellView[][] cellViews;
	private int sideLength;
	private int numRows;
	private int numCols;
	
	public CellGridView(int numRows, int numCols, int cellLength)
	{
		cellViews = new CellView[numCols][numRows];
		this.numRows = numRows;
		this.numCols = numCols;
		sideLength = cellLength;
		
		buildCellViews();
	}
	
	private void buildCellViews() 
	{	
		for(int i = 0; i < this.numRows; i++)
		{
			for(int j = 0; j < this.numCols; j++)
			{
				CellView cell = new CellView(j, i, sideLength);
				cellViews[j][i] = cell;
				this.add(cell, j, i);
			}
		}	
	}
	
	public void setCellState(int rowIdx, int colIdx, CellState state)
	{
		cellViews[colIdx][rowIdx].setState(state);
	}
	
	public void register(Presenter presenter)
	{
		for(int i = 0; i < numRows; i++)
		{
			for(int j = 0; j < numCols; j++)
			{
				cellViews[j][i].register(presenter);
			}
		}
	}
}
