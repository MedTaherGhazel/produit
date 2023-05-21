package com.iset.produit.service;

import com.iset.produit.entities.Categorie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategorieService {

    Categorie saveCategorie(Categorie p);
    Categorie updateCategorie(Categorie p , long id);
    void deleteCategorie(Categorie p);
    void deleteCategorieById(Long id);
    Categorie getCategorie(Long id);
    List<Categorie> getAllCategories();
    Page<Categorie> getAllCategoriesParPage(int page, int size);
}
