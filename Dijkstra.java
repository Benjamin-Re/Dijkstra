package graphs;

// Berechne den kürzesten Weg von v zu jedem w.
public class Dijkstra {
	
	// Unendlichkeit wird als Float.MAX_VALUE repräsentiert
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
		
		// v ist grün
		green[0] = true;
		
		// Während Gelb nicht leer ist, wähle den v nächsten Knoten w
		for(int i = 0; i < n - 1; i++) {
			minDist = infinity;
			w = 0;
			for(int j = 0; j < n; j++) {
				// Gelb ergibt sich aus nicht unendlich und nicht grün
				if(dist[j] < minDist && !green[j]) {
					minDist = dist[j];
					// w ist der Knoten mit geringstem Abstand zu v
					w = j;
				}
				// Färbe w grün, wenn w nicht 0 ist denn 0 bedeutet dass keine Kante existiert
				if(w > 0) {
					green[w] = true;
				} else {
					// Else Gelb ist leer
					return;
				} 
			}
			// Betrachte alle Nachfolger wi von w (hier wi = j)
			for(int j = 1; j < n; j++) {
				// Wenn der Weg über w kürzer ist update die distanz und...
				if(dist[j] > dist[w] + graph[w][j]) {
					dist[j] = dist[w] + graph[w][j];
					// ... Färbe die Kante w-wi rot
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
