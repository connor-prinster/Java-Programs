package cs2410.assn4.model;

import cs2410.assn4.controller.Controller;

import java.io.FileWriter;

public class WriteToFile
{
    //------------------------------------------------------------------------//
    //           Functions for writing a specific image to the file           //
    //------------------------------------------------------------------------//
    public void writeImgToFile(String passedURL, String passedTitle)
    {
        writeImgToFileFull(passedURL, passedTitle);
    }
    private void writeImgToFileFull(String passedURL, String passedTitle)   //the protected function that is an extention of writeImgToFile
    {
        FileWriter fw = null;                                                           //setting up the FileWriter object before actually try/catch
        String fileName = "data/images.data";                                          //setting fileName to write to
        String stringFromImage = passedURL + " " + passedTitle + "\n";   //setting up the string to print to file
        try
        {
            fw = new FileWriter(fileName, true);      //initialize FileWriter
            fw.write(stringFromImage);          //setting up file to write to
            fw.close();                         //close file
        }
        catch(Exception e)
        {
            e.getCause();
        }
    }
    //-----------------------------------------------------------------//
    //          Writes the ENTIRE Image ArrayList to the file          //
    //-----------------------------------------------------------------//
    public void writeArrToFile(Controller cont)
    {
        FileWriter fw = null;                   //setting up the FileWriter object before actually try/catch
        String fileName = "data/images.data";
        String stringFromImage = "";

        try
        {
            fw = new FileWriter(fileName, false);      //initialize FileWriter so it doesn't append
        }
        catch(Exception e)
        {
            e.getCause();
        }
        for(int i = 0; i < cont.getArrSize(); i++)
        {
            stringFromImage += cont.getURL(i) + " " + cont.getTitle(i) + "\n";   //setting up the string to print to file
        }
        try
        {
            fw.write(stringFromImage);
            fw.close();
        }
        catch(Exception e)
        {
            e.getCause();
        }
    }
}
