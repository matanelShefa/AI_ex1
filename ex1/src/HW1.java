/**
 * Created by Matanel on 28/11/2017.
 */
public class HW1
{
	public static void main(String [ ] args)
	{
		// Create the map from the input.
		String inputFile = args[0];
		Map map = new Map(inputFile);
		//System.out.println(map); //TODO - REMOVE!!
		map.search();
	}
}