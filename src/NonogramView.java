import javafx.scene.layout.BorderPane;

public class NonogramView extends BorderPane implements View
{
	private RowCluesView left;
	private ColCluesView top;
	private CellGridView center;
	
	public NonogramView(int[][] rowClues, int[][] colClues, int cellLength)
	{
		
	}
	
	@Override
	public void setCellState(int rowIdx, int colIdx, CellState state) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setRowClueState(int rowIdx, boolean solved) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setColClueState(int colIdx, boolean solved) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setPuzzleState(boolean solved) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void register(Presenter presenter) {
		// TODO Auto-generated method stub
		
	}
}
