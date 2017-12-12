import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Matanel on 03/12/2017.
 * This class is an implement of the IDS algorithm for search in graph.
 * The algorithm calculates the path to be the shortest.
 */
public class IDS extends Searcher
{
	// Members
	private int m_maxDepth;
	private Cell m_root;
	private String m_solution;
	private Map m_map;
	private HashSet<Cell> m_duplicatesList;
	private int m_cost;

	/**
	 * Constructor.
	 * @param map The map of the world.
	 */
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
	// The search method. Searches for the goal from the start point of the map.
	public void search()
	{
		Cell goal;
		// Search as long as the moves number is under the limit.
		for (int currentDepth = 0; currentDepth < m_maxDepth; currentDepth++)
		{
			// start search from the start to the goal.
			goal = ids(m_root, currentDepth);
			if (goal != null)
			{
				// Print to the file.
				printToOutput(m_solution + " " + m_cost);
				return;
			}
		}
		// Print to the file.
		printToOutput(NO_PATH);
	}

	/**
	 * The recursive search part. Searches for the goal from the start point of the map.
	 * @param node The current node to search from.
	 * @param depth The maximum depth to go to in the recursive call.
	 * @return The cell to go to reach for the goal.
	 */
	private Cell ids(Cell node, int depth)
	{
		String currentSolution;
		Cell goal;
		// This node is the goal - return it.
		if (depth == 0 && node.getType() == Cell.GOAL)
		{
			return node;
		}
		// There is still moves to go - keep search.
		if (depth > 0)
		{
			// Generate the children list of the node.
			ArrayList<Cell> childrenList = m_map.addChildrenList(node);
			for (Cell child : childrenList)
			{
				// Go over all the valid and yet unknown moves.
				if ((m_map.isValidMove(node, child)) && (!m_duplicatesList.contains(child)))
				{
					// Insert the child to the duplicates list.
					m_duplicatesList.add(child);
					// Keep the current solution.
					currentSolution = m_solution;
					// Add this step to the current solution.
					m_solution += addStep(node, child);
					// Keep the current cost.
					m_cost += child.getCost();
					// Call recursively on this child and remove it from the duplicates list.
					goal = ids(child, depth - 1);
					m_duplicatesList.remove(child);
					if (goal != null)
					{
						// This node is part of the solution - return it.
						return goal;
					}
					// This node is not a part of the solution - go back to the last solution and cost.
					m_solution = currentSolution;
					m_cost -= child.getCost();
				}
			}
		}
		// There is no path to the goal - return null.
		return null;
	}
}
