package com.iset.produit.entities;



import groovy.transform.ToString;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Produit {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idProduit;
    @NotNull
    @Size(min = 4,max = 15)
    private String nomProduit;
    @Min(value = 10)
    @Max(value = 10000)
    private Double prixProduit;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private Date dateCreation;

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @ManyToOne
    private Categorie categorie;

    public Produit(String nomProduit, Double prixProduit, Date dateCreation) {
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.dateCreation = dateCreation;
    }

    public Produit() {
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public Double getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(Double prixProduit) {
        this.prixProduit = prixProduit;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "idProduit=" + idProduit +
                ", nomProduit='" + nomProduit + '\'' +
                ", prixProduit=" + prixProduit +
                ", dateCreation=" + dateCreation +
                ", categorie=" + categorie +
                '}';
    }
}