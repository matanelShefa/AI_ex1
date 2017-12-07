import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Matanel on 03/12/2017.
 */
public class IDS extends Searcher
{
	// Members
	private String NO_PATH = "no path";
	private int m_maxDepth;
	private Cell m_root;
	private String m_solution;
	private Map m_map;
	private HashSet<Cell> m_duplicatesList;
	private int m_cost;

	// Constructor
	IDS(Map map)
	{
		m_map = map;
		m_maxDepth = (map.getSize() * map.getSize());
		m_root = map.getCell(0, 0);
		m_duplicatesList = new HashSet<>();
		m_solution = "";
		m_cost = 0;
	}

	@Override
	public void search()
	{
		Cell goal;
		for (int currentDepth = 0; currentDepth < m_maxDepth; currentDepth++)
		{
			//TODO - REMOVE!
			//System.out.println("===== Depth: " + currentDepth + " =====");
			goal = ids(m_root, currentDepth);
			if (goal != null)
			{
				printToOutput(m_solution + " " + m_cost);
				return;
			}
		}
		printToOutput(NO_PATH);
	}

	private Cell ids(Cell node, int depth)
	{
		String currentSolution;
		int currentCost = 0;
		Cell goal;
		if (depth == 0 && node.getType() == Cell.GOAL)
		{
			return node;
		}
		if (depth > 0)
		{
			ArrayList<Cell> childrenList = m_map.addChildrenList(node);
			for (Cell child : childrenList)
			{
				if ((m_map.isValidMove(node, child)) && (!m_duplicatesList.contains(child)))
				{
					//TODO - REMOVE
					//System.out.println("Depth: " + depth + ", " + node + " ===> " + child);
					m_duplicatesList.add(child);
					currentSolution = m_solution;
					m_solution += addStep(node, child);
					m_cost += child.getCost();
					goal = ids(child, depth - 1);
					m_duplicatesList.remove(child);
					if (goal != null)
					{
						System.out.println(m_solution);
						System.out.println(depth);
						return goal;
					}
					m_solution = currentSolution;
					m_cost -= child.getCost();
				}
			}
		}
		return null;
	}
}
