package javaprojs.javastrings;

/**
 * Created by Ram Velury on 4/8/16.
 */
public class StringBuilderTest1
{
    public static void main(String[] args)
    {
        String [] names = {"Fabricio", "Jonathan", "Luke", "Robert", "Rafael", "Connor", "Dominick", "Demetrius"};

        StringBuilder str = new StringBuilder("(");
        for (String name: names)
        {
            str.append(name).append(",");
        }
        str.deleteCharAt(str.length()-1);
        str.append(")");

        System.out.println(str);
    }
}
