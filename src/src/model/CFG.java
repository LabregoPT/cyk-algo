package model;
import java.util.*;

/**
 * Class meant to represent Context-Free Grammars.<br>
 * Creation, utilization and addition of transformations are made in this class, along with respective operations used in the CYK algorithm.<br>
 * This class follows the formal definition of a CFG, a 4-tuple consisting of:<br>
 * -A set V of non-terminal variables.<br>
 * -An alphabet A with a set of terminals used in the grammar.<br>
 * -A set R of rewrite rules, combining elements from V and A.<br>
 * -S, the start variable of the grammar. Must be an element of V.
 */
public class CFG {

    //Attributes

    /**Preferable a single character name, but supports name longer than that.*/
    private String name;

    /**Set V of non-terminal variables in the CFG.*/
    private List<Variable> variables;

    /**Set of terminals used in the CFG*/
    private Alphabet alphabet;

    /**Set R of Rewrite Rules*/
    private List<Rule> rules;

    /**Start Variable for CFG*/
    private Variable start;


    //Methods

    /**Constructor method. Initializes instance and all dependencies.
     * @param n The name of this CFG
     */
    public CFG(String n){
        name = n;
        variables = new ArrayList<Variable>();
        rules = new ArrayList<Rule>();
        alphabet = new Alphabet();
    }

    /**
     * Adds a new terminal for the CFG alphabet.
     * @param terminal The terminal to be added in the alphabet
     */
    public void addTerminal(String terminal){
        alphabet.addTerminal(terminal);
    }

}
