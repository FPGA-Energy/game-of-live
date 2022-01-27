package gol;

/**
 * The Game of Live.
 */
public class GoL {

    int[][] current;
    int[][] next;

    /**
     * Start a GoL scene.
     *
     * The board is assumed to be quadratic. No cells on the boarders.
     * The boarders of the array shall be empty and boarders are ignored to simplify the computation.
     */
    public GoL(int start[][]) {
        current = start;
        next = new int[current.length][current.length];
    }

    int[][] getState() {
        return current;
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
