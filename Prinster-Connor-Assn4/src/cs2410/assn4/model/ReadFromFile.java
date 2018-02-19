package cs2410.assn4.model;

import cs2410.assn4.controller.Controller;

import java.io.*;
import java.util.Scanner;

public class ReadFromFile
{
    //--------------------------------------------------------------------------------------------------------//
    //           When the program first opens with data in the .data file, load all data into array           //
    //--------------------------------------------------------------------------------------------------------//
    public void initialFillFromFile(Controller cont)
    {
        //Controller cont = new Controller();
        String filename = "data/images.data";
        Scanner scan = null;
        try
        {
            scan = new Scanner(new FileReader(filename));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        String readURL, readTitle;
        while (scan.hasNext())
        {
            scan.useDelimiter("\\s");
            readURL = scan.next();
            scan.useDelimiter("\n");
            readTitle = scan.next();
            cont.addWithParameters(readURL, readTitle);
        }
    }
}
