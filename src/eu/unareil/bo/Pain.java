package eu.unareil.bo;

import java.time.LocalDate;

public class Pain extends ProduitPerissable {
    private int poids;

    public Pain(int poids) {
        this.poids = poids;
    }

    public Pain(long reProd, String marque, String libelle, int poids, long qteStock,float prixUnitaire) {
        super(reProd, libelle, marque, prixUnitaire, qteStock, LocalDate.now().plusDays(2));
        this.poids = poids;
    }

    public Pain(String marque, String libelle, int poids, long qteStock,float prixUnitaire) {
        super(libelle, marque, prixUnitaire, qteStock, LocalDate.now().plusDays(2));
        this.poids = poids;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Pain[");
        sb.append("libelle=").append(getLibelle());
        sb.append(",marque=").append(getMarque());
        sb.append(",prixUnitaire=").append(getPrixUnitaire()).append(" euros");
        sb.append(",qteStock=").append(getQteStock());
        sb.append(",dateLimiteConso=").append(getDateLimiteConso());
        sb.append(", poids=").append(poids).append("g");
        sb.append(']');
        return sb.toString();
    }
}
