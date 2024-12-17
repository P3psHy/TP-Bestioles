package com.example.demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Categorie;
import com.example.demo.model.Espece;
import com.example.demo.model.Insecte;
import com.example.demo.repository.CategorieRepository;
import com.example.demo.repository.EspeceRepository;
import com.example.demo.repository.InsecteRepository;
import com.example.demo.service.EspeceService;
import com.example.demo.service.InsecteService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	private CategorieRepository categorieRepository;
	private EspeceRepository especeRepository;
	private InsecteRepository insecteRepository;

	private InsecteService insecteService;
	private EspeceService especeService;


	@Autowired
	public DemoApplication(CategorieRepository categorieRepository, EspeceRepository especeRepository, InsecteRepository insecteRepository, InsecteService insecteService, EspeceService especeService){
		this.categorieRepository = categorieRepository;
		this.especeRepository = especeRepository;
		this.insecteRepository = insecteRepository;

		this.insecteService = insecteService;
		this.especeService = especeService;
	}

	public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application démarrée, base de données créée !");

		Categorie categorie1 = new Categorie();
		categorie1.setNom("Lépidoptères");
		Categorie categorie2 = new Categorie();
		categorie2.setNom("Diurnes");
		Categorie categorie3 = new Categorie();
		categorie3.setNom("Volants");
		Categorie categorie4 = new Categorie();
		categorie4.setNom("Coccinelles");

		categorieRepository.saveAll(Arrays.asList(categorie1, categorie2, categorie3, categorie4));



		Espece espece1 = new Espece();
		espece1.setNom("Danaus plexippus");
		Espece espece2 = new Espece();
		espece2.setNom("Musca domestica");
		Espece espece3 = new Espece();
		espece3.setNom("Coccinella septempunctata");
		Espece espece4 = new Espece();
		espece4.setNom("Datanolage decidia");
		
		especeRepository.saveAll(Arrays.asList(espece1, espece2, espece3, espece4));



		Insecte insecte1 = new Insecte();
		insecte1.setNom("Papillon monarque");
		insecte1.setEspece(espece1);
		insecte1.setPoids(50);
		insecte1.setCategories(new HashSet<>(Arrays.asList(categorie1, categorie2)));
		Insecte insecte2 = new Insecte();
		insecte2.setNom("Mouche domestique");
		insecte2.setEspece(espece2);
		insecte2.setPoids(20);
		insecte2.setCategories(new HashSet<>(Arrays.asList(categorie3)));
		Insecte insecte3 = new Insecte();
		insecte3.setNom("Coccinelle à sept points");
		insecte3.setEspece(espece3);
		insecte3.setPoids(40);
		insecte3.setCategories(new HashSet<>(Arrays.asList(categorie4)));
		Insecte insecte4 = new Insecte();
		insecte4.setNom("Papillon déchu");
		insecte4.setEspece(espece1);
		insecte4.setPoids(50);
		insecte4.setCategories(new HashSet<>(Arrays.asList(categorie1, categorie2)));

		insecteRepository.saveAll(Arrays.asList(insecte1, insecte2, insecte3, insecte4));
		


		Insecte getInsecte = insecteService.findInsecteFromNom("Papillon monarque");
		if(getInsecte != null){
			System.out.println("Insecte: "+getInsecte.getNom()+", Espèce: "+getInsecte.getEspece().getNom());
		}else{
			System.out.println("Rien trouvé");
		}

		System.out.println("Liste de nom d'espèces: ");
		List<Espece> resultat = especeService.getItemByNameOrderByName("d");
        resultat.forEach(entite -> System.out.println(entite.getNom()));


		System.out.println("Liste de nom d'insecte avec poids entre 20 et 30");
		List<Insecte> result = insecteService.getItemBetweenPoidsMinAndMax(40,50);
        result.forEach(entite -> System.out.println(entite.getNom()));



		
    }

}
