import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/************************************************************************************
 * Created by Matanel on 06/12/2017.												*
 * This class is an implement of the A* algorithm for an Informal search in graph.	*
 * The algorithm calculates the next step to me the most efficient, summing the		*
 * real cost with a heuristic prediction.											*
 ***********************************************************************************/
public class AStar extends Searcher
{
	// Finals
	private static final int MAX_COST = 10;

	// Members
	private String NO_PATH = "no path";
	private int m_size;
	private Cell m_root;
	private String m_solution;
	private Map m_map;
	private PriorityQueue<Cell> m_priorityQueue;
	private int m_cost;

	// Constructor
	AStar(Map map)
	{
		m_map = map;
		m_size = map.getSize();
		m_root = map.getCell(0, 0);
		m_priorityQueue = new PriorityQueue<Cell>(new Comparator<Cell>()
		{
			@Override
			public int compare(Cell node1, Cell node2)
			{
				int priority = heuristic(node1) + node1.getCost() - heuristic(node2) - node2.getCost();
				if (priority != 0)
				{
					return priority;
				}
				return node1.getCreationTime() - node2.getCreationTime();
			}
		});

		//m_priorityQueue = new PriorityQueue<Cell>((node1, node2)-> (heuristic(node1) + node1.getCost() - heuristic(node2) - node2.getCost()));
		/*
				((heuristic(node1) + node1.getCost() - heuristic(node2) - node2.getCost()) == 0) ?
				(heuristic(node1) + node1.getCost() - heuristic(node2) - node2.getCost()) :
				(int)(node1.getCreationTime() - node2.getCreationTime()));
		*/ //TODO - REMOVE
		m_solution = "";
		m_cost = 0;
	}

	@Override
	public void search()
	{
		m_priorityQueue.add(m_root);
		while (!m_priorityQueue.isEmpty())
		{
			//TODO - remove the queue print
			System.out.println("====================== Q U E U E ======================");
			for (Cell node : m_priorityQueue)
			{
				System.out.print("{ node: " + node + ", heuristic = " + heuristic(node) + ", cost = " + node.getCost() + ", priority: " + (heuristic(node) + node.getCost()) + " }\n");
			}

			Cell node = m_priorityQueue.poll();
			if ((node.getCost()) > (m_size * m_size * MAX_COST))
			{
				break;
			}
			//System.out.println("node.getCost() = " + node.getCost());
			if (node.getType() == Cell.GOAL)
			{
				//TODO - REMOVE!
				//System.out.println("GOAL FOUND!! HEYDAD!!");
				backTraceSolution(node);
				printToOutput(m_solution + " " + m_cost);
				return;
			}
			if (m_priorityQueue.contains(node))
			{
				continue;
			}
			ArrayList<Cell> childrenList = m_map.addChildrenList(node);
			for (Cell child : childrenList)
			{
				if ((m_map.isValidMove(node, child)) && (!m_priorityQueue.contains(child)))
				{
					child.setCost(child.getCost() + node.getCost());
					child.setParent(node);
					child.setHeuristic(heuristic(child));
					m_priorityQueue.add(child);
				}
			}
		}
		//TODO - REMOVE!
		//System.out.println("NOT FOUND!");
		printToOutput(NO_PATH);
	}

	// Backtrace the path from the goal to the start.
	private String backTraceSolution(Cell node)
	{
		// The start cell - return.
		if (node.getType() == Cell.START)
		{
			return m_solution;
		}
		// Not the start cell - go to the parent.
		else
		{
			m_solution = backTraceSolution(node.getParent());
		}
		// Add the node cost to the path cost.
		m_cost += node.typeToCost(node.getType());
		// Add the step to the solution string.
		return m_solution += addStep(node.getParent(), node);
	}

	// Heuristic on this cell. Calculates the
	private int heuristic(Cell cell)
	{
		return Math.max(m_size - 1 - cell.getXVal(), m_size - 1 - cell.getYVal());
	}
}