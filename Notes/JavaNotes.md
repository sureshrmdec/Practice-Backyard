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
