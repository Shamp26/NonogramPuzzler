import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NonogramModel implements Model
{
	private int[][] rowClues;
	private int[][] colClues;
	private CellState[][] cellStates;
	
	public NonogramModel(int[][] rowClues, int[][] colClues)
	{
		this.rowClues = Arrays.copyOf(rowClues, rowClues.length);
		this.colClues = Arrays.copyOf(colClues, colClues.length);
		this.cellStates = new CellState[rowClues.length][];
		
		cellStates = new CellState[rowClues.length][];
		for(int i = 0; i < rowClues.length; i++) 
		{
			cellStates[i] = new CellState[colClues.length]; //
		}
		
		for(int i = 0; i < cellStates.length; i++)
		{
			Arrays.fill(cellStates[i], CellState.EMPTY);
		}
	}
	
	public NonogramModel(String filename) throws FileNotFoundException
	{
		FileReader bot = new FileReader(filename);
		Scanner scnr = new Scanner(bot);
		
		this.rowClues = new int[scnr.nextInt()][]; //initializes to first inputs
		this.colClues = new int[scnr.nextInt()][];
		scnr.nextLine();
		
		for(int i = 0; i < rowClues.length; i++) //Builds rowClues array by each line
		{
			String line = scnr.nextLine();
			
			String[] vals = line.split(" "); //Splits the clues by spaces and stores in a string array
			
			int[] rowArray = new int[vals.length]; 
			
			for(int j = 0; j < vals.length; j++)
			{
				rowArray[j] = Integer.valueOf(vals[j]); //iterate through the string array and add int values
			}
			
			rowClues[i] = rowArray;
		}
		
		for(int i = 0; i < colClues.length; i++) //Builds colClues array by each line
		{
			String line = scnr.nextLine();
			
			String[] vals = line.split(" "); //Splits the clues by spaces and stores in a string array
			
			int[] colArray = new int[vals.length];
			
			for(int j = 0; j < vals.length; j++)
			{
				colArray[j] = Integer.valueOf(vals[j]); //iterate through the string array and add int values
			}
			
			colClues[i] = colArray;
		}
		
		cellStates = new CellState[rowClues.length][]; //Builds the cellSttates 2D array and fills it with empty cells
		
		for(int i = 0; i < rowClues.length; i++)
		{
			cellStates[i] = new CellState[colClues.length];
		}
		
		for(int i = 0; i < cellStates.length; i++)
		{
			Arrays.fill(cellStates[i], CellState.EMPTY);
		}
		
		scnr.close();
	}

	@Override
	public CellState getCellState(int rowIdx, int colIdx) 
	{
		return cellStates[rowIdx][colIdx];
	}

	@Override
	public boolean setCellState(int rowIdx, int colIdx, CellState state) 
	{
		if(this.isSolved() || state == null || state == cellStates[rowIdx][colIdx])
		{
			return false;
		}
		else
		{
			cellStates[rowIdx][colIdx] = state;
			return true;
		}
		
	}

	@Override
	public int getNumRows() 
	{
		
		return rowClues.length;
	}

	@Override
	public int getNumCols() 
	{
		
		return colClues.length;
	}

	@Override
	public int[] getRowClue(int rowIdx) 
	{
		
		int[][] copy = Arrays.copyOf(rowClues, rowClues.length);
		int[] copyRow = Arrays.copyOf(copy[rowIdx], copy[rowIdx].length);
		return copyRow;
	}

	@Override
	public int[] getColClue(int colIdx) 
	{
		int[][] copy = Arrays.copyOf(colClues, colClues.length);
		int[] copyCol = Arrays.copyOf(copy[colIdx], copy[colIdx].length);
		return copyCol;
	}

	@Override
	public boolean isRowSolved(int rowIdx) 
	{
		//Compares the projected row with rowClues
		return Arrays.equals(projectRow(rowIdx), rowClues[rowIdx]);
	}

	@Override
	public boolean isColSolved(int colIdx) 
	{
		//Compares the projected column with colClues
		return Arrays.equals(projectCol(colIdx), colClues[colIdx]);
	}

	@Override
	public boolean isSolved()
	{
		
		for(int i = 0; i < cellStates.length; i++)
		{
			if(isRowSolved(i) == false)
			{
				return false;
			}
		}
		
		for(int i = 0; i < cellStates[0].length; i++)
		{
			if(isColSolved(i) == false)
			{
				return false;
			}
		}
		
		return true;
		
	}
	public int[] projectRow (int rowIdx)
	{
		//Code based off of Project 4
		ArrayList<Integer> rowList = new ArrayList<>();
		
		int count = 0;
		
		for(int i = 0; i < cellStates[rowIdx].length; i++)
		{
			//If the cell is filled count is increased
			if(cellStates[rowIdx][i] == CellState.FILLED)
			{
				count++;
			}
			else if((cellStates[rowIdx][i] == CellState.EMPTY || cellStates[rowIdx][i] == CellState.MARKED)  && count > 0)
			{
				//When the cell becomes empty or marked, the count is added to the list and set to 0
				rowList.add(count);
				count = 0;
			}
			
			if(i == cellStates[rowIdx].length - 1 && count > 0)
			{
				//If the cell is the last cell and count is over 0, add the count to the list
				rowList.add(count);
			}
		}
		
		if(rowList.isEmpty())
		{
			rowList.add(0);
		}
		
		//iterates through the list and adds it to an array
		int[] row = new int[rowList.size()];

		for(int i = 0; i < rowList.size(); i++)
		{
			row[i] = rowList.get(i);
		}
		
		return row;
	}
	
	public int[] projectCol (int colIdx)
	{
		//Code based off of project 4
		ArrayList<Integer> colList = new ArrayList<>();
		
		int count = 0;
		
		for(int i = 0; i < cellStates.length; i++)
		{
			//If the cell is filled count is increased
			if(cellStates[i][colIdx] == CellState.FILLED)
			{
				count++;
			}
			else if((cellStates[i][colIdx] == CellState.EMPTY || cellStates[i][colIdx] == CellState.MARKED) && count > 0)
			{
				//When the cell becomes empty or marked, the count is added to the list and set to 0
				colList.add(count);
				count = 0;
			}
			
			if(i == cellStates.length - 1 && count > 0)
			{
				//If the cell is the last cell and count is over 0, add the count to the list
				colList.add(count);
			}
		}
		
		
		if(colList.isEmpty())
		{
			colList.add(0);
		}
		
		//iterates through the list and adds it to the array
		int[] col = new int[colList.size()];

		for(int i = 0; i < colList.size(); i++)
		{
			col[i] = colList.get(i);
		}
		
		return col;
	}
}
