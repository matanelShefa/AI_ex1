import java.util.ArrayList;

/**
 * Created by Matanel on 29/11/2017.
 */

public class Map
{
	// Members
	private int m_size;
	private String m_algorithm;
	private ArrayList<Cell> m_cellsList;

	// Constructor
	Map(String inputFile)
	{
		// Get the information from the parser
		Parser parser = new Parser(inputFile);
		m_algorithm = parser.getAlgorithm();
		m_size = parser.getSize();
		String typeString = parser.getTypeString();

		// Create the map.
		m_cellsList = new ArrayList<>();
		for (int i = 0; i < m_size; i++)
		{
			for (int j = 0; j < m_size; j++)
			{
				m_cellsList.add(new Cell(new Point(i, j), typeString.charAt((i * m_size) + j)));
			}
		}

		// Generate the children lists.
		for (int i = 0; i < m_size; i++)
		{
			for (int j = 0; j < m_size; j++)
			{
				getCell(i, j).setChildrenList(addChildrenList(getCell(i, j)));
				// TODO - REMOVE!!
				//System.out.println("Cell : " + getCell(i, j));
				//System.out.println("List : " + getCell(i, j).getChildrenList());
			}
		}
	}

	// Create the children list for the cells.
	private ArrayList<Cell> addChildrenList(Cell cell)
	{
		int offsetArray[][] = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1}, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
		ArrayList<Cell> childrenList = new ArrayList<>();
		for (int offset[] : offsetArray)
		{
			Cell child = getCell(cell.getXVal() + offset[0], cell.getYVal() + offset[1]);
			if (child != null)
			{
				childrenList.add(child);
			}
		}
		return childrenList;
	}

	// Search for the goal, use the algorithm the input asked to.
	public void search()
	{
		if (m_algorithm.equals("IDS"))
		{
			IDS searcher = new IDS(this);
			System.out.println(searcher.idsSearch());
		}
		else
		{
			//AStar searcher = new AStar(this);
			//System.out.println(searcher.idsSearch());
		}

	}

	public boolean isValidMove(Cell from, Cell to)
	{
		int fromX = from.getXVal();
		int fromY = from.getYVal();
		int toX = to.getXVal();
		int toY = to.getYVal();

		if (to.getType() == 'W')
		{
			return false;
		}
		if ((fromX - toX == 1) && (fromY - toY == 1))
		{
			return (getCell(fromX - 1, fromY).getType() != 'W') && (getCell(fromX, fromY - 1).getType() != 'W');
		}
		if ((fromX - toX == 1) && (fromY - toY == -1))
		{
			return (getCell(fromX - 1, fromY).getType() != 'W') && (getCell(fromX, fromY + 1).getType() != 'W');
		}
		if ((fromX - toX == -1) && (fromY - toY == -1))
		{
			return (getCell(fromX + 1, fromY).getType() != 'W') && (getCell(fromX, fromY + 1).getType() != 'W');
		}
		if ((fromX - toX == -1) && (fromY - toY == 1))
		{
			return (getCell(fromX + 1, fromY).getType() != 'W') && (getCell(fromX, fromY - 1).getType() != 'W');
		}
		return true;
	}

	// Getter
	public Cell getCell(int xVal, int yVal)
	{
		if ((xVal < 0) || (yVal < 0) || (xVal >= m_size) || (yVal >= m_size))
		{
			return null;
		}
		return m_cellsList.get((xVal * m_size) + yVal);
	}

	// Getter
	public ArrayList<Cell> getCellsList() { return m_cellsList; }

	// Getter
	public int getSize() { return m_size; }

	// Print the map
	public String toString()
	{
		String mapToPrint = "Algorithm : " + m_algorithm + "\n" + "Size : " + m_size + "\n";
		for (int i = 0; i < m_size; i++)
		{
			for (int j = 0; j < m_size; j++)
			{
				mapToPrint += m_cellsList.get(j + (m_size * i)).toString();
			}
			mapToPrint += '\n';
		}
		return mapToPrint;
	}
}
