public class PassValuesThreeWays {

    /*   Determine the effect upon object references and primitive
    values when they are passed into methods that change the values.
    Create a method 3 parameters, one is parameter is pass by
    value, one is passed by reference and one with the keyword final.
    Explain each and what the effects in side the method that changes
    each one. */

    public static void main(String[] args) {
        PassValuesThreeWays pvtw = new PassValuesThreeWays();

        // if the object has changed (as we are passing an object
        // instead of a primitive value), the terminal should all
        // print 'true'
        CustomObject co = new CustomObject();
        pvtw.changeObjectValues(co);
        System.out.println(co.getVal1() == 2);
        System.out.println(co.getVal2() == 10);
        System.out.println(co.getVal3() == 20);

        // the primitive value sent in should not be changed
        // inside the function if the return value is void
        // the value should end up staying the same;
        int i = 1;
        pvtw.voidPrimitiveValues(i);
        System.out.println(i == 1);
        // even though 'i' is changed in the function, and
        // is returned differently, 'i' should remain the
        // same
        System.out.println(pvtw.returnPrimitiveValues(i) == 6);
        System.out.println(i == 1);
    }

    void changeObjectValues(CustomObject co) {
        int val1 = co.getVal1();
        val1++;
        co.setVal1(val1);

        int val2 = co.getVal2();
        val2 += 5;
        co.setVal2(val2);

        int val3 = co.getVal3();
        val3 += 10;
        co.setVal3(val3);
    }

    void voidPrimitiveValues(int i) {
        i += 5;
    }

    int returnPrimitiveValues(int i) {
        return (i += 5);
    }
}

class CustomObject {
    private int val1;
    private int val2;
    private int val3;
    CustomObject() {
        this.val1 = 1;
        this.val2 = 5;
        this.val3 = 10;
    }

    public int getVal1() {
        return val1;
    }

    public int getVal2() {
        return val2;
    }

    public int getVal3() {
        return val3;
    }

    public void setVal1 (int val1) {
        this.val1 = val1;
    }

    public void setVal2(int val2) {
        this.val2 = val2;
    }

    public void setVal3(int val3) {
        this.val3 = val3;
    }
}
