/**
 * Created by ${user} on 28/11/2017.
 */
public class Point
{
	// Members
	private static int m_xVal;
	private static int m_yVal;

	// Constructor
	public Point(int xVal, int yVal)
	{
		m_xVal = xVal;
		m_yVal = yVal;
	}

	// Print the point.
	public String toString() { return "(" + m_xVal + "," + m_yVal + ")"; }

	// Getter
	public int getXVal() { return m_xVal; }

	// Getter
	public int getYVal() { return m_yVal; }
}
