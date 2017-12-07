import java.util.ArrayList;
import java.util.Objects;

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
	public static final char UNKNOWN_TYPE = ' ';

	// Members
	private Point m_point;
	private char m_type;
	private int m_cost;
	private Cell m_parent;
	private int m_heuristic;
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
			case GOAL:
				m_cost = 0;
				break;
			default:
				m_cost = (int)Double.POSITIVE_INFINITY;
		}
	}

	@Override
	public int hashCode()
	{
		return m_point.getXVal() * 17 + m_point.getYVal();
	}

	public boolean equals(Object other)
	{
		return (((Cell)other).getXVal() == m_point.getXVal()) &&
				(((Cell)other).getYVal() == m_point.getYVal());
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

	// Getter
	public Cell getParent() { return m_parent; }

	// Setter
	public void setType(char type) { m_type = type; }

	// Setter
	public void setChildrenList(ArrayList<Cell> childrenList) { m_childrenList = childrenList; }

	// Setter
	public void setCost(int cost) { m_cost = cost; }

	// Setter
	public void setParent(Cell parent) { m_parent = parent; }

	// Setter
	public void setHeuristic(int heuristicValue) { m_heuristic = heuristicValue; }
}
