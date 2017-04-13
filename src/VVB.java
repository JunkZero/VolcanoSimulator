import java.util.ArrayList;
import java.util.Scanner;
//Simulator of the Hearthstone card "Volcano" on whatever board you please to input.
public class VVB {
	public static ArrayList<VVBNode> creatures = new ArrayList<VVBNode>(); //Arraylist of all creatures on board.
	public static int[] killed; //Amount of times your first minion is killed.
	public static int numTests = 100000000; //Amount of volcano tests you'd like performed.
	//This method returns the percent chance that each minion in a given list of minions is killed in a volcano.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input data for any amount of minions, starting with their name, then their health. Input DONE at any time to complete your list.");
		String minionName;
		int minionHealth;
		int count = 1;
		do {
			System.out.println("Input name for minion " + count  + ", or 'DONE' to stop.");
			minionName = sc.next();
			if(minionName.equals("DONE"))
				break;
			System.out.println("Input health for minion " + count++);
			minionHealth = sc.nextInt();
			creatures.add(new VVBNode(minionHealth, minionName));
		} while (!minionName.equals("DONE"));
		killed = new int[creatures.size()];
		for(int i = 0; i < numTests; i++) {
			ArrayList<VVBNode> tempCreatures = new ArrayList<VVBNode>();
			for(VVBNode minion : creatures) {
				tempCreatures.add(new VVBNode(minion));
			}
			volcano(tempCreatures);
			for(int j = 0; j < tempCreatures.size(); j++) {
				if(tempCreatures.get(j).data <= 0)
					killed[j]++;
			}
		}
		for(int k = 0; k < killed.length; k++) {
			System.out.println("Minion " + k + " | Name: " + creatures.get(k).name + " | Health: " + creatures.get(k).data + " | Died " + (((float)killed[k]) / numTests * 100) + "% of the time | Lived " + (100.0 - ((float)killed[k]) / numTests * 100) + "% of the time");
		}
	}
	//A helper method that volcanoes.
	public static void volcano(ArrayList<VVBNode> board) {
		for(int i = 1; i <= 15; i++) {
			int x = (int)(Math.random() * board.size());
			if(board.get(x).data <= 0) 
				i--;
			else
				board.get(x).data--;
		}
	}
}
