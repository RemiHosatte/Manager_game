package risicomp.manager.classes;

import risicomp.manager.bdd.GenreBDD;
import risicomp.manager.bdd.Sous_CategorieBDD;

/**
 * Created by PC on 24/10/2017.
 */

public class Jeu {

    private int id;
    private String nom;
    private String image_path;
    private GenreBDD genreBDD;
    private Genre genre;
    private String developpeur;
    private String editeur;
    private Integer annee;
    private int rarete;
    private int quantité;
    private Sous_CategorieBDD sous_categorieBDD;
    private Sous_Categorie sous_categorie;

    public Jeu() {
    }

    public Jeu(int id, String nom, String image_path, GenreBDD genreBDD, String developpeur, String editeur, Integer annee, int rarete, int quantité, Sous_CategorieBDD sous_categorieBDD) {
        this.id = id;
        this.nom = nom;
        this.image_path = image_path;
        this.genreBDD = genreBDD;
        this.developpeur = developpeur;
        this.editeur = editeur;
        this.annee = annee;
        this.rarete = rarete;
        this.quantité = quantité;
        this.sous_categorieBDD = sous_categorieBDD;
    }

    public Jeu(int id, String nom, String image_path, Genre genre, String developpeur, String editeur, Integer annee, int rarete, int quantité, Sous_Categorie sous_categorie) {
        this.id = id;
        this.nom = nom;
        this.image_path = image_path;
        this.genre = genre;
        this.developpeur = developpeur;
        this.editeur = editeur;
        this.annee = annee;
        this.rarete = rarete;
        this.quantité = quantité;
        this.sous_categorie = sous_categorie;
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

    public GenreBDD getGenreBDD() {
        return genreBDD;
    }

    public void setGenreBDD(GenreBDD genreBDD) {
        this.genreBDD = genreBDD;
    }

    public String getDeveloppeur() {
        return developpeur;
    }

    public void setDeveloppeur(String developpeur) {
        this.developpeur = developpeur;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public int getRarete() {
        return rarete;
    }

    public void setRarete(int rarete) {
        this.rarete = rarete;
    }

    public int getQuantité() {
        return quantité;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    public Sous_CategorieBDD getSous_categorieBDD() {
        return sous_categorieBDD;
    }

    public void setSous_categorieBDD(Sous_CategorieBDD sous_categorieBDD) {
        this.sous_categorieBDD = sous_categorieBDD;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Sous_Categorie getSous_categorie() {
        return sous_categorie;
    }

    public void setSous_categorie(Sous_Categorie sous_categorie) {
        this.sous_categorie = sous_categorie;
    }
}
