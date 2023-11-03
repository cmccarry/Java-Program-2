import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T> {
    private Node topNode;

    public LinkedStack() {
        topNode = null;
    }

    public void push(T newEntry) {
        topNode = new Node(newEntry, topNode);
    }

    public T pop() {
        T top = peek();
        topNode = topNode.next;
        return top;
    }

    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();
        else
            return topNode.data;
    }

    public boolean isEmpty() {
        return topNode == null;
    }

    public void clear() {
        topNode = null;
    }


    /**
     * Converts a given infix epxression to a postfix expression
     * 
     * A for loop is used to check every character in the infix expression
     * Each character is case checked
     * Operators call getPriority to find the order they are pushed to operatorStack
     * Parenthesis are put through a while loop to calculate the contents within them recursively
     * All other characters such as the letters are pushed to operatorStack
     */
    public String convertToPostfix (String infixExpression) {
        LinkedStack <Character> operatorStack = new LinkedStack <>();
        StringBuilder postfixExpression = new StringBuilder();

        for (int i = 0; i < infixExpression.length(); i++) {
            char ch = infixExpression.charAt(i);
            switch (ch) {
                case ' ':
                    break;
                case '+': case '-': case '*': case '/': case '^':
                    while (!operatorStack.isEmpty() && operatorStack.peek() != '(' && getPriority(ch) <= getPriority(operatorStack.peek())) {
                        postfixExpression.append(operatorStack.pop());
                    }
                    operatorStack.push(ch);
                    break;
                case '(':
                    operatorStack.push(ch);
                    break;
                case ')':
                    while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                        postfixExpression.append(operatorStack.pop());
                    }
                    operatorStack.pop();
                    break;
                default:
                    postfixExpression.append(ch);
                    break;
            }
        }

        while (!operatorStack.isEmpty()) {
            postfixExpression.append(operatorStack.pop());
        }

        return postfixExpression.toString();
    }

    private static int getPriority(char operator) {
        switch (operator) {
            case '+': case '-':
                return 1;
            case '*': case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 9000;
        }
    }
    
    private class Node {
        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

	@Override
	public int evaluatePostfix(String postfix) {
		// TODO Auto-generated method stub
		return 0;
	}
}