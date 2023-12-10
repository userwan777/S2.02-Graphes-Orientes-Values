package graphe.algos;

import graphe.core.IGrapheConst;

import java.util.*;

public class Dijkstra {

    static int INFINI = - 1;
    public static void dijkstra(IGrapheConst graphe, String source, Map<String, Integer> dist, Map<String, String> pred) {

        Comparator<String> comp = (s1, s2) -> (Integer)(dist.get(s1) - dist.get(s2));
        PriorityQueue<String> queue = new PriorityQueue<>(comp);
        HashSet<String> sommetsVisites = new HashSet<>();

        dist.put(source, 0);
        pred.put(source, "");
        queue.offer(source);

        while(!queue.isEmpty()) {
            String sommet = queue.poll();

            for(String sommetAdjacent : graphe.getSucc(sommet)) {

               dist.putIfAbsent(sommetAdjacent,INFINI);
               pred.putIfAbsent(sommetAdjacent,"");

                int nouvelleDistance = dist.get(sommet) + graphe.getValuation(sommet,sommetAdjacent);

                if(dist.get(sommetAdjacent) == INFINI) {
                    dist.replace(sommetAdjacent,nouvelleDistance);
                    pred.replace(sommetAdjacent,sommet);
                }

                if(nouvelleDistance < dist.get(sommetAdjacent) && sommetsVisites.contains(sommetAdjacent)) {
                    dist.replace(sommetAdjacent, nouvelleDistance);
                    pred.replace(sommetAdjacent, sommet);
                    // mise à jour du queue suite au changement de la distance du sommet, donc son ordre dans la file doit changer
                    queue.remove(sommetAdjacent);
                    queue.offer(sommetAdjacent);
                }
                if(!sommetsVisites.contains(sommetAdjacent)) {
                    queue.offer(sommetAdjacent);
                    sommetsVisites.add(sommetAdjacent);
                }
            }
        }
    }
}

