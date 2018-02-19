package cs2410.assn3.gui;

import cs2410.assn3.directory.Directory;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GUIDirectory extends Application
{
    public static void main(String [] args)
    {
        launch(args);
    }
    public void start(Stage primaryStage)
    {
        List<String> choices = new ArrayList<>();
        choices.add("Show Directory");
        choices.add("Add Movie");
        choices.add("Show Average Stars");
        choices.add("Exit");
        ChoiceDialog<String> multChoiceDialog = new ChoiceDialog<>("Show Directory", choices);
        multChoiceDialog.setTitle("Directory Menu");
        multChoiceDialog.setContentText("Make a selection");
        multChoiceDialog.setHeaderText(null);
        do{
            Optional<String> result = multChoiceDialog.showAndWait();
            if(result.isPresent())
            {
                if(multChoiceDialog.getSelectedItem() == "Show Directory")
                {
                    Alert readSelectionAlert = new Alert(Alert.AlertType.INFORMATION);
                    readSelectionAlert.setTitle("Directory List");
                    readSelectionAlert.setHeaderText(null);
                    readSelectionAlert.setContentText(Directory.listContents(true));
                    readSelectionAlert.showAndWait();
                }
                else if(multChoiceDialog.getSelectedItem() == "Add Movie")
                {
                    Alert avgStarAlrt = new Alert(Alert.AlertType.INFORMATION);
                    avgStarAlrt.setTitle("Added Movie");
                    avgStarAlrt.setHeaderText(null);
                    avgStarAlrt.setContentText(Directory.addMovie(true));
                    avgStarAlrt.showAndWait();
                }
                else if(multChoiceDialog.getSelectedItem() ==  "Show Average Stars")
                {
                    Alert avgStarAlrt = new Alert(Alert.AlertType.INFORMATION);
                    avgStarAlrt.setTitle("Average Stars");
                    avgStarAlrt.setHeaderText(null);
                    avgStarAlrt.setContentText(Directory.readAverageStars(true));
                    avgStarAlrt.showAndWait();
                }
            }
        }while(multChoiceDialog.getSelectedItem() != "Exit");
    }
}
