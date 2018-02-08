package better.disk.sim;

/**
 * The disk for the file system simulation
 */
public class Disk {

    Block[] blocks;  // the blocks on the diak

    public Disk() {
        blocks = new Block[Globals.getNumberOfBlocks()];       // allocate storage
        for (int i = 0; i < Globals.getNumberOfBlocks(); i++) {
            blocks[i] = new Block();                            // create the Blocks
        }
    }

    
    /**
     * creates the free inode list
     *
     * @param inodeFreeList - the list of shorts (indices of free inode blocks)
     */
    public void createInodes(BlockList inodeFreeList) {
        for (short i = 0; i < Globals.getNumberOfInodes(); i++) {
            inodeFreeList.add(i);
        }
    }

    /**
     * creates the free data block list
     *
     * @param blockFreeList - the list of shorts (indices of free data blocks)
     */
    public void createDataBlocks(BlockList blockFreeList) {
        for (short i = Globals.getNumberOfInodes(); i < Globals.getNumberOfBlocks(); i++) {
            blockFreeList.add(i);
        }
    }

    /**
     * Writes a buffer to the disk
     *
     * @param blockNum - block to write
     * @param aBuffer - buffer to write
     */
    public void write(short blockNum, Buffer aBuffer) {
        blocks[blockNum].write(aBuffer);
    }

    /**
     * Reads a block from the disk
     *
     * @param blockNum - block to read
     * @return the byte[] in that block
     */
    public byte[] read(short blockNum) {
        if (!(blockNum < Globals.getNumberOfBlocks())) {
            Globals.complain("blockNum" + blockNum + " is too big!!");
        }

        return blocks[blockNum].read();
    }

    public String toString() {
        String returnMe = "I am a Disk";

        for (short i = 0; i < Globals.getNumberOfBlocks(); i++) {
            returnMe += "\n\t" + formatted(i);
        }

        return returnMe;
    }

    /*          T E S T   C O D E    F O L L O W S   */
    public static void main(String[] args) {
        Disk testCode = new Disk();
        testCode.init();
        testCode.writeTinyFile();
    }

    Disk theDisk;
    BlockList inodeFreeList, blockFreeList;

    private void init() {
        new FileSystem();
        theDisk = FileSystem.getTheDisk();
        inodeFreeList = new BlockList();
        blockFreeList = new BlockList();

        theDisk.createInodes(inodeFreeList);
        theDisk.createDataBlocks(blockFreeList);
    }

    private String formatted(short i) {
        return blocks[i].toString(i);
    }

    private void writeTinyFile() {
        System.out.println("Initially... theDisk = " + theDisk.toString());

        String s = "hello!";

        // Get a DataBuffer and store the string in it
        DataBuffer aDataBuffer = new DataBuffer();
        aDataBuffer.setText(s);
        System.out.println("\n\n...and now... aDataBuffer with hello! in it (I hope!) ===>" + aDataBuffer + "<==");

        // write out the data
        short directNumber = blockFreeList.remove(0);   // get a free block number
        theDisk.write(directNumber, aDataBuffer);       // write the buffer to that block

        // Get an InodeBuffer; set its size and the number of its direct block (where the data is)
        InodeBuffer anInodeBuffer = new InodeBuffer();  // create an inode buffer
        anInodeBuffer.setSize(s.length());              // save the size in it
        anInodeBuffer.setDirect(directNumber);          // save the direct link in it

        // write the inode
        short inodeNumber = inodeFreeList.remove(0);    // get a free inode block number
        theDisk.write(inodeNumber, anInodeBuffer);      // write the inode buffer there

        System.out.println("\n\n...and finally... with two blocks written theDisk = " + theDisk);

        //Globals.complain("I'm done!!");    }
    }
}
