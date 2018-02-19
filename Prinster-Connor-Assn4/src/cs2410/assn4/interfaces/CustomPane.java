package cs2410.assn4.interfaces;

import cs2410.assn4.controller.Controller;
import cs2410.assn4.model.ReadFromFile;
import cs2410.assn4.model.WriteToFile;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CustomPane
{
    //--------------------------------------//
    //           Creating Objects           //
    //--------------------------------------//
    private ImageView iv = new ImageView();
    private Controller cont = new Controller();
    private ReadFromFile rff = new ReadFromFile();
    private String noImgImg = "https://www.us.aspjj.com/sites/aspjj.com.us/files/default_images/No_available_image_3.jpg";
    private Image noImagesInArrayImage = new Image(noImgImg, 400, 300, true, false);
    private HBox headerHBox = new HBox(new Text());

    //--------------------------------------//
    //           Creating Buttons           //
    //--------------------------------------//
    private Button add = new Button("Add");
    private Button next = new Button("Next");
    private Button prev = new Button("Prev");
    private Button delete = new Button("Delete");

    //----------------------------------------//
    //           Creating the Scene           //
    //----------------------------------------//
    private BorderPane borderPane = new BorderPane();
    public Scene customPane()
    {

        rff.initialFillFromFile(cont);
        if(!cont.fileVoid())
        {
            iv.setImage(cont.getImage(cont.getCounterVal()));
        }
        if(cont.getArrSize() == 0)
        {
            iv.setImage(noImagesInArrayImage);
            Text headerText = new Text("NO IMAGES YET");
            headerHBox = new HBox(headerText);
            headerHBox.setAlignment(Pos.CENTER);
        }

        setButtonStatus();
        add.setOnAction(e -> { cpAddImage();});
        next.setOnAction(e -> { cpNextImage();});
        prev.setOnAction(e -> { cpPrevImage();});
        delete.setOnAction(e -> { cpDelImage();});


        if(cont.getArrSize() == 0)
        {
            Text headerText = new Text("NO IMAGES YET");
            Font font = new Font(12);
            headerText.setFont(font);
            headerHBox = new HBox(headerText);
            headerHBox.setAlignment(Pos.CENTER);
            headerHBox.setPrefHeight(50);
        }
        else
        {
            Text headerText = new Text(cont.getTitle(cont.getCounterVal()));
            headerHBox = new HBox(headerText);
            headerHBox.setAlignment(Pos.CENTER);
        }
        HBox hboxButtons = new HBox(prev, next, add, delete);
        hboxButtons.setAlignment(Pos.CENTER);
        hboxButtons.setSpacing(10);
        hboxButtons.setPrefHeight(50);

        iv.setPreserveRatio(true);
        iv.setFitHeight(300);
        iv.setFitWidth(550);

        borderPane.setTop(headerHBox);
        borderPane.setCenter(iv);
        borderPane.setBottom(hboxButtons);
        return (new Scene(borderPane, 600, 400));
    }

    //-------------------------------------------------------------------//
    //           Set Button Alive-ness based on size of arrays           //
    //-------------------------------------------------------------------//
    private void setButtonStatus()
    {
        if (cont.getArrSize() == 1)
        {
            prev.setDisable(true);
            next.setDisable(true);
            delete.setDisable(false);
        }
        else if (cont.getArrSize() > 1)
        {
            prev.setDisable(false);
            next.setDisable(false);
            delete.setDisable(false);
        }
        else
        {
            prev.setDisable(true);
            next.setDisable(true);
            delete.setDisable(true);
        }
    }

    //-----------------------------------------//
    //           Results of Buttons            //
    //-----------------------------------------//
    private void cpAddImage()
    {
        iv.setImage(cont.addImage());
        changeHeaderBox(cont.getTitle(cont.getCounterVal()));
        setButtonStatus();
    }
    private void cpNextImage()
    {
        iv.setImage(cont.nextImage());
        changeHeaderBox(cont.getTitle(cont.getCounterVal()));
    }
    private void cpPrevImage()
    {
        iv.setImage(cont.prevImage());
        changeHeaderBox(cont.getTitle(cont.getCounterVal()));
    }
    private void cpDelImage()
    {
        iv.setImage(cont.delImage());
        setButtonStatus();
        if(cont.getArrSize() == 0)
        {
            borderPane.setTop(new Text("No Images"));
        }

        WriteToFile wtf = new WriteToFile();
        wtf.writeArrToFile(cont);
        if(cont.getArrSize() != 0)
            changeHeaderBox(cont.getTitle(cont.getCounterVal()));
    }

    private void changeHeaderBox(String str)
    {
        Text textToInsert = new Text(cont.getTitle(cont.getCounterVal()));
        textToInsert.setTextAlignment(TextAlignment.CENTER);
        headerHBox = new HBox(textToInsert);
        headerHBox.setAlignment(Pos.CENTER);
        headerHBox.setPrefHeight(50);
        borderPane.setTop(headerHBox);
    }
}
