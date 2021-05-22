package model;

/**
 * Class meant to represent a rewrite rule in the CFG. Is composed of both Variables and terminals.<br>
 * Because the program is meant to be used only with Chomsky Normal Form (CNF) CFGs, rules can only be either binary rules or simple rules.
 */
public class Rule {

    //Attributes
    /**Whether or not this rule is a binary rule*/
    private boolean binary;

    /**The terminal of this rule, in case it is not binary*/
    private String terminal;

    /**First Variable in this rule, in case it is a binary.*/
    private Variable first;

    /**Second Variable in this rule, in case it is a binary.*/
    private Variable second;


    //Methods

    /**
     * Constructor used when this Rule is a Simple Rule. Initializes an instance of the class.
     * @param t The terminal used for this Rule.
     */
    public Rule(String t){
        binary = true;
        terminal = t;
    }

    /**
     * Constructor used when this Rule is a binary rule. Initializes an instance of the class.
     * @param f First variable of the binary rule.
     * @param s Second variable of the binary rule.
     */
    public Rule(Variable f, Variable s){
        binary = false;
        first = f;
        second = s;
    }

    /**
     * Returns the value of the terminal string of this Rule.
     * @return the String in the terminal for this Rule.
     */
    public String getTerminal(){
        return terminal;
    }

    /**
     * Returns first Variable in this Rule
     * @return first Variable in this Rule.
     */
    public Variable getFirst(){
        return first;
    }

    /**
     * Returns second Variable in this Rule.
     * @return second Variable in this Rule.
     */
    public Variable getSecond(){
        return second;
    }

    /**
     * Determines whether this Rule has a Variable or not.
     * @param r The given Variable to check in this Rule.
     * @return -1 if this Rule is simple or given Variable is not found. 1 if Variable is found as first Variable, 2 if Variable is found as second variable.
     */
    public int hasVariable(Variable r){
        int ret = 0;
        if(binary){
            ret = -1;
        }else{
            if(first.equals(r)) ret = 1;
            if(second.equals(r)) ret = 2;
            ret = -1;
        }
        return ret;
    }

}
