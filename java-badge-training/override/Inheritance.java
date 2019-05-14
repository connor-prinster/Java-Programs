class Inheritance {
  public static void main(String[] args) {
    Parent parent = new Parent();
    System.out.println("Parent toString() " + parent.toString());
    Parent child = new Child();
    System.out.println("Child toString() " + child.toString());
  }
}

class Parent {
  Parent() {

  }

  @Override
  public String toString() {
    return "This is the parent";
  }
}

class Child extends Parent {
  Child() {
    super();
  }

  @Override
  public String toString() {
    return "This is the child with the parent class' constructor as " + super.toString();
  }
}