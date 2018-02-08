package better.disk.sim;

/**
 * The buffer for an inode!
 */
public class InodeBuffer extends Buffer {

    protected short size;
    protected short direct;
    protected short indirect;
    protected short doubleIndirect;

    public InodeBuffer() {
        super();
    }   //empty default constructor

    public InodeBuffer(short size, short direct, short indirect, short doubleIndirect) {   //initializing constructor
        this();   // invoke the default constructor
        this.size = size;
        this.direct = direct;
        this.indirect = indirect;
        this.doubleIndirect = doubleIndirect;
    }

    InodeBuffer (short blockNum) {
        super();    // read the block
        read(blockNum);
    }
    
    /**
     * Pushes each of the four fields into two bytes in the appropriate spot
     *
     * @return the byte array
     */
    byte[] toBytes() {
        setLink(0, size);
        setLink(2, direct);
        setLink(4, indirect);
        setLink(6, doubleIndirect);

        return data;
    }

    /**
     * called right after read (in Buffer) to convert the byte array to 4 shorts
     * take the bytes 2 at a time and make a short out of each pair
     */
    public void fromBytes() {
        size = decodeLink(0);
        direct = decodeLink(2);
        indirect = decodeLink(4);
        doubleIndirect = decodeLink(6);
    }

    public short getSize() {
        return size;
    }

    public short getDirect() {
        return direct;
    }

    public short getIndirect() {
        return indirect;
    }

    public short getDoubleIndirect() {
        return doubleIndirect;
    }

    public void setSize(int size) {
        this.size = (short) size;
    }

    public void setDirect(short direct) {
        this.direct = direct;
    }

    public void setIndirect(short indirect) {
        this.indirect = indirect;
    }

    public void setDoubleIndirect(short doubleIndirect) {
        this.doubleIndirect = doubleIndirect;
    }

    private short decodeLink(int i) {
        return (short) (256 * data[i] + data[i + 1]);
    }

    /**
     * puts the top 8 bits of x in data[i[ and the lower 8 bits in data[i+1]
     * @param i -- index of the first byte to convert
     * @param linkOr - the short (which is either the length, or a link
     */
    private void setLink(int i, short linkOr) {
        data[i + 1] = (byte) (linkOr % 256);
        data[i] = (byte) (linkOr >> 8);
    }

    public String toString() {
        String returnMe = "I am a InodeBuffer: ";
        returnMe += "\tsize=" + getSize();
        returnMe += "\tdirect=" + getDirect();
        returnMe += "\tindirect=" + getIndirect();
        returnMe += "\tdoubleIndirect=" + getDoubleIndirect();
        return returnMe;
    } // toString()
}  // InodeBuffer

