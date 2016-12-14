package javaprojs.file;

import java.io.File;

/**
 * Created by Ram on 12/14/2016.
 */
public class AbsolutePathTest {
    static String absolutePathName = "C:/cygwin/tmp/ssbm";
    static String relativePathName = "tmp/ssbm";

    public static void main(String[] args) {
        File file1 = new File(absolutePathName);
        File file2 = new File(relativePathName);

        System.out.println(absolutePathName + " is an " + (file1.isAbsolute()?"absolute" : "relative") + " pathname");
        System.out.println(relativePathName + " is an " + (file2.isAbsolute()?"absolute" : "relative") + " pathname");
    }
}
