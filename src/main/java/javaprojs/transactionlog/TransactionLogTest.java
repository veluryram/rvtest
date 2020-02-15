package javaprojs.transactionlog;

import sun.applet.Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionLogTest
{
        public static void main(String args[]) {

            EntryFile entryFile = null;
            try {
                entryFile = new EntryFile("entries");
                for (int i = 0; i < 100; i++) {
                    Entry entry = new Entry("name[" + i + "]", "message[" + i + "]");
                    entryFile.appendEntry(entry);
                }
                for (int i = 99; i >= 0; i--) {
                    Entry entry = entryFile.readEntry(i);
                    System.out.println("Entry["+i+"]");
                    System.out.println("\\ttimestamp=" + entry.getTimestamp().toString());
                    System.out.println("\\tusername=" + entry.getUsername());
                    System.out.println("\\tmessage=" + entry.getMessage());
                }
                entryFile.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}

