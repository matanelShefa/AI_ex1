/**
 * Created by Matanel on 28/11/2017.
 */
public class Cell
{
	// Members
	private Point m_point;

	// Constructor
	public Cell(Point point) { m_point = point; }

	// Print the cell.
	public String toString() { return "(" + getXVal() + "," + getYVal() + ")"; }

	// Getter
	Point getPoint() { return m_point; }

	// Getter
	public int getXVal() { return m_point.getXVal(); }

	// Getter
	public int getYVal() { return m_point.getYVal(); }
}
