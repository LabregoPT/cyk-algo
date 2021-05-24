package model;

import java.util.ArrayList;

public class CYK {

    static String[][] x;

    /**
     * Uses the tabulation method of dynamic programming to check if w
     * can be produced through the production rules in g. Named after
     * its creators: John Cocke, Daniel Younger, Tadao Kasami, and
     * Jacob T. Schwartz.
     * <p>
     * Has a worst case running time of O(n^3 * |g|), where n is the
     * length of the parsed string and |g| is the size of the CFG
     * grammar g.
     *
     * @param g
     * @param w
     * @return
     */
    public static boolean belongsToCFG(CFG g, String w) {//todo: generalizar para que las variables puedan tener cualquier String de nombre.
        int n = w.length();
        x = new String[n][n];

        for (int i = 0; i < x[0].length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                x[i][j] = "";
            }
        }

        initializeMatrix(g, w, n);
        fillMatrix(g, n);

        printMatrix(x);

        System.out.println("last cell: " + x[0][n - 1]);
        return x[0][n - 1].contains("S");//todo: agregar a CFG un atributo con S?
    }

    /**
     * Method for debugging.
     */
    private static void printMatrix(String[][] x) {
        for (int i = 0; i < x[0].length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                System.out.print(x[i][j] + "|");
            }
            System.out.println();
        }
    }

    /**
     * Initializes the first column of x.
     *
     * @param g
     * @param w
     * @param n
     */
    private static void initializeMatrix(CFG g, String w, int n) {
        for (int i = 0; i < n; i++) {
            ArrayList<Variable> simpleProductionVariables = g.getVariablesOfSimpleProductionRules();
            for (Variable variable : simpleProductionVariables
            ) {
                for (Rule rule : variable.getRules()
                ) {//todo: generalizarlo.
                    if (!rule.isBinary()) {
                        if (rule.getTerminal().charAt(0) == w.charAt(i)) {
                            x[i][0] += variable.getName();
                        }
                    }
                }
            }
        }
    }

    /**
     * Concatenates to each cell in x the name of every variable A in G
     * such that A-->BC, where B exists in x[i][k] and C exists in
     * x[i+k][j-k].
     *
     * @param g
     */
    private static void fillMatrix(CFG g, int n) {
        for (int j = 2; j <= n; j++) { // Each column.
            for (int i = 1; i <= n - j + 1; i++) {
                int k = 1;
                do {
                    for (Variable binaryProductionVariables : g.getVariablesOfBinaryProductionRules()
                    ) {
                        for (Rule rule : binaryProductionVariables.getRules()
                        ) {
                            if (rule.isBinary()) {
                                String firstVariableOfRule = rule.getFirst().getName();
                                if (x[i - 1][k - 1].contains(firstVariableOfRule)) {
                                    String secondVariableOfRule = rule.getSecond().getName();
                                    System.out.println("n: " + n + ", i+k: " + (i + k) + ", j-k: " + (j - k));
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
                } while (k <= j - 1);
            }
        }
    }
}
