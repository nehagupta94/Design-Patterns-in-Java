# CSX42: Assignment 1
## Name: Neha Gupta
B00818087
-----------------------------------------------------------------------
-----------------------------------------------------------------------

Due Date: Thursday, February 6, 2020
Submission Date: Thursday, February 6, 2020
Author(s): Neha Gupta
e-mail(s): ngupta4@binghamton.edu

PERCENT COMPLETE:
I believe I have completed 100% of this project.

BUGS: 
No bugs.

Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in wordPlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile wordPlay/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

Note: Absolute path to build.xml

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile wordPlay/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

Note: Absolute path to biuld.xml

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile wordPlay/src/build.xml run -Darg0="input.txt" -Darg1="output.txt" -Darg2="metrics.txt"

Note: Arguments accept the absolute path of the input file - arg0.
Absolute path for build.xml.

Note: Make sure ant run command is trigerred in the src folder.


-----------------------------------------------------------------------
## Description: Java program to reverse the words constituting sentences in a file and also to calculate certain metrics.


-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 02-05-2020


-----------------------------------------------------------------------
### Sample Output:
-----------------------------------------------------------------------

ngupta4@remote01:~/Downloads/csx42-spring-2020-assign1-neharajeshgupta/wordPlay/src$ ant -buildfile /home/ngupta4/Downloads/csx42-spring-2020-assign1-neharajeshgupta/wordPlay/src/build.xml run -Darg0="/home/ngupta4/Downloads/csx42-spring-2020-assign1-neharajeshgupta/wordPlay/src/wordPlay/input.txt" -Darg1="output.txt" -Darg2="metrics.txt"
Buildfile: /home/ngupta4/Downloads/csx42-spring-2020-assign1-neharajeshgupta/wordPlay/src/build.xml

jar:

run:
     [java] Hello World! Lets get started with the assignment
     [java] 
     [java] LONGEST_WORD    applications
     [java] 
     [java] AVG_NUM_CHARS_PER_SENTENCE    87.67
     [java] 
     [java] MAX_FREQ_WORD    design
     [java] 
     [java] AVG_NUMBER_WORDS_PER_SENTENCE    14.33
     [java] 
     [java] A wen tneduts sah deretsiger rof ngised snrettap ni eht gnirps fo 0202. gniruD eht retsemes eht students
     [java] era gniog ot nrael doog ngised selpicnirp dna ngised senilediug ot eb dewollof nehw gnipoleved snoitacilppa.
     [java] llA gnimmargorp stnemngissa era ot eb enod ni avaJ.
     [java] 

BUILD SUCCESSFUL
Total time: 0 seconds

-----------------------------------------------------------------------
### Citations:
-----------------------------------------------------------------------

I have referred to "https://stackoverflow.com/" for syntax and implementation details.'

