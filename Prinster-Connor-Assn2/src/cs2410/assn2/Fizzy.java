package cs2410.assn2;

public class Fizzy
{
    //counter value
    private static int counter = 101;

    //main value
    public static void main(String[] args)
    {
        for(int i = 1; i < counter; i++)
        {
            //if divisible by 3 AND 5, prints FizzBuzz
            if(isFizz(i) && isBuzz(i))
            {
                System.out.println("FizzBuzz");
            }
            //if divisible by 3, prints Fizz
            else if(isFizz(i))
            {
                System.out.println("Fizz");
            }
            //if divisible by 5, prints Buzz
            else if(isBuzz(i))
            {
                System.out.println("Buzz");
            }
            //if divisible by either, prints the number to be checked
            else
            {
                System.out.println(i);
            }
        }
    }

    //Checks to see if divisible by 3
    private static boolean isFizz(int val)
    {
        if(val % 3 == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //checks to see if divisible by 5
    private static boolean isBuzz(int val)
    {
        if(val % 5 == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
