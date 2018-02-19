package cs2410.assn3.directory;

import javafx.scene.control.TextInputDialog;

import java.io.*;
import java.util.Scanner;

public class Directory {
    public static String listContents(boolean fromGUI) {
        String strReturn = "";
        String filename = "data/cs2410-directory.data";
        Scanner scan = null;
        try {
            scan = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String title, released, rating, stars, mth, dy, yr, nwl = " ";
        while (scan.hasNext()) {
            scan.useDelimiter("\n");
            title = scan.next();
            scan.useDelimiter("\\s");
            rating = scan.next();
            released = scan.next();
            stars = (scan.next() + " stars");
            mth = scan.next();
            dy = scan.next();
            yr = scan.next();
            String date = (mth + "/" + dy + "/" + yr + "");
            if(!fromGUI)
            {
                System.out.printf("%-30s", title);
                System.out.printf("%-8s", rating);
                System.out.printf("%-7s", released);
                System.out.printf("%7s", stars);
                System.out.printf("%13s", date);
                System.out.println(" ");
            }
            strReturn += (String.format(title, "%-30s") + " " + String.format(rating, "%-8s") + " " + String.format(released, "%-7s") + " " + String.format(stars, "7s") + " " + String.format(date, "%13s") + "\n");
        }
        return strReturn;
    }

    public static void printToFile(String printInfo)
    {
        String filePath = "data/cs2410-directory.data";
        BufferedWriter bw = null;
        FileWriter fw = null;
        try{
            fw = new FileWriter(filePath, true);
            bw = new BufferedWriter(fw);
            //bw.write(printableTitle);
            bw.write(printInfo);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            try{
                if(bw != null)
                    bw.close();
                if(fw != null)
                    fw.close();
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

    public static String readAverageStars(boolean fromGUI)
    {
        String filename = "data/cs2410-directory.data";
        float numOfStars = 0;
        float avgStars = 0;
        Scanner scan = null;
        try{
            scan = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        String title, released, rating, stars, mth, dy, yr, nwl = " ";
        while(scan.hasNext()) {
            scan.useDelimiter("\n");
            title = scan.next();
            scan.useDelimiter("\\s");
            rating = scan.next();
            released = scan.next();
            stars = scan.next();
            mth = scan.next();
            dy = scan.next();
            yr = scan.next();
            ++numOfStars;
            avgStars += Float.parseFloat(stars);
        }
        avgStars /= numOfStars;
        if(!fromGUI)
        {
            System.out.print("Your average movie ratings is ");
            System.out.printf("%.3f", avgStars);
            System.out.printf(" stars\n");
        }
        String rtStar = ("Your average movie rating is " + avgStars + " stars");
        return rtStar;
    }

    public static String addMovie(boolean fromGUI)
    {
        String addMovieConf = "";
        if(!fromGUI)
        {
            String title, released, rating, stars, mth, dy, yr, nwlChar;
            Scanner input = new Scanner(System.in);
            System.out.print("Title: ");
            title = input.nextLine();
            System.out.print("Year Released: ");
            released = input.nextLine();
            System.out.print("Rating: ");
            rating = input.nextLine();
            System.out.print("Stars: ");
            stars = input.nextLine();
            System.out.print("Last Watched: ");
            System.out.print("Month (1-12): ");
            mth = input.nextLine();
            System.out.print("Day (1-31): ");
            dy = input.nextLine();
            System.out.print("Year(YYYY): ");
            yr = input.nextLine();

            String printInfo = title + " \n" + rating + " " + released + " " + stars + " " + mth + " " + dy + " " + (yr + "\n");

            Directory.printToFile(printInfo);
            //Save confirmation
            System.out.println("The following movie has been added to the directory:");
            //String addMovieConf = (title + " " + "(" + released + ")" + " " + rating + "\nStars: " + stars + "\nLast Watched: " + mth + "/" + dy + "/" + yr + "\n");
            System.out.print(title + " " + "(" + released + ")" + " " + rating + "\nStars: " + stars + "\nLast Watched: " + mth + "/" + dy + "/" + yr + "\n");
        }
        else
        {
            String title = "", released = "", rating = "", mth = "", dy = "", yr = "";
            int stars = 0;
            String blank = "";

            TextInputDialog dialogIpt = new TextInputDialog(blank);
            dialogIpt.setTitle("Add a Movie");
            dialogIpt.setHeaderText(null);
            dialogIpt.setContentText("Title");
            dialogIpt.showAndWait();
            title = dialogIpt.getResult();

            TextInputDialog releaseIpt = new TextInputDialog(blank);
            releaseIpt.setTitle("Add a Movie");
            releaseIpt.setHeaderText(null);
            releaseIpt.setContentText("Release Date");
            releaseIpt.showAndWait();
            released = releaseIpt.getResult();

            TextInputDialog ratingIpt = new TextInputDialog(blank);
            ratingIpt.setTitle("Add a Movie");
            ratingIpt.setHeaderText(null);
            ratingIpt.setContentText("Rating");
            ratingIpt.showAndWait();
            rating = ratingIpt.getResult();

            TextInputDialog starsIpt = new TextInputDialog(blank);
            starsIpt.setTitle("Add a Movie");
            starsIpt.setHeaderText(null);
            starsIpt.setContentText("Stars");
            starsIpt.showAndWait();
            stars = Integer.parseInt(starsIpt.getResult());

            TextInputDialog mthIpt = new TextInputDialog(blank);
            mthIpt.setTitle("Add a Movie");
            mthIpt.setHeaderText(null);
            mthIpt.setContentText("Last Watched Month");
            mthIpt.showAndWait();
            mth = mthIpt.getResult();

            TextInputDialog dayIpt = new TextInputDialog(blank);
            dayIpt.setTitle("Add a Movie");
            dayIpt.setHeaderText(null);
            dayIpt.setContentText("Last Watched Day");
            dayIpt.showAndWait();
            dy = dayIpt.getResult();

            TextInputDialog yearIpt = new TextInputDialog(blank);
            yearIpt.setTitle("Add a Movie");
            yearIpt.setHeaderText(null);
            yearIpt.setContentText("Last Watched Year");
            yearIpt.showAndWait();
            yr = yearIpt.getResult();

            String printInfo = (title + " \n" + rating + " " + released + " " + stars + " " + mth + " " + dy + " " + (yr + "\n"));
            Directory.printToFile(printInfo);
            addMovieConf = ("The following movie has been added to the directory:\n" + title + " " + "(" + released + ")" + " " + rating + "\nStars: " + stars + "\nLast Watched: " + mth + "/" + dy + "/" + yr + "\n");

        }
        return addMovieConf;
    }
}
