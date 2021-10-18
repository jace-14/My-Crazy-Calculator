
/**
 * This program is my Machine Problem, to write a calculator design around arrays, stacks and queues,
 * and this is the class which contains the constructors and methods of the
 * program, utilizing previous classes which implements stacks and queues
 * 
 * @author Jarred Antonii Acedillo
 *
 */
import java.util.Scanner;

public class MyCrazyCalculator {
	private CalcStack stack;

	public MyCrazyCalculator(int length) {
		stack = new CalcStack(length);

	}

	// main method
	@SuppressWarnings("resource")
	public static void main(String[] args) throws StackFullException, StackEmptyException {
		Scanner s = new Scanner(System.in);
		String expression = null, postfix;
		double evaluated;

		MyCrazyCalculator calc = new MyCrazyCalculator(10000);

		System.out.println("My Crazy Calculator");

		do {
			System.out.println(
					"Enter Infix Expression: (please include spaces in between each operator, operand, and parenthesis)");
			expression = s.nextLine();

			postfix = calc.infixToPostfix(expression);

			if (postfix == "Invalid Statement")
				System.out.println(postfix);
		} while (postfix == "Invalid Statement");

		String delims = "[ ]+";
		String[] tokens = postfix.trim().split(delims);

		System.out.print("Postfix Expression: ");
		for (String t : tokens)
			System.out.print(t + " ");

		evaluated = calc.evaluate(postfix);
		if (evaluated != evaluated) //
			System.out.println("\nCannot  Divide by 0");
		else
			System.out.println("\nEvaluated Postfix Expression: " + evaluated);

	}

	// convert infix expression to post-fix expression
	public String infixToPostfix(String n) throws StackFullException, StackEmptyException {
		// check validity of infix expression
		if (isValidExpression(n) == false) {
			return "Invalid Statement";
		} else {
			String exp = n;
			String postfix = "";

			for (int i = 0; i < exp.length(); i++) { // scan all elements of the expression
				char currentChar = exp.charAt(i);
				if (!isOperator(currentChar)) { // check if current char is not an operator
					if (currentChar == '(') {
						stack.push(Character.toString(currentChar)); // push into stack if current char is an open
																		// parenthesis
					} else if (currentChar == ')') {
						while (!stack.isEmpty()) { // pop all elements in the stack until stack is empty or until the
													// top element is an open parenthesis
							String x = stack.pop();
							char z = x.charAt(0);
							if (z != '(') {
								postfix = postfix + " " + x;
							} else {
								break;
							}
						}
					} else {
						postfix += Character.toString(currentChar); // append to postfix expression
					}
				} else { // if current char is an operator
					if (stack.isEmpty()) {
						stack.push(Character.toString(currentChar)); // push operator in stack if stack is empty
					} else {
						// compare precedence of current operation and operation in the stack
						if (plusOrMinus(currentChar) && plusOrMinusString(stack.peek())) {
							postfix = postfix + " " + stack.pop();
							String s = Character.toString(currentChar);
							stack.push(s);
							if (i == (exp.length() - 1)) {
								while (!stack.isEmpty()) {
									postfix = postfix + " " + stack.pop();
								}
							}
						}
						// compare precedence of current operation and operation in the stack
						else if (multiplyOrDivide(currentChar) && multiplyOrDivideString(stack.peek())) {
							postfix = postfix + " " + stack.pop();
							stack.push(Character.toString(currentChar));
							if (i == (exp.length() - 1)) {
								while (!stack.isEmpty()) {
									postfix = postfix + " " + stack.pop();
								}
							}
						}
						// compare precedence of current operation and operation in the stack
						else if (plusOrMinus(currentChar) && multiplyOrDivideString(stack.peek())) {
							postfix = postfix + " " + stack.pop();
							stack.push(Character.toString(currentChar));
							if (i == (exp.length() - 1)) {
								while (!stack.isEmpty()) {
									postfix = postfix + " " + stack.pop();
								}
							}
						}
						// compare precedence of current operation and operation in the stack
						else if (multiplyOrDivide(currentChar) && plusOrMinusString(stack.peek())) {
							stack.push(Character.toString(currentChar));
							if (i == (exp.length() - 1)) {
								while (!stack.isEmpty()) {
									postfix = postfix + " " + stack.pop();
								}
							}
						} else {
							stack.push(Character.toString(currentChar));
						}
					}
				}

			}

			while (!stack.isEmpty())
				// pop remaining elements in stack and append to post-fix expression
				postfix = postfix + " " + stack.pop();

			return postfix;
		}
	}

