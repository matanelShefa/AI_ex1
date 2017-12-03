import java.util.ArrayList;

/**
 * Created by Matanel on 28/11/2017.
 */
public class Cell
{
	public enum Type { START, GOAL, ROAD, DESERT, HILL, WATER } //TODO REMOVE?

	// Members
	private Point m_point;
	private char m_type;

	private ArrayList<Cell> m_childrenList;

	// Constructor
	public Cell(Point point, char type)
	{
		m_point = point;
		m_type = type;
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

	// Setter
	public void setType(char type) { m_type = type; }

	// Setter
	public void setChildrenList(ArrayList<Cell> childrenList) { m_childrenList = childrenList; }
}
