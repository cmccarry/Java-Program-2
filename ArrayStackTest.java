public class ArrayStackTest
{
    public static void main(String[] args)
    {
        ResizeableArrayStack <Integer> stack = new ResizeableArrayStack <Integer>();
        
        System.out.println("Postfix expression: a b * c a - / d e * + \n"
        		+ "Evaluate postfix: " + stack.evaluatePostfix("ab*ca-/de*+"));
    }
}