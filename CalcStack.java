/**
 * This program is my Machine Problem, to write a calculator design around
 * arrays, stacks and queues, and this is the class which contains the
 * implementation of a stack using queues, along with its constructors and
 * methods
 * 
 * @author Jarred Antonii Acedillo
 *
 */
public class CalcStack {
	public MyModifiedQueue queue1;
	public MyModifiedQueue queue2;

	public CalcStack(int size) {
		queue1 = new MyModifiedQueue(size);
		queue2 = new MyModifiedQueue(size);
	}

	public String peek() throws StackEmptyException {
		if (!queue1.peek().isEmpty()) {
			return queue1.peek();
		} else {
			throw new StackEmptyException();
		}
	}

	public void push(String n) throws StackFullException, StackEmptyException {
		if (queue1.isEmpty()) {
			queue1.enqueue(n);
		} else {
			while (!queue1.isEmpty()) {
				queue2.enqueue(queue1.dequeue());
			}
			queue1.enqueue(n);

			while (!queue2.isEmpty()) {
				queue1.enqueue(queue2.dequeue());
			}
		}
	}

	public String pop() throws StackEmptyException, StackFullException {
		if (queue1.isEmpty()) {
			throw new StackEmptyException();
		} else {
			return queue1.dequeue();
		}
	}

	public boolean isEmpty() throws StackEmptyException {
		return queue1.isEmpty() == true;
	}
}
