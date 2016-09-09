package cs4321.project1;

import cs4321.project1.tree.*;

/**
 * Class for a parser that can parse a string and produce an expression tree. To
 * keep the code simple, this does no input checking whatsoever so it only works
 * on correct input.
 * 
 * An expression is one or more terms separated by + or - signs. A term is one
 * or more factors separated by * or / signs. A factor is an expression in
 * parentheses (), a factor with a unary - before it, or a number.
 * 
 * @author Lucja Kot
 * @author Your names and netids go here
 */
public class Parser {

	private String[] tokens;
	private int currentToken; // pointer to next input token to be processed
	private int bound;

	/**
	 * @precondition input represents a valid expression with all tokens
	 *               separated by spaces, e.g. "3.0 - ( 1.0 + 2.0 ) / - 5.0. All
	 *               tokens must be either numbers that parse to Double, or one
	 *               of the symbols +, -, /, *, ( or ), and all parentheses must
	 *               be matched and properly nested.
	 */
	public Parser(String input) {
		this.tokens = input.trim().split("\\s+");
		this.currentToken = this.tokens.length - 1;
		this.bound = 0;
	}

	/**
	 * Parse the input and build the expression tree
	 * 
	 * @return the (root node of) the resulting tree
	 */
	public TreeNode parse() {
		return expression();
	}

	/**
	 * Parse the remaining input as far as needed to get the next factor
	 * 
	 * @return the (root node of) the resulting subtree
	 */
	private TreeNode factor() {
		// TODO fill me in
		if(this.tokens[this.currentToken].charAt(0) == '('){
			this.bound = this.currentToken + 1;
			while(this.tokens[this.currentToken].charAt(0) != ')'){
				this.currentToken++;
			}
			this.currentToken--;
			return this.expression();
		}
		
		if(this.tokens[this.currentToken].charAt(0) == '-' ){
			this.currentToken++;
			return new UnaryMinusTreeNode(this.factor());
		}
		return new LeafTreeNode(Double.parseDouble(this.tokens[this.currentToken]));
		
	}

	/**
	 * Parse the remaining input as far as needed to get the next term
	 * 
	 * @return the (root node of) the resulting subtree
	 */
	private TreeNode term() {

		// TODO fill me in
		int pCount = 0;
		int temp, temp2, start = this.currentToken;
		for(; this.currentToken > this.bound; this.currentToken--){
			if(this.tokens[this.currentToken].charAt(0) == ')') {
				pCount++;
			}
			if(this.tokens[this.currentToken].charAt(0) == '(' && pCount > 0) {
				pCount--;
			}
			
			if(pCount > 0){
				continue;
			}else{ //pCount == 0
				
				if(this.tokens[this.currentToken].charAt(0) == '*'){
					temp = this.currentToken;
					this.currentToken++; 
					temp2 = this.bound;
					this.bound = start;
					TreeNode right = this.factor();
					this.currentToken = temp - 1;
					this.bound = temp2;
					TreeNode left = this.term(); 
					return new MultiplicationTreeNode(left, right);
				}else if (this.tokens[this.currentToken].charAt(0) == '/'){  //binary minus operator
					temp = this.currentToken;
					this.currentToken++; 
					temp2 = this.bound;
					this.bound = start;
					TreeNode right = this.factor();
					this.currentToken = temp - 1;
					this.bound = temp2;
					TreeNode left = this.term(); 
					return new DivisionTreeNode(left, right);
				}
			}
			
		}
		return this.factor();
		

	}

	/**
	 * Parse the remaining input as far as needed to get the next expression
	 * 
	 * @return the (root node of) the resulting subtree
	 */
	private TreeNode expression() {

		// TODO fill me in
		int pCount = 0;
		int start = this.currentToken, temp, temp2;
		for(; this.currentToken > this.bound; currentToken--){
			if(this.tokens[this.currentToken].charAt(0) == ')') {
				pCount++;
			}
			if(this.tokens[this.currentToken].charAt(0) == '(' && pCount > 0) {
				pCount--;
			}
			
			if(pCount > 0){
				continue;
			}else{ //pCount == 0
				if(this.tokens[this.currentToken].charAt(0) == '+'){
					temp = this.currentToken;
					temp2 = this.bound;
					this.currentToken = start;
					this.bound = temp + 1;
					TreeNode right = this.term();
					this.currentToken = temp - 1;
					this.bound = temp2;
					TreeNode left = this.expression(); 
					return new AdditionTreeNode(left, right);
				}else if (this.currentToken > (this.bound + 1) && this.tokens[this.currentToken].charAt(0) == '-' && 
						 (this.tokens[this.currentToken - 1].charAt(0) != '+') && 
						(this.tokens[this.currentToken - 1].charAt(0) != '-') && (this.tokens[this.currentToken - 1].charAt(0) != '*') &&
						(this.tokens[this.currentToken - 1].charAt(0) != '/') &&
						(this.tokens[this.currentToken - 1].charAt(0) != '(')){  //binary minus operator
					temp = this.currentToken;
					temp2 = this.bound;
					this.currentToken = start;
					this.bound = temp + 1;
					TreeNode right = this.term();
					this.currentToken = temp - 1;
					this.bound = temp2;
					TreeNode left = this.expression(); 
					return new SubtractionTreeNode(left, right);
				}
			}
			
		}
		this.currentToken = start;
		return this.term();

	}
}
