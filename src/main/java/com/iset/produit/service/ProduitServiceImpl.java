package com.iset.produit.service;

import com.iset.produit.DAO.CategorieRepository;
import com.iset.produit.DAO.ProduitRepository;
import com.iset.produit.entities.Categorie;
import com.iset.produit.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProduitServiceImpl implements ProduitService {
    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    CategorieRepository categorieRepository;

    @Override
    public Produit saveProduit(Produit p , Long CategorieId) {
     Categorie categorie =categorieRepository.findFirstByIdCat(CategorieId);
       p.setCategorie(categorie);
       return produitRepository.save(p);
    }
    @Override
    public Produit updateProduit(Produit p , long id , Long categorieId) {
        Produit prod = produitRepository.findFirstByIdProduit(id);
        Categorie cat = categorieRepository.findFirstByIdCat(categorieId);
        prod.setCategorie(cat);
        prod.setNomProduit(p.getNomProduit());
        prod.setPrixProduit(p.getPrixProduit());
        prod.setDateCreation(p.getDateCreation());
        return produitRepository.save(prod);
    }
    @Override
    public void deleteProduit(Produit p) {
        produitRepository.delete(p);

    }
    @Override
    public void deleteProduitById(Long id) {
        produitRepository.deleteById(id);
    }
    @Override
    public Produit getProduit(Long id) {
        return produitRepository.findById(id).get();
    }
    @Override
    public List<Produit> getAllProduits() {

        return produitRepository.findAll();
    }
    @Override
    public Page<Produit> getAllProduitsParPage(int page, int size) {
        // TODO Auto-generated method stub
        return produitRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Produit> findByNomProduit(String nom){
        List<Produit> prods = produitRepository.findByNomProduit(nom);
        return prods;
    }

    @Override
    public List<Produit> findByNomProduitContains(String nom)
    {
        List<Produit> prods = produitRepository.findByNomProduitContains(nom);
        return prods;
    }
    @Override
    public List<Produit> findByNomPrix(String nom, Double prix)
    {
        List<Produit> prods = produitRepository.findByNomPrix(nom,prix);
        return prods;

    }



    @Override
    public List<Produit> findByCategorie (Categorie categorie){
        List<Produit> prods = produitRepository.findByCategorie(categorie);
        return prods;

    }
    @Override
    public List<Produit> findByCategorieIdCat(Long id){
        List<Produit> prods = produitRepository.findByCategorieIdCat(id);
        return prods;

    }
    @Override
    public List<Produit> findByOrderByNomProduitAsc(){
        List<Produit> prods = produitRepository.findByOrderByNomProduitAsc() ;
        return prods;

    }
    @Override
    public List<Produit> trierProduitsNomsPrix(){
        List<Produit> prods = produitRepository.trierProduitsNomsPrix();
        return prods;
    }
    public Page<Produit> searchProductsContainingName(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return produitRepository.findByNomProduitContaining(name,pageable);
    }

}