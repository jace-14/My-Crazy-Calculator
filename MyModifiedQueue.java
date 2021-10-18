/**
 * This program is my Machine Problem, to write a calculator design around
 * arrays, stacks and queues, and this is the class which contains the
 * implementation of a queue using stacks, along with its constructors and
 * methods of the program
 * 
 * @author Jarred Antonii Acedillo
 *
 */
public class MyModifiedQueue {
	public MyModifiedStack stack1;
	public MyModifiedStack stack2;

	public MyModifiedQueue(int size) {
		this.stack1 = new MyModifiedStack(size);
		this.stack2 = new MyModifiedStack(size);
	}

	public String peek() throws StackEmptyException {
		if (!stack1.isEmpty()) {
			String a = stack1.peek();
//			System.out.println("Top Element: " + a);
			return a;
		} else {
			throw new StackEmptyException();
		}
	}

	public void enqueue(String n) throws StackFullException, StackEmptyException {
		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}

		stack1.push(n);
//		System.out.println("Queued Element: " + n);

		while (!stack2.isEmpty()) {
			stack1.push(stack2.pop());

		}
	}

	public String dequeue() throws StackEmptyException, StackFullException {
		if (stack1.isEmpty() && stack2.isEmpty()) {
			throw new StackEmptyException();
		}

//		if (stack2.isEmpty()) {
//			while (!stack1.isEmpty()) {
//				stack2.push(stack1.pop());
//			}
//			System.out.println("Dequeue successful!");
//		}

		String a = stack1.peek();
		stack1.pop();
//		System.out.println("Dequeued Element: " + a);

		return a;
	}

	public boolean isEmpty() throws StackEmptyException {
		return stack1.isEmpty() == true;
	}
}
