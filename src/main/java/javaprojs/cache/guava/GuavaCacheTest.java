package javaprojs.cache.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaCacheTest
{
    LoadingCache<String, Integer> toDosCache;
    Random random = new Random();

    public static void main(String[] args)
    {
        try
        {
            GuavaCacheTest test = new GuavaCacheTest();

            test.random.setSeed(23);
            test.toDosCache = CacheBuilder.newBuilder()
                    .maximumSize(1000)
                    .expireAfterAccess(60, TimeUnit.SECONDS)
                    .build(
                            new CacheLoader<String, Integer>()
                            {
                                public Integer load(String id)
                                        throws ExecutionException
                                {
                                    final Integer toDo = test.getInteger(id);
                                    return toDo;
                                }
                            }
                    );
            System.out.println("Creating entry: " + test.toDosCache.get("first"));
            System.out.println("Reading entry: " + test.toDosCache.get("first"));
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
    }

    Integer getInteger(String toDoId)
            throws ExecutionException
    {
        final Integer toDo = random.nextInt();
        return toDo;
    }
}
