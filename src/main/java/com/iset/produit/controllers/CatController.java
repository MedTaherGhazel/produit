package com.iset.produit.controllers;

import com.iset.produit.entities.Categorie;
import com.iset.produit.entities.Produit;
import com.iset.produit.service.CategorieService;
import com.iset.produit.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CatController {
    @Autowired
    ProduitService produitService;
    @Autowired
    CategorieService categorieService;
    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {

        modelMap.addAttribute("produit", new Produit());
        modelMap.addAttribute("categorie",new Categorie());
        List<Categorie> CategorieList = categorieService.getAllCategories();
        modelMap.addAttribute("CategorieList",CategorieList);

        return "createProduit";
    }
    /*
     * @RequestMapping("/showCreate") public String showCreate() { return
     * "createProduit"; }
     */
    @RequestMapping("/saveProduit")
    public String saveProduit(@Valid Produit produit,
                              BindingResult bindingResult,
                              ModelMap modelMap,
                              @RequestParam(name="categorieId") Long CategorieId
    ) {
        if (bindingResult.hasErrors()) {
            List<Categorie> CategorieList = categorieService.getAllCategories();
            modelMap.addAttribute("CategorieList",CategorieList);
            return "createProduit";
        }
        Produit saveProduit = produitService.saveProduit(produit,CategorieId);
        String msg = "produit enregistré avec Id " + saveProduit.getIdProduit();
        modelMap.addAttribute("msg", msg);
        return "createProduit";
    }

    @RequestMapping("/saveCategorie")
    public String saveCategorie(@Valid Categorie categorie,
                              BindingResult bindingResult,
                              ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "createCategorie";
        }
        Categorie saveCategorie = categorieService.saveCategorie(categorie);
        String msg = "Categorie enregistré avec Id " +
                saveCategorie.getIdCat();
        modelMap.addAttribute("msg", msg);
        return "createCategorie";
    }

    @RequestMapping("/ListeCategories")
    public String listeCategories(
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size)
    {
        Page<Categorie> Categories = categorieService.getAllCategoriesParPage(page, size);
        modelMap.addAttribute("categories", Categories);
        modelMap.addAttribute("pages", new int[Categories.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "listeCategories";
    }



    @RequestMapping("/ListeProduits")
    public String listeProduits(
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size)
    {
        Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
        modelMap.addAttribute("produits", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "listeProduits";
    }

    @RequestMapping("/filter")
    public String FiltrerParNom( ModelMap modelMap,
                                 @RequestParam(name="nom") String nom,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "2") int size){
//        List<Produit>  filtredProds = produitService.findByNomProduitContains(nom);
//          Page<Produit> filtredProds = produitService.getAllProduitsParPage(page,size);
       Page<Produit> filtredProds = produitService.searchProductsContainingName(nom, page, size);
        if(filtredProds!=null)
        {
            modelMap.addAttribute("produits", filtredProds);
            modelMap.addAttribute("pages", new int[filtredProds.getTotalPages()]);
            modelMap.addAttribute("currentPage", page);
            String msg = "Product found :";
            modelMap.addAttribute("foundMessage", msg);

        }
        else{
            Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
            modelMap.addAttribute("produits", prods);
            modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
            modelMap.addAttribute("currentPage", page);
            System.out.println("product with this name not found ");
        }
        return "listeProduits";
    }
    @RequestMapping("/supprimerProduit")
    public String supprimerProduit(@RequestParam("id") Long id, ModelMap
            modelMap,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "2") int size) {
        produitService.deleteProduitById(id);
        Page<Produit> prods = produitService.getAllProduitsParPage(page,
                size);
        modelMap.addAttribute("produits", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
       return "listeProduits";
    }
    @RequestMapping("/supprimerCategorie")
    public String supprimerCategorie(@RequestParam("id") Long id, ModelMap
            modelMap,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "2") int size) {
        categorieService.deleteCategorieById(id);;
        Page<Categorie> categories = categorieService.getAllCategoriesParPage(page,
                size);

        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("pages", new int[categories.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeCategories";
    }


    @RequestMapping("/modifierProduit")
    public String editerProduit(@RequestParam("id") Long id,ModelMap modelMap)
    {
        Produit p= produitService.getProduit(id);
        modelMap.addAttribute("produit", p);
        List<Categorie> CategorieList = categorieService.getAllCategories();
        modelMap.addAttribute("CategorieList",CategorieList);
        return "editerProduit";
    }

    @RequestMapping("/modifierCategorie")
    public String editerCategorie(@RequestParam("id") Long id,ModelMap modelMap)
    {
        Categorie c = categorieService.getCategorie(id);
        modelMap.addAttribute("categorie", c);
        return "createCategorie";
    }
    @RequestMapping("/updateProduit")
    public String updateProduit(@ModelAttribute("produit") Produit produit,
                                @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateCreation,
                                ModelMap modelMap,
                                @RequestParam(name="categorieId") Long id ,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "2") int size) throws ParseException {
        //conversion de la date
        produit.setDateCreation(dateCreation);
        produitService.updateProduit(produit , produit.getIdProduit() , id);
        Page<Produit> prods = produitService.getAllProduitsParPage(page,
                size);
        modelMap.addAttribute("produits", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeProduits";
    }

    @RequestMapping("/updateCategorie")
    public String updateCategorie(@ModelAttribute("categorie") Categorie categorie,
                                ModelMap modelMap,
                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "2") int size,
                                  @RequestParam(name="idCat") Long id
    ) {
        categorieService.updateCategorie(categorie ,id);
        Page<Categorie> Categories = categorieService.getAllCategoriesParPage(page, size);
        modelMap.addAttribute("categories", Categories);
        modelMap.addAttribute("pages", new int[Categories.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "listeCategories";
    }

    }