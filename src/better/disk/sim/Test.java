package better.disk.sim;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author levenick
 */
public class Test {

    public static void main(String[] args) {
        new MyFSFrame();
    }
    
    void play() {
        for (;;) {
            String s = getFN();
            if (s==null) break;
            System.out.println("s = " + s);
        }
        testToString();
        playWithStrings();
    }

    private static void testToString() {
        byte[] alpha = {'a', 'b', 'c', 'd', 'A', 'B'};

        byte[] beta = {1, 2, 3, 97};
        System.out.println("alpha");
        outputAsLetters(alpha);
        outputAsNumbers(alpha);
        System.out.println("\nbeta");
        outputAsLetters(beta);
        outputAsNumbers(beta);

    }

    static void outputAsLetters(byte[] data) {
        String buffer = "as letters... ";
        for (int i = 0; i < data.length; i++) {
            buffer += (char) data[i] + ",";
        }
        System.out.println("" + buffer);
    }

    private static void outputAsNumbers(byte[] data) {
        String buffer = "without recourse... ";
        for (int i = 0; i < data.length; i++) {
            buffer += data[i] + " ";
        }
        System.out.println("" + buffer);
    }

    static void playWithStrings() {
        String s = "willamette!";
        String will = s.substring(0, 4);
        String tail = s.substring(4);
        System.out.println("will = " + will);
        System.out.println("tail = " + tail);
    }

    static String getFN() {
        String s = (String) JOptionPane.showInputDialog(
                new JFrame(),
                "Enter filename",
                "File name, please!",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "foo");

        //If a string was returned, say so.
        if ((s != null) && (s.length() > 0)) {
            return s;
        }
        return null;
    }
}
