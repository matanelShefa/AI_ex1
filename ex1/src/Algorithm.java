/**
 * Created by Matanel on 05/12/2017.
 * This interface use to define an algorithm.
 * Every algorithm that claim to fit for this program - has to implement this interface.
 */
public interface Algorithm
{
	// The search method. Searches for the goal from the start in the matrix.
	void search();

	/**
	 * Add one step to the solution string.
	 * @param from The source cell.
	 * @param to The destination cell.
	 * @return String that tells the step need to be done in order to go
	 * from the source cell to the destination cell.
	 */
	String addStep(Cell from, Cell to);

	/**
	 * Print the solution string to the output file.
	 * @param stringToPrint The solution string to print.
	 */
	void printToOutput(String stringToPrint);
}
