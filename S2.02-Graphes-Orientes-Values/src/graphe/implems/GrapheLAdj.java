package graphe.implems;

import graphe.core.Arc;
import graphe.core.Graphe;

import java.util.*;

public class GrapheLAdj extends Graphe {
    private Map<String, List<Arc>> ladj;

    public GrapheLAdj() {
        ladj = new HashMap<String, List<Arc>>();
    }

    public GrapheLAdj(String strGraphe) {
        this();
        this.peupler(strGraphe);
    }

    public void ajouterSommet(String sommet) {
        if (!this.ladj.containsKey(sommet)) {
            this.ladj.put(sommet, new ArrayList<Arc>());
        }
    }

    public boolean contientSommet(String sommet) {
        return this.ladj.containsKey(sommet);
    }

    @Override
    public void oterSommet(String sommet) {
        if (this.ladj.containsKey(sommet))
            this.ladj.remove(sommet);
        for (List<Arc> ensembleArcs : this.ladj.values()) {
            for (Arc arc : ensembleArcs) {
                if (arc.getDestination() == sommet)
                    this.ladj.get(arc.getSource()).remove(arc);
            }
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) throws IllegalArgumentException {
        if (valeur < 0 || contientArc(source,destination)) {
            throw new IllegalArgumentException();
        }
        if (!this.ladj.containsKey(source)) {
            this.ajouterSommet(source);
        }
        this.ladj.get(source).add(new Arc(source, destination, valeur));
    }

    @Override
    public boolean contientArc(String debut, String fin) {
        if (!this.ladj.containsKey(debut)) {
            return false;
        }
        for (Arc arc : this.ladj.get(debut)) {
            if (arc.getDestination().equals(fin)) {
                return true;
            }
        }
        return false;
    }

    public void oterArc(String source, String destination) throws IllegalArgumentException {
        if (!contientArc(source,destination)) {
            throw new IllegalArgumentException();
        }
        for(Arc arc : this.ladj.get(source)) {
            if(arc.getDestination() == destination) {
                this.ladj.get(source).remove(arc);
            }
        }
    }

    @Override
    public int getValuation(String debut, String fin) {
        assert (contientArc(debut, fin));
        for (Arc arc : this.ladj.get(debut)) {
            if (arc.getDestination().equals(fin))
                return arc.getValuation();
        }
        return -1;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> successeurs = new ArrayList<>();
        if(contientSommet(sommet)){
            for(Arc a : ladj.get(sommet)){
                successeurs.add(a.getDestination());
            }
        }
        return successeurs;
    }

    public List<String> getSommets() {
        List<String> sommets = new ArrayList<>();
        for (String sommet : this.ladj.keySet()) {
            sommets.add(sommet);
        }
        return sommets;
    }
}
