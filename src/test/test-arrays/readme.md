# This folder contains tests and test generators for Game of Life
- "generate game of life input.ipynb" which is a python 3 notebook that generates new random data input files according to the specifications in the second cell (execute all cells in the notebook starting from the top).
- a set of folders containing input grids of the size indicated by in the folder name.


# Each test folder contains test of three different kinds:
- a test with only dead cells
- two tests with 1 beacon and maximum number of isolated beacons, i.e., beacons that do not interact with each other, permitted by the grid size
- ten tests with random patterns

In addition, the folder with 100x100 grids contains a series of grids with increasing number of isolated beacons. 

# Execution of the tests
Each test is executed for all following numbers of timesteps

0 1 2 3 4 5 
10 20 30 40 50 60 70 80 90 
100 200 300 400 500 600 700 800 900 
1000 2000 3000 4000 5000 6000 7000 8000 9000 
10000 11000 12000 13000 14000 15000 16000 17000 18000 19000 
20000 21000 22000 23000 24000 25000 
50000 75000 100000 125000 150000 175000 200000
