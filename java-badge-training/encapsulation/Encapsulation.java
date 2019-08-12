import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Encapsulation {
    public static void main(String[] args) {
        System.out.println("hello");

        GoodEncapsulation gEnc = new GoodEncapsulation(0);
        gEnc.decrementCounter();
        gEnc.incrementCounter();

        BadEncapsulation bEnc = new BadEncapsulation();
        bEnc.setId(37618); //definitely potentially dangerous, especially if in a API or whatever
        bEnc.setDateMade(new Date()); //can get changed, bad juju
    }
}

class GoodEncapsulation {
    private int counter;

    private List list = new ArrayList<>();

    GoodEncapsulation(int counter) {
        this.counter = counter;
    }

    void incrementCounter() {
        this.counter++;
    }

    void decrementCounter() {
        this.counter--;
    }

    // good
    List returnList() {
        return Collections.unmodifiableList(list);
    }
}

class BadEncapsulation {

    private int id;
    private Date dateMade;
    private int counter;
    private String otherStuffNotNecessarilyScaryToChange;
    private List list = new ArrayList();

    BadEncapsulation() {
        this.id = (int)(Math.random() * 100);
        this.dateMade = new Date();
        this.counter = 0;
        this.otherStuffNotNecessarilyScaryToChange = "this is other stuff not necessarily cool or necessarily scary";
    }

    // can modify actual list
    List returnList() {
        return list;
    }

    //this should not be able to be set id again,
    // generally an id should not be able to be changed
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    //this should not be able to be changed
    public void setDateMade(Date dateMade) {
        this.dateMade = dateMade;
    }

    //getting the date isn't necessarily bad
    public Date getDateMade() {
        return dateMade;
    }

    //not scary, why would you not be able to see them?
    public int getCounter() {
        return counter;
    }

    // bad, should probably not be able to be changed
    public void setCounter(int counter) {
        this.counter = counter;
    }
}
