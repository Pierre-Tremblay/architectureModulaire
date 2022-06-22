package eu.unareil.bo;

public class Auteur {
    private long idAuteur;
    private String prenom;
    private String nom;

    public Auteur() {

    }

    public Auteur(long idAuteur, String prenom, String nom) {
        this.idAuteur = idAuteur;
        this.prenom = prenom;
        this.nom = nom;
    }

    public Auteur(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public long getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(long idAuteur) {
        this.idAuteur = idAuteur;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
