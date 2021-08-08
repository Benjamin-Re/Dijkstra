package graphs;

// Berechne den k�rzesten Weg von v zu jedem w.
public class Dijkstra {
	
	// Unendlichkeit wird als Float.MAX_VALUE repr�sentiert
	static float infinity = Float.MAX_VALUE;
	

	
	public static void doDijkstra(float [][] graph, int [] father) {
		int n = graph.length;
		float [] dist = new float[n];
		boolean [] green = new boolean[n];
		int w;
		float minDist;
		
		// Initialisierung
		for(int i = 1; i < n; i++) {
			dist[i] = graph[0][i];
			father[i] = 0;
			green[i] = false;
		}
		
		// v ist gr�n
		green[0] = true;
		
		// W�hrend Gelb nicht leer ist, w�hle den v n�chsten Knoten w
		for(int i = 0; i < n - 1; i++) {
			minDist = infinity;
			w = 0;
			for(int j = 0; j < n; j++) {
				// Gelb ergibt sich aus nicht unendlich und nicht gr�n
				if(dist[j] < minDist && !green[j]) {
					minDist = dist[j];
					// w ist der Knoten mit geringstem Abstand zu v
					w = j;
				}
				// F�rbe w gr�n, wenn w nicht 0 ist denn 0 bedeutet dass keine Kante existiert
				if(w > 0) {
					green[w] = true;
				} else {
					// Else Gelb ist leer
					return;
				} 
			}
			// Betrachte alle Nachfolger wi von w (hier wi = j)
			for(int j = 1; j < n; j++) {
				// Wenn der Weg �ber w k�rzer ist update die distanz und...
				if(dist[j] > dist[w] + graph[w][j]) {
					dist[j] = dist[w] + graph[w][j];
					// ... F�rbe die Kante w-wi rot
					father[j] = w;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// Der Graph als Adjazenzmatrix dargestellt:
		float [][] graph = {
									{0, 2, 30, infinity, infinity},
									{0, 0, 1, infinity, infinity},
									{0, 0, 0, 5, infinity},
									{0, 0, 0, 0, 10},
									{0, 0, 0, 0, 0},			
							};
		
		int [] father = new int [graph.length];
		
		doDijkstra(graph, father);
		
		for(int i = 0; i < father.length; i++) {
			System.out.println(father[i]);
		}

	}
}
