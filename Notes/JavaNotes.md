Cloning is a process of creating a exact copy of object, a method
of java.lang.object class. A class should implement clonable interfact to support
cloning.

Cloping are two types Deep cloning vs Shallow cloning. Default cloning provide Shallow
cloning, you need to overwrite cloning method to support deep cloning

Shallow Copy : will have exact copy of all the field of original object and original
object have any reference to other object, then only reference will be cloned so
whatever changes you made through cloned object will be reflected in original object

Deep Copy is independent and disjoint to original object as it will create clone
copy for all referenced objects

Shallow copy is perfered if object has only premitive fields, fast and less expensive


Q: How HashMap Works in java ?
A: HashMap allows to store and reterive data on hash, which gives O(1) time.
When we call put method, hashCode method of key object is called, hashcode will be
generated to find the bucket where value will be stored

HashMap Collision : since the table is fixed size, eventually two keys will lead
to same hashCode and result in Collision,In this case linkedList is formed at
bucket location and new entry is stored as next entry.
Java 8 collision bucket contain a search tree than linkedList that gives O(logn)
complexity comparison to linkedList O(n) complexity in worst case.

HashMap keys can't be null, but values can and HashMap is not syncronized.
Once a get() method return a bucket with more than one value ,then call keys.equals()
method to identify correct node in the linkedList.

HashMap table is entry table where each entry is <key,value,next> ,if not Collision
next will be null else it form a singly linked linkedList

Rehashing is an technique to find new bucket when earlir hash table is filled and
you resize the hashtable.but this can lead to race condition when two thread tries
to resize the HashMap

Q: Why String, Integer and Other Wrapper are good candidate for HashMap Key ?
A: String are immutable and final which also override equals and hashCode method so
hashCode will remain same during insertion and reterival

Custom object can be the key of hashMap, if they override equals and hashCode with
contract or support immutability in some format.


Q: Difference between hashMap and HashSet in java ?
A: HashMap                                  HashSet
   implementation of Map                   implementation of set Interface
   Interface
   Store value as Key Value pair            Store only objects
   use key object hashCode to find
   bucket location                          use hashCode of member objects,if
                                            same, then use equals method to check
                                            equality
    HashMap is fast as have unique
    elements                                slow comparison to HashMap


Q: Differnce between HashMap and ConcurrentHashMap in java  ?
A:
1.  Concurrent HashMap is an alternate of hashTable so it provide syncronized and
    thread safe implementation of HashMap
2. HashMap can be converted to therad safe using Collection.syncronizedMap(map) but
   with every operation, it will lock the complete map object while ConcurrentHashMap
   will divide the map into differnt partition and lock only needed space.

Q: Difference between String and StringBuilder in java ?
A: String is immutable and StringBuilder is mutable, In comparison to StringBuilder
and StringBuffer- StringBuffer is syncronized and provide thread safety

Q: What is difference between Queue poll and remove ?
A: if queue is empty then remove will throw exception while poll() will return null

Q: What is fail safe and fail fast iterator in java ?
A:
1.  fail fast iterator will throw a concurrentModificationException as soon as they
    found any structural change in collection during iteration
2.  Fail fast iterator work over original collection while fail safe iterator Work
    over a copy/view of collection. That's why we don't detect any  changes and mostly
    we are working with stale values.
3.  fail fast(ArrayList, HashSet and Vector) , fail safe(CopyOnWriteArrayList,
    CopyOnWriteArraySet)
4.  Iterator returned by synchronized collection are fail fast while iterator
    returned by concurrent collection classes (CopyOnWriteArraySet) are fail safe

Q: Difference between iterator remove and collection remove method ?
A: if you traversing the list using iterator and in between you use collection
   remove then it will throw concurrentModificationException.

Q: Difference between Iterator and enu=meration ?
A: Iterator is a further step for traversing the list compared to enumeration
   as it provide remove method ,also it's safe as if other thread try to change
   underlying structure it will throw concurrentModification excecption

Q: How is hashSet implemented in java ?
A:  HashSet is implemented on top on HashMap where set values are keys of map to
    provide uniqueness and all keys have duplicate values.

Q: What to consider while making custom object as key in collection map?
A:  you should always override equal and hashCode method. so that the map operation
    are consisent. It will be able to insert and reterive based on key object.

Q:  Difference between syncronizedHashMap, ConcurrentHashMap and HashTable ?
A:  HashMap is syncronized while hashtable is not. ConcurrentHashMap is an updated
    version of hashtable.

