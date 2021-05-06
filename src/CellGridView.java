import javafx.scene.layout.GridPane;

public class CellGridView extends GridPane
{
	private CellView[][] cellViews;
	private int sideLength;
	private int numRows;
	private int numCols;
	
	public CellGridView(int numRows, int numCols, int cellLength)
	{
		cellViews = new CellView[numRows][numCols];
		this.numRows = numRows;
		this.numCols = numCols;
		sideLength = cellLength;
		
		buildCellViews();
	}
	
	private void buildCellViews() 
	{
		System.out.println(numRows);
		System.out.println(numCols);
		
		for(int i = 0; i < this.numRows; i++)
		{
			for(int j = 0; j < this.numCols; j++)
			{
				CellView cell = new CellView(i, j, sideLength);
				cellViews[i][j] = cell;
				this.add(cell, i, j);
			}
		}
		
		
	}
	
	public void setCellState(int rowIdx, int colIdx, CellState state)
	{
		cellViews[rowIdx][colIdx].setState(state);
	}
	
	public void register(Presenter presenter)
	{
		for(int i = 0; i < numRows; i++)
		{
			for(int j = 0; j < numCols; j++)
			{
				cellViews[i][j].register(presenter);
			}
		}
	}
}
