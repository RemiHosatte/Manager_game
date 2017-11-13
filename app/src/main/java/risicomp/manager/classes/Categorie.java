package risicomp.manager.classes;

/**
 * Created by PC on 24/10/2017.
 */


public class Categorie {
    private int id;
    private String nom;
    private String image_path;

    public Categorie() {

    }

    public Categorie(int id, String nom, String image_path) {
        this.id = id;
        this.nom = nom;
        this.image_path = image_path;
    }

    public Categorie(String nom, String image_path) {
        this.nom = nom;
        this.image_path = image_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
    @Override
    public String toString() {
        return nom;
    }
}
