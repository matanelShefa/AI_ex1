/************************************************************************************
 * Created by Matanel on 28/11/2017.												*
 * This Program gets an input file as argument, with an algorithm name and a board.	*
 * The program search for the goal using the algorithm specified in the input file.	*
 ***********************************************************************************/
public class java_ex1
{
	//private static final String INPUT_FILE = "input.txt";
	private static final String INPUT_FILE = "Test8IDS.txt";

	// The main function of the program
	public static void main(String [ ] args)
	{
		// Create the map from the input.
		Map map = new Map(INPUT_FILE);
		//System.out.println(map); //TODO - REMOVE!!
		map.search();
	}
}