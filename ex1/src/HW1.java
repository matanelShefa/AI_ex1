/**
 * Created by Matanel on 28/11/2017.
 */
public class HW1
{
	public static void main(String [ ] args)
	{
		System.out.println(new Point(5, 4));
		System.out.println(new Map(3));
		Parser parser = new Parser();
		parser.parse();
	}
}