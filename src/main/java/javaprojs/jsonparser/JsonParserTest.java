package javaprojs.jsonparser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;
import java.util.Stack;

/**
 * Created by velury on 3/26/16.
 */
public class JsonParserTest
{
    public static Stack<String> fieldNameStack = new Stack<String>();

    public static JsonParser jp;

    public static void main(String [] args)
    {
        try {
            JsonFactory f = new JsonFactory();
            jp = f.createParser(new File("/home/velury/test/javaprojdata/user.json"));
            fieldNameStack.push("");
            jsonToCsv();
            jp.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    static void jsonToCsv()
    {
        while (readJsonObject());
    }

    public static boolean readJsonObject()
    {
        JsonToken token;

        try {
            while (jp.nextToken() != null) {
                token = jp.getCurrentToken();
                switch (token) {
                    case START_OBJECT: {
                        break;
                    }
                    case END_OBJECT: {
                        fieldNameStack.pop();
                        if (fieldNameStack.empty()) {
                            return true;
                        }
                        break;
                    }
                    case START_ARRAY: {
                        break;
                    }
                    case END_ARRAY: {
                        break;
                    }
                    case FIELD_NAME: {
                        fieldNameStack.push(getParentFieldWithQualifier() + jp.getCurrentName());
                        break;
                    }
                    case VALUE_EMBEDDED_OBJECT: {
                        break;
                    }
                    case VALUE_STRING: {
                        System.out.println(fieldNameStack.pop() + ":" + jp.getText());
                        break;
                    }
                    case VALUE_NUMBER_INT: {
                        System.out.println(fieldNameStack.pop() + ":" + jp.getText());
                        break;
                    }
                    case VALUE_NUMBER_FLOAT: {
                        System.out.println(fieldNameStack.pop() + ":" + jp.getText());
                        break;
                    }
                    case VALUE_TRUE: {
                        System.out.println(fieldNameStack.pop() + ":" + jp.getText());
                        break;
                    }
                    case VALUE_FALSE: {
                        System.out.println(fieldNameStack.pop() + ":" + jp.getText());
                        break;
                    }
                    case VALUE_NULL: {
                        System.out.println(fieldNameStack.pop() + ":" + jp.getText());
                        break;
                    }
                    case NOT_AVAILABLE:
                    default: {
                        JsonLocation loc = jp.getCurrentLocation();
                        throw new RuntimeException("Error parsing json. Cannot determine token type(" + "Line:" + loc.getLineNr() + ", Column:" + loc.getColumnNr() + ")");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error parsing json", e);
        }

        return false;
    }

    public static String getParentFieldWithQualifier()
    {
        return fieldNameStack.peek() == "" ? "" : fieldNameStack.peek() + ".";
    }
}
