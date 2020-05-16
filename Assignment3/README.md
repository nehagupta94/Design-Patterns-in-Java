# CSX42: Assignment 3
## Name: Neha Gupta (B00818087).   Bhargav Choudhury (B00817811)
```
Due Date: Sunday, March 29, 2020
Submission Date: Wednesday, April 1, 2020
Grace Period Used This Project: 3 Days
Grace Period Remaining: 2 Days
Author(s): Neha Gupta , Bhargav Choudhury
e-mail(s): ngupta4@binghamton.edu , bchoudh4@binghamton.edu
```

DATASTRUCTURE CHOICE:
[

	ArrayList (Results)
 	    Adding item O(1)
        Retrieval of data O(n)
]

SAMPLE OUTPUT:

[
  
    Output can be viewed on Server console.
    Output.txt file will be generated.

]

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in numberPlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

```commandline
ant -buildfile primeDetectorSockets/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

```commandline
ant -buildfile primeDetectorSockets/src/build.xml all
```

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

#### Use the below command to run the program.

```commandline to run persister service
ant -buildfile primeDetectorSockets/src/build.xml run-persister-service -Dport="<Port number on which the server should listen>"
\-DoutputFile="<Name of the output file to which the data received on the port should be written>"


commandline to run prime detector
ant -buildfile primeDetectorSockets/src/build.xml run-prime-detector -DinputStream="<Input file path>"
-DnumThreads="<No of threads>" -Dcapacity="<Capacity of results Data Structure>" 
-DpersisterServiceIp="<Valid IP address>" -DpersisterServicePort="<Valid port Number>" 
-DdebugValue="<Debug Value>"

```
Note: Persister Service to be up and running before Prime Detector.

-----------------------------------------------------------------------
## Description: Observe a stream of numbers and differenciate Prime numbers and write to Server.
The project is build based on classic Producer Consumer problem.
```
->The program accepts one input file. This input file contains integer numbers.
->Each number read from the input file is passed to the worker class.
->Problem uses multi-threading with the numThreads obtained as an input parameter.
->A common thread pool is maintained where Threads are borrowed and returned.
->Each thread will poll a new number from a file, process it and store it in a common Results Data structure.
->Results class has a thread (DataSender) which will constantly send data to PersisterService.

->PersisterService constantly listens on the specified port number.
->Writes the data recieved onto the output file.
->Terminates after it recieves "STOP" signal.

```

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"We have done this assignment completely on our own. We have not copied
it, nor have we given our solution to anyone else. I understand that if
we are involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 04-01-2020
