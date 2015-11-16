import java.util.Scanner;

public class problem01 {
 /* Palatka Conf
  https://judge.softuni.bg/Contests/Practice/DownloadResource/1048 
 */
	
	static int beds = 0;
	static int meals =0;
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int people = Integer.parseInt(scn.nextLine());
		int n = Integer.parseInt(scn.nextLine());
	
		for (int i=0; i<n; i++){
			String[] input = scn.nextLine().split(" ");
			switch (input[0]){
			case "tents": addTents(input); break;
			case "food": addFood(input); break;
			case "rooms": addRooms(input); break;
			}
		}
		int bedsOverview =  beds - people;
		int foodOverview =  meals - people;
		if (bedsOverview>=0) {
			System.out.printf("Everyone is happy and sleeping well. Beds left: %d\n", bedsOverview);
		}
		else {
			System.out.printf("Some people are freezing cold. Beds needed: %d\n", bedsOverview*-1);
		}
		if (foodOverview>=0){
			System.out.printf("Nobody left hungry. Meals left: %d\n", foodOverview);
		}
		else {
			System.out.printf("People are starving. Meals needed: %d\n", foodOverview*-1);
		}
	}
	private static void addRooms(String[] input){
		switch(input[2]){
			case "single": beds +=1*Integer.parseInt(input[1]); break;
			case "double": beds +=2*Integer.parseInt(input[1]); break;
			case "triple": beds +=3*Integer.parseInt(input[1]); break;
		}
	}
	private static void addFood(String[] input){
		switch(input[2]){
			case "musaka": meals +=2*Integer.parseInt(input[1]); break;
		}	
	}
	private static void addTents(String[] input){
		switch(input[2]){
			case "normal": beds +=2*Integer.parseInt(input[1]); break;
			case "firstClass": beds +=3*Integer.parseInt(input[1]); break;
		}
	}
}
