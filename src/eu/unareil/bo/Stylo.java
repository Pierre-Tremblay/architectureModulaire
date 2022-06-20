package eu.unareil.bo;

import java.text.DecimalFormat;

public class Stylo extends Produit {
    private String couleur;
    private String typeMine;

    public Stylo(String couleur, String typeMine) {
        this.couleur = couleur;
        this.typeMine = typeMine;
    }

    public Stylo(long reProd, String marque,String libelle, float prixUnitaire, long qteStock, String couleur, String typeMine) {
        super(reProd, libelle, marque, prixUnitaire, qteStock);
        this.couleur = couleur;
        this.typeMine = typeMine;
    }

    public Stylo(String marque,String libelle,  long qteStock, float prixUnitaire, String couleur, String typeMine) {
        super(libelle, marque, prixUnitaire, qteStock);
        this.couleur = couleur;
        this.typeMine = typeMine;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getTypeMine() {
        return typeMine;
    }

    public void setTypeMine(String typeMine) {
        this.typeMine = typeMine;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Stylo[");
        DecimalFormat df = new DecimalFormat("#0.00");
        sb.append("libelle=").append(getLibelle());
        sb.append(",marque=").append(getMarque());
        sb.append(",prixUnitaire=").append(df.format(getPrixUnitaire())).append(" euros");
        sb.append(",qteStock=").append(getQteStock());
        sb.append(",couleur=").append(couleur);
        sb.append(",typeMine=").append(typeMine);
        sb.append(']');
        return sb.toString();
    }

}
