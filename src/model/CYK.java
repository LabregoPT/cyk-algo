package model;

import java.util.ArrayList;

/**
 * The {@code CYK} class provides a method for determining if a given context-free grammar, <i>G</i>, is able to produce
 * a given string <i>w</i>. <i>G</i> must be in <i>Chomsky Normal Form</i> for the algorithm to guarantee its results
 * are correct. <i>G</i> must also be an instance of the {@code CFG} class.
 * <p>
 * Its only public method is {@code static} and returns <tt>true</tt> if <i>G</i> is able to produce <i>w</i>. Returns
 * <tt>false</tt> otherwise.
 */
public class CYK {

    /**
     * Is the square matrix used for the ground-up procedure of the algorithm. Each side has the length of the
     * input string w.
     */
    static String[][] x;

    /**
     * Uses a bottom-up approach and the tabulation method of dynamic programming to check if w
     * can be produced through the production rules in G. Named after
     * its creators: John Cocke, Daniel Younger, Tadao Kasami, and
     * Jacob T. Schwartz.
     * <p>
     * Has a worst-case running time of O(n^3 * |G|), where n is the
     * length of the parsed string and |G| is the size of the CFG
     * grammar G.
     * <p>
     * It first assigns to x[i][0] the name of all variables in G that can produce the ith character of w. Then, to
     * assign a value to x[i][j], it follows the procedure described in the fillMatrix() method.
     *
     * @param g Context-free grammar that is tested if it produces w
     * @param w The string to be checked
     * @return true if G is able to produce w. False otherwise
     */
    public static boolean belongsToCFG(CFG g, String w) {
        int n = w.length();
        x = new String[n][n];

        // Initializes empty String values to each cell in x.
        for (int i = 0; i < x[0].length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                x[i][j] = "";
            }
        }

        // Work is relegated to two methods: one for initialization and the other for matrix filling.
        initializeMatrix(g, w, n);
        fillMatrix(g, n);
//        printMatrix();
        return x[0][n - 1].contains("S");
    }

    /**
     * Initializes the first column of x. Assigns to x[i][0] the name of all variables in G that can produce the ith
     * character of w.
     *
     * @param g Context-free grammar that is tested if it produces w
     * @param w The string to be checked
     * @param n The length of w
     */
    private static void initializeMatrix(CFG g, String w, int n) {
        for (int i = 0; i < n; i++) {
            ArrayList<Variable> simpleProductionVariables = new ArrayList<>(g.getVariablesOfSimpleProductionRules());
            for (Variable variable : simpleProductionVariables
            ) {
                for (Rule rule : variable.getRules()
                ) {
                    if (!rule.isBinary()) {
                        if (rule.getTerminal().charAt(0) == w.charAt(i)) {
                            x[i][0] += variable.getName();
                        }
                    }
                }
            }
        }
    }

//    private static void printMatrix(){
//        int n = x[0].length;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(x[i][j]+"|");
//            }
//            System.out.println();
//        }
//    }

    /**
     * Concatenates to each cell in x the name of every variable A in G
     * such that A-->BC, where B exists in x[i][k] and C exists in
     * x[i+k][j-k].
     *
     * @param g Context-free grammar that is tested if it produces w
     */
    private static void fillMatrix(CFG g, int n) {
        // Columns are filled first.
        for (int j = 2; j <= n; j++) {
            for (int i = 1; i <= n - j + 1; i++) {
                int k = 1;
                // A do-while loop is needed so that the (k <= j - 1) condition is true when k = 1 and j = 1.
                do {
                    for (Variable binaryProductionVariables : g.getVariablesOfBinaryProductionRules()
                    ) {
                        for (Rule rule : binaryProductionVariables.getRules()
                        ) {
                            // Checks all rules of all variables that have at least one binary production rule.
                            if (rule.isBinary()) {
                                String firstVariableOfRule = rule.getFirst().getName();
                                // Checks the first condition of the algorithm for filling x. That is, if B exists in x[i][k]
                                if (x[i - 1][k - 1].contains(firstVariableOfRule)) {
                                    String secondVariableOfRule = rule.getSecond().getName();
                                    // Checks the second condition of the algorithm for filling x. That is, if C exists in
                                    //     * x[i+k][j-k].
                                    if (x[i + k - 1][j - k - 1].contains(secondVariableOfRule)) {
                                        String variableA = binaryProductionVariables.getName();
                                        if (!x[i - 1][j - 1].contains(variableA)) {
                                            x[i - 1][j - 1] += variableA;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    k++;
                    // End of the do-while loop.
                } while (k <= j - 1);
            }
        }
    }
}
