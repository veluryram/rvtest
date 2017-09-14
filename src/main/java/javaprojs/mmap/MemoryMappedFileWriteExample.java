package javaprojs.mmap;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MemoryMappedFileWriteExample {
    private static String bigExcelFile = "test.txt";

    public static void main(String[] args) {
        try
        {
            // Create file object
            File file = new File(bigExcelFile);

            //Delete the file; we will create a new file
            //file.delete();

            // Get file channel in readonly mode
            FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel();

            // Get direct byte buffer access using channel.map() operation
            MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());
            System.out.println("File size (Before): " + fileChannel.size());
            fileChannel.position(buffer.limit());

            //Write the content using put methods
            fileChannel.write(ByteBuffer.wrap("fourth line".getBytes()));
            fileChannel.force(false);
            System.out.println("File size (After): " + fileChannel.size());

            buffer.position(0);
            //You can read the file from this buffer the way you like.
            for (int i = 0; i < buffer.limit(); i++)
            {
                System.out.print((char) buffer.get()); //Print the content of file
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
