package better.disk.sim;

/**
 * A disk block, just an array of bytes. It will try to infer what it is being
 * used for and toString() itself as that
 */
public class Block {

    private byte[] bytes;

    public Block() {
        bytes = new byte[Globals.getBlockLength()];
    }

    /**
     * Reads this block
     * @return the raw bytes from the disk
     */
    public byte[] read() {
        return bytes;
    }

    public String toString() {
        String returnMe = "I am a Block and not printable directly, this should never display!!.";

        return returnMe;
    }

    /**
     * For initial debugging only!  Will not work if there are more than, um...
     * ...see asADatablock...
     * @param blockNumber -- the block number!
     * @return this block as a String!
     */
    public String toString(short blockNumber) {
        if (blockNumber < Globals.getNumberOfInodes()) {
            return "" + blockNumber + ": " + asAnInode(blockNumber);
        } else {
            return "" + blockNumber + ": " + asADataBlock();
        }
    }

    /**
     * Converts the internal variables in a Buffer to byte[] and stores here
     *
     * @param aBuffer -- the Buffer to write
     */
    public void write(Buffer aBuffer) {
        byte[] data;
        data = aBuffer.toBytes();
        System.arraycopy(data, 0, bytes, 0, Globals.getBlockLength());
    }

    private String asAnInode(short blockNumber) {
        InodeBuffer iBuffer = new InodeBuffer(blockNumber);

        return iBuffer.toString();
    }

    /**
     * For initial debugging only!
     * Will NOT work if the number of blocks exceeds the smallest value of data.
     * What!? Well... in the for loop... 
     * if ((int) nextByte >= Globals.getNumberOfBlocks()) CANNOT work correctly in that case!
     * But, that's okay, since toString() should not be sent to Blocks (only to Buffers!)
     * @return this block as a data block (debugging only)
     */
    private String asADataBlock() {
        boolean isData = false;  // is it a data block??

        for (byte nextByte : bytes) {
            if ((int) nextByte >= Globals.getNumberOfBlocks()) {
                isData = true;
            }
        }

        if (isData) {
            return "data block: " + asData();
        } else {
            return "link block" + asLink();
        }
    }

    private String asData() {
        String returnMe = "";

        for (byte b : bytes) {
            returnMe += (char) b;
        }

        return returnMe;
    }

    private String asLink() {
        String returnMe = "";

        for (int i = 0; i < Globals.getLinksPerBlock(); i++) {
            returnMe += " " + bytes[i * 2] * 256 + bytes[i * 2 + 1];
        }

        return returnMe;
    }
}
