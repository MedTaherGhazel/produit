package com.iset.produit.DAO;

import com.iset.produit.entities.Categorie;
import com.iset.produit.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
 Categorie findFirstByIdCat(long id);
}
