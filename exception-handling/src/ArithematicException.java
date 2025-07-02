public class ArithematicException extends RuntimeException {
    String message;
    ArithematicException(String message){
        super(message);
    }
    public void ErrorMessage(String message) throws Exception {
        System.out.println(message +" not allowed");
    }
}
class Calculator
{
    public int Calc(int input) {
        if (input == 0) {
            throw new ArithematicException("Zero");
        } else if (input < 0) {
            throw new ArithematicException("Negative");
        }
        return input;
    }
}
class MyCalcApp
{
    public static void main(String[] args)  {
        int input=0;
        Calculator calc=new Calculator();
        try{

            System.out.println(calc.Calc(input));
        }
        catch (ArithematicException e)
        {
            System.out.println(e.getMessage());
        }
    }
}


