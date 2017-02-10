import java.util.LinkedList;
import java.util.ListIterator;

@SuppressWarnings("unchecked")
public class Graph {
    private int VertexCount; //set of vertices
    private LinkedList<Integer> adjList[]; //Array of List for Adjecency v

    public Graph(int vertices) {
        this.VertexCount = vertices;
        adjList = new LinkedList[VertexCount];

        for(int i = 0; i < VertexCount; i++) {
            adjList[i] = new LinkedList();
        }
    }

        public void addEdge(int v, int w) {
            adjList[v].add(w);
        }

        public void DFS() {
            boolean[] visitedArray = new boolean[VertexCount];

            for(int i = 0; i < VertexCount; i++) {
                if (!visitedArray[i]) DFSUtil(i, visitedArray);
            }
        }

        public void DFSUtil(int vertex, boolean[] visitedArray) {

            //Mark the current node as visited and print
            visitedArray[vertex] = true;
            System.out.print(vertex + " ");

            //Reccurs for all the adjencent vertices of this VertexCount
            ListIterator<Integer> itemsList = adjList[vertex].listIterator();
            while(itemsList.hasNext()) {
                int item = itemsList.next();
                if(!visitedArray[item]) DFSUtil(item, visitedArray);
            }
        }

        public static void main(String[] args) {
            Graph objGraph = new Graph(4);
            objGraph.addEdge(0, 1);
            objGraph.addEdge(0, 2);
            objGraph.addEdge(1, 2);
            objGraph.addEdge(2, 0);
            objGraph.addEdge(2, 3);
            objGraph.addEdge(3, 3);
            System.out.println("DFS from vertex 2 is");
            objGraph.DFS();
        }
}
