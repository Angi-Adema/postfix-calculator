package postfixCalculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class PostfixCalculator {
	
	// Method to calculate input postfix expression
	public static int inputPostfix(String postfixInput) {
		
		// Initialize a stack of type Integer to hold operands and calculated value
		Stack<Integer> stack = new Stack<>();
		
		// Initialize a new StringTokenizer object passing in the postfix
		StringTokenizer tokenizer = new StringTokenizer(postfixInput);
		
		// While loop to loop through input tokens
		while (tokenizer.hasMoreTokens()) {
			
			// Retrieve the next token
			String token = tokenizer.nextToken();
			
			// Call number() to verify input is number
			if (number(token)) {
				
				// If the input is a number, push it onto the stack
				stack.push(Integer.parseInt(token));
				
				// If the input is an operator, pop last two numbers from the stack,
				// perform calculation, and push final value back onto the stack
			} else {
				
				// Verify there are at least two numbers saved onto the stack
				if (stack.size() < 2) {
					System.out.println("Invalid postfix expression.");
					return 0;
				}
			
				
				// Pop last two numbers off the stack and calculate based on operator type using switch
				int value2 = stack.pop();
				int value1 = stack.pop();
				
				// Switch holding each operation type pushing calculated number back to the stack
				switch(token) {
					case "+":
						stack.push(value1 + value2);
						break;
					case "-":
						stack.push(value1 - value2);
						break;
					case "*":
						stack.push(value1 * value2);
						break;
					case "/":
						try {
						stack.push(value1 / value2);
						} catch (ArithmeticException e) {
							System.out.println("Cannot divide by 0.");
							return 0;
						}
						break;
					case "%":
						try {
						stack.push(value1 % value2);
						} catch (ArithmeticException e) {
							System.out.println("Cannot divide by 0.");
							return 0;
						}
						break;
					default:
						System.out.println("Invalid token: " + token);
						break;
				}
			}
		}
		// Confirm stack holds only one value, if not then invalid expression
		if (stack.size() != 1) {
			System.out.println("Invalid postfix expression, try again.");
			return 0;
		}
		
		// Return final result of the expression stored on the top of the stack
		return stack.pop();
	}
	
	// Method to validate input as a number
	public static boolean number(String token) {
		
		// Use try/catch to convert token from String into a number, return error if not number
		try {
			Integer.parseInt(token);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}

	}
	
	// Method to handle reading from file
	public static void inputFile(String fileName) {
		
		// Instantiate new file object using file path
		File file = new File(fileName);
		
		// Read in file using try with resources
		try (Scanner scanner = new Scanner(file)) {
			
			// Loop through file text while text exists
			while (scanner.hasNextLine()) {
				
				// Store postfix nextLine() into a variable
				String postfix = scanner.nextLine();
				
				// Confirm the input is not blank
				if (!postfix.isBlank()) {
					
					// Print result of call to inputPostfix() to process the text
					System.out.println(postfix + " result is " + inputPostfix(postfix));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Sorry, file was not found.");
		}
		
	}

	public static void main(String[] args) {
		
		// Initialize an array to hold test postfix expressions
		String[] postfixTestExpressions = {
				"3 8 + 2 *",
				"12 14 + 3 -",
				"2 7 - 3 +",
				"32 5 - 12 +",
				"8 2 / 0 /",
				"12 4 / 2 +",
				"15 2 % 5 +",
				"6 * 3 2 +"
		};
		
		// Call inputPostfix() to evaluate each expression and output the result
		for (String result : postfixTestExpressions) {
			System.out.println(result);
		}

	}

}
