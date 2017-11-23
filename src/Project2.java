/**
 * Imported java IO Exception
 */
import java.io.IOException;

/**
 * This is the main class. This is where the 
 * program starts. The main method will create an
 * instance of the UserInterface class and pass
 * is into the instance of the Engine class. The program
 * will then start once the engine start method is called on.
 * @author blayala
 *
 */
public class Project2 {
	
	/**
	 * The Main Method takes in @param args and creates instance of UserInterface class then 
	 * passes it into the instance of the Engine class. Program starts when
	 * the start method in engine runs. Class also @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		UserInterface ui = new UserInterface();
		Engine engine = new Engine(ui);
		engine.start();

	}

}
