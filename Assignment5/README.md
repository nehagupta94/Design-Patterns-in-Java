# CSX42: Assignment 5
## Name: Neha Gupta (B00818087)
```
Due Date: Saturday, May 2, 2020
Submission Date: Sunday, May 3, 2020
Grace Period Used This Project: 1 Days
Grace Period Remaining: 1 Days
Author(s): Neha Gupta
e-mail(s): ngupta4@binghamton.edu
```
PERCENT COMPLETE:

[
  	
	-I assume that the code is 100% complete and running.
  	-The performance may be boosted using memory mapped IO for reading/writing using IO operations to avoid cache miss and extert all the IO operations in 1 shot.
  	-used diff command in java runtime to check that the backup works.
	
]

DATASTRUCTURE CHOICE:
[

	Priority Queue (TopK Metrics)
 	    Adding item O(1)
        Retrieval of data O(n)
        
    ArrayList (Results)
        O(n)
]

SAMPLE OUTPUT:

[
  
    2 Files will be created and the output is written into the respective files.
    
    run:
     [java] Let's get started with this assignment!
     [java] Completed!


]

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in dictionary/src folder.
#### Note: all commands to be run in dictionary/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

```commandline
ant -buildfile build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

```commandline
ant -buildfile build.xml all
```

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

#### Use the below command to run the program.

```commandline
ant -DinputFilename="<input absolute file path>" -DacceptableWordsFilename="<acceptableWords absolute file path" -Dk=<window size for TopK> -DtopKOutputFilename="<TopK output file name>" -DspellCheckOutputFilename="<SpellCheck output file name>" run
```

-----------------------------------------------------------------------
## Description: Visitor pattern to run different analytics on the given input.
The project is build based on Observer Pattern.
```
->The program accepts two input file. This input file contains sentences in the form of a paragraph.
->The Builder pattern is used to read the sentences one at a time and contruct MyArrayList.
->The Iterator pattern is used to iterate on the array of sentences encapsulated in MyElement.
->The Visitor pattern is used to visit
	TopK - TopK words of every sentence.
	SpellCheck - A spell check on words corresponding the the acceptableWords file provided via commandline.
->The results are directed to the Results class which are then written on the output file as specified via commandline.
```

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 03-02-2020


