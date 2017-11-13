package risicomp.manager.classes;

import risicomp.manager.bdd.CategorieBDD;
import risicomp.manager.bdd.TypeBDD;

/**
 * Created by PC on 24/10/2017.
 */


public class Sous_Categorie {

    private int id;
    private String nom;
    private String image_path;
    private TypeBDD typeBDD;
    private Type type;
    private CategorieBDD categorieBDD;
    private Categorie categorie;

    public Sous_Categorie() {
    }


    public Sous_Categorie(int id, String nom, String image_path, TypeBDD typeBDD, CategorieBDD categorieBDD) {
        this.id = id;
        this.nom = nom;
        this.image_path = image_path;
        this.typeBDD = typeBDD;
        this.categorieBDD = categorieBDD;
    }
    public Sous_Categorie(int id, String nom, String image_path, Type type, Categorie categorie) {
        this.id = id;
        this.nom = nom;
        this.image_path = image_path;
        this.type = type;
        this.categorie = categorie;
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

    public TypeBDD getTypeBDD() {
        return typeBDD;
    }

    public void setTypeBDD(TypeBDD typeBDD) {
        this.typeBDD = typeBDD;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public CategorieBDD getCategorieBDD() {
        return categorieBDD;
    }

    public void setCategorieBDD(CategorieBDD categorieBDD) {
        this.categorieBDD = categorieBDD;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return nom;
    }
}
