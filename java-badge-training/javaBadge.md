# Java Badge Notes

## Core Java

* Describe the lifecycle of an object instance in Java and how garbage collection works
    * **Lifecycle**
        * class must be *loaded* from the *.class* file
        * the *new* keyword puts the object in memory with a reference
        * does stuff with the object
        * object is removed when it dies
    * **Garbage Collection:**
        * watches live objects and destroys everything else
        * "an allocation simply claims some portion of a memory array and moves the offset pointer forward. Next allocation starts at this offset and claims the next portion of the array [(source)](https://www.dynatrace.com/resources/ebooks/javabook/how-garbage-collection-works/)"
        * no memory is given back to operating system, but the memory is allocated for further use by the program
        * this is cheaper because it's easier to create an object than firmly delete them
    
* Describe how the basic data types are represented in memory (boolean, int, long, String, array of ints, array of objects, class w/ fields)


* Write an application to find out how many total characters can be held in a list of strings before you run out of memory


* Compare and contrast StringBuffer and StringBuilder and when to use each


* Compare/contrast use of ArrayList/LinkedList/HashMap/HashSet/TreeSet


* Write an application to read a file with 10k lines of text, and output another file with the lines in sorted order


* Write an application to read a file with 10k lines of text, and output another file with the lines in reverse sorted order


* Write code to show exception handling inlcuding examples of checked, unchecked, and error exceptions


* write your own enum type. Describe when you would use it.

## Working w/ Methods, Encapsulation, and Inheritance

* Show how to use a common piece of logic from two different classes, in three different ways: 
    * by composition
    * by inheritance
    * by static method calls
    discuss the tradeoffs 
    
    for example: two different classes that write a message to a file, one in XML, one in line-oriented text, but both need to reuse logic to open the file in the same way
* ~~Create and overload constructors -- Create a class that has 4 fields and construct the class with variations of one required field and the others are optional.  Use constructor chaining as an example.~~
* ~~Apply encapsulation principles to a class -- Show an example of good encapsulation.  Show a bad example of encapsulation and explain why.  Additionally explain access modifiers and how they can be used as part of the class encapsulation.~~
* Determine the effect upon object references and primitive values when they are passed  into methods that change the values -- Create a method 3 parameters, one is parameter is pass by value, one is passed by reference and one with the keyword final.  Explain each and what the effects in side the method that changes each one.
* Write code to show how access modifiers work: private, protected, and public, talk about why you would use each of these.
* Write code to show how virtual method invocation lets one implementation be swapped for another.
* Write code that uses the instanceof operator and show how casting works.
* Show how to override a method in a subclass, talk about plusses and minuses in doing so.
* Show how to overload constructors and methods, talk about plusses and minuses in doing so.

## Library

* Write an application that uses the slf4j logging library directly (can also choose log4j if you want)
    * do the following:
        * configure the logging using an accepted department log statement format
        * log at different logging levels (error, warn, info, debug) to see the effect of the default logging level setting
        * turn on DEBUG in the logging config to show DEBUG output
        * configure logging to go to **both** the console and a log file