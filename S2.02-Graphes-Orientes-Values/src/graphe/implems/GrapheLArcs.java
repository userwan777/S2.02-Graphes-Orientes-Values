package graphe.implems;

import graphe.core.Arc;
import graphe.core.Graphe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrapheLArcs extends Graphe {
    private final List<Arc> arcs;

    public GrapheLArcs(){
        arcs = new ArrayList<>();
    }

    public GrapheLArcs(String strGraphe){
        this();
        this.peupler(strGraphe);
    }

    public void ajouterSommet(String noeud){
        if (!contientSommet(noeud))
            arcs.add(new Arc(noeud,"",0));
    }

    public void ajouterArc(String source, String destination, Integer valeur){
        if(contientArc(source, destination))
            throw new IllegalArgumentException("Le graphe contient déjà le sommet");
        if(valeur < 0)
            throw new IllegalArgumentException("La valuation est négative");
        if(!"".equals(destination))
            arcs.add(new Arc(source,destination,valeur));
    }

    public void oterSommet(String noeud){
        if (contientSommet(noeud)){
            for (int i = 0; i < arcs.size(); ++i){
                if (arcs.get(i).getSource().equals(noeud) || arcs.get(i).getDestination().equals(noeud))
                    arcs.remove(arcs.get(i));
            }
        }
    }

    public void oterArc(String source, String destination){
        if(contientArc(source, destination)) {
            for (int i = 0; i < arcs.size(); ++i) {
                if (arcs.get(i).getSource().equals(source) && arcs.get(i).getDestination().equals(destination))
                    arcs.remove(arcs.get(i));
            }
        } else
            throw new IllegalArgumentException("L'Arc n'existe pas dans le graphe");
    }

    public List<String> getSommets(){
        List<String> sommets = new ArrayList<>();
        for (Arc arc : arcs) {
            if (!sommets.contains(arc.getSource()))
                sommets.add(arc.getSource());
            if (!sommets.contains(arc.getDestination()) && !arc.getDestination().equals(""))
                sommets.add(arc.getDestination());
        }
        Collections.sort(sommets);
        return sommets;
    }

    public List<String> getSucc(String sommet){
        List<String> successeur = new ArrayList<>();
        for (Arc arc : arcs) {
            if (arc.getSource().equals(sommet) && !"".equals(arc.getDestination())) {
                successeur.add(arc.getDestination());
            }
        }
        Collections.sort(successeur);
        return successeur;
    }

    public int getValuation(String src, String dest){
        for (Arc arc : arcs) {
            if (arc.getSource().equals(src) && arc.getDestination().equals(dest))
                return arc.getValuation();
        }
        return -1;
    }

    public boolean contientSommet(String sommet){
        return getSommets().contains(sommet);
    }

    public boolean contientArc(String src, String dest){
        for (String s: getSommets()) {

            if (src.equals(s) && getSucc(s).contains(dest))
                return true;
        }
        return false;
    }
}
