/**
 * This program is my Machine Problem, to write a calculator design around
 * arrays, stacks and queues, and this is an exception class
 * 
 * @author Jarred Antonii Acedillo
 *
 */
@SuppressWarnings("serial")
public class StackEmptyException extends Exception {

	public StackEmptyException() {
		super("Stack is Empty!");
	}
}
