import java.util.Scanner;
import java.util.regex.*;
public class problem02 {
/*Softuni Defence System
  https://judge.softuni.bg/Contests/Practice/DownloadResource/1049 
 */
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		Pattern parser = Pattern.compile(
				"(?<name>[A-Z][a-z]+).*?(?<alchohol>[A-Z][a-z]*[A-Z]).*?(?<quantity>\\d+)L");
		double totalQuantity = 0;
		String line = scn.nextLine();
		while (!line.equals("OK KoftiShans")) {
			Matcher parsed = parser.matcher(line);
			while (parsed.find()){
				int currentQuantity = Integer.parseInt(parsed.group("quantity"));
				System.out.printf("%s brought %s liters of %s!\n", 
						parsed.group("name"),
						parsed.group("quantity"),
						parsed.group("alchohol").toLowerCase());
				totalQuantity += currentQuantity;
			}
			line = scn.nextLine();
		}
		System.out.printf("%.3f softuni liters", totalQuantity*0.001);
	}
}
