package graphe.core;

public class Arc {
    private String noeudSource;
    private String noeudDestination;
    private int valuation;
  
    public Arc(String noeudSource, String noeudDestination, int valuation) throws IllegalArgumentException{
        if(valuation<0)
            throw new IllegalArgumentException();
        
        this.noeudSource = noeudSource;
        this.noeudDestination = noeudDestination;
        this.valuation = valuation;
    }

    public String getSource() {
        return noeudSource;
    }

    public String getDestination() {
        return noeudDestination;
    }

    public int getValuation() {
        return valuation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Arc arc = (Arc) o;
        if (this.valuation != arc.valuation) return false;
        if (!this.noeudSource.equals(arc.noeudSource)) return false;
        return (this.noeudDestination.equals(arc.noeudDestination));
    }
}