Q1: Difference between composition and inheritance
A1:
   1. Compostion provides more of HAS-A functionality while inheritance provides IS-A functionality
   2. Compostion don't inherit ,it keep that class reference as member variable
   3. inheritance provides Polymorphism where you can give sub class object when a super
      class object is needed
   4. One reason for this is java don't support multiple inheritance and composition provides
      a better way to implement more than one class.
   5. Unit testing is best with composition as you can mock those reference objects while
      you need super class object in inheritance testing.

   6. inheritance breaks encapsulation in one way. For example, if child class need operation
      of parent class and it creates a high coupling which is not good for maintanable code.

Q2: Why use interface in OOP ?
A2:
   1. Intrface is the key to achieve Polymorphism.
   ** Polymorphism is powerful,it provides flexibilty and dynamicism

 **Always strive for highly cohesive and loosly couple Solution.
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

Q: Tell me about SOLID principle ?
    1. Single Responsibilty Principle : A class should do only thing and can only be changed by one reason.
    Ex: AreaCalculator class is printing the data also along with providing sum method. But area output() method
    should be separate class with different output format such as HTML, JSON.

    2.Open Closed principle : Object and entities should be open for extension and closed for modification
    Ex: Coding for interface then implementation, that require you to extend class and code further functionality
    than chaning the base class.

    3.LisKov Substitution principle: All the subclass/derived class should be substituable for base/parent class

    4. Interface segregration principle: A client should not be forced to implement a method which he don't require
    Ex: Area() and volume() method are not needed for all shapes so divide the methods in two interface will help
    solve the problem.

    5. Dependency Inversion principle: Entities must depend on abstraction not concretions.
    High Level module should not depend on low level module but they should depend on abstractions.


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
                  grid = new Cells[Size.X][Size.Y];
               }

               public void update() {
                  if(int i = 0; i < size.X; i++) {
                     for(int j = 0; j < size.Y; j++) {
                        boolean isLive = (grid[i][j].isAlive);
                        int count = countNeighours(i, j, grid[i][j]);
                        if(isLive && count < 2) result = false;
                        if(isLive && (count ==2 || count ==3)) result true;
                        if(isLive && count >3) result false;
                        if(!isLive && count == 3) result true;
                        cells[i][j].isAlive = result;
                     }
                  }
               }

               public int countNeighours(int row, int col, cellObject) {
                   private static final int[][] NEIGHBOURS = {
                        {-1, -1}, {-1, 0}, {-1, +1},
                        { 0, -1},          { 0, +1},
                        {+1, -1}, {+1, 0}, {+1, +1}};
                        int cnt = 0;
                        for (int[] offset : NEIGHBOURS) {
                            if (cellObject.getLiveStatus(row + offset[0], col + offset1)) {
                                          cnt++;
                          }
                        }
                    return cnt;
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

Q: Create a vending machine ?
A: 1. Define Requirement Statement //Don't Assume, Ask interviewer about what core functionality is needed
      System which accept coin of 1,5,10,15,25 cent
      Allow user to select product Coke(25) pepsi(35) Soda(45)
      Allow user to cancel request and take refund
      Return selected product and change if any
      Allow reset operation for vending machine supplier

    2. Define the core objects
      Public API for vending machine - high level functionality will go into this
      VeningMachineImp sample implementation of vending machine
      VendingMachineFactory A factory class to create different kind of vending machine
      Item - enum to represent item served
      Inventory - java class to represent Inventory
      Coin - another enum to define what supported coin are
      NotFullPaidException
      NotSufficentChangeException
      SoldOutException

    3. Define the relationship between core objects

    4. Write the implemnation

    public interface VendingMachine {
        public long selectItemGetPrice(Item item);
        public void insertCoin(Coin coin);
        public List<Coin> refund();
        public Bucket<Item, List<Coin>> collectItemAndChange();
        public void reset();
    }

    public class VendingMachineImpl implements VendingMachine {
        private Inventory<Coin> cashInventory  = new Inventory<Coin>();
        private Inventory<Item> itemInventory = new Inventory<Item>();
        private long totalSales;
        private item currentItem;
        private long currentBalance;

        public VendingMachineImpl() {
            intialize();
        }
        public void intialize() {
            //Intialize with 5 coin of each denomination and 5 can of each item
            For(Coin c : Coin.values()) {
                cashInventory.put(c, 5);
            }

            for(Item i : item.values()) {
                itemInventory.put(i, 5);
            }
        }

        @Override
        public Long selectItemGetPrice(Item item) {
            if(itemInventory.hasItem(item)) {
                currentItem = item;
                return currentItem.getPrice();
            }
            throw new SoldOutException("sold out, Please buy another item");
        }

        @Override
        public void insertCoin(Coin coin) {
            currentBalance = currentBalance + coin.getDenomination();
            cashInventory.add(currentBalance);
        }

        @Override
        public Bucket<Item, List<Coin>> collectItemAndChange() {
            Item item = CollectItem();
            totalSales = totalSales + item.getPrice();
            List<Coin> change = collectChange();
            return new Bucket<Item, List<Coin>>(item, change);
        }

        private Item collectItem() throws NotSufficentChangeException {
        if(isFullPaid) {
            if(hasSufficientChange()){
                itemInventory.deduct(currentItem);
                return currentItem;
            }
            thow new NotSufficentChangeException("Not sufficient change in itemInventory");
        }
        long remainingBalance = currentItem.getPrice() - currentBalance;
        }

        private List<Coin> collectChange() {
            long changeAmount = currentBalance - currentItem.getPrice();
            List<Coin> change = getChange(changeAmount);
            updateCashInventory(change);
            currentBalance = 0;
            currentItem = null;
            return change;
        }
    }
Q: Design a student registration and deregistration system a particualr course
        1. A catalog of courses from hich user can select a course to register
        2. Deregsister from the course
        3. update student that course is full

    2. Define the core objects ?
        Registration, student courses

    3. Define the relationship between different objects
       A course can have many students,student can only register only once in individual coursetr

    Code:

    public interface Registration {
        public void register(Student student, Course course);
        public void deregister(Student student, Course course);
        public void avalibility(Course course);
    }

Q: What is the difference between abstract class and interface ?
A: 1.Is there a functionality which could be coded at one place and can be utilized
     by all implementing classes in same way then it's good to use abstract class
   2. DisAdvantage of abastract class is, java only allow single inheritance
   while with interface you can add multiple behaviour to application.

Q: Smart phone class and will have desired classes like IPhone, AndroidPhone, WindowPhone
   can be phone with brand, how would you design this system of classes ?
A: Create factory static method which takes some argument which identify what type
   of ojbect you want to create at run time instead of hard coding that at compile time

   1- Smartphone parent class which have model, release year and brand with abstract methods
   such as camera, calling
   2. Then brand will go and have child implementation and have common properties which
   is available in all the models then model class extend brand for further functionality


Q:When do you overload a method in Java and when do you override it?
A: Overloading define providing similar functionality with different input set while
   overriding provide different functionality on the same input set.

**Chain of Responsibilty Pattern in JDK:
** Used to achieve loose coupling in software design where a request from client is passed
   to chain of objects to process them.Then object in chain will decide themselves who will
   be processing the request and whether request is required to sent to next object.
   Client don't know which part of the chain will be processing the request.Each object
   in the chain will have it's own implementation to process the request either full or partial
   and send to user back again -Ex: java.util.Logging.Logger #Log()

Q:Design an ATM Machine ?
A:


Q: Design a chase game ?
A:
    Use Case/Requirement:
    1. Two players playing the game
    2. Each will have it's own pieces black or white
    3. Each piece will have face value and suit it belong to
    4. all this happens on cell which have co-ordinate on board
    5. Board consist of cell, pieces at different location on cells
    6. A chessgame class binding all this things together.

    Core Objects :
    1. chessgame object
    2. Player object
    3. piece object
    4. board object

    Relationshipt between objects:


Q: Decorator Design Pattern in java ?
A:
*******Pending*********
1. Abstraction, Encapsulation, Polymorphism, inheritance
