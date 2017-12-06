import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Matanel on 29/11/2017.
 */

public class Map
{
	public static final String IDS = "IDS";
	private static final int offsetArray[][] = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1}, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };

	// Members
	private int m_size;
	private String m_algorithm;
	//TODO - remove this member:
	private ArrayList<Cell> m_cellsList;
	private Algorithm m_searcher;
	private String m_typeString;

	// Constructor
	Map(String inputFile)
	{
		// Get the information from the parser
		Parser parser = new Parser(inputFile);
		m_algorithm = parser.getAlgorithm();
		m_size = parser.getSize();
		m_typeString = parser.getTypeString();

		// Create the map.
		m_cellsList = new ArrayList<>();
		for (int i = 0; i < m_size; i++)
		{
			for (int j = 0; j < m_size; j++)
			{
				m_cellsList.add(new Cell(new Point(i, j), m_typeString.charAt((i * m_size) + j)));
			}
		}

		// Create the searcher according to the input.
		if (m_algorithm.equals(IDS))
		{
			m_searcher = new IDS(this);
		}
		else
		{
			m_searcher = new AStar(this);
		}
	}

	// Create the children list for the cells.
	public ArrayList<Cell> addChildrenList(Cell cell)
	{
		ArrayList<Cell> childrenList = new ArrayList<>();
		Cell child;

		for (int offset[] : offsetArray)
		{
			child = getCell(cell.getXVal() + offset[0], cell.getYVal() + offset[1]);
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
		m_searcher.search();
	}

	public boolean isValidMove(Cell from, Cell to)
	{
		int fromX = from.getXVal();
		int fromY = from.getYVal();
		int toX = to.getXVal();
		int toY = to.getYVal();

		if (to.getType() == Cell.WATER)
		{
			return false;
		}

		if ((fromX - toX == 1))
		{
			if (fromY - toY == 1)
			{
				return (getType(fromX - 1, fromY) != Cell.WATER) && (getType(fromX, fromY - 1) != Cell.WATER);
			}
			else if (fromY - toY == -1)
			{
				return (getType(fromX - 1, fromY) != Cell.WATER) && (getType(fromX, fromY + 1) != Cell.WATER);
			}
		}

		if (fromX - toX == -1)
		{
			if (fromY - toY == -1)
			{
				return (getType(fromX + 1, fromY) != Cell.WATER) && (getType(fromX, fromY + 1) != Cell.WATER);
			}
			else if ((fromY - toY == 1))
			{
				return (getType(fromX + 1, fromY) != Cell.WATER) && (getType(fromX, fromY - 1) != Cell.WATER);
			}
		}

/*		if ((fromX - toX == 1))
		{
			if (fromY - toY == 1)
			{
				return (getCell(fromX - 1, fromY).getType() != Cell.WATER) &&
						(getCell(fromX, fromY - 1).getType() != Cell.WATER);
			}
			else if (fromY - toY == -1)
			{
				return (getCell(fromX - 1, fromY).getType() != Cell.WATER) &&
						(getCell(fromX, fromY + 1).getType() != Cell.WATER);
			}
		}

		if (fromX - toX == -1)
		{
			if (fromY - toY == -1)
			{
				return (getCell(fromX + 1, fromY).getType() != Cell.WATER) &&
						(getCell(fromX, fromY + 1).getType() != Cell.WATER);
			}
			else if ((fromY - toY == 1))
			{
				return (getCell(fromX + 1, fromY).getType() != Cell.WATER) &&
						(getCell(fromX, fromY - 1).getType() != Cell.WATER);
			}
		}
*/
		return true;
	}

	// Getter
	public char getType(int xVal, int yVal)
	{
		if ((xVal < 0) || (yVal < 0) || (xVal >= m_size) || (yVal >= m_size))
		{
			return Cell.UNKNOWN_TYPE;
		}
		return m_cellsList.get((xVal * m_size) + yVal).getType();
	}

	// Generating a new cell according to it's type & values.
	public Cell getCell(int xVal, int yVal)
	{
		if ((xVal < 0) || (yVal < 0) || (xVal >= m_size) || (yVal >= m_size))
		{
			return null;
		}
		return new Cell(new Point(xVal, yVal), m_typeString.charAt((xVal * m_size) + yVal));
	}

	// Getter
	public int getSize() { return m_size; }

	//TODO - REMOVE THIS METHOD!! NO OPTION TO PRINT THE MAP!
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
