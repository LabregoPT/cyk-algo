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

        initializeMatrix(g, w, n);
        fillMatrix(g, n);
        return x[0][n - 1].contains("s");//todo: agregar a CFG un atributo con S?
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
                ) {//todo: generalizarlo y revisar si hace falta filtrar las reglas, pues pueden haber binarias filtradas.
                    char terminal = rule.getTerminal().toLowerCase().charAt(0);
                    if (terminal == w.charAt(i)) {
                        x[i][0] += terminal;
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
        for (int j = 1; j < n; j++) { // Each column.
            for (int i = 0; i < n - j; i++) {
                for (int k = 0; k < j - 1; k++) {
                    for (Variable binaryProductionVariables : g.getVariablesOfBinaryProductionRules()
                    ) {//todo: revisar si hace falta filtrar las reglas, pues pueden haber producciones simples filtradas.
                        for (Rule rule : binaryProductionVariables.getRules()
                        ) {
                            String firstVariableOfRule = rule.getFirst().getName().toLowerCase();
                            if (x[i][k].contains(firstVariableOfRule)) {
                                String secondVariableOfRule = rule.getSecond().getName().toLowerCase();
                                if (x[i + k][j - k].contains(secondVariableOfRule)) {
                                    String variableA = binaryProductionVariables.getName().toLowerCase();
                                    x[i][j] += variableA;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
