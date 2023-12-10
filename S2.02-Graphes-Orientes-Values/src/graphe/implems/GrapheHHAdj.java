package graphe.implems;
import java.util.*;

import graphe.core.Graphe;

public class GrapheHHAdj extends Graphe {
    private Map<String, Map<String, Integer>> hhadj;

    public GrapheHHAdj(String str) {
        this.hhadj = new HashMap<>();
        this.peupler(str);
    }

    public GrapheHHAdj() {
        this.hhadj = new HashMap<>();
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!this.hhadj.containsKey(noeud)) {
            this.hhadj.put(noeud, new HashMap<>());
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) throws IllegalArgumentException{
        if (valeur < 0 || this.contientArc(source, destination)) {
            throw new IllegalArgumentException();
        }
        if (!this.hhadj.containsKey(source)) {
            this.ajouterSommet(source);
        }
        this.hhadj.get(source).put(destination, valeur);
    }

    @Override
    public void oterSommet(String noeud) {
        this.hhadj.remove(noeud);
    }

    @Override
    public void oterArc(String source, String destination) throws IllegalArgumentException {
        if (!this.contientArc(source, destination)) {
            throw new IllegalArgumentException();
        }
        this.hhadj.get(source).remove(destination);
    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<>(this.hhadj.keySet());
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> successeurs = new ArrayList<>();
        if (!this.hhadj.containsKey(sommet)) {
            return successeurs;
        }
        this.hhadj.get(sommet).forEach((k, v) -> successeurs.add(k));
        return successeurs;
    }

    @Override
    public int getValuation(String src, String dest) {
        return this.hhadj.get(src).get(dest);
    }

    @Override
    public boolean contientSommet(String sommet) {
        return this.hhadj.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        if (this.hhadj.get(src) == null) {
            return false;
        }
        return this.hhadj.get(src).containsKey(dest);
    }
}