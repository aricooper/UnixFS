/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package better.disk.sim;

/**
 *
 * @author levenick
 */
class FileSystem {

    static Disk getTheDisk() {
        return theDisk;
    }

    FileList theFileList;
    static Disk theDisk;
    BlockList inodeFreeList;
    BlockList blockFreeList;
    private String text;
    private Short directNumber;
    private int length;

    private void init() {
        inodeFreeList = new BlockList();
        blockFreeList = new BlockList();

        theDisk.createInodes(inodeFreeList);
        theDisk.createDataBlocks(blockFreeList);
    }
 
    FileSystem() {
        theFileList = new FileList();
        theDisk = new Disk();
        init();
    }

    void save(String t) {
        this.text = t;
        length = t.length();
        if (text.length() == 0) {
            return;
        }
        
        while (text.length() > 0) {
            storeDirect();
            storeInDirect();
            storeDDirect();
           
        }

       storeInode();
        
        System.out.println("\n\n...and finally... with two blocks written theDisk = " + theDisk);

        //Globals.complain("I'm done!!");    }
    }

    String load() {
        short inodeNumber = 0;  // cheating, need to get a filename, and see if it's in the list, then pick up the associated inode

        InodeBuffer inodeBuffer = new InodeBuffer(inodeNumber);

        return readData(inodeBuffer);
    }

    private String readData(InodeBuffer inode) {
        String returnMe = "";

        DataBuffer aDataBuffer = new DataBuffer();
        aDataBuffer.read(inode.getDirect());
        returnMe += aDataBuffer.toString();
        
        // now, if there are indirect blocks, add their data
        
        // finally, if there are double indirect blocks, add their data

        System.out.println("Returning data = " + returnMe);

        return returnMe;
    }

    private void storeDirect() {
        
        char[] charList = new char[8];
        String firstBlock = "";
         // Get a DataBuffer and store the string in it
        DataBuffer aDataBuffer = new DataBuffer();
        if (text.length() < Globals.getBlockLength()) {
            
            text.getChars(0, text.length(), charList, 0);
            for(char c: charList) {
                firstBlock += c;
            }
            text = "";
            
        } else {
        firstBlock = text.substring(0, Globals.getBlockLength());
        text = text.substring(Globals.getBlockLength());
        }
        aDataBuffer.setText(firstBlock);
        // write out the data
        directNumber = blockFreeList.remove(0);   // get a free block number
        theDisk.write(directNumber, aDataBuffer);       // write the buffer to that block
        
        
    }

    private void storeInDirect() {
        
        
    }

    private void storeDDirect() {
        
    }

    private void storeInode() {
        
         // Get an InodeBuffer; set its size and the number of its direct block (where the data is)
        InodeBuffer anInodeBuffer = new InodeBuffer();  // create an inode buffer
        anInodeBuffer.setSize(length);              // save the size in it
        anInodeBuffer.setDirect(directNumber);          // save the direct link in it
        
        // write the inode
        short inodeNumber = inodeFreeList.remove(0);    // get a free inode block number
        theDisk.write(inodeNumber, anInodeBuffer);      // write the inode buffer there


    }

}
