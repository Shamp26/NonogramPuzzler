import javafx.scene.layout.BorderPane;

public class NonogramView extends BorderPane implements View
{
	private RowCluesView left;
	private ColCluesView top;
	private CellGridView center;
	
	public NonogramView(int[][] rowClues, int[][] colClues, int cellLength)
	{
		left = new RowCluesView(rowClues, cellLength, 1);
		top = new ColCluesView(colClues, cellLength, 1);
		center = new CellGridView(rowClues.length, colClues.length, cellLength);
		
		this.setLeft(left);
		this.setTop(top);
		this.setCenter(center);
		
		this.setStyle("nonogram-view");
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
			this.setStyle("nonogram-view-solved");
		}
		else
		{
			this.setStyle("nonogram-view");
		}
	}
	@Override
	public void register(Presenter presenter) 
	{
		this.register(presenter);
	}
}
