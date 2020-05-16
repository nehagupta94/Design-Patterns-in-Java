# CSX42: Assignment 2
## Name: Neha Gupta (B00818087)
```
Due Date: Sunday, February 23, 2020
Submission Date: Sunday, February 23, 2020
Grace Period Used This Project: 0 Days
Grace Period Remaining: 3 Days
Author(s): Neha Gupta
e-mail(s): ngupta4@binghamton.edu
```
PERCENT COMPLETE:

[
  -I assume that the code is 100% complete and running.
  -The performance may be boosted using memory mapped IO for reading/writing using 
	IO operations to avoid cache miss and extert all the IO operations in 1 shot.
  -used diff command in java runtime to check that the backup works.
]

DATASTRUCTURE CHOICE:
[

	Priority Queue (TopK Metrics)
 	    Adding item O(1)
        Retrieval of data O(n)
        
    ArrayList (Running Average & Number Peak)
        O(n)
]

SAMPLE OUTPUT:

[
  
    3 Files will be created and the output is written into the respective files.

]

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in numberPlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

```commandline
ant -buildfile numberPlay/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

```commandline
ant -buildfile numberPlay/src/build.xml all
```

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

#### Use the below command to run the program.

```commandline
ant run -buildfile numberPlay/src/build.xml \
-DinputNumStream="<input file path>" \
-DrunAvgWindowSize="<size of the window for running average calculations>" \
-DrunAvgOutFile="<output file path to which running averages are written>" \
-Dk="<max size of the list containing the top K numbers>" \
-DtopKNumOutFile="<path of output file to which the top K numbers are written>" \
-DnumPeaksOutFile="<path of output file to which the peaks in the number stream are written>"
```

-----------------------------------------------------------------------
## Description: Observe a stream of numbers and calculate different metrics.
The project is build based on Observer Pattern.
```
->The program accepts one input file. This input file contains integer and/or floating point numbers.
->Each number read from the input file is passed to a NumberProcessor.
->The NumberProcessor operates as indicated below, for each number received.
    An event is triggered based on the data type of the number. Below are the possible events that can be triggered.
      INTEGER_EVENT - Triggered if the number read is an integer.
      FLOATING_POINT_EVENT - Triggered if the number read is a floating point number (float or double).
     Based on the type of event triggered, the appropriate Observers are notified and the number is provided to them along        with the notification.
->Each Observer then carries out some operations using the provided number and stores the result of the operation.
->Once all the numbers have been read in from the file, the NumberProcessor would trigger a PROCESSING_COMPLETE event and would notify all the observers. The observers, when notified of this event, should persist the results into the corresponding output files.
```

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 02-23-2020


