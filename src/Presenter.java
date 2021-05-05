
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
