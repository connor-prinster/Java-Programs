package tenThousandSorted;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] newStr;
        Scanner scanner;
        String basePath = "/Users/connorprinster/Documents/_REPOS/java-programs/java-badge-training/tenThousandSorted/";
        String file = basePath + "sample.txt";

        System.out.println(new File(".").getAbsoluteFile());

        try {
            File textFile = new File(file);
            scanner = new Scanner(textFile);

            while (scanner.hasNext()) {
                arrayList.add(scanner.next());
            }
            String[] alpha = new String[arrayList.size()];

            for(int i = 0; i < arrayList.size(); i++) {
                alpha[i] = arrayList.get(i);
            }

            Alphabetically a = new Alphabetically();
            newStr = a.sort(alpha);

        } catch (FileNotFoundException fne) {
            System.out.println("File not found " + fne);
            newStr = null;
        }

        Main main = new Main();
        main.writeToFileCorrect(newStr, basePath);
    }

    private void writeToFileCorrect(String[] string, String basePath) {

        Main main = new Main();
        String[] back = string;
        String[] forwards = string;

        try {
            BufferedWriter bfFront = new BufferedWriter(new FileWriter(new File(basePath + "outputFront.txt")));
            BufferedWriter bfBack = new BufferedWriter(new FileWriter(new File(basePath + "outputBack.txt")));
            bfFront.write(main.forward(forwards));
            bfBack.write(main.backwards(back));
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    private String forward(String[] string) {
        String divString = "";
        for(int i = 0; i < string.length; i++) {
            String newLine = string[i] + "\n";
            divString += newLine;
        }
        return divString;
    }

    private String backwards(String[] string) {
        String divString = "";
        for(int i = string.length - 1; i > 0; i--) {
            String newLine = string[i] + "\n";
            divString += newLine;
        }
        return divString;
    }
}

class Alphabetically {
    String[] sort(String[] arrayList) {
        int n = arrayList.length;
        String temp;

        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                if (arrayList[i].compareTo(arrayList[j])>0)
                {
                    temp = arrayList[i];
                    arrayList[i] = arrayList[j];
                    arrayList[j] = temp;
                }
            }
        }

        return arrayList;
    }
}
