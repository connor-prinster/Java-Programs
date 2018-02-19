package cs2410.assn1;

public class Main
{
    public static void main(String[] args)
    {
        String fName, lName; //creates new variables
        fName = "Connor"; //assigns the two variables
        lName = "Prinster";
        Main nonStatic = new Main(); //creates the nonstatic variable so it can access other method
        nonStatic.printName(fName, lName); //calls method in Main class
        cs2410.assn1.control.PrintLine.print();
    }

    public void printName(String fName, String lName) //method to print name
    {
        System.out.println("This is the first CS2410 Assignment " +
                fName + " " + lName + " has ever done!"); //concatenations of the variables and strings
    }
}
