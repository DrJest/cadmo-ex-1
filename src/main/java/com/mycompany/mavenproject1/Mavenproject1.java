/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;
import com.google.gson.Gson;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author SimoneAlbano
 */
public class Mavenproject1 {
    
    private static EntityManager em;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonaPU");
        
        em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Inserisci il nome:");
        String nome = input.next();
        
        System.out.println("Inserisci il cognome:");
        String cognome = input.next();
        
        System.out.println("Inserisci l'anno di nascita:");
        int anno = input.nextInt();
        
        System.out.println("Inserisci hobby:");
        String hobby = input.next();
        
        Persona mario = new Persona(nome, cognome, anno);
        
        // mario è TRANSIENT
        
        em.persist(mario);
        
        mario.setHobby(hobby); 
                // si riflette su DB
        
        // mario MANAGED
        
        em.flush();
        
        em.getTransaction().commit();
        
        // mario DETACHED
        
        em.getTransaction().begin();
        
        em.remove(mario);
        
        // mario è removed ma non detached
        
        em.getTransaction().commit();
        
        // mario is no more
        
        int id = mario.getId();
        System.out.println(id);
        
        Gson gson = new Gson();
        String json = gson.toJson(mario);
        System.out.println(json);
    }
}