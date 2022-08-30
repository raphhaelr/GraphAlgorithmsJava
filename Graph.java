import java.util.ArrayList;

public class Graph {

  private int countNodes;
  private int countEdges;
  private int[][] adjMatrix;

  public Graph(int countNodes) {
    this.countNodes = countNodes;
    this.adjMatrix = new int[countNodes][countNodes];
  }

  public int getCountNodes() {
    return this.countNodes;
  }

  public int getCountEdges() {
    return this.countEdges;
  }

  public int[][] getAdjMatrix() {
    return this.adjMatrix;
  }

  public String toString() {
    String str = "";
    for (int i = 0; i < this.adjMatrix.length; ++i) {
      for (int j = 0; j < this.adjMatrix[i].length; ++j) {
        str += this.adjMatrix[i][j] + "\t";
      }
      str += "\n";
    }
    return str;
  }

  public void addEdge(int source, int sink, int weight) {
    if (source < 0 || source > this.countNodes - 1
        || sink < 0 || sink > this.countNodes - 1 || weight <= 0) {
      System.err.println("Invalid edge: " + source + sink + weight);
      return;
    }
    this.adjMatrix[source][sink] = weight;
    this.countEdges++;
  }

  public void addUnorientedEdge(int u, int v, int w) {
    if (u < 0 || u > this.countNodes - 1
        || v < 0 || v > this.countNodes - 1 || w <= 0) {
      System.err.println("Invalid edge: " + u + v + w);
      return;
    }
    this.adjMatrix[u][v] = w;
    this.adjMatrix[v][u] = w;
    this.countEdges += 2;
  }

  public int degree(int node) {
    if (node < 0 || node > this.countNodes - 1)
      System.err.println("Invalid node: " + node);
    int degree = 0;
    for (int j = 0; j < this.adjMatrix[node].length; ++j) {
      if (this.adjMatrix[node][j] != 0)
        ++degree;
    }
    return degree;
  }

  public int highestDegree() {
    int highest = 0;
    for (int i = 0; i < this.adjMatrix.length; ++i) {
      int degreeNodeI = this.degree(i);
      if (highest < degreeNodeI)
        highest = degreeNodeI;
    }
    return highest;
  }

  public int lowestDegree() {
    int lowest = this.countNodes;
    for (int i = 0; i < this.adjMatrix.length; ++i) {
      int degreeNodeI = this.degree(i);
      if (lowest > degreeNodeI)
        lowest = degreeNodeI;
    }
    return lowest;
  }

  public Graph complement() {
    Graph g2 = new Graph(this.countNodes);
    for (int i = 0; i < this.adjMatrix.length; ++i) {
      for (int j = 0; j < this.adjMatrix[i].length; ++j) {
        if (i != j && this.adjMatrix[i][j] == 0) {
          g2.addEdge(i, j, 1);
        }
      }
    }
    return g2;
  }

  public float density() {
    return (float) this.countEdges / (this.countNodes * (this.countNodes - 1));
  }

  public boolean subGraph(Graph g2) {
    if (g2.countNodes > this.countNodes || g2.countEdges > this.countEdges)
      return false;
    for(int i = 0; i < g2.adjMatrix.length; ++i) {
      for(int j = 0; j < g2.adjMatrix[i].length; ++j) {
        if(g2.adjMatrix[i][j] != 0 && this.adjMatrix[i][j] == 0)
          return false;
      }
    }
    return true;
  }

  public ArrayList<Integer> bfs(int s){
    int desc[] = new int[this.countNodes];

    ArrayList<Integer> Q = new ArrayList<>();

    Q.add(s);

    ArrayList<Integer> R = new ArrayList<>();

    R.add(s);

    desc[s] = 1;

    while(Q.size() != 0){
      int u = Q.remove(0);

      for (int v = 0; v<this.adjMatrix[u].length; v++) {
        if(this.adjMatrix[u][v] != 0){
          if(desc[v] == 0){
            Q.add(v);
  
            R.add(v);
  
            desc[v] = 1;
          }
        }
      }
    }

    return R;
  }

  public boolean connected(int s){
    return this.bfs(s).size() == this.countNodes;
  }

  public ArrayList<Integer> dfs(int s){
    int desc[] = new int[this.countNodes];

    ArrayList<Integer> S = new ArrayList<>();

    S.add(s);

    ArrayList<Integer> R = new ArrayList<>();

    R.add(s);

    desc[s] = 1;

    while(S.size() != 0){
      int lastIdx = S.size() - 1;
		  int u = S.get(lastIdx);
      
      boolean desempilhar = true;

      for (int v = 0; v<this.adjMatrix[u].length; v++) {
        if(this.adjMatrix[u][v] != 0){
          if(desc[v] == 0){
            desempilhar = false;

            S.add(v);
  
            R.add(v);

            desc[v] = 1;

            break;
          }
        }
      }

      if(desempilhar){
        lastIdx = S.size() - 1;

        S.remove(lastIdx);
      }
    }

    return R;
  }

  public void dfs_rec_aux(int u, int[] desc, ArrayList<Integer>R){
    desc[u] = 1;
    
    R.add(u);

    for (int v = 0; v<this.adjMatrix[u].length; v++) {
      if(this.adjMatrix[u][v] != 0){
        if(desc[v] == 0){
          dfs_rec_aux(v, desc, R);
        }
      }
    }
  }

  public ArrayList<Integer> dfs_rec(int s){
    int desc[] = new int[this.countNodes];

    ArrayList<Integer> R = new ArrayList<>();

    dfs_rec_aux(s, desc, R);

    return R;
  }
}