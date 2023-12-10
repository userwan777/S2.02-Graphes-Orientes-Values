package graphe.implems;

import graphe.core.Graphe;

import java.util.*;

public class GrapheMAdj extends Graphe {
    private int[][] matrice;
    private final Map<String, Integer> indices;

    public GrapheMAdj() {
        this.matrice = new int[0][0];
        this.indices = new HashMap<>();
    }

    public GrapheMAdj(String graphe) {
        this();
        this.peupler(graphe);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!contientSommet(noeud)){
            this.indices.put(noeud, this.indices.size());
            int[][] m = new int[this.indices.size()][this.indices.size()];
            for (int i = 0; i < m.length; ++i) {
                for (int j = 0; j < m.length; ++j) {
                    if (i == m.length - 1 || j == m.length - 1) m[i][j] = -1;
                    else m[i][j] = this.matrice[i][j];
                }
            }
            this.matrice = m;
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (contientArc(source,destination) || valeur < 0) throw new IllegalArgumentException();
        if (!contientSommet(source)) ajouterSommet(source);
        if (!destination.equals("")) {
            if (!contientSommet(destination)) ajouterSommet(destination);
            this.matrice[this.indices.get(source)][this.indices.get(destination)] = valeur;
        }
    }

    @Override
    public void oterSommet(String noeud) {
        if (contientSommet(noeud)){
            int indice = this.indices.get(noeud);
            this.indices.remove(noeud);
            for (String sommet : this.indices.keySet()) {
                if (this.indices.get(sommet) > indice) this.indices.replace(sommet, this.indices.get(sommet) - 1);
            }
            int[][] m = new int[this.indices.size()][this.indices.size()];
            for (int i = 0; i < m.length; ++i) {
                for (int j = 0; j < m.length; ++j) {
                    if (i == indice && j == indice) m[i][j] = this.matrice[i + 1][j + 1];
                    else if (i != indice && j == indice) m[i][j] = this.matrice[i][j + 1];
                    else if (i == indice && j != indice) m[i][j] = this.matrice[i + 1][j];
                    else m[i][j] = this.matrice[i][j];
                }
            }
            this.matrice = m;
        }
    }

    @Override
    public void oterArc(String source, String destination) {
        if (!contientArc(source,destination)) throw new IllegalArgumentException();
        this.matrice[this.indices.get(source)][this.indices.get(destination)] = -1;
    }

    @Override
    public List<String> getSommets() {
        List<String> listeSommets = new ArrayList<>(this.indices.keySet());
        Collections.sort(listeSommets);
        return listeSommets;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> successeurs = new ArrayList<>();
        for (String succ : this.indices.keySet()) {
            if (contientArc(sommet, succ)) {
                successeurs.add(succ);
            }
        }
        Collections.sort(successeurs);
        return successeurs;
    }

    @Override
    public int getValuation(String src, String dest) {
        if (contientArc(src, dest))
            return this.matrice[this.indices.get(src)][this.indices.get(dest)];
        return -1;
    }

    @Override
    public boolean contientSommet(String sommet) {
        return this.indices.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        if (contientSommet(src) && contientSommet(dest))
            return this.matrice[this.indices.get(src)][this.indices.get(dest)] != -1;
        return false;
    }
}