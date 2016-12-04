package javaprojs.jline;

import jline.TerminalFactory;
import jline.console.ConsoleReader;

import java.io.IOException;

/**
 * Created by Ram Velury on 12/3/16.
 */

public class MultilineDemo
{

    public static void main(String[] args)
    {
        try
        {
            ConsoleReader console = new ConsoleReader();
            console.getTerminal().setEchoEnabled(false);
            console.setPrompt("> ");
            String line;
            StringBuilder commandBuffer = new StringBuilder();
            String prompt = "$ ";
            while ((line = console.readLine(prompt)) != null)
            {
                String trimmedLine = line.trim();
                if (trimmedLine.length() == 0)
                {
                    commandBuffer.delete(0, commandBuffer.length());
                    continue;
                }

                if (trimmedLine.endsWith(";"))
                {
                    System.out.println(commandBuffer.toString());
                    prompt = "$ ";
                }
                else
                {
                    prompt = "> ";
                    commandBuffer.append(trimmedLine).append("\n");
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
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
