/************************************************************************************
 * Created by Matanel on 28/11/2017.												*
 * This Program gets an input file as argument, with an algorithm name and a board.	*
 * The program search for the goal using the algorithm specified in the input file.	*
 ***********************************************************************************/
public class HW1
{
	// The main function of the program
	public static void main(String [ ] args)
	{
		// Create the map from the input.
		String inputFile = args[0];
		Map map = new Map(inputFile);
		//System.out.println(map); //TODO - REMOVE!!
		map.search();
	}
}