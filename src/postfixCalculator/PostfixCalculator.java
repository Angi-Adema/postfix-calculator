package postfixCalculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class PostfixCalculator {
	
	// Method to calculate input postfix expression
	public static Integer inputPostfix(String postfixInput) {
		
		// Initialize a stack of type Integer to hold operands and calculated value
		Stack<Integer> stack = new Stack<>();
		
		// Initialize a new StringTokenizer object passing in the postfix
		// Used this to handle spaces in expression input to differentiate single and multi digit numbers
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
					return null;
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
							return null;
						}
						break;
					case "%":
						try {
						stack.push(value1 % value2);
						} catch (ArithmeticException e) {
							System.out.println("Cannot divide by 0.");
							return null;
						}
						break;
					default:
						System.out.println("Invalid token: " + token);
						return null;
				}
			}
		}
		// Confirm stack holds only one value, if not then invalid expression
		if (stack.size() != 1) {
			System.out.println("Invalid postfix expression, try again.");
			return null;
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
					
					// Store result in a variable
					Integer result = inputPostfix(postfix);
					
					// Print only if result is not an error or null
					if (result != null) {
					
						// Print result of call to inputPostfix() to process the text
						System.out.println(postfix + " result is " + result);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Sorry, file was not found.");
		}
		
	}

	public static void main(String[] args) {
		// Print statement to indicate main() method results
		System.out.println("~~~ Main method test results ~~~");
		
		// Initialize an array to hold test postfix expressions
		String[] postfixTestExpressions = {
				"3 8 + 2 *",        // 3+8 = 11*2 = 22
				"12 14 + 3 -",      // 12+14 = 26-3 = 23    
				"2 7 - 3 +",        // 2-7 = -5+3 = -2
				"32 5 - 12 +",      // 32-5 = 27+12 = 39
				"8 2 / 0 /",        // 8/2 = 4/0 = "Cannot divide by 0." will return a null result
				"12 4 / 2 +",       // 12/4 = 3+2 = 5
				"15 2 % 5 +",       // 15 % 2 = 1+5 = 6
				"6 * 3 2 +",        // 6 * = "Invalid postfix expression." will return a null result
				"5 5 -"             // 5-5 = 0
		};
		
		// Call inputPostfix() to evaluate each expression and output the result
		for (String expression : postfixTestExpressions) {
			
			// Store result in a variable 
			Integer result = inputPostfix(expression);
			
			// Print only if result is not an error or null
			if (result != null) {
				System.out.println(expression + " result of expression evaluation: " + result);
			}
		}
		
		// Empty line separating data run in main() from data run from file input
		System.out.println();
		
		// Print statement to indicate .txt file results
		System.out.println("~~~ .txt file test results ~~~");
		
		// Read input from file and evaluate
		inputFile("C:/Users/angia/bootcamp/miniprojects/csu-global/Data Structures and Algorithms/postfix-calculator/postfix.txt");

	}

}
