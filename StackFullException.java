/**
 * This program is my Machine Problem, to write a calculator design around
 * arrays, stacks and queues, and this is an exception class
 * 
 * @author Jarred Antonii Acedillo
 *
 */
@SuppressWarnings("serial")
public class StackFullException extends Exception {

	public StackFullException() {
		super("Stack is Full!");
	}
}
