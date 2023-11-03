public class LinkedStackTest
{   
    public static void main(String[] args)
    {
    	LinkedStack <String> stack = new LinkedStack <String>();
        
        System.out.println("Infix Expression: a b / (c - a) + d + e + \n" 
        		+ "Postfix Expression: " + stack.convertToPostfix("a*b/(c-a)+d*e"));
    }
}