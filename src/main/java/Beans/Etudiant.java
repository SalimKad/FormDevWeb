package Beans;
/*Ceci est un bean :

Il doit implémenter Serializable;
il doit posséder un constructeur vide, soit celui qui existe par défaut, soit déclaré explicitement ;
il doit exposer des propriétés, sous forme de paires getters/setters.*/
import java.io.Serializable;

public class Etudiant implements Serializable{
    private String nom;
    private String prenom;
    private String specialite;
    public Etudiant() {
    }
    public Etudiant(String nom, String prenom, String specialite) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
