/**
 * This program is my Machine Problem, to write a calculator design around
 * arrays, stacks and queues, and this is the class which contains the
 * implementation of a stack using arrays, along with its constructors and
 * methods
 * 
 * @author Jarred Antonii Acedillo
 *
 */
public class MyModifiedStack {
	private String[] data;
	private int top;
	private int size;

	public MyModifiedStack(int size) {
		this.size = size;
		this.data = new String[size];
		this.top = -1;
	}

	public String peek() throws StackEmptyException {
		if (!isEmpty()) {
//			System.out.println("Top Element: " + data[top]);
			return data[top];
		} else {
			throw new StackEmptyException();
		}

	}

	public String pop() throws StackEmptyException {
		if (!isEmpty()) {
			int x = top;
			top--;
//			System.out.println("Popped element: " + data[x]);
			return data[x];

		} else {
			throw new StackEmptyException();
		}
	}

	public void push(String n) throws StackFullException {
		if (!isFull()) {
			top++;
			data[top] = n;
//			System.out.println("Pushed Element: " + n);
		} else {
			throw new StackFullException();
		}
	}

	public boolean isEmpty() {
		return (top < 0);
	}

	public boolean isFull() {
		return (top == size - 1);
	}
}
