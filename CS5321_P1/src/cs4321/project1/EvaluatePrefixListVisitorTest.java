package cs4321.project1;

import static org.junit.Assert.*;

import org.junit.Test;

import cs4321.project1.list.*;

public class EvaluatePrefixListVisitorTest {
	
	private static final double DELTA = 1e-15;

	@Test
	public void testSingleNumberNode() {
		ListNode n1 = new NumberListNode(1.0);
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n1.accept(v1);
		assertEquals(1.0, v1.getResult(), DELTA);
	}

	@Test
	public void testAdditionSimple() {
		ListNode n1 = new NumberListNode(1.0);
		ListNode n2 = new NumberListNode(2.0);
		ListNode n3 = new AdditionListNode();
		n3.setNext(n2);
		n2.setNext(n1);
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n3.accept(v1);
		assertEquals(3.0, v1.getResult(), DELTA);
		
		ListNode n4 = new NumberListNode(1.0);
		ListNode n5 = new NumberListNode(2.0);
		ListNode n6 = new AdditionListNode();
		n6.setNext(n5);
		n5.setNext(n4);
		EvaluatePrefixListVisitor v2 = new EvaluatePrefixListVisitor();
		n6.accept(v2);
		assertEquals(3.0, v2.getResult(), DELTA);
	}

	@Test
	public void testUnarySimple() {
		ListNode n2 = new NumberListNode(2.0);
		ListNode n3 = new UnaryMinusListNode();
		n3.setNext(n2);
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n3.accept(v1);
		assertEquals(-2.0, v1.getResult(), DELTA);

		ListNode n4 = new NumberListNode(2.0);
		ListNode n5 = new UnaryMinusListNode();
		n5.setNext(n4);
		EvaluatePrefixListVisitor v2 = new EvaluatePrefixListVisitor();
		n5.accept(v2);
		assertEquals(-2.0, v2.getResult(), DELTA);

	}

	@Test
	public void testAdditionMultipleInstances() {
		ListNode n1 = new NumberListNode(1.0);
		ListNode n2 = new NumberListNode(2.0);
		ListNode n3 = new AdditionListNode();
		ListNode n4 = new NumberListNode(4.0);
		ListNode n5 = new MultiplicationListNode();
		n5.setNext(n4);
		n4.setNext(n3);
		n3.setNext(n2);
		n2.setNext(n1);
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n5.accept(v1);
		assertEquals(12.0, v1.getResult(), DELTA);
	}


	@Test
	public void testMultipleInstances() {
		ListNode n11 = new NumberListNode(9.0);
		ListNode n22 = new NumberListNode(3.0);
		ListNode n33 = new DivisionListNode();
		ListNode n1 = new NumberListNode(3.0);
		ListNode n2 = new NumberListNode(2.0);
		ListNode n3 = new MultiplicationListNode();
		ListNode n4 = new NumberListNode(5.0);
		ListNode n5 = new NumberListNode(1.0);
		ListNode n6 = new UnaryMinusListNode();
		ListNode n7 = new MultiplicationListNode();
		ListNode n8 = new AdditionListNode();
		ListNode n9 = new SubtractionListNode();
		n9.setNext(n8);
		n8.setNext(n7);
		n7.setNext(n6);
		n6.setNext(n5);
		n5.setNext(n4);
		n4.setNext(n3);
		n3.setNext(n2);
		n2.setNext(n1);
		n1.setNext(n33);
		n33.setNext(n11);
		n11.setNext(n22);
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n9.accept(v1);
		assertEquals(-2.0, v1.getResult(), DELTA);
	}

}
