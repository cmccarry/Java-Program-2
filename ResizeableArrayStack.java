import java.util.Arrays;
import java.util.EmptyStackException;

public final class ResizeableArrayStack <T> implements StackInterface <T> {
	private T[] stack;    
	private int topIndex; 
	private static final int DEFAULT_CAPACITY = 50;
	
	public ResizeableArrayStack() {
		this(DEFAULT_CAPACITY);
	} 
	
	@SuppressWarnings("unchecked")
	public ResizeableArrayStack (int initialCapacity) {	
		T[] tempStack = (T[]) new Object[initialCapacity];
		stack = tempStack;
		topIndex = -1;
	}
	
	public void push(T newEntry) {
		checkResizing();
		stack[topIndex+ 1] = newEntry;
		topIndex++;
	}
	
	public T pop() {		
		if(isEmpty())
			throw new EmptyStackException();
		else {
			T top = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			return top;
		}
	}
	
	public T peek() {	
		if(isEmpty())
			throw new EmptyStackException();
		else 
			return stack[topIndex];
	}
	
	public boolean isEmpty() {
		return topIndex < 0;
	}

	public void clear() {
		while(topIndex > -1){
			stack[topIndex] = null;
			topIndex--;
		}
	}
	
	// Doubles array length if needed
	private void checkResizing() {
		if(topIndex >= stack.length-1) {
			int newLength = 2* stack.length;
			stack = Arrays.copyOf(stack, newLength);
		}
	}
	
	// Returns int value for operators with 2 int parameters
	private static int mathCalculations(char operator, int a, int b) {
    	int result = -1;
    	
        if (operator == '+')
        	return a + b;
        else if (operator == '-')
        	return a - b;
        else if (operator == '*')
        	return a * b;
        else if (operator == '/')
        	return a / b;
        
        return result;
	}
	
	/** 
	 * Calculates the value for a given postfix expression
	 * 
	 * A while loop checks the character at each position i
	 * If the characyer is an integer it gets pushed to the valueStack array
	 * If the character is an operator the mathCalculations method is called
	 * and the result gets pushed to the valueStack array
	 */
	public int evaluatePostfix(String postfix) {
		ResizeableArrayStack<Integer> valueStack = new ResizeableArrayStack<Integer>(postfix.length());
	    int i = 0;
	    
	    while (i < postfix.length()) {
	    	char nextCharacter = postfix.charAt(i);
	    	switch (nextCharacter) {
	        	case 'a' :
	            	valueStack.push(2);
	                break;
	            case 'b' :
	                valueStack.push(3);
	                break;
	            case 'c' :
	                valueStack.push(4); 
	                break;
	            case 'd' :
	                valueStack.push(5); 
	                break;
	            case 'e' :
	                valueStack.push(6);
	                break;
	            case '+' : case '-' : case '*' : case '/' : case '^' :
	                int operandTwo = (int)valueStack.pop();
	                int operandOne = (int)valueStack.pop();
	                int result = mathCalculations(nextCharacter, operandOne, operandTwo);
	                valueStack.push(result);
	                break;
	            default : break;
	        }
	        i++;
		}
	    
	    return valueStack.peek();
	}

	@Override
	public String convertToPostfix(String infix) {
		// TODO Auto-generated method stub
		return null;
	} 
}