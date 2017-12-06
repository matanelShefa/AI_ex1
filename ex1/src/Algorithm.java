/**
 * Created by Matanel on 05/12/2017.
 */
public interface Algorithm
{
	// The search method. Searches for the goal from the start in the matrix.
	public void search();

	// Add one step to the solution string.
	public String addStep(Cell from, Cell to);

	// Print the solution string to the output file.
	public void printToOutput(String stringToPrint);
}
