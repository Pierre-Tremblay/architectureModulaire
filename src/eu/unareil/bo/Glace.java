package eu.unareil.bo;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Glace extends ProduitPerissable {
    private String parfum;
    private int temperatureConservation;
    private Auteur lesAuteurs;

    public Glace(String parfum, int temperatureConservation) {
        this.parfum = parfum;
        this.temperatureConservation = temperatureConservation;
    }

    public Glace(LocalDate dateLimiteConso, String marque, String libelle, int temperatureConservation, String parfum, long qteStock, float prixUnitaire) {
        super(libelle, marque, prixUnitaire, qteStock, dateLimiteConso);
        this.parfum = parfum;
        this.temperatureConservation = temperatureConservation;
    }

    public Glace(long reProd, LocalDate dateLimiteConso, String marque, String libelle, int temperatureConservation, String parfum, long qteStock, float prixUnitaire) {
        super(reProd, libelle, marque, prixUnitaire, qteStock, dateLimiteConso);
        this.parfum = parfum;
        this.temperatureConservation = temperatureConservation;
    }

    public String getParfum() {
        return parfum;
    }

    public void setParfum(String parfum) {
        this.parfum = parfum;
    }

    public int getTemperatureConservation() {
        return temperatureConservation;
    }

    public void setTemperatureConservation(int temperatureConservation) {
        this.temperatureConservation = temperatureConservation;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Glace[");
        DecimalFormat df = new DecimalFormat("#0.00");
        sb.append("libelle=").append(getLibelle());
        sb.append(",marque=").append(getMarque());
        sb.append(",prixUnitaire=").append(df.format(getPrixUnitaire())).append(" euros");
        sb.append(",qteStock=").append(getQteStock());
        sb.append(",dateLimiteConso=").append(getDateLimiteConso().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        sb.append(",parfum='").append(parfum).append('\'');
        sb.append(",temperatureConservation=").append(temperatureConservation);
        sb.append(']');
        return sb.toString();
    }
}
