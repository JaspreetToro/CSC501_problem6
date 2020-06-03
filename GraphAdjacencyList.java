import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GraphAdjacencyList 
{
	static int number_vertices; 
	static ArrayList<Integer> edgeList = new ArrayList<Integer>();
	private static Map<Integer, HashSet<Integer>> adjListsMap;
	public GraphAdjacencyList(int vertices)
	{
		adjListsMap = new HashMap<Integer, HashSet<Integer>>();
		for(int i =1; i<=vertices;i++)
		{
			HashSet<Integer> neighbours = new HashSet<Integer>();
			adjListsMap.put(i, neighbours);
		}
	}
	public void addEdges(int u, int v)
	{
		if(u > adjListsMap.size() || v > adjListsMap.size())
		{
			return; 
		}
		(adjListsMap.get(u)).add(v); // only one edge
		//System.out.println(adjListsMap);
		(adjListsMap.get(v)).add(u);
		//System.out.println(adjListsMap);
	}
	public ArrayList<Integer> getNeighbours(int u)
	{
		if(u > adjListsMap.size())
		{
			return null;
		}
		return new ArrayList<Integer>(adjListsMap.get(u));
	}
	public static void main(String args[])
	{
		int count  = 1, source, dest;
		
		System.out.print("Enter the number of vertices");
		Scanner scan = new Scanner(System.in);
		number_vertices = scan.nextInt();
		int number_edges  = ThreadLocalRandom.current().nextInt(0, (number_vertices-1)*(number_vertices-1));
		System.out.println("Edges are: "+number_edges);
		GraphAdjacencyList adjacencyList = new GraphAdjacencyList(number_vertices);
		while(count <= number_edges)
		{
			source = ThreadLocalRandom.current().nextInt(1, number_vertices);
			//System.out.print(source+" ");
			dest = ThreadLocalRandom.current().nextInt(1, number_vertices);
			//System.out.println(dest);
			if(source != dest)  // Does't create edge for self loop
			{
				adjacencyList.addEdges(source, dest);
			}
			count++;
		}
		System.out.println("The given adjacency List for the graph\n");
		for(int i=1; i<number_vertices; i++)
		{
			System.out.print(i+"->");
			edgeList = adjacencyList.getNeighbours(i);
			//System.out.println(edgeList.size());
			if(edgeList.size() > 0)
			{
				for(int j=1;;j++)
				{
					if(j!=edgeList.size())
					{
						System.out.print(edgeList.get(j-1)+" -> ");
					}
					else 
					{
						System.out.println(edgeList.get(j-1));
						break;
					}
				}
				System.out.println();
			}
			
		}
		System.out.println("Please enter the start node for the BFS between "+"1 and "+number_vertices);
		int startnode = scan.nextInt(); 
		BFS(startnode);
	}
	
    static void BFS(int s) 
    { 
    	//int r=0;
    	int parent = 0;
    	int previousnode = 0;
        if(!adjListsMap.get(s).isEmpty()) // if node has edges then only it will go inside the loop
        {
	        
	        boolean visited[] = new boolean[number_vertices]; 
	        LinkedList<Integer> queue = new LinkedList<Integer>(); 
	        visited[s]=true; 
	        queue.add(s); 
	        System.out.println("BFS Tree: ");
	        while (queue.size() != 0) 
	        { 
	            s = queue.poll(); 
	            System.out.print(" "+s+" "); 
	            //System.out.println(adjListsMap.get(s));
	            List<Integer> neighborlist = new ArrayList<Integer>(adjListsMap.get(s));
				Iterator<Integer> i = neighborlist.listIterator();
				if(parent >=1)
	            {
	            	if (adjListsMap.get(previousnode).contains(s))
					{
						System.out.print("("+previousnode+")");
					}
					else 
					{
						 List<Integer> updatelist = new ArrayList<Integer>(adjListsMap.get(previousnode));
						for (int j =0;;j++)
						{
							previousnode = updatelist.get(j); // adjListsMap.get(previousnode)
							if (adjListsMap.get(previousnode).contains(s))
							{
								System.out.print("("+previousnode+")");
								break;
							}
						}
						
					}
	            }
	            while (i.hasNext()) 
	            { 
	                int n = i.next(); 
	               if (!visited[n]) 
	                { 
	                    visited[n] = true; 
	                    queue.add(n); 
	                }
	            } 
				if(parent < 1)
				{
						previousnode = s;
						System.out.println();
				}
	            parent++;
	        }
        }
        else
        {
        	System.out.println();
        	System.out.println("Please choose another node since "+s+" doesn't have any edge");
        	Scanner scan = new Scanner(System.in);
			int newstartnode = scan.nextInt();
			BFS(newstartnode);
        }
    } 
}