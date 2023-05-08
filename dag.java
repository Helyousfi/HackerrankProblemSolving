

import java.util.*;

class dag
{

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph)
    {
        // Create our queue and our result
        Queue<List<Integer>> queue = new ArrayDeque<> ();
        List<List<Integer>> result = new ArrayList<> ();

        // Add [0] to our queue
        queue.offer( Arrays.asList(0) );

        // Define goal node
        int goalNode = graph.length - 1;

        // Repeat while the queue is not empty
        while(!queue.isEmpty())
        {
            List<Integer> path = queue.poll();
            int lastNode = path.get(path.size() - 1);
            if( lastNode == goalNode)
            {
                result.add(path);
            }
            else
            {
                for(int neighbor : graph[lastNode])
                {
                    List<Integer> lst = new ArrayList<> (path);
                    lst.add(neighbor);
                    queue.offer(lst);
                }
            }
        }
        return result;
    }

    public static void main(String args[])
    {
        int[][] graph = {{1, 2}, {3}, {1, 3}, {}};
        System.out.println(allPathsSourceTarget(graph));
    }
}
