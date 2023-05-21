package com.iset.produit.service;

import com.iset.produit.DAO.CategorieRepository;
import com.iset.produit.DAO.ProduitRepository;
import com.iset.produit.entities.Categorie;
import com.iset.produit.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService{

    @Autowired
    CategorieRepository categorieRepository;

    @Override
    public Categorie saveCategorie(Categorie p) {
        return categorieRepository.save(p);
    }

    @Override
    public Categorie updateCategorie(Categorie c,long id) {
       Categorie Cat = categorieRepository.findFirstByIdCat(id);
       Cat.setDescriptionCat(c.getDescriptionCat());
       Cat.setNomCat(c.getNomCat());
        return categorieRepository.save(Cat);
    }

    @Override
    public void deleteCategorie(Categorie p) {
        categorieRepository.delete(p);
    }

    @Override
    public void deleteCategorieById(Long id) {
        categorieRepository.deleteById(id);

    }

    @Override
    public Categorie getCategorie(Long id) {
        return categorieRepository.findById(id).get();
    }

    @Override
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();

    }

    @Override
    public Page<Categorie> getAllCategoriesParPage(int page, int size) {
        return categorieRepository.findAll(PageRequest.of(page, size));

    }
}
