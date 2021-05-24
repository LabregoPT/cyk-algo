package model;
import java.util.*;

/**Class to represent each element in set V (non-terminal variables) of a CFG.*/
public class Variable {

    //Attributes
    /**Notation name for this variable. Preferably a single uppercase letter, but supports other strings.*/
    private String name;

    /**Whether or not this variable is the start variable for the CFG*/
    private boolean isStart;

    /**List of rewrite rules for this Variable.*/
    private List<Rule> rules;

    //Methods

    /**Constructor method. Initializes an instance of the class.
     * @param n Name of this variable
     * @param start Whether or not this Variable is the start Variable.
     */
    public Variable(String n, boolean start){
        isStart = start;
        name = n;
        rules = new ArrayList<Rule>();
    }

    /**Adds a new rewrite rule to this variable.
     * @param r The rule to be added
     */
    public void addRule(Rule r){
        rules.add(r);
    }

    /**Returns whether or not this variable is the start variable for the grammar.
     * @return Whether or not this variable is the start variable.
     */
    public boolean isStart(){
        return isStart();
    }

    /**Determines whether or not a given rule is part of this Variable.
      * @param r a given rule to be evaluated as part of this Variable.
     * @return true if the rule is found in this Variable, false otherwise.
     */
    public boolean hasRule(Rule r){
        return rules.contains(r);
    }

    /**
     * Returns the name of this Variable
     * @return The name of the Variable
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a List containing the rules used in this production.
     * @return A List of Rule objects corresponding to the production rules of this Variable.
     */
    public List<Rule> getRules() {
        return rules;
    }

    public boolean hasSimpleRule(){
        boolean ret = false;
        for(Rule r : rules){
            if(!r.isBinary()){
                ret = true;
                break;
            }
        }
        return ret;
    }

    public boolean hasBinaryRule(){
        boolean ret = false;
        for(Rule r : rules){
            if(r.isBinary()){
                ret = true;
                break;
            }
        }
        return ret;
    }
}
