package sameLogic;

public class CompositionExample {
    public static void main(String[] args) {
        Composition comp = new Composition();
        System.out.println(comp.giveData());
    }
}

class Composition {

    private String rand1;
    private String rand2;
    private ChildOne childOne = new ChildOne();
    private ChildTwo childTwo = new ChildTwo();

    public Composition() {
        this.rand1 = childOne.returnChildOneData();
        this.rand2 = childTwo.returnChildTwoData();
    }

    public String giveData() {
        return "I have two random variables; " + rand1 + " and " + rand2;
    }
}

class ChildOne {
    public String returnChildOneData() {
        int rand = (int)(Math.random() * 100);

        return "my first unique rv is " + rand;
    }
}

class ChildTwo {
    public String returnChildTwoData() {
        int rand = (int)(Math.random() * 1000);

        return "my second unique rv is " + rand;
    }
}
