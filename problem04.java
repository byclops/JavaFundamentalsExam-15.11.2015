import java.util.*;
import java.util.regex.*;

public class problem04 {
/*LogParser
  https://judge.softuni.bg/Contests/Practice/DownloadResource/1050
 */
	static boolean firstLine = true;
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);	
		TreeMap<String,HashMap<String,ArrayList<String>>> projects = 
				new TreeMap<String,HashMap<String,ArrayList<String>>>();
		HashMap<String,Integer> counter = new HashMap<String,Integer> ();
		Pattern parser = Pattern.compile(
				"\\{\"Project\": \\[\"(?<name>.*?)\"\\], \"Type\": \\[\"(?<type>.*?)\"\\], \"Message\": \\[\"(?<message>.*?)\"\\]\\}");
		String line = scn.nextLine();
		while(!line.equals("END")) {
			Matcher parsedData = parser.matcher(line);
			while (parsedData.find()) {
				String name = parsedData.group("name");
				String type = parsedData.group("type");
				String message = parsedData.group("message");
				if (!projects.containsKey(name)){
					projects.put(name, new HashMap<String,ArrayList<String>>());
					projects.get(name).put("Critical", new ArrayList<String>());
					projects.get(name).put("Warning", new ArrayList<String>());
					counter.put(name, 0);
				}
				projects.get(name).get(type).add(message);
				counter.put(name, counter.get(name)+1);
			}
			line= scn.nextLine();
		}
		projects.entrySet().stream().
		sorted((a,b) -> counter.get(b.getKey()).compareTo(counter.get(a.getKey()))).
		forEach(x->printMessages(x.getKey(),x.getValue()));
	}
	private static void printMessages(String name,HashMap<String,ArrayList<String>> input){
		if(!firstLine){
			System.out.println();
		}
		firstLine = false;
		System.out.println(name+":");
		System.out.printf("Total Errors: %d\n",input.get("Critical").size()+input.get("Warning").size() );
		System.out.printf("Critical: %d\n",input.get("Critical").size());
		System.out.printf("Warnings: %d\n",input.get("Warning").size() );
		System.out.println("Critical Messages:");
		input.get("Critical").stream().
				sorted((a,b)-> a.compareTo(b)).
				sorted((a,b)-> Integer.compare(a.length(), b.length())).forEach(x->System.out.println("--->"+x));
		if (input.get("Critical").size()==0) {
			System.out.println("--->None");
		}
		System.out.println("Warning Messages:");
		input.get("Warning").stream().
				sorted((a,b)-> a.compareTo(b)).
				sorted((a,b)-> Integer.compare(a.length(), b.length())).forEach(x->System.out.println("--->"+x));
		if (input.get("Warning").size()==0) {
			System.out.println("--->None");
		}
	}
}