Exception Handling:
   The main objective of exception handling is graceful termination of program.
   For every thread JVM will create a runtime stack, each entry is called activation record.

Default Exception handling,is responsibility of method to create exception object with
information such as name, description and stack trace. method handover exception to
JVM and JVM will see if method contain any exception handling code ,if not it will
remove stack entry and return to caller function ,untill it reaches the main thread
and pass information to default Exception handler.

Exception hirarchy :
1. Throwable class act as root for java exception hirarchy - child class (Exception, error)
2. Checked vs Unchecked Exception : Exception which are checked by compiler for smooth
execution of program at runtime are called checked Exception.
Errors and all it's child classes are unchecked Exception and all remaining are checked Exceptions

A checked exception is fully checked if it's all child exception are fully checked.
Try catch block is used for catching exception but catch block should have all block for
all possible exception that can happen.

printStackTrace(), getMessage(), toString() ,if different catch block is used then
it should be in order of child to parent

finally is the place to put all the clean up code - don't matter exception raised or not
finally block dominate return statement so if your try block contain return statement then
also finally will execute first -except System.exit
final class cant have child class, a final method can't be overrided, final variable can't be
re-referenced means can't assign to other object.

finally is a clean up code segment associated with try catch whereas finalliaze method
should be executed by Garbage Collector before destorying any object to perform clean activites

In comparison to finalize you should use finally() as we can't predict JVM behavior
Throw :
   To create exception object manaually and handover object to JVM explicitly, After throw
   no further statement otherwise it will throw unreachable statement
   We can only use throw keyword only for throwable type, otherwise will get compiletime
   error saying incompatible type

Copy on write is a technique where callers use resource object on shared basis until
some one wants to modify the object. if that happens then it will create a copy of that object

ClassCastException : When you want to sort object naturally but class don't implement comparable Interface

Example of Comparator :

public int ReverseNumericalOrder implements Comparator<Integer> {
    @overridede
    public int compare(Integer o1, Integer o2) {
        return  o2 - o1;
        // negative : first argument should come before o2 in sorted order
    }
}

@Test
public void customSorting() {
    final List<Integers> numbers = Arrays.asList(4, 7, 1, 6, 3, 5, 4);
    final List<Integer> expected = Arrays.asList(7, 6, 5, 4, 4, 3, 1);
    Collections.sort(numbers, new ReverseNumericalOrder());
    assertEquals(numbers, expected);
}

Static in java:
    static variable and method belong to class and they are commom to all instances
    You can update the variable value either through class or instance but to avoid
    confusion with object variable ,use class reference.

Polymorphism : (https://www.tutorialspoint.com/java/java_polymorphism.htm)
    ability of object to take many forms,a java object that can pass IS-A relationship
    Virtual method ensures that overriden method is exectued at run time , no matter
    at compile time what reference method have been associated with.
    Polymorphism can be thought of using a sub class when super class has been asked for

Abstraction : (https://www.tutorialspoint.com/java/java_abstraction.htm)
   It's the quality of idea rather than events, Abstraction is process of hiding
   the implementation details from user,only functionality will be provided
   User know what object does not how it does it ?

   Java provides abstraction through abstract classes , where each abstract class
   can have one more abstract method along with typical implemented methods, a java
   class needs to inherit the abstract class and implement all the abstracts method

   If you want a method implementation to be supported by it's child classes then
   mark that as abstract and also mark the class as abstract.

   The child class can override the method or create a hirarchy of abstract class

Inhertiance:
   When a new object of child class is created as copy of parent class object is intialized
   in child class
   HAS-A releationhip provide code resuability and clarity to support functionality
   such as Viechle has a speed. you can either put all code in viechle class or create
   a speed class and use in mutiple ways

Two Equals object must return same hashCode
The relationship betweens equals and hashCode work in needed fashion because of the way
other data structure such as hashmap are implemented

Arrays are object,can be victim of changes due to various references in diffrent part
of code/threads

String are at so core of java programming language that JVM treats string very specially
that you don't need to use new keyword for string literal.Internally String is a char
sequence.

String.intern() will make reference to alreday defined string memomry object in constant pool
but be careful about number of usage ,since this memory space is differnt than heap and called
permanent gen which typcially hold non user object such as class,method and internal java object
intern() can overflow the memory

Generics :
How type variance affects generics ?
Co-variant : an array of type T can hold data of type T or subtype of t
java genereics are type erasure at run time
What does reified mean ?
