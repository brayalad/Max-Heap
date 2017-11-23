/**
 * Imported Java Random Class
 */
import java.util.Random;
/**
 * Imported Java IO Functions and classes
 */
import java.io.*;

/**
 * This is the Engine class. Everything that the program does happens here.
 * The Engine takes in date from the UserInterface class and uses that data 
 * to create Max Heaps and conduct two test which the user can choose.
 * Engine will also Manipulate the data depending on the User's input.
 * @author blayala
 *
 */
public class Engine {
	/**
	 * Instance of the User Interface class;
	 */
	private UserInterface ui;
	/**
	 * Instance of the Random Class
	 */
	private Random rand;
	/**
	 * Instance of the Max Heap created using series of insertion
	 */
	private MaxHeap insert;
	/**
	 * Instance of the Max Heap created using the optimal method
	 */
	private MaxHeap optimal;
	/**
	 * This is the array of data that will be passed into the constructor
	 * of a Max Heap when user decides to create using optimal method
	 */
	private int [] data;
	/**
	 * This is the amount of total swaps made while creating max heap 
	 * using series of insertion
	 */
	private int totalInsertSwaps;
	/**
	 * This is the amount of total swaps made while creating max heap 
	 * using the optimal method
	 */
	private int totalOptimalSwaps;
	/**
	 * Instance of the Print Writer class
	 */
	private PrintWriter pw;
	/**
	 * Name of the file where the data report of the program will be stored.
	 */
	private String fileName;
	
