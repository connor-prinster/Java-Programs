package sameLogic;

public class InheritanceExample {
    public static void main(String[] args) {
        InheritanceExample ie = new InheritanceExample();
        ie.print();
    }

    public void print() {
        Abnormal abnormal = new Abnormal("Dunkey", 30);
        UberNormal uberNormal = new UberNormal("Steve", 50);

        System.out.println(abnormal.stringify());
        System.out.println(uberNormal.stringify());
    }
}

class Normal {

    String name;
    int age;

    public Normal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "My name is " + name + " and I am " + age + "\n";
    }
}

class Abnormal extends Normal {

    private int abnormalValue;

    public Abnormal(String name, int age) {
        super(name, age);
        this.abnormalValue = generateAbnormal();
    }

    public String stringify() {
        return super.toString() + "my abnormal value is " + abnormalValue + "\n";
    }

    int generateAbnormal() {
        int val = (int)(Math.random() * 100) % 3;
        switch(val) {
            case 1:
                return (int)(Math.random() * 10);
            case 2:
                return (int)(Math.random() * 100);
            case 3:
                return (int)(Math.random() * 1000);
            default:
                return 0;
        }
    }
}

class UberNormal extends Normal {

    private int uberNormal;

    UberNormal(String name, int age) {
        super(name, age);
        this.uberNormal = returnUber();
    }

    public String stringify() {
        return super.toString() + "I'm so normal that my 'abnormal' value is " + uberNormal + "\n";
    }

    private int returnUber() {
        int val = (int)(Math.random() * 100) % 2;
        switch(val) {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                return -1;
        }
    }
}
