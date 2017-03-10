Q1: Difference between composition and inheritance
A1:
   1. Compostion provides more of HAS-A unctionality while inheritance provides IS-A functionality
   2. Compostion don't inherit ,it keep that class reference as member variable
   3. inheritance provides Polymorphism where you can give sub class object when a super
      class object is needed
   4. One reason for this is java don'tssupport multiple inheritance and composition provides
      a better way to implement more than one class.
   5. Unit testing is best with composition as you can mock those reference objects while
      you need super class object in inheritance testing.

   6. inheritance breaks encapsulation in one way. For example, if child class need operation
      of parent class and it creates a high coupling which is not good for maintanable code.

Q2: Why use interface in OOP ?
A2:
   1. Intrface is the key to achieve Polymorphism.
   ** Polymorphism is powerful,it provides flexibilty and dynamicism

 **Always strive for highly cohesive and loosly couple Solutiont
 **Programming for interface than the implementation

 Q: List out some object oriented design principle ?
 A:
    1. Don't repeat yourself (DRY) : for a single functionality don't write code twice

    2. Encapsulate what changes - encapsualte the code that you expect/suspect
    in future Ex : Factory Design pattern (encapsulate object creation code and provide facility
    to introduce new product later with no impact on existing code)

    3. Open Closed Design principle -Classes, methods and function should be open
    for extension and closed for modification so you don't change already tried and tested code

    4. Single Responsibilty Principle - A class should handle only one functionality ane md do it well
    because if your class handles more than one , there may be chances ,that updating once code will
    break other function code

    5.Dependency Injection or Inversion principle
    6. Favour composition over inheritance


(Link :http://javarevisited.blogspot.com/2011/12/factory-design-pattern-java-example.html)
Q: Tell me about Factory Design pattern
A: (You know you have to create a object, but don't know in advance what kind of object)
   It's a creational design pattern based on the idea of encapsulation. It will encapsulate
   object creation

   Ex: Whenever we create object using new ,we somehow hard coded the object instantation.
   Ex: When a class contain object from other class ,you create other class object using new
   and this creates dependency between two classes.

Q: Design Conway game of life ?
   1. What are the use cases/functionality of given system ?
      What is conway game of life - a two dimensional system with some cell are on/off
      and rules to govern how they come alive or dead . doing all this we can get
      patterns how life evolve, spread and stabalize

   2. core objects :
      Cell : can be in two state dead or alive. Cells don't move it just affect their
      neighbours
      Behaviour of Cell object:
         1. It needs to take care of position, bound and state so they can be clicked
         or drawn correctly.
         2. They need to toggle between alive and dead.
         3. Need to drawn black if alive or white if dead.

         public class Cell {
            public Point point;
            public Rectangle Bound;

            public boolean isAlive;

            public void update(Mousestate mouseState) {
               if(mouseState.leftButton == buttonState.pressed) isAlive = true;
               elseif(mouseState.rightButton == buttonState.pressed) isAlive = flase;
            }
            public void Draw() {
               if(isAlive) Draw()
            }
         }

      Behaviour of Grid Object :
         1. any cell with fewer than 2 live neighbour dies, as by under population
         2. Any live cell with two or three live neighbour goes to live on next generation
         3. Any live cell with more than 3 live neighbout die due to over crowding
         4. Any dead cell with exactly three live neighbour become a live cell.

         class Grid {
               public Point Size;
               public Cell[][] grid;

               public Grid() {
                  Size = new Point(Game1.Cellx, Game2,CellsY);
                  cells = new Cells[Size.X][Size.Y];
               }

               public void update() {
                  if(int i = 0; i < size.X; i++) {
                     for(int j = 0; j < size.Y; j++) {
                        boolean isLive = (grid[i][j].isAlive);
                        int count = countNeighours(i, j);
                        if(isLive && count < 2) result = false;
                        if(isLive && (count ==2 || count ==3)) result true;
                        if(isLive && count >3) result false;
                        if(!isLive && count == 3) result true;
                        cells[i][j].isAlive = result;
                     }
                  }
               }

               public int countNeighours(int row, int col) {
                  //Check cell on top right, top left and top
                  //Check cell on bottom left, bottom right, bottom top
                  //Check cell left and right
               }
         }

      Updating the grid in discrete time step :

Q: Design shape base class for triangle and Rectangle
   // Below code is still need work such as checking input for negative number,
   // higher dimensions
A: public abstract class Shape {
      public abstract double area();
      public abstract double perimeter();
   }

   public class Rectangle extends shape {
      private final double width, height;

      public rectangle() {
         this(1,1);
      }
      public rectangle(double width, double height){
         this.width = width;
         this.height = height;
      }

      @Override
      public double area() {
         return (width * length);
      }

      @Override
      public double permiter() {
         return 2*(width + height);
      }
   }

   public class Circle extends shape {
      private final double radius;
      private static final double PI = Math.PI;

      public Circle(){
         this(1);
      }

      public Circle(int radius) {
         this.radius = radius;
      }

      @Override
      public double area() {
         return PI * Math.pow(radius, 2);
      }

      @Override
      public double perimeter(){
         return (2 * PI * radius);
      }
   }

   public class Triangle extends shape {
      private final double a, b, c;

      public Triangle(){
         this(1,1,1);
      }

      public Triangle(double a, double b, double c) {
         this.a = a;
         this.b = b;
         this.c = c;
      }

      @Override
      public double area(){
         double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
      }

      @Override
      public double perimeter() {
         // P = a + b + c
         return a + b + c;
      }
   }

*******Pending*********
1. Abstraction, Encapsulation, Polymorphism, inheritance
