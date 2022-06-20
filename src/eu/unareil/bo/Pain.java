package eu.unareil.bo;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pain extends ProduitPerissable {
    private int poids;

    public Pain(int poids) {
        this.poids = poids;
    }

    public Pain(long reProd, String marque, String libelle, int poids, long qteStock, float prixUnitaire) {
        super(reProd, libelle, marque, prixUnitaire, qteStock, LocalDate.now().plusDays(2));
        this.poids = poids;
    }

    public Pain(String marque, String libelle, int poids, long qteStock, float prixUnitaire) {
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
        DecimalFormat df = new DecimalFormat("#0.00");
        sb.append("libelle=").append(getLibelle());
        sb.append(",marque=").append(getMarque());
        sb.append(",prixUnitaire=").append(df.format(getPrixUnitaire())).append(" euros");
        sb.append(",qteStock=").append(getQteStock());
        sb.append(",dateLimiteConso=").append(getDateLimiteConso().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        sb.append(", poids=").append(poids).append("g");
        sb.append(']');
        return sb.toString();
    }
}
