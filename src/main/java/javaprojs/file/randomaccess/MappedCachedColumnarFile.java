package javaprojs.file.randomaccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.RandomAccessFile;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ram Velury on 12/30/15.
 */
class MappedCachedColumnarFile
    implements IMappedCachedColumnarFile
{
    private static final Logger log = LoggerFactory.getLogger(MappedCachedColumnarFile.class);
    private static final int BLOCKBITS = 12;
    private static final int BLOCKSIZE = 2 << 12;
    private String pathName;
    private transient RandomAccessFile file;
    private transient boolean fixed;

    // File number of this instance out of all files being managed
    private transient int fileNum;

    // File number - manages number files being managed at any point
    static AtomicInteger fileCount = new AtomicInteger(0) ;

    public MappedCachedColumnarFile(String fileName, boolean fixed)
    {
        pathName = fileName;
        this.fixed = fixed;

        try {
            file = new RandomAccessFile(pathName, "r");
            fileNum = fileCount.incrementAndGet();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close()
    {
        try {
            file.close();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            fileCount.decrementAndGet();
        }
    }

    @Override
    public int readInt(long offset)
    {
        long blockId = ((long)fileNum << (64-BLOCKBITS)) | (offset >> BLOCKBITS);
        //System.out.println(String.format("0x%x", (long)fileNum<< 32));
        //System.out.println(String.format("0x%x", (long)fileNum<< (64-BLOCKBITS)));
        System.out.println("File Num: "  + fileNum + " Offset: " + offset + " Block Id: " +String.format("0x%x", blockId));
        return 0;
    }

    @Override
    public long readLong(long offset)
    {
        return 0;
    }

    @Override
    public byte[] readBytes(long offset, int count)
    {
        return new byte[0];
    }

    public static void main(String[] args)
    {
        IMappedCachedColumnarFile file = new MappedCachedColumnarFile("test.dat", true);
        file.readInt(0);
        file.readInt(4097);
    }
}
