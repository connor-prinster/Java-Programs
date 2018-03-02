package cs2410.assn5;

import cs2410.assn5.interfaces.ToolPane;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import static java.lang.Math.abs;


public class Main extends Application {

    //=================================================//
    //          CREATING THE GLOBAL VARIABLES          //
    //=================================================//

    //------------------------------//
    //   Creating Initial Objects   //
    //------------------------------//
    private ToolPane tP = new ToolPane();
    private Pane dP = new Pane();
    private Shape currentShape = new Ellipse(); //meant to be the inital object, subject to change

    //-----------------------------//
    //   Creating Initial Points   //
    //-----------------------------//
    private double initX;
    private double initY;
    private double origX;
    private double origY;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //=================================================================================================================================================
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    //=================================================================================================================================================
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //--------------------------------------------------------------//
        //          Creating The Scene to be Shown (Initially)          //
        //--------------------------------------------------------------//
        BorderPane mainPane = new BorderPane();                                                      //creating the BorderPane object
        mainPane.setTop(tP);                                                                        //setting the toolPane object to be at the top
        mainPane.setCenter(dP);                                                                    //setting the draw pane to the center
        Scene rootScene = new Scene(mainPane, 700, 700);                              //creating a scene object
        Rectangle clip = new Rectangle(0, 0, rootScene.getWidth(), rootScene.getHeight());  //creating the clip
        dP.setClip(clip);                                                  //implementing the clip

        //-----------------------------------------------------------------//
        //          Making Sure the MousePressed issue is handled          //
        //-----------------------------------------------------------------//
        dP.setOnMousePressed(this::drawMethod);

        //---------------------------------//
        //          Action Events          //
        //---------------------------------//
        tP.setFillPickerAction(e -> {
            if(tP.editBtnSelected())
            {
                if(currentShape.getFill() != null)
                {
                    currentShape.setFill(tP.getFillPickerValue());
                }
            }
        });
        tP.setStrokePickerAction(e -> {
            if(tP.editBtnSelected())
            {
                currentShape.setStroke(tP.getStrokePickerValue());
            }
        });
        tP.setStrokeSizeAction(e -> {
            if(tP.editBtnSelected())
            {
                currentShape.setStrokeWidth(tP.getStrokeSizeValue());
            }
        });

        //------------------------------------------------------//
        //          Finishing Up The Scene to be Shown          //
        //------------------------------------------------------//
        primaryStage.setTitle("Somewhat Decent Drawing Tablet");
        primaryStage.setScene(rootScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    //=================================================================================================================================================
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    //=================================================================================================================================================

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //=========================================================//
    //          What Happens When Buttons Are Toggled          //
    //=========================================================//
    private void drawMethod(MouseEvent mouseEvent) {
        initX = mouseEvent.getX();
        initY = mouseEvent.getY();
        if(tP.ellBtnSelected())
        {
            Ellipse ell = new Ellipse();
            currentShape = ell;
            dP.getChildren().add(currentShape);
            dP.setOnMouseDragged(this::drawEll);
            editShapeHandler(ell);
        }
        else if(tP.rectBtnSelected())
        {
            Rectangle newRect = new Rectangle();
            currentShape = newRect;
            dP.getChildren().add(currentShape);
            dP.setOnMouseDragged(this::drawRec);
            editShapeHandler(newRect);
        }
        else if(tP.freeBtnSelected())
        {
            Path newPath = new Path();
            currentShape = newPath;
            dP.getChildren().add(newPath);
            newPath.getElements().add(new MoveTo(initX, initY));
            dP.setOnMouseDragged(this::drawPath);
            editShapeHandler(newPath);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //=================================================================//
    //          Handling Data When the Edit Button is Pressed          //
    //=================================================================//
    private void editShapeHandler(Shape passShape)
    {
        passShape.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                currentShape = passShape;    //when clicked, the currently editable shape is changed to the clicked on shape
                if(tP.editBtnSelected())
                {
                    passShape.toFront(); //sends the clicked on shape to the front
                    origX = event.getX();
                    origY = event.getY();
                    tP.setStrokeSizeValue((int) passShape.getStrokeWidth());
                    tP.setStrokePickerValue((Color) passShape.getStroke());
                    if(passShape.getFill() != null)
                    {
                        tP.setFillPickerValue((Color) passShape.getFill());
                    }
                }
                if(tP.eraseBtnSelected())
                {
                    dP.getChildren().remove(passShape);
                }
            }
        });

        passShape.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                if(tP.editBtnSelected())
                {
                    passShape.setTranslateX(passShape.getTranslateX() + event.getX() - origX);
                    passShape.setTranslateY(passShape.getTranslateY() + event.getY() - origY);
                }
            }
        });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //============================================================//
    //          Methods for Drawing the Different Shapes          //
    //============================================================//
    //------------------//
    //      Ellipse     //
    //------------------//
    private void drawEll(MouseEvent me)
    {
        if(tP.ellBtnSelected())
        {
            Ellipse ell = (Ellipse) currentShape;
            updateStyle(ell);
            ell.setCenterX((initX + me.getX()) / 2);
            ell.setRadiusX(abs(me.getX() - initX) / 2);
            ell.setCenterY((initY + me.getY()) / 2);
            ell.setRadiusY(abs(me.getY() - initY) / 2);
        }
    }

    //--------------------//
    //      Rectangle     //
    //--------------------//
    private void drawRec(MouseEvent me)
    {
        if(tP.rectBtnSelected())
        {
            Rectangle rec = (Rectangle) currentShape;
            updateStyle(rec);
            rec.setX((initX > me.getX() ? me.getX() : initX));      //if the newly dragged X value is less than the initial X, we set the shape's X value to the getY, else et to initX
            rec.setY((initY > me.getY() ? me.getY() : initY));      //if the newly dragged Y value is less than the initial Y, we set the shape's Y value to the getY, else et to initY
            rec.setWidth(abs(initX - me.getX()));    //measure side to side
            rec.setHeight(abs(initY - me.getY()));   //measure top to bottom
        }
    }

    //---------------//
    //      Path     //
    //---------------//
    private void drawPath(MouseEvent me)
    {
        if(tP.freeBtnSelected())
        {
            Path path = (Path) currentShape;
            updateStyle(path);
            path.setFill(null);
            path.getElements().add(new LineTo(me.getX(), me.getY()));
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //====================================================//
    //          Updating the Style of the Shapes          //
    //====================================================//

    private void updateStyle(Shape passShape)
    {
        passShape.setStroke(tP.getStrokePickerValue());
        passShape.setStrokeWidth(tP.getStrokeSizeValue());
        passShape.setFill(tP.getFillPickerValue());
    }
}