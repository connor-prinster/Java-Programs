/*
* Create a class that has 4 fields and construct the class
* with variations of one required field and the others are
* optional.  Use constructor chaining as an example.
*/

class Main {

    public static void main(String[] args) {
        Overload overload = new Overload();
        overload.print();
    }
}

class Overload {
    int one;
    int two;
    int three;
    int four;

    Overload() {
        this(1);
    }

    Overload(int one) {
        this(one, 2);

    }

    Overload(int one, int two) {
        this(one, two, 3);
    }

    Overload(int one, int two, int three) {
        this(one, two, three, 4);
    }

    Overload(int one, int two, int three, int four) {
        System.out.println("final constructor\n");
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
    }

    protected void print() {
        System.out.println("printed with:" + "\none = " + this.one + "\ntwo = " + this.two + "\nthree = " + this.three + "\nfour = " + this.four);

    }
}