import java.util.ArrayList;

/**
 * Created by Matanel on 03/12/2017.
 */
public class IDS
{
	// Members
	private ArrayList<Cell> m_cellsList;
	private int m_size;
	private int m_maxDepth;

	// Constructor
	IDS(Map map)
	{
		m_cellsList = map.getCellsList();
		m_size = map.getSize();
		m_maxDepth = (m_size * m_size);
	}

	Cell idsRoot(Cell root)
	{
		Cell goal = null;
		for (int currentDepth = 0; currentDepth < m_maxDepth; currentDepth++)
		{
			goal = ids(root, currentDepth);
			if (goal != null)
			{
				return goal;
			}
		}
		return null;
	}

	Cell ids(Cell node, int depth)
	{
		Cell goal = null;
		if (depth == 0 && node.getType() == 'G')
		{
			return node;
		}
		if (depth > 0)
		{
			ArrayList<Cell> childrenList = node.getChildrenList();
			for (Cell child : childrenList)
			{
				goal = ids(child, depth - 1);
				if (goal != null)
				{
					return goal;
				}
			}
		}
		return null;
	}
}