	/**
	 * This is the constructor of the Engine class which takes in @param ui
	 * and initializes the User Iterface, Random Class, both total swaps, file
	 * name, and the print writer class
	 */
	public Engine(UserInterface ui) {
		this.ui = ui;
		rand = new Random();
		totalInsertSwaps = 0;
		totalOptimalSwaps = 0;
		fileName = "output.txt";
		try {
			pw = new PrintWriter(new FileWriter(fileName));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * This is the start method which begins the run loop. It takes in
	 * an input from the user through the user interface and uses that 
	 * to run either the Test 1 or Test 2
	 * @throws IOException
	 */
	public void start() throws IOException{
		boolean run = true;
		//loop runs until user enters 3
		while(run){
			ui.menu();
			int input = 0;
			try {
				input = ui.getInput();
			} catch (Exception e) {
				input = 4;
				e.printStackTrace();
			}
			
			if(input != 0){
				saveFileMenu(fileName, true, input );
				switch(input){	
				//runs test 1
				case 1: test1(); 
						break;
				//runs test 2
				case 2: test2();
						break;
				//ends the program
				case 3: ui.endMessage();
						System.exit(0);
						break;
				// displays error if input isnt valid
				default: ui.error(2);
						break;
				}
				//total number of swaps made are counted
			totalInsertSwaps = totalOptimalSwaps = 0;
			}
		}
	}
	
	/**
	 * This test uses 20 sets of 100 randomly generated integers to build 20 max heaps using both methods. In
	 * this test it informs the user of the average swaps needed for building
	 * these max heaps
	 * @throws IOException
	 */
	private void test1() throws IOException{
		// for loop creates heap using both methods
		for(int i = 0; i < 20; i++ ){
			insert(1);
			optimal(1);
			}
		// calls UI to print tp console
		print(1);
	}
	
	/**
	 * This test uses an array of 100 fixed values from 1,2,3,....,100
	 * It then creates max heaps using both methods
	 * 
	 * @throws IOException
	 */
	private void test2() throws IOException{
		// only 1 heap array is made
			insert(2);
			optimal(2);
			print(2);
	}
	
	/**
	 * the insert method is used to create a max heap using a 
	 * series of insertions. Every time a new number is added,
	 * swaps are made to endure that the tree says a heap
	 * 
	 * @param method is what method is used to create max heap
	 */
	private void insert(int method){
		insert = new MaxHeap();
		switch(method){
		// Creation of insert method heap for test 1
		case 1:	
			for(int i = 0; i < 100; i++) {
				int randomNumber = rand.nextInt(100)+1;
				if(!insert.contains(randomNumber))
				insert.add(randomNumber);
			else
				i--;	
			}
			break;
		//Creation of insert heap for test 2
		case 2:
			for(int i = 0; i < 100; i++) {
					insert.add(i+1);
			}
			break;
		default:
			ui.error(2);
			break;
		}
		totalInsertSwaps = totalInsertSwaps + insert.getSeriesSwaps();
	}
	
	/**
	 * The optimal method creates a max heap my first passing an array
	 * of data (in this case Integers) and "Heapifying" the whole array using 
	 * the reheap method in the MaxHeap class
	 * 
	 * @param method is what method is used to create max heap
	 */
	private void optimal(int method){
		data = new int[100];
		switch(method){
		//Creation of optimal heap for test 1
		case 1: 
			for(int i = 0; i < 100; i++) {
				int randomNumber = rand.nextInt(100)+1;
				if(!checkDuplicates(randomNumber))
					data[i] = randomNumber;
				else
					i--;
			}
			break;
		//Creation of optimal heap for test 1
		case 2: 
			for(int i = 0; i < 100; i++) 
					data[i] = i + 1;
			break;
			
			}
		optimal = new MaxHeap(data);
		totalOptimalSwaps = totalOptimalSwaps + optimal.getOptimalSwaps();
	}
	
	/**
	 * This method calls on the User Interface to print out the 
	 * results of the test to the user
	 * @param test is which test results are being printed
	 * @throws IOException
	 */
	private void print(int test) throws IOException{
		switch(test){
		//prints test 2
		case 1:
			ui.printTest1(totalInsertSwaps, totalOptimalSwaps);
			saveTest1(totalInsertSwaps, totalOptimalSwaps);
			break;
		//prints test 2
		case 2:
			ui.printTest2(insert, totalInsertSwaps, "series of insertions: ", 1);
			saveTest2(insert, totalInsertSwaps, "series of insertions: ", 1);
			for(int i = 0; i < 10; i++)
				insert.removeMax();
			ui.printRemoval(insert);
			saveRemoval(insert);
			ui.printTest2(optimal, totalOptimalSwaps, "optimal", 2);
			saveTest2(optimal, totalOptimalSwaps, "optimal", 2);
			for(int i = 0; i < 10; i++)
				optimal.removeMax();
			ui.printRemoval(optimal);
			saveRemoval(insert);
			break;
		}
	}
	
	/**
	 * Method checks for duplicates
	 * @param entry is the entry being checked for duplicate
	 * @return contains
	 */
	private boolean checkDuplicates(int entry){
		boolean contains = false;
		//traverses through array
		for(int i = 0; i < data.length; i++){
			if(data[i] == entry)
				contains = true;
		}
		return contains;
	}
	
	/**
	 * This method saves the menu into a txt file report.
	 * It saves the menu and the users input and writes it into
	 * a file for the user to read later
	 * @param file name of the file
	 * @param reset to amend file
	 * @param input the users input
	 * @throws IOException
	 */
	private void saveFileMenu(String file, boolean reset, int input) throws IOException {
		
		pw = new PrintWriter(new FileWriter(file,reset));
		
		pw.println("=========================================================================================");
		pw.println("Please select how to test the program:");
		pw.println("(1) 20 sets of 100 randomly generated integers");
		pw.println("(2) Fixed integer values 1-100");
		pw.println("(3) Exit Program");
		if(input == 1 || input == 2)
			pw.println("Enter choice: " + input);
		else if(input == 3) {
			pw.println("Enter choice: " + input);
			pw.println("\nProgram was ended by user");
		}
		else{
			pw.println("Enter choice: " + input);
			pw.println("\nERROR");
			pw.println("Invalid input was inputed. The user was asked to input a valid input");
		}
		pw.close();
		
	}
	
	/**
	 * This method saves results of test 1 into a txt file report.
	 * 
	 * @param seriesSwaps amount of series of insertion swaps
	 * @param optimalSwaps amount of optimal method swaps
	 * @throws IOException
	 */
	private void saveTest1  (int seriesSwaps, int optimalSwaps) throws IOException {
		
		pw = new PrintWriter(new FileWriter(fileName,true));
		
		pw.println("\nAverage swaps for series of insertions: " + (seriesSwaps/20));
		pw.println("Average swaps for optimal method: " + (optimalSwaps/20));
		pw.close();
	}
	
	/**
	 * This method saves results of test 2 into a txt file report.
	 * 
	 * @param heap the max heap
	 * @param swaps amount of swaps made
	 * @param x a string
	 * @param method which method was used to create heap
	 * @throws IOException
	 */
	private void saveTest2 (MaxHeap heap,int swaps, String x, int method) throws IOException{
		
		pw = new PrintWriter(new FileWriter(fileName,true));
		
		if(method == 1)
			pw.print("\nHeap built using " + x);
		else if(method == 2)
			pw.print("\nHeap built using " + x +" method: ");
		
		for(int i = 1; i <= 10; i++) {
			pw.print(heap.getHeapIndexData(i) + " ");
		}
		pw.println("\nNumber of swaps: " + swaps);	
		pw.close();
	}
	
	/**
	 * This method saves results of the removal of integers
	 *  into a txt file report.
	 * 
	 * @param heap the heap being tested
	 * @throws IOException
	 */
	private void saveRemoval(MaxHeap heap) throws IOException{
		
		pw = new PrintWriter(new FileWriter(fileName,true));
		
		pw.print("Heap after 10 removals: ");
		for(int i = 1; i <= 10; i++) {
			pw.print(heap.getHeapIndexData(i) + " ");
		}
		pw.println();
		pw.close();
	}
	
	
	
}