	// evaluate given post-fix expression
	@SuppressWarnings("null")
	public double evaluate(String n) throws StackEmptyException, StackFullException {

		String delims = "[ ]+";
		String[] tokens = n.trim().split(delims); // trim and split given string

		for (int i = 0; i < tokens.length; i++) { // loop through all elements
			if (isNumberString(tokens[i])) {
				stack.push(tokens[i]); // push element in stack if it is a number
			} else {
				// pop 2 elements from stack, parse into double, and assign into variables for
				// computation
				String v1 = stack.pop();
				double value1 = Double.parseDouble(v1);
				String v2 = stack.pop();
				double value2 = Double.parseDouble(v2);

				// compute
				switch (tokens[i]) {
				case "+":
					double res = value2 + value1;
					String result = String.valueOf(res);
					stack.push(result);
					break;

				case "-":
					double res2 = value2 - value1;
					String result2 = String.valueOf(res2);
					stack.push(result2);
					break;

				case "*":
					double res3 = value2 * value1;
					String result3 = String.valueOf(res3);
					stack.push(result3);
					break;
				case "/":
					if (value1 == 0) {
						return Double.NaN; // return NaN ( Not a Number ) if dividing by 0
					} else {
						double res4 = value2 / value1;
						String result4 = String.valueOf(res4);
						stack.push(result4);
						break;
					}
				}
			}
		}

		double x = Double.parseDouble(stack.pop());
		return x;

	}

	// Utility Functions

	// determine if given char is a + or -
	public boolean plusOrMinus(char c) {
		switch (c) {
		case '+':
			return true;
		case '-':
			return true;
		default:
			return false;
		}
	}

	// determine if given char is * or /
	public boolean multiplyOrDivide(char c) {
		switch (c) {
		case '*':
			return true;
		case '/':
			return true;
		default:
			return false;
		}
	}

	// determine if given string is + or -
	public boolean plusOrMinusString(String s) {
		switch (s) {
		case "+":
			return true;
		case "-":
			return true;
		default:
			return false;
		}
	}

	// determine if given string is * or /
	public boolean multiplyOrDivideString(String s) {
		switch (s) {
		case "*":
			return true;
		case "/":
			return true;
		default:
			return false;
		}
	}

	// determine if given char is an operator
	public boolean isOperator(char c) {
		switch (c) {
		case '+':
		case '-':
		case '*':
		case '/':
			return true;
		default:
			return false;
		}
	}

	public boolean isOperatorString(String s) {
		switch (s) {
		case "+":
		case "-":
		case "*":
		case "/":
			return true;
		default:
			return false;
		}
	}

	// determine if given char is a number
	public boolean isNumber(char c) {
		return ((int) c) >= 48 && ((int) c) <= 57;
	}

	// determine if given string is a number
	public boolean isNumberString(String currentChar) {
		try {
			Double.parseDouble(currentChar);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// determine if given infix expression is valid
	public boolean isValidExpression(String n) {
		int parentheses = 0, numbersCount = 0, operatorsCount = 0;
		boolean previousIsOperator = false, previousIsOpenParenthesis = false;

		String delims = "[ ]+";
		String[] tokens = n.trim().split(delims);

		for (String t : tokens) {
			if (isNumberString(t)) // checks if current String is a number and increments if true
				numbersCount++;
			if (isOperatorString(t)) // checks if current String is an operator and increments if true
				operatorsCount++;
		}

		// checks if expression starts or ends with an operator
		if (isOperator(n.charAt(0)) || isOperator(n.charAt(n.length() - 1))) {
			return false;
		}

		for (char c : n.toCharArray()) {
			// checks if current char is white space
			if (c == ' ')
				continue;

			if (c == '(') { // checks if current char is an open parenthesis
				parentheses++;
				previousIsOpenParenthesis = true;
				continue;
			} else if (c == ')') { // checks if current char is a closing parenthesis
				if (parentheses <= 0 || previousIsOperator) {
					return false;
				}
				parentheses--;
			} else if (isOperator(c)) { // checks if current char is an operator
				if (previousIsOperator || previousIsOpenParenthesis)
					return false;
				previousIsOperator = true;
				continue;
			} else if (!isNumber(c)) { // checks if current char is a number
				return false;
			}

			previousIsOperator = false;
			previousIsOpenParenthesis = false;
		}
		if (parentheses != 0)
			return false; // return false if there are extra opening or closing parentheses
		if (previousIsOperator || previousIsOpenParenthesis)
			return false; // return false if there are successive operators or parentheses
		if (numbersCount - operatorsCount != 1)
			return false; // return false if there are not enough operators or operands

		return true; // return true if expression is valid after checking all conditions
	}
}
