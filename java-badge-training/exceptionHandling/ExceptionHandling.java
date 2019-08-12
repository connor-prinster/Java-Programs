package exceptionHandling;

import java.io.FileWriter;
import java.io.IOException;

public class ExceptionHandling {

    public static void main(String[] args) {
        ExceptionHandling main = new ExceptionHandling();
//        main.checkedException();
//        main.uncheckedException();
        main.causeUncatchableError();
    }

    // checked means that it is found at compile time, not during.
    private void checkedException() {
        try {
            FileWriter fw = new FileWriter("../");
        } catch (IOException e) {
            System.out.println("IO Error " + e);
        }
    }

    // unchecked is a runtime exception. To quote G4G, only the
    // Error and RuntimeExceptions are unchecked. Every other
    // throwable class is checked.
    private void uncheckedException() {
        try {
            int x = 0;
            int y = 10;
            int z = y / x;
            System.out.println(z);
        } catch (ArithmeticException ae) {
            System.out.println("Error: " + ae);
        }
    }

    private void causeUncatchableError() {
        Boolean willPotentiallyCauseError = true;
        String error;
        int count = 999999997;
        while(willPotentiallyCauseError) {
            try {
                StringBuilder sb = new StringBuilder(count);
                for(int i = 0; i < count; i++) {
                    sb.append('*');
                }
            } catch (OutOfMemoryError e) {
                error = e.toString();
                System.out.println(error + ". Ran out of memory with " + count + " characters");
            }
        }
        System.out.println("Something went wrong");
    }
}





