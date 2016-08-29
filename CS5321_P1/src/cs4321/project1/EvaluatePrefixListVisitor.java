package cs4321.project1;

import cs4321.project1.list.*;
import java.util.*;

/**
 * Provide a comment about what your class does and the overall logic
 * 
 * @author Your names and netids go here
 */

public class EvaluatePrefixListVisitor implements ListVisitor {
	
	private Stack<ArrayList<String>> operatorStack;
	private Stack<Double> operandStack;
	private ArrayList<String> tmpList;
	
	public EvaluatePrefixListVisitor() {
		// TODO fill me in
		operatorStack = new Stack<ArrayList<String>>();
		operandStack = new Stack<Double>();
		tmpList = new ArrayList<String>();
		tmpList.add("0");
		tmpList.add("0");
	}

	public double getResult() {
		// TODO fill me in
		return operandStack.pop(); // so that skeleton code compiles
	}

	@Override
	public void visit(NumberListNode node) {
		// TODO fill me in
		
		operandStack.push(node.getData());
		if (operatorStack.size() == 0){
			return;
		}
		
		helper(node);
	}
	
	public void helper(NumberListNode node){
		tmpList = operatorStack.pop();
		tmpList.set(1, Integer.toString(Integer.parseInt(tmpList.get(1)) - 1));
		operatorStack.push(tmpList);
		
		String tmpStr = new String();
		tmpStr = tmpList.get(1);
		int tmpInt = Integer.parseInt(tmpStr);
		
		if (tmpInt == 1){
			if (node.getNext() != null){
				node.getNext().accept(this);
			}
		}else{ //此处有问题， tmpInt会变成－1， 不应该。tmpStr == "0"
			operatorStack.pop();
			tmpStr = tmpList.get(0);
			double op1 = operandStack.pop();
			if (tmpStr == "~"){
				operandStack.push(-op1);
			}else{
				double op2 = operandStack.pop();
				switch (tmpStr){
					case "+":
						operandStack.push(op1 + op2);
						break;
					case "-":
						operandStack.push(op1 - op2);
						break;
					case "*":
						operandStack.push(op2 * op1);
						break;
					case "/":
						operandStack.push(op2 / op1);
						break;
				} //switch

			}
			if (operatorStack.size() > 0){
				helper(node);
			}			
			else if (node.getNext() != null){
				node.getNext().accept(this);
			}
		}
		
	}

	@Override
	public void visit(AdditionListNode node) {
		// TODO fill me in
		tmpList.set(0, "+");
		tmpList.set(1, "2");
		operatorStack.push(tmpList);
		node.getNext().accept(this);
	}

	@Override
	public void visit(SubtractionListNode node) {
		// TODO fill me in
		tmpList.set(0, "-");
		tmpList.set(1, "2");
		operatorStack.push(tmpList);
		node.getNext().accept(this);
	}

	@Override
	public void visit(MultiplicationListNode node) {
		// TODO fill me in
		tmpList.set(0, "*");
		tmpList.set(1, "2");
		operatorStack.push(tmpList);
		node.getNext().accept(this);
	}

	@Override
	public void visit(DivisionListNode node) {
		// TODO fill me in
		tmpList.set(0, "/");
		tmpList.set(1, "2");
		operatorStack.push(tmpList);
		node.getNext().accept(this);
	}

	@Override
	public void visit(UnaryMinusListNode node) {
		// TODO fill me in
		tmpList.set(0, "~");
		tmpList.set(1, "1");
		operatorStack.push(tmpList);
		node.getNext().accept(this);
	}
}
