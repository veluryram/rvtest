package javaprojs.transactionlog;

import java.io.ByteArrayInputStream;
        import java.io.ByteArrayOutputStream;
        import java.io.IOException;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;
        import java.io.Serializable;
        import java.util.Date;

public class Entry implements Serializable {

    private Date timestamp = new Date();
    private String username;
    private String message;

    public Entry(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getUsername() {
        return username;
    }

    public static byte[] serialize(Entry entry)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(entry);
        oos.flush();

        return baos.toByteArray();
    }

    public static Entry deserialize(byte[] byteArray)
            throws IOException, ClassNotFoundException {

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteArray));
        Entry entry = (Entry) ois.readObject();

        return entry;
    }
}