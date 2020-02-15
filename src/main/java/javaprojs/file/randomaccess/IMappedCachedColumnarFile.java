package javaprojs.file.randomaccess;

public interface IMappedCachedColumnarFile
{
    void close();

    int readInt(long offset);

    long readLong(long offset);

    byte[] readBytes(long offset, int count);
}
