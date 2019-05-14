import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    Car car = new Car();
    System.out.println(car.toString());
  }

}

class Vehicle {

  String type;
  Vehicle(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}

class Car extends Vehicle {

  List<Tire> tires;
  Bumper bumper;

  Car() {
    super("car");
    List<Tire> tireList = new ArrayList<>();
    for(int i = 0; i < 4; i++) {
      tireList.add(new Tire(i));
    }

    this.tires = tireList;
    this.bumper = new Bumper(6);
  }

  @Override
  public String toString() {
    return "Type: "
            + this.getType()
            + "\nLicense Plate Number: "
            + bumper.getLicensePlate()
            + "\n# of tires: " + tires.size();
  }
}

class Tire {
  private int id;

  Tire(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
}

class Bumper {
  private String licensePlate;

  Bumper(int licensePlateLength) {
    String plate = "";

    for(int i = 0; i < licensePlateLength - 1; i++) {
      plate += ((int)(Math.random() * 10) + "-");
    }
    plate += (int)(Math.random() * 10);

    this.licensePlate = plate;
  }

  public String getLicensePlate() {
    return licensePlate;
  }
}

