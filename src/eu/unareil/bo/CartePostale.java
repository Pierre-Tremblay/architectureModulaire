package eu.unareil.bo;

import java.text.DecimalFormat;
import java.util.List;

public class CartePostale extends Produit {
    private TypeCartePostale type;
    private List<Auteur> lesAuteurs;

    public CartePostale(TypeCartePostale type) {
        this.type = type;
    }


    public CartePostale(long reProd, String marque, String libelle, long qteStock, float prixUnitaire, List<Auteur> lesAuteurs, TypeCartePostale type) {
        super(reProd, libelle, marque, prixUnitaire, qteStock);
        this.type = type;
        this.lesAuteurs = lesAuteurs;
    }

    public CartePostale(String marque, String libelle, long qteStock, float prixUnitaire, List<Auteur> lesAuteurs, TypeCartePostale type) {
        super(libelle, marque, prixUnitaire, qteStock);
        this.type = type;
        this.lesAuteurs = lesAuteurs;
    }


    public TypeCartePostale getType() {
        return type;
    }

    public void setType(TypeCartePostale type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CartePostale[");
        DecimalFormat df = new DecimalFormat("#0.00");
        sb.append("libelle=").append(getLibelle());
        sb.append(",marque=").append(getMarque());
        sb.append(",prixUnitaire=").append(df.format(getPrixUnitaire())).append(" euros");
        sb.append(",qteStock=").append(getQteStock());
        sb.append(", auteur(s)=");
        for (Auteur auteur : lesAuteurs) {
            sb.append("auteur(").append(lesAuteurs.indexOf(auteur) + 1).append(")=");
            sb.append(" ").append(auteur.getPrenom());
            sb.append(" ").append(auteur.getNom()).append(",");
        }

        sb.append("type=").append(getType());

        sb.append(']');
        return sb.toString();
    }
}
