
public class Presenter 
{
	private Model model;
	private View view;
	
	public Presenter(Model model, View view)
	{
		
		this.model = model;
		this.view = view;
		view.register(this);
		
		syncClueStates(model, view);
		
		syncCellStates(model, view);
		
		syncPuzzleState(model, view);
		
		
	}
	
	public void cellClicked(int rowIdx, int colIdx, boolean primaryButton)
	{
		
		
		CellState initState = model.getCellState(rowIdx, colIdx);
		
		System.out.println(rowIdx + " " + colIdx);
		CellState chosenState;
		// Left-clicking changes an EMPTY or MARKED cell to FILLED. Right-clicking changes an EMPTY or FILLED cell to MARKED.
		//Left-clicking a FILLED cell or right-clicking a MARKED cell changes it to EMPTY
		if(primaryButton == true && (initState == CellState.EMPTY || initState == CellState.MARKED))
		{
			chosenState = CellState.FILLED;
			model.setCellState(rowIdx, colIdx, chosenState);
			syncCellStates(model, view);
			syncClueStates(model, view);
			syncPuzzleState(model, view);
		}
		else if(primaryButton == false && (initState == CellState.EMPTY || initState == CellState.FILLED))
		{
			chosenState = CellState.MARKED;
			model.setCellState(rowIdx, colIdx, chosenState);
			syncCellStates(model, view);
			syncClueStates(model, view);
			syncPuzzleState(model, view);
		}
		else if((primaryButton == true && initState == CellState.FILLED) || (primaryButton == false && initState == CellState.MARKED))
		{
			chosenState = CellState.EMPTY;
			model.setCellState(rowIdx, colIdx, chosenState);
			syncCellStates(model, view);
			syncClueStates(model, view);
			syncPuzzleState(model, view);
		}
		//update model to the desired state, then update the view
		//if that solves the puzzle, change all marked cells to empty
	}

	private void syncPuzzleState(Model model, View view) {
		if(model.isSolved())
		{
			view.setPuzzleState(true);
			for(int i = 0; i < model.getNumRows(); i++)
			{
				for(int j = 0; j < model.getNumCols(); j++)
				{
					if(model.getCellState(i, j) == CellState.MARKED)
					{
						model.setCellState(i, j, CellState.EMPTY);
						view.setCellState(i, j, CellState.EMPTY);
					}
				}
			}
		}
		else
		{
			view.setPuzzleState(false);
		}
	}

	private void syncCellStates(Model model, View view) {
		for(int i = 0; i < model.getNumRows(); i++)
		{
			for(int j = 0; j < model.getNumCols(); j++)
			{
				view.setCellState(i, j, model.getCellState(i, j));
			}
		}
	}

	private void syncClueStates(Model model, View view) {
		for(int i = 0; i < model.getNumRows(); i++)
		{
			if(model.isRowSolved(i))
			{
				view.setRowClueState(i, true);
			}
			else
			{
				view.setRowClueState(i, false);
			}
		}
		for(int i = 0; i < model.getNumCols(); i++)
		{
			if(model.isColSolved(i))
			{
				view.setColClueState(i, true);
			}
			else
			{
				view.setColClueState(i, false);
			}
		}
	}
	
}
