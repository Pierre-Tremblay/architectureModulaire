package eu.unareil.bo;

import java.time.LocalDate;

public class ProduitPerissable extends Produit {
    private LocalDate dateLimiteConso;

    public ProduitPerissable(LocalDate dateLimiteConso) {
        this.dateLimiteConso = dateLimiteConso;
    }

    public ProduitPerissable(String libelle, String marque, float prixUnitaire, long qteStock, LocalDate dateLimiteConso) {
        super(libelle, marque, prixUnitaire, qteStock);
        this.dateLimiteConso = dateLimiteConso;
    }

    public ProduitPerissable(long reProd, String libelle, String marque, float prixUnitaire, long qteStock, LocalDate dateLimiteConso) {
        super(reProd, libelle, marque, prixUnitaire, qteStock);
        this.dateLimiteConso = dateLimiteConso;
    }

    public ProduitPerissable() {

    }

    public LocalDate getDateLimiteConso() {
        return dateLimiteConso;
    }

    public void setDateLimiteConso(LocalDate dateLimiteConso) {
        this.dateLimiteConso = dateLimiteConso;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ProduitPerissable{");
        sb.append("dateLimiteConso=").append(dateLimiteConso);
        sb.append('}');
        return sb.toString();
    }
}
