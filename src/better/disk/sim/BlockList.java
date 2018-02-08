package better.disk.sim;

import java.util.ArrayList;

/**
 * BlockList.java   --  created on Feb 23, 2012, 10:24:59 AM
 * A list of the free block NUMBERS! Not the blocks, the numbers!
 * @author levenick
 */

public class BlockList extends ArrayList<Short>{

    public BlockList() {

    }

     public String toString() {
        String returnMe = "\nBlockList: free=" + this.size() + " " + super.toString();
        return returnMe;
    }

    public String toString(boolean longDisplay) {
        String returnMe = "\nBlockList: free=" + this.size();
        if (longDisplay) {
            returnMe += " " + super.toString();
        }
        return returnMe;
    }
        

}
