package com.example.produit.DAO;


import com.iset.produit.DAO.ProduitRepository;
import com.iset.produit.ProduitApplication;
import com.iset.produit.entities.Categorie;
import com.iset.produit.entities.Produit;
import com.iset.produit.service.ProduitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootTest(classes = ProduitApplication.class)


class ProduitApplicationTests {
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private ProduitService service;
    @Test
    public void testCreateProduit() {
        Produit prod = new Produit("PC Asus",1500.500,new Date());
        produitRepository.save(prod);
    }
    @Test
    public void testFindProduit() {
        Produit p = produitRepository.findById(1L).get();
        System.out.println(p);
    }

    @Test
    public void testUpdateProduit() {
        Produit p = produitRepository.findById(1L).get();
        p.setPrixProduit(2000.0);
        produitRepository.save(p);

        System.out.println(p);
    }

    @Test
    public void testDeleteProduit() {
        produitRepository.deleteById(1L);

    }

    @Test
    public void testFindAllProduits() {
        List<Produit> prods = produitRepository.findAll();

        for (Produit p : prods)
            System.out.println(p);

    }
//@Test
//    public void testFindByNomProduit() {
//        List<Produit> prods = produitRepository.findByNomProduit(PageRequest.of(page, size), "Asus");
//        for (Produit p : prods) {System.out.println("testFindByNomProduit " + p.getNomProduit());
//        }
//    }
    @Test
    public void testFindByNomProduitContains() {
        List<Produit> prods =
                produitRepository.findByNomProduitContains("Asus");
        for (Produit p : prods) {
            System.out.println(p.getNomProduit());
        }
    }
    @Test
    public void testfindByNomPrix() {
        List<Produit> prods = produitRepository.findByNomPrix("Asus", 1000.0);
        for (Produit p : prods) {
            System.out.println(p);
        }
    }
    @Test
    public void testfindByCategorie() {
        Categorie cat = new Categorie();
        cat.setIdCat(1L);
        List<Produit> prods = produitRepository.findByCategorie(cat);
        for (Produit p : prods) {
            System.out.println(p);
        }
    }
    @Test
    public void findByCategorieIdCat() {
        List<Produit> prods = produitRepository.findByCategorieIdCat(1L);
        for (Produit p : prods) {
            System.out.println(p);
        }
    }
    @Test
    public void testfindByOrderByNomProduitAsc() {
        List<Produit> prods =
                produitRepository.findByOrderByNomProduitAsc();
        for (Produit p : prods) {
            System.out.println(p);
        }
    }
    @Test
    public void testTrierProduitsNomsPrix() {
        List<Produit> prods = produitRepository.trierProduitsNomsPrix();
        for (Produit p : prods) {
            System.out.println(p);
        }
    }

    }
