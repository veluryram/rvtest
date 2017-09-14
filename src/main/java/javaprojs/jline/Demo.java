package javaprojs.jline;

import jline.TerminalFactory;
import jline.console.ConsoleReader;

import java.io.IOException;

/**
 * Created by Ram Velury on 12/3/16.
 */

public class Demo
{

    public static void main(String[] args)
    {
        try
        {
            ConsoleReader console = new ConsoleReader();
            String line;
            String prompt = "$ ";
            while ((line = console.readLine(prompt)) != null)
            {
                System.out.println(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally
        {
            try
            {
                TerminalFactory.get().restore();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
