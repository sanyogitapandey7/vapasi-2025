public class MyArithException extends RuntimeException {
    String message;
    MyArithException(String message){
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
            throw new MyArithException("Zero");
        } else if (input < 0) {
            throw new MyArithException("Negative");
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
        catch (MyArithException e)
        {
            System.out.println(e.getMessage());
        }
    }
}