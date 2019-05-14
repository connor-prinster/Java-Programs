public class InstanceOf {
    public static void main(String[] args) {
        InstanceOf io = new InstanceOf();

        io.testInstanceOfPracticeClass1();
        io.testInstanceOfPracticeClass2();
    }

    private void testInstanceOfPracticeClass1() {
        PracticeClass1 pc = new PracticeClass1();
        System.out.println("The instanceOf operator comparing 'pc1 to pc1' returns: " + (pc instanceof PracticeClass1));
    }

    private void testInstanceOfPracticeClass2() {
        PracticeClass2 pc2 = new PracticeClass2();
        System.out.println("The instanceOf operator comparing 'pc2 to pc1'"
                + "actually causes an error (check the comment to the right");//+ (pc2 instanceof PracticeClass1));
    }
}

class PracticeClass1 {
    PracticeClass1() {}
}

class PracticeClass2 {
    PracticeClass2() {}
}
