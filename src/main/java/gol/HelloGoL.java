package gol;

public class HelloGoL {

    static void printState(int[][] state) {
        for (int i = 0; i < state.length; ++i) {
            for (int j = 0; j < state[0].length; ++j) {
                System.out.print(state[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        };
        GoL gol = new GoL(start);
        printState(gol.current);
        gol.step();
        printState(gol.current);
        gol.step();
        printState(gol.current);
        gol.step();
        printState(gol.current);

    }
}