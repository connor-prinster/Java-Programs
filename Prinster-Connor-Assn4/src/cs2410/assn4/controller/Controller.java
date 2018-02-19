package cs2410.assn4.controller;

import cs2410.assn4.model.WriteToFile;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Controller
{
    private ArrayList<Image> imgAL = new ArrayList<>();
    private ArrayList<String> titleAL = new ArrayList<>();
    private ArrayList<String> urlAL = new ArrayList<>();
    private int counter = 0;

    //---------------------------------------------------//
    //           Check if the file is void/empty         //
    //---------------------------------------------------//
    public boolean fileVoid()
    {
        String fileName = "data/images.data";
        try
        {
            FileWriter fw = null;
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            if (br.readLine() == null)
            {
                return true;
            }
        }
        catch(Exception e)
        {
            e.getCause();
        }
        return false;
    }

    //---------------------------------------------------------//
    //           Gets the Objects from their Array List        //
    //---------------------------------------------------------//
    public Image getImage(int idx)
    {
        return imgAL.get(idx);
    }
    public String getTitle(int idx)
    {
        return titleAL.get(idx);
    }
    public String getURL(int idx)
    {
        return urlAL.get(idx);
    }
    //----------------------------------------------------------------------------------------------//
    //           Dealing with the private counter int to avoid unnecessary/accidental changes       //
    //----------------------------------------------------------------------------------------------//
    public void resetCounter()
    {
        counter = 0;
    }
    public int getCounterVal()
    {
        checkCounterBounds();
        return counter;
    }
    public int getArrSize()
    {
        return imgAL.size();
    }
    private void incrementCurLoc()
    {
        counter++;
    }
    private void decrementCurLoc()
    {
        counter--;
    }
    private void checkCounterBounds()
    {
        if(counter == imgAL.size())
        {
            counter = 0;
        }
        else if(counter > imgAL.size())
        {
            counter = 0;
        }
        else if(counter < 0)
        {
            counter = imgAL.size();
        }
    }
    private void setCounterToSizeOfAL()
    {
        counter = imgAL.size();
    }

    //--------------------------------------------------------------//
    //           Essentially a custom ArrayList add function        //
    //--------------------------------------------------------------//
    public void addWithParameters(String readURL, String readTitle)
    {
        imgAL.add(new Image(readURL, 400, 400, true, false));
        titleAL.add(readTitle);
        urlAL.add(readURL);
    }

    //--------------------------------------------------------------------//
    //           Making the Buttons in CustomPane do their duty           //
    //--------------------------------------------------------------------//
    public Image prevImage()
    {
        decrementCurLoc();
        return imgAL.get(getCounterVal());
    }
    public Image nextImage()
    {
        incrementCurLoc();
        return imgAL.get(getCounterVal());
    }
    public Image addImage()
    {
        WriteToFile wrtf = new WriteToFile();
        String blank = "";
        TextInputDialog addPicUrl = new TextInputDialog(blank);
        addPicUrl.setTitle("Insert URL of Image");
        addPicUrl.setHeaderText(null);
        addPicUrl.setContentText("URL:");
        addPicUrl.showAndWait();
        String readURL = addPicUrl.getResult();
        TextInputDialog addPicTitle = new TextInputDialog(blank);
        addPicTitle.setTitle("Insert Title of Image");
        addPicTitle.setHeaderText(null);
        addPicTitle.setContentText("Title:");
        addPicTitle.showAndWait();
        String readTitle = addPicTitle.getResult();
        wrtf.writeImgToFile(readURL, readTitle);
        imgAL.add(getCounterVal(), new Image(readURL, 400, 400, true, false));
        titleAL.add(getCounterVal(), readTitle);
        urlAL.add(readURL);
        setCounterToSizeOfAL();

        return (imgAL.get(getCounterVal()));
    }
    public Image delImage()
    {
        imgAL.remove(getCounterVal());
        titleAL.remove(getCounterVal());
        urlAL.remove(getCounterVal());

        if(getArrSize() == 0)
        {
            String noImgImg = "https://www.us.aspjj.com/sites/aspjj.com.us/files/default_images/No_available_image_3.jpg";
            return new Image(noImgImg, 300, 500, true, false);
        }

        return imgAL.get(getCounterVal());
    }
}
