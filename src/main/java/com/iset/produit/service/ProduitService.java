package com.iset.produit.service;


import com.iset.produit.entities.Categorie;
import com.iset.produit.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProduitService {
    Produit saveProduit(Produit p , Long CategorieId);
    Produit updateProduit(Produit p ,long id , Long categorieID);
    void deleteProduit(Produit p);
    void deleteProduitById(Long id);
    Produit getProduit(Long id);
    List<Produit> getAllProduits();
    Page<Produit> getAllProduitsParPage(int page, int size);
    List<Produit> findByNomProduit(String nom);

    List<Produit> findByNomProduitContains(String nom);
    List<Produit> findByNomPrix (String nom, Double prix);
    List<Produit> findByCategorie (Categorie categorie);
    List<Produit> findByCategorieIdCat(Long id);
    List<Produit> findByOrderByNomProduitAsc();
    List<Produit> trierProduitsNomsPrix();
    Page<Produit> searchProductsContainingName(String name, int pageNumber, int pageSize);

}