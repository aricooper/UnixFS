package better.disk.sim;

/**
 * An indirect block of 4 shorts... needs to be finished!
 * I.e. declare the 4 shorts and stuff...
 */
public class IndirectBuffer extends Buffer {

    public IndirectBuffer() {
        super();

        // other stuff
    }

    IndirectBuffer(short blockNum) {
        this();    // read the block
        read(blockNum);
    }

    public byte[] toBytes() {
        data = new byte[Globals.getBlockLength()];

//        fill in the blank
        Globals.complain("Time to write toBytes() in IndirectBuffer!");

        return data;
    }

    /**
     * called right after read (in Buffer) to convert the byte array to 4 shorts
     */
    public void fromBytes() {
//        fill in the blank; see InodeBuffer for a clue!
        Globals.complain("Time to write fromBytes() in IndirectBuffer!");
    }

    public String toString() {
        String returnMe = "I am a IndirectBuffer, please fill in my variables so I can be debugged.";

        return returnMe;
    }
}
