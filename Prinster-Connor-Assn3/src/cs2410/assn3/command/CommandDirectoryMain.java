package cs2410.assn3.command;

import cs2410.assn3.directory.Directory;

import java.util.Scanner;

import static cs2410.assn3.directory.Directory.listContents;
import static cs2410.assn3.directory.Directory.readAverageStars;

public class CommandDirectoryMain
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int inputVal;
        do {
            System.out.println("1.  List Directory contents");
            System.out.println("2.  Add Movie to Directory");
            System.out.println("3.  Display Average Rating");
            System.out.println("4.  Quit Program");
            inputVal = input.nextInt();
            if(inputVal == 1) {
                listContents(false);
            }
            else if(inputVal == 2) {
                Directory.addMovie(false);
            }
            else if(inputVal ==3)
            {
                readAverageStars(false);
            }

        } while(inputVal != 4);
        System.out.println("Quitting program...");
    }


}
