package enumExample;

public class enumExample {

    enum Suit {
        CLUB("club"), DIAMOND("diamond"), HEART("heart"), SPADE("spade");

        private String name;

        public String getName() {
            return this.name;
        }

        Suit(String value) {
            this.name = value;
        }
    }

    public static void main(String[] args) {
        System.out.println("This is the " + Suit.CLUB.getName() + " aspect of the Suit enum.");
        System.out.println("This is the " + Suit.DIAMOND.getName() + " aspect of the Suit enum.");
        System.out.println("This is the " + Suit.HEART.getName() + " aspect of the Suit enum.");
        System.out.println("This is the " + Suit.SPADE.getName() + " aspect of the Suit enum.");
    }
}
