package com.example.t00533766.calculator;

import java.util.Scanner;
import java.util.Stack;

public class Evaluator {
	private final static char ADD = '+';
	private final static char SUBTRACT = '-';
	private final static char MULTIPLY = '*';
	private final static char DIVIDE = '/';

	private Stack<String> integerStack;
	private Stack<String> operatorStack;



	/**
	 * Sets up this evalutor by creating a new stack.
	 */
	public Evaluator() {
		integerStack = new Stack<String>();
		operatorStack = new Stack<>();
	}
	public double calculate(String x){
		return evaluate(convertToPostFix(x));
	}

	public String convertToPostFix(String infix){

		String number = "";
		for(int i = 0;i<infix.length();i++){
			char current = infix.charAt(i);

			switch (current){
				case '+':
				case '-':
					operatorStack.push(String.valueOf(current));
					if(number!="") {
						integerStack.push(number);
						number = "";
					}
					break;
				case'*':
				case '/':
					if(operatorStack.size()>=1) {
						operatorStack.add(operatorStack.size() - 1, String.valueOf(current));
					}
					else{
						operatorStack.push(String.valueOf(current));
					}
					if(number!="") {
						integerStack.push(number);
						number = "";
					}
					break;
				default:
					number+=current;
					if(i == infix.length()-1){
						integerStack.push(number);
					}
					break;
			}
		}

		String postFix = "";
		while(!integerStack.empty()){

			if(!operatorStack.empty()) {
				postFix = operatorStack.pop() + " "+postFix;

				if (!operatorStack.empty()) {
					if (operatorStack.peek().equals("*") || operatorStack.peek().equals("/")) {
						postFix = operatorStack.pop() +" "+ postFix;
					}
				}
			}
			postFix = integerStack.pop()+" "+postFix;
		}


		System.out.println(postFix+": as");
		return postFix;
	}


	public double evaluate(String expr)
	{
		double op1, op2, result = 0;
		String token;
		Scanner parser = new Scanner(expr);

		try {

			while (parser.hasNext()) {
				token = parser.next();

				if (isOperator(token)) {
					op2 = Double.parseDouble((integerStack.pop()));
					op1 = Double.parseDouble((integerStack.pop()));
					result = evaluateSingleOperator(token.charAt(0), op1, op2);
					integerStack.push(String.valueOf(result));
				} else
					integerStack.push(token);
			}
		}catch (Exception c){
		}
		System.out.println("AAAA" +result);
		return result;
	}



	private boolean isOperator(String token) {
		return (token.equals("+") || token.equals("-") ||
				token.equals("*") || token.equals("/"));
	}

	private double evaluateSingleOperator(char operation, double op1, double op2) {
		double result = 0;

		switch (operation) {
			case ADD:
				result = op1 + op2;
				break;
			case SUBTRACT:
				result = op1 - op2;
				break;
			case MULTIPLY:
				result = op1 * op2;
				break;
			case DIVIDE:
				result = op1 / op2;
		}

		return result;
	}
}

