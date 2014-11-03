package iteration;

import java.util.Queue;

/**
 *
 * Iteration
 * Question
 * Reimplement this code so that its results will always be the same, but that it does not cause a stack overflow on large inputs. Your solution must still implement the Folder interface.
 * Coding
 * Your class must be named iteration.MyFolder
 * Download
 * iteration/Folder.java
 * iteration/Function2.java
 */
public class MyFolder<T, U> implements Folder<T, U>
{
    public U fold(U u, Queue<T> ts, Function2<T, U, U> function)
    {
        if(u == null || ts == null || function == null)
            throw new IllegalArgumentException();

        while(!ts.isEmpty()){
            u = function.apply(ts.poll(), u);
        }
        return u;
    }
}
