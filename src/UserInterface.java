/**
 * imported the InputMismatchException
 */
import java.util.InputMismatchException;
/**
 * Imported the java Scanner Class
 */
import java.util.Scanner;

/**
 * The UserInterface is an interface that is implemented by the Engine. It contains all the methods that
 * are needed and that will be used. Because the Engine specifies this type
 * rather than a specific type of UserInterface, the only methods it will be
 * allowed to use are in here. 
 * 
 * @author blayala
 *
 */
public class UserInterface {

	/**
	 * This is the instance of the Scanner calass
	 */
	private Scanner input;
	
	/**
	 * The constructor instantiates the Scanner class
	 */
	public UserInterface(){
		input = new Scanner(System.in);
	}
	/**
	 * The menu is printed out to the console for the suser to see
	 */
	public void menu(){
		System.out.println("=========================================================================================");
		System.out.println("Please select how to test the program:");
		System.out.println("(1) 20 sets of 100 randomly generated integers");
		System.out.println("(2) Fixed integer values 1-100");
		System.out.println("(3) Exit program");
		System.out.print("Enter choice: ");
	}
	
	
	/**
	 * This takes the users input, validates that it is 
	 * an integer, and returns it to the engine 
	 * @return users input
	 */
	public int getInput(){
		int user = 0;
		try {
			user = input.nextInt();
		} catch (InputMismatchException e) {
            error(1);
			input.nextLine();
        }
		
		return user;
	}
	
	/**
	 * prints out the results of Test 1
	 * @param seriesSwaps the total amount of swaps using series of insertion
	 * @param optimalSwaps the total amount of swaps using optimal method
	 */
	public void printTest1(int seriesSwaps, int optimalSwaps){
		System.out.println("\nAverage swaps for series of insertions: " + (seriesSwaps/20));
		System.out.println("Average swaps for optimal method: " + (optimalSwaps/20));
	}
	
	/**
	 * Prints out the results of Test 2
	 * @param heap the max heap that was created
	 * @param swaps the number of swaps
	 * @param x string to clarify what is being printed 
	 * @param method the method used to create the max heap
	 */
	public void printTest2(MaxHeap heap,int swaps, String x, int method){
		if(method == 1)
			System.out.print("\nHeap built using " + x);
		else if(method == 2)
			System.out.print("\nHeap built using " + x +" method: ");
		printHeap(heap.getHeap());
		System.out.println("\nNumber of swaps: " + swaps);	
	}
	public void printRemoval(MaxHeap heap){
	System.out.print("Heap after 10 removals: ");
	printHeap(heap.getHeap());
	System.out.println();
	}
	
	/**
	 * takes in an array representing the max heap and prints 
	 * out the first 10 indexs starting from 1
	 * @param heap the array representing the max heap
	 */
	public void printHeap(int[] heap) {
		
		for(int i = 1; i <= 10; i++) {
			System.out.print(heap[i] + " ");
		}
	}
	
	/**
	 * Prints out an error message whenever the user inputs an
	 * invalid input
	 * @param error is the type of error the user has made
	 */
	public void error(int error){
		System.out.println("ERROR!!!");
		switch(error){
		case 1:
			System.out.println("Input must be an integer");
			break;
		case 2:
			System.out.println("Input must be either '1' or '2' ");
		}
		System.out.println("Please try again");
	}
	public void endMessage() {
		System.out.println("Thank you for using my program");
	}
}
