package cs4321.project1;

import cs4321.project1.list.*;
import java.util.*;

/**
 * Provide a comment about what your class does and the overall logic
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
	
	
	private class Pair{
		private ListNode node;
		private int count;
		
		public Pair(ListNode n, int i){
			node = n;
			count = i;
		}
		
		public void decCount(){
			count--;
		}
	}
	
	public double getResult() {
		// TODO fill me in
		return operandStack.pop(); // so that skeleton code compiles
	}

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

	
	
	@Override
	public void visit(AdditionListNode node) {
		// TODO fill me in
		Pair pair = new Pair(node, 2);
		operatorStack.push(pair);
		node.getNext().accept(this);
	}

	@Override
	public void visit(SubtractionListNode node) {
		// TODO fill me in
		Pair pair = new Pair(node, 2);
		operatorStack.push(pair);
		node.getNext().accept(this);
	}

	@Override
	public void visit(MultiplicationListNode node) {
		// TODO fill me in
		Pair pair = new Pair(node, 2);
		operatorStack.push(pair);
		node.getNext().accept(this);
	}

	@Override
	public void visit(DivisionListNode node) {
		// TODO fill me in
		Pair pair = new Pair(node, 2);
		operatorStack.push(pair);
		node.getNext().accept(this);
	}

	@Override
	public void visit(UnaryMinusListNode node) {
		// TODO fill me in
		Pair pair = new Pair(node, 1);
		operatorStack.push(pair);
		node.getNext().accept(this);
	}
}
