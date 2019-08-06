public class Main {
    public static void main(String[] args) {
        A myA = new A();
        A myB = new B();

        System.out.println("A myA = new A() returns: "
                + myA.sayName()
                + " when sayName() is called");

        System.out.println("A myB = new B() returns: "
                + myB.sayName()
                + " when sayName() is called");
    }
}
