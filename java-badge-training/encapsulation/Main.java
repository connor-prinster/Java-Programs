package encapsulation;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello");

        GoodEncapsulation gEnc = new GoodEncapsulation(0);
        gEnc.decrementCounter();
        gEnc.incrementCounter();
    }
}

class GoodEncapsulation {
    private int counter;

    GoodEncapsulation(int counter) {
        this.counter = counter;
    }

    public void incrementCounter() {
        this.counter++;
    }

    public void decrementCounter() {
        this.counter--;
    }
}

class BadEncapsulation {
    BadEncapsulation() {

    }
}
