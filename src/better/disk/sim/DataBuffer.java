package better.disk.sim;

/**
 * DataBuffer is the buffer for a data block Inherits data from Buffer... text
 * is the local array of chars
 */
public class DataBuffer extends Buffer {

    char[] text = new char[Globals.getBlockLength()];

    public DataBuffer() {
        super();
    }

    public char[] getText() {
        return text;
    }

    /**
     * casts each char as a byte
     *
     * @return the buffer as 8 bytes
     */
    public byte[] toBytes() {
        data = new byte[Globals.getBlockLength()];

        for (int i = 0; i < Globals.getBlockLength(); i++) {
            data[i] = (byte) text[i];  // move the local variables
        }

        return data;
    }

    /**
     * called right after read (in Buffer) to convert the byte array to a char
     * array -- just copy and cast each element
     */
    public void fromBytes() {
        for (int i = 0; i < Globals.getBlockLength(); i++) {
            text[i] = (char) data[i];  // move to the local variables
        }
    }

    /**
     * Converts a String into an array of chars (and blanks the rest!)
     *
     * @param s -- the String to convert
     */
    public void setText(String s) {
        assert (s.length() <= Globals.getBlockLength());

        for (int i = 0; i < Globals.getBlockLength(); i++) {
            text[i] = 0;  // see toString, below, for what the 0's are for
        }

        for (int i = 0; i < s.length(); i++) {
            text[i] = s.charAt(i);
        }

        for (int i = 0; i < Globals.getBlockLength(); i++) {
            data[i] = (byte) text[i];  // move from the local variables
        }

    }

    public String toString() {
        String returnMe = "";

        for (char ch : text) {
            if (ch != 0) {
                returnMe += ch;
            }
        }

        return returnMe;
    }
}
