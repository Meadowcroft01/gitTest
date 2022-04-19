class Shape {
    public void area() {
      System.out.println("The area of a shape is the space contained within it");
    }
  }
  
  class Circle extends Shape {
    public void area() {
      System.out.println("The area of a circle is it's radius squared multiplied by pi");
    }
  }
  
  
  class Main {
    public static void main(String[] args) {
      Shape myShape = new Shape();  // Create a shape object
      Shape myCircle = new Circle();  // Create a circle object
     
      myShape.area();
      myCircle.area(); //overrides parent class method and uses it's own
      
    }
  }