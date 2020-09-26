# CSX42: Assignment 4
## Name: Neha Gupta (B00818087)
```
Due Date: Monday, April 20, 2020
Submission Date: Monday, April 20, 2020
Grace Period Used This Project: 0 Days
Grace Period Remaining: 2 Days
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

	HashMap (Storing Items file)
   		 O(1)
        
  	ArrayList (Storing Results)
        	O(n)
]

SAMPLE OUTPUT:

[
  
  	BASIC::cinema--NO
  	BASIC::medicine--YES
  	BASIC::householdItem--YES
  	BASIC::medicine--YES
  	BASIC::householdItem--YES
  	LUXURIOUS::yacht--NO
  	LUXURIOUS::vacationTrip--YES
  	LUXURIOUS::medicine--YES
  	LUXURIOUS::householdItem--YES
  	EXTRAVAGANT::yacht--YES
  
  	A <outputFile>.txt file will be generated at the end of program run.

]

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in statePattern/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

```commandline
ant -buildfile build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

Note: Command to be run from the src folder.

-----------------------------------------------------------------------
## Instruction to compile:

```commandline
ant -buildfile build.xml
```

Description: Compiles your code and generates .class files inside the BUILD folder.

Note: Command to be run from the src folder.

-----------------------------------------------------------------------
## Instruction to run:

#### Use the below command to run the program.

```commandline
ant -DinputFile="<input file path>" 
-DavailableItemsFile="<available items file path>" 
-DrunningAverageWindowSize="<Window size for running average calculation>" 
-DoutputFile="<Output filename>" run
```

Note: Absolute file path required.
All files extensions to be ".txt".

-----------------------------------------------------------------------
## Description: Change of purchases based on income earned.
The project is build based on State Pattern.
```
->The program accepts two input file. One is the input file and other is the available items file.
->AvailableItemsFile is read and stored in an HashMap.
->The inout file is parsed and segregated into money and items.
->The running average is calculated on the basis of the money input.
->The state is decided on the basis of the running avarage calculated.
->The items purchased are based on the running average.
->Result is written to the output file as passed in the command line argument.
```

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 04-20-2020



