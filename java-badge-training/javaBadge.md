# Java Badge Notes

## Core Java

* Describe the lifecycle of an object instance in Java and how garbage collection works
    * **Lifecycle**
        * class must be *loaded* from the *.class* file
        * the *new* keyword allocates space for the object in memory with a reference
        * does stuff with the object
        * object is removed when it is no longer active
    * **Garbage Collection:**
        * watches live/active objects and destroys everything else
        * "an allocation simply claims some portion of a memory array and moves the offset pointer forward. Next allocation starts at this offset and claims the next portion of the array [(source)](https://www.dynatrace.com/resources/ebooks/javabook/how-garbage-collection-works/)"
        * no memory is given back to operating system, but the memory is allocated for further use by the program
        * this is cheaper because it's easier to create an object than firmly delete them
    
* Describe how the basic data types are represented in memory (boolean, int, long, String, array of ints, array of objects, class w/ fields)
    * Boolean: single bit, 1 or 0
    * Byte: 8 bits
    * Char: 16 bits
    * short: 16 bits
    * int: 32 bits
    * long: 64 bits
    * float: 32 bits
    * double: 64 bits
    * String: held in *Java String Pool*. Will only store a single copy of each variable (will search to see if there is an equivalent string elsewhere)
    * Int Arrays: array of pointers
    * Object Arrays: array of pointers to objects
    * Class w/ Fields:

* Write an application to find out how many total characters can be held in a list of strings before you run out of memory
    * see `./runOutOfMemory`

* Compare and contrast StringBuffer and StringBuilder and when to use each
    * StringBuffer: 
        * thread safe
        * growable and writable character sequences (while String is immutable)
        * can be edited in the middle, end, or beginning.
    * StringBuilder:
        * not thread safe
        * mutable sequence of characters
        * very similar to StringBuffer, but is faster as it is NOT thread safe
        * designed for use as replacement for StringBuffer where the buffer was being used by a single thread


* Compare/contrast use of ArrayList/LinkedList/HashMap/HashSet/TreeSet
    * **Similarities**
        * They are all *Collections*
    * ArrayList:
        * resizeable List
        * isEmpty, size, get, set, iterator, and listIterator run in constant time.
        * all others run in linear time-ish
        * has capacity = size of array used to store the elements in the list. At least as large as the list size.
    * Linked List:
        * not synchronized. if another thread changes the LL, you'll have to sync it externally.
    * HashMap:
        * lookup has worst-case O(log(n))
        * a lot of constant times (get and put)
        * not synchronized
        * does not allow identical keys
    * HashSet:
        * allows null
        * does not allow identical values
    * TreeSet:
        * add(), remove(), contains() has O(log(n)) complexity
    

* Write an application to read a file with 10k lines of text, and output another file with the lines in sorted order
    * see `./tenThousandSorted`

* Write an application to read a file with 10k lines of text, and output another file with the lines in reverse sorted order
    * see `./tenThousandSorted`

* Write code to show exception handling including examples of checked, unchecked, and error exceptions
    * see `./exceptionHandling`

* write your own enum type. Describe when you would use it.
    * see `./enumExample`

## Working w/ Methods, Encapsulation, and Inheritance

* Show how to use a common piece of logic from two different classes, in three different ways:
    * by composition
    * by inheritance
    * by static method calls

    discuss the tradeoffs 
    * see `./compositionInheritance`

*   Create and overload constructors -- Create a class that has 4 fields and construct the class with variations of one required field and the others are optional.  Use constructor chaining as an example. 
    * see `./overloading`

*   Apply encapsulation principles to a class -- Show an example of good encapsulation.  Show a bad example of encapsulation and explain why.  Additionally explain access modifiers and how they can be used as part of the class encapsulation.  
    * see `./encapsulation`

*   Determine the effect upon object references and primitive values when they are passed into methods that change the values -- Create a method 3 parameters, one is parameter is pass by value, one is passed by reference and one with the keyword final.  Explain each and what the effects in side the method that changes each one.  
    * see `./passValuesThreeWays`

*   Write code to show how access modifiers work: private, protected, and public, talk about why you would use each of these.  
    * see `./encapsulation`

*   Write code to show how virtual method invocation lets one implementation be swapped for another.  
    * see `./virtualMethodInvocation`

*   Write code that uses the instanceof operator and show how casting works.  
    * see `./instanceOf`

* Show how to override a method in a subclass, talk about plusses and minuses in doing so.
    * see `./override`
    * plusses
        * helps in writing generic code
        * can use the super class as well as some custom code
    * minuses
        * may not be necessary but will attempt it anyways, only making it far too complex

* Show how to overload constructors and methods, talk about plusses and minuses in doing so.
    * see `./overloading`

## Library

* Write an application that uses the slf4j logging library directly (can also choose log4j if you want)
    * see `./project`
    * do the following:
        * configure the logging using an accepted department log statement format
        * log at different logging levels (error, warn, info, debug) to see the effect of the default logging level setting
        * turn on DEBUG in the logging config to show DEBUG output
        * configure logging to go to **both** the console and a log file
