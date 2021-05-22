package model;
import java.util.*;
/**Wrapper class for a dictionary meant to represent the alphabet used in the CFG.<br>
 * Usage of dictionary ensures no terminal used is not defined first in the alphabet for the CFG.
 */
public class Alphabet {

    //Atributes
    /**Actual Java Map containing terminals and their respective Ids.*/
    HashMap<Integer, String> terminals;

    /**Stores current id of last terminal added in Alphabet.*/
    private int lastId;

    //Methods
    /**Constructor method. Instantiates class.*/
    public Alphabet(){
        terminals = new HashMap<Integer, String>();
        lastId = 0;
    }

    /**Adds a new terminal to Alphabet, and increases current id of terminal.*/
    public void addTerminal(String terminal){
        terminals.put(lastId, terminal);
        lastId++;
    }

    /**Returns terminal corresponding to given id.*/
    public String getTerminal(int id){
        return terminals.get(id);
    }

    /**Returns id of particular terminal, or -1 if terminal is not added in alphabet*/
    public int getTerminalId(String terminal){
        for(Map.Entry<Integer,String> entry : terminals.entrySet()){
            if(Objects.equals(terminal, entry.getValue())){
                return entry.getKey();
            }
        }
        return -1;
    }

    /**Returns terminal list*/
    public ArrayList<String> getTerminals(){
        ArrayList<String> ret = new ArrayList<>();
        ret.addAll(terminals.values());
        return ret;
    }
}
