package referenceValueFinal;

public class Reference {
    public static void main(String[] args) {
        Reference ve = new Reference();
        ve.balloonMixing(new Balloon("pink"));
    }

    void balloonMixing(Balloon green) {
        Balloon originalValues = green;
        System.out.println("original color " + green.returnColor());
        green = new Balloon("green");
        System.out.println("this is the second color " + green.returnColor());
        green.setColor("blue");
        green = new Balloon("red");
        System.out.println("the third color is" + green.returnColor());
        green.setColor("yellow");
        System.out.println("the fourth color is " + green.returnColor());

        System.out.println("this is hopefully the original color " + originalValues.returnColor());

    }
}

class Balloon {

    private String color;

    public Balloon(String color) {
        this.color = color;
    }

    public String returnColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
