package eu.unareil.bo;

import java.text.DecimalFormat;

public class Ligne {
    private int quantite;
    private Produit produit;


    public Ligne(Produit produit, int quantite) {
        this.quantite = quantite;
        this.produit = produit;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQte() {
        return quantite;
    }

    public void setQte(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return produit.getPrixUnitaire() * this.getQte();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("  Ligne[");
        DecimalFormat df = new DecimalFormat("#0.00");
        sb.append("produit=").append(getProduit());
        sb.append(", qte=").append(getQte());
        sb.append(", prix=").append(df.format(this.getPrix())).append(" euro").append((this.getPrix() > 1) ? "s" : "");
        sb.append(']');
        return sb.toString();
    }
}
