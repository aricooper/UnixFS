package better.disk.sim;

/**
 * abstract class that the three real buffers inherit from
 */
public abstract class Buffer {

    /**
     * Convert the buffer to an array of bytes
     *
     * @return the array of bytes corresponding to this buffer
     */
    abstract byte[] toBytes();

    /**
     * Convert to the internal form of the buffer from the byte[] data array
     */
    abstract void fromBytes();

    byte[] data;

    public Buffer() {
        data = new byte[Globals.getBlockLength()];
    }

    /**
     * Reads a block from the disk and converts (using fromBytes in one of the
     * subclasses) into the correct internal buffer form
     *
     * @param blockNum - the block number
     */
    public final void read(short blockNum) {
        data = FileSystem.getTheDisk().read(blockNum);
        fromBytes();  // let the subclass figure out what the bytes mean! And convert from bytes
    }

    public String toString() {
        String returnMe = "you will never see this (I hope)!";

        return returnMe;
    }
}
