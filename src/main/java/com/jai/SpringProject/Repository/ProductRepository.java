package com.jai.SpringProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jai.SpringProject.Entite.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
