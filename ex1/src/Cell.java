import java.util.ArrayList;

/**
 * Created by Matanel on 28/11/2017.
 */
public class Cell
{
	public static final char START = 'S';
	public static final char ROAD = 'R';
	public static final char GOAL = 'G';
	public static final char DESERT = 'D';
	public static final char HILL = 'H';
	public static final char WATER = 'W';

	// Members
	private Point m_point;
	private char m_type;
	private int m_cost;
	private ArrayList<Cell> m_childrenList;

	// Constructor
	public Cell(Point point, char type)
	{
		m_point = point;
		m_type = type;

		switch (m_type)
		{
			case ROAD:
				m_cost = 1;
				break;
			case DESERT:
				m_cost = 3;
				break;
			case HILL:
				m_cost = 10;
				break;
			default:
				m_cost = 0;
		}
/*
		// Create the children list for the cells.
			int offsetArray[][] = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1}, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
			ArrayList<Cell> childrenList = new ArrayList<>();
			for (int offset[] : offsetArray)
			{
				Cell child = Map.getCell(m_point.getXVal() + offset[0], m_point.getYVal() + offset[1]);
				if (child != null)
				{
					childrenList.add(child);
				}
			}
			m_childrenList =  childrenList;
		}
		*/
	}

	// Print the cell.
	public String toString() { return "(" + getXVal() + "," + m_type + "," + getYVal() + ")"; }

	// Getter
	Point getPoint() { return m_point; }

	// Getter
	public int getXVal() { return m_point.getXVal(); }

	// Getter
	public int getYVal() { return m_point.getYVal(); }

	// Getter
	public char getType() { return m_type; }

	// Getter
	public ArrayList<Cell> getChildrenList() { return m_childrenList; }

	// Getter
	public int getCost() { return m_cost; }

	// Setter
	public void setType(char type) { m_type = type; }

	// Setter
	public void setChildrenList(ArrayList<Cell> childrenList) { m_childrenList = childrenList; }
}
