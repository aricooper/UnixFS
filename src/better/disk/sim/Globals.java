package better.disk.sim;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Globals.java   --  created on Feb 23, 2012, 9:14:11 AM
 * A place for global things...
 * The file system lives here: if there were multiple file systems, they would be
 * stored here in an array (or some such), and be selectable from without (as in
 * Globals.getFileSystem(1);
 * 
 * @author levenick
 */
public class Globals {
    private final static short BLOCK_LENGTH = 8;
    private final static short LINKS_PER_BLOCK = BLOCK_LENGTH/2;  // since each link is 2 bytes
    private final static short NUMBER_OF_BLOCKS = 30;
    private final static short NUMBER_OF_INODES = 3;
    
    public static int singleIndirectSizeMax() {
        return getBlockLength() + getLinksPerBlock() * getBlockLength();  // useful number!
    }

    public static int doubleIndirectSizeMax() {
        return singleIndirectSizeMax() + getLinksPerBlock() * getLinksPerBlock() * getBlockLength();
    }


    public static short getLinksPerBlock() {
        return LINKS_PER_BLOCK;
    }

    public static short getBlockLength() {
        return BLOCK_LENGTH;
    }

    public static short getNumberOfBlocks() {
        return NUMBER_OF_BLOCKS;
    }

    public static short getNumberOfInodes() {
        return NUMBER_OF_INODES;
    }


    public static void init() {

    }

    public static void complain(String s) {
        JOptionPane.showMessageDialog(new JFrame(),
                s,
                "Complaint!",
                JOptionPane.ERROR_MESSAGE);
    }

    public String toString() {
        String returnMe = "I am the Globals";

        returnMe +=("\n\tgetLinksPerBlock() = " + getLinksPerBlock());
        returnMe +=("\n\tgetBlockLength() = " + getBlockLength());
        returnMe +=("\n\tgetNumberOfBlocks() = " + getNumberOfBlocks());
        returnMe +=("\n\tgetNumberOfInodes() = " + getNumberOfInodes());
        returnMe +=("\n\tsingleIndirectSizeMax() = " + singleIndirectSizeMax());
        returnMe +=("\n\tdoubleIndirectSizeMax() = " + doubleIndirectSizeMax());

        return returnMe;
    }
    
    public static void main(String[] args) {
        System.out.println("toString() = " + new Globals().toString());
    }
}
