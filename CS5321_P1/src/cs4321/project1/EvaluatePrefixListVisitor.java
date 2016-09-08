package cs4321.project1;

import cs4321.project1.list.*;
import java.util.*;

/**
 * Evaluate a prefix list obtained from preorder traversal of a binary arithematic expressino
 * tree.
 * 
 * @author Your names and netids go here
 */

public class EvaluatePrefixListVisitor implements ListVisitor {
	
	private Stack<Pair> operatorStack;
	private Stack<Double> operandStack;
	
	public EvaluatePrefixListVisitor() {
		// TODO fill me in
		operatorStack = new Stack<Pair>();
		operandStack = new Stack<Double>();
	}
	
	/**wrapper class, object of this class is the basic unit on operatorStack*/
	private class Pair{
		private ListNode node;
		private int count;
		
		/**Constructor
		 * Precondition: n is not null and i is in [1,2]*/
		public Pair(ListNode n, int i){
			node = n;
			count = i;
		}
		
		/**mutator
		 * subtract 1 from count */
		public void decCount(){
			count--;
		}
	}
	
	/**Return the result after evaluation*/
	public double getResult() {
		// TODO fill me in
		return operandStack.pop(); // so that skeleton code compiles
	}

	/**visit NumberListNode objects*/
	@Override
	public void visit(NumberListNode node) {
		// TODO fill me in
		
		operandStack.push(node.getData());
		if (operatorStack.size() != 0){
			Pair pair = operatorStack.peek();
			pair.decCount();
			
			if (pair.count == 0){
				helper();
			}
			if (node.getNext() != null){
				node.getNext().accept(this);
			}
		}
	}
	
	/**a recursive helper method for visit(NumberListNode node)*/
	public void helper(){
		double second = operandStack.pop();
		double first =0;  
		// in case of unary list node 
		Pair pair= operatorStack.pop();
		if(pair.node instanceof UnaryMinusListNode) {  
			operandStack.push(-second);
		}else{
			first = operandStack.pop();		
			if(pair.node instanceof AdditionListNode) {
				operandStack.push(first + second);
			} else if (pair.node instanceof DivisionListNode) {
				operandStack.push(first / second);
			} else if (pair.node instanceof MultiplicationListNode) {
				operandStack.push(first * second);
			} else if (pair.node instanceof SubtractionListNode) {
				operandStack.push(first - second);
			} 
		}
		if (operatorStack.isEmpty()){
			return;
		}else{
			pair = operatorStack.peek();
			pair.decCount();
			if (pair.count == 0){
				helper();
			}
		}
	}

	/**visit AdditionListNode objects*/
	@Override
	public void visit(AdditionListNode node) {
		// TODO fill me in
		Pair pair = new Pair(node, 2);
		operatorStack.push(pair);
		node.getNext().accept(this);
	}

	/**visit SubtractionListNode objects*/
	@Override
	public void visit(SubtractionListNode node) {
		// TODO fill me in
		Pair pair = new Pair(node, 2);
		operatorStack.push(pair);
		node.getNext().accept(this);
	}

	/**visit MultiplicationListNode objects*/
	@Override
	public void visit(MultiplicationListNode node) {
		// TODO fill me in
		Pair pair = new Pair(node, 2);
		operatorStack.push(pair);
		node.getNext().accept(this);
	}

	/**visit DivisionListNode objects*/
	@Override
	public void visit(DivisionListNode node) {
		// TODO fill me in
		Pair pair = new Pair(node, 2);
		operatorStack.push(pair);
		node.getNext().accept(this);
	}

	/**visit UnaryMinusListNode objects*/
	@Override
	public void visit(UnaryMinusListNode node) {
		// TODO fill me in
		Pair pair = new Pair(node, 1);
		operatorStack.push(pair);
		node.getNext().accept(this);
	}
}
