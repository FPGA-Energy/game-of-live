package gol;

/**
 * The Game of Live.
 */
public class GoL {

    private int[][] current;
    private int[][] next;

    /**
     * Start a GoL scene.
     *
     * The board is assumed to be quadratic. No cells on the boarders (copy to a larger board for this).
     * The boarders of the array are generated to be empty and boarders are ignored to simplify the computation.
     */
    public GoL(int start[][]) {
        // assert (true, "TODO: check that dimensions are the same, or remove that restriction")
        current = new int[start.length + 2][start.length + 2];
        for (int y=0; y < start.length; ++y) {
            for (int x = 0; x < start.length; ++x) {
                current[y+1][x+1] = start[y][x];
            }
        }
        next = new int[current.length][current.length];
    }

    public int getDim() {
        return current.length - 2;
    }
    public int getVal(int y, int x) {
        return current[y+1][x+1];
    }

    void step() {
        int n = current.length;
        // do the GoL computation from current into next
        for (int i = 1; i < n-1; ++i) {
            for (int j = 1; j < n-1; ++j) {
                int cnt = 0;
                for (int k = -1; k <= 1; ++ k) {
                    cnt += current[i-1][j+k];
                    cnt += current[i+1][j+k];
                }
                cnt += current[i][j-1];
                cnt += current[i][j+1];
                if (current[i][j] == 1 && (cnt == 2 || cnt == 3)) {
                    next[i][j] = 1;
                } else if (cnt == 3) {
                    next[i][j] = 1;
                } else {
                    next[i][j] = 0;
                }
            }
        }
        // Exchange the two arrays, no need to do some allocation here
        // save current
        int[][] saved = current;
        // move next to current
        current = next;
        // move saved to next
        next = saved;
        // and clean it
        for (int[] row: next)
            java.util.Arrays.fill(row, 0);
    }
}
