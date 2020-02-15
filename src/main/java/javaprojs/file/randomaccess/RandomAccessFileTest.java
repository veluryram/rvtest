package javaprojs.file.randomaccess;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest
{

    private static final int BLOCKSIZE = 4096;

    public static void main(String[] args)
    {
        RandomAccessFile randomAccessFile = null;
        try
        {
            randomAccessFile = new RandomAccessFile("values0", "r");
            long fileSize = randomAccessFile.length();
            long position = randomAccessFile.getFilePointer();
            System.out.println("File pointer after opening the file : " + position);
            System.out.println("Size of the file: " + fileSize);

            int length = BLOCKSIZE;
            byte[] dest = new byte[length];
            int offset = 0;
            int bytesRead = randomAccessFile.read(dest, offset, length);

            position = randomAccessFile.getFilePointer();
            System.out.println("File pointer after reading 1024 bytes at offset 0 : " + position);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
