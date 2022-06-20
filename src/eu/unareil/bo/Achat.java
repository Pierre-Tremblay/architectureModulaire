package eu.unareil.bo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Achat {
    private double montant;
    private List<Ligne> achatLignes = new ArrayList<>();

    public Achat(Ligne ligne) {
        this.achatLignes.add(ligne);
    }

    public double getMontant() {
        return montant;
    }

    public List<Ligne> getAchatLignes() {
        return achatLignes;
    }
    public Ligne getLigne (int index){
        return achatLignes.get(index);
    }

    public void ajouteLigne(Produit produit, int quantite) {
        Ligne ligne = new Ligne(produit, quantite);
        this.achatLignes.add(ligne);
    }

    public void modifieLigne(int index, int nouvelleQte) {
        Ligne ligne = achatLignes.get(index);
        ligne.setQte(nouvelleQte);
    }
    public void supprimeLigne(int index) {
        achatLignes.remove(index);
    }
    public double calculMontant() {
        montant = 0;
        for (Ligne ligne : achatLignes) {
            montant += ligne.getProduit().getPrixUnitaire() * ligne.getQte()  ;
        }
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setAchatLignes(List<Ligne> achatLignes) {
        this.achatLignes = achatLignes;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Achat : ");
        DecimalFormat df = new DecimalFormat("#0.00");
        for (Ligne ligne : achatLignes) {
            sb.append("\n");
            sb.append("ligne ");
            sb.append((achatLignes.indexOf(ligne)) + 1).append(" : ");
            sb.append(ligne.toString());
        }
        sb.append("\n");
        sb.append("\n");
        sb.append("Total de l'achat : ").append(df.format(calculMontant())).append(" euro").append((montant > 1) ? "s" : "");
        return sb.toString();
    }
}
