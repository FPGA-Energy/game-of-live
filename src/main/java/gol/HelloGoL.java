package gol;

public class HelloGoL {

    static void printState(GoL g) {
        int dim = g.getDim();
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                System.out.print(g.getVal(i, j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[][] start = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0},
        };
        GoL gol = new GoL(start);
        printState(gol);
        gol.step();
        printState(gol);
        gol.step();
        printState(gol);
        gol.step();
        printState(gol);

    }
}