
# This contains the files
- readme.md 	which is this file
- gol.c 	which is the game of life
- test.in	which is a small example of a data input file
- test_1.in	which is a larger example of a data input file
- "generate game of life input.ipynb" which is a python 3 notebook that generates new random data input files according to the specifications in the second cell (execute all cells in the notebook starting from the top).


# the executable's input and output

The game of life is written in c in gol.c and the executable takes two inputs:
1. a filename with a data input file with content of the form:

	<n> <m> <followed by n*m numbers which each is either 1 or 0 and are seperated by a single space> 

an example of such content is:
	5 7 0 0 0 0 0 0 0 0 1 1 1 1 0 0 0 0 0 0 1 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 

which specifies that there are 5 rows and 7 columns and the numbers specifies the following 2D array:
	0 0 0 0 0 0 0 
	0 1 1 1 1 0 0 
	0 0 0 0 1 0 0 
	0 1 0 0 0 0 0 
	0 0 0 0 0 0 0  

2. an integer expressing how many time steps the game of life should execute. The number should at make the execution run for at least be a couple of seconds to reduce the imprecision of measurements.
See the below for an example on how to use the system timer (Mac OS) to get an indication of the time, so you can more easily adjust the number of repetitions according to your chosen array size.
See below for how to generate new input using the python file 
NB!: The program will default to 10 time steps if you do not provide this input. 


The executable ./gol prints out the input 2D array and the output 2D array to ease the comparison to other implementations.

# how to run game of life 

1. make sure that you have the c compiler gcc installed
2. download all files, unzip them, and move into that directory 
3. compile the gol.c
	gcc -g gol.c -o gol
4. run the ./gol with the filename which contains the input test
	./gol test_1.in 40

# Example calibrating the chosen repetition number to fit:

test$ cat test.in 
10 20 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1 1 0 0 0 1 1 0 0 1 1 0 1 0 0 0 0 1 1 0 0 0 1 1 0 1 1 1 0 1 1 1 0 1 0 0 0 1 1 0 0 0 0 1 1 1 1 0 0 1 0 0 1 0 1 0 0 1 0 0 1 0 0 0 0 1 1 0 0 0 1 1 1 1 1 0 0 1 0 0 0 0 1 1 1 1 1 0 1 0 1 0 0 0 1 0 0 0 1 0 0 0 1 0 1 0 0 1 1 0 1 0 0 0 0 0 0 1 1 0 0 0 1 1 1 0 1 1 0 0 0 1 1 0 0 0 0 1 1 0 1 0 0 0 0 0 1 1 1 1 0 0 1 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
test$ gcc -o2 gol.c -o gol
test$ time ./gol test.in 400
00000000000000000000
00101100011001101000
01100011011101110100
01100001111001001010
01001000011000111110
01000011111010100010
00100010100110100000
01100011101100011000
01101000001111001010
00000000000000000000

00000000000000000000
00000000000000000000
00000000000000000000
00000000000000010000
00000000000000101000
00000000000000101000
00000000000000010000
00000000000000000000
00000000000000000000
00000000000000000000


real	0m0.012s
user	0m0.002s
sys	0m0.002s
test$ 
