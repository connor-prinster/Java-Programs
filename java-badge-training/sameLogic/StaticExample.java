package sameLogic;

public class StaticExample {
    public static void main(String[] args) {
        System.out.println("The correct name of the Church is " + StaticStuff.correctName() + " which has an integer value of " + StaticStuff.nameToString(StaticStuff.correctName()));
    }
}

class StaticStuff {
    public static String correctName() {
        return "The Church of Jesus Christ of Latter-day Saints";
    }

    public static int nameToString(String string) {
        int val = 0;

        for(int i = 0; i < string.length(); i++) {
            char s = string.charAt(i);
            val += (int)s;
        }

        return val;
    }
}
