# Conway's Game of Life

Implementing [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)
in Software and Hardware.

The Game of Life (GoL) is an extremely easy problem to parallelize.
We aim to beat a standard laptop with a cheap FPGA board.

## These rules are

 * Any live cell with two or three live neighbours survives. 
 * Any dead cell with three live neighbours becomes a live cell.
 * All other live cells die in the next generation. Similarly, all other dead cells stay dead.