package main;
import model.Produit;
import service.PanierService;

/**
 * Classe de test pour démontrer le fonctionnement du panier
 * Projet ChriOnline - Gestion du panier
 */
public class MainTest {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("   ChriOnline - Test Gestion du Panier  ");
        System.out.println("========================================\n");

        // Création du service
        PanierService panierService = new PanierService();

        // Simulation de quelques produits (normalement venant de ProduitService + DB)
        Produit laptop    = new Produit(1, "Laptop",      "PC portable 15 pouces", 8000.00, 5);
        Produit phone     = new Produit(2, "Phone",       "Smartphone Android",    3000.00, 10);
        Produit casque    = new Produit(3, "Headphones",  "Casque Bluetooth",       500.00, 20);

        int userId = 42; // Utilisateur simulé

        // --- Test 1 : Ajouter des produits ---
        System.out.println(">> Test 1 : Ajout de produits");
        System.out.println(panierService.ajouterProduitPanier(userId, laptop, 1));
        System.out.println(panierService.ajouterProduitPanier(userId, phone, 2));
        System.out.println(panierService.ajouterProduitPanier(userId, casque, 3));
        System.out.println();

        // --- Afficher le panier ---
        System.out.println(">> Contenu du panier :");
        System.out.println(panierService.afficherPanier(userId));

        // --- Test 2 : Stock insuffisant ---
        System.out.println(">> Test 2 : Stock insuffisant");
        Produit produitRare = new Produit(4, "TV 4K", "Smart TV", 5000.00, 1);
        System.out.println(panierService.ajouterProduitPanier(userId, produitRare, 10)); // stock = 1
        System.out.println();

        // --- Test 3 : Calculer le total ---
        System.out.println(">> Test 3 : Calcul du total");
        double total = panierService.calculerTotalPanier(userId);
        System.out.printf("   Total panier : %.2f DH%n%n", total);

        // --- Test 4 : Supprimer un produit ---
        System.out.println(">> Test 4 : Suppression du Phone (id=2)");
        System.out.println(panierService.retirerProduitPanier(userId, 2));
        System.out.println(panierService.afficherPanier(userId));

        // --- Test 5 : Vider le panier ---
        System.out.println(">> Test 5 : Vider le panier");
        panierService.viderPanier(userId);
        System.out.println(panierService.afficherPanier(userId));

        System.out.println("========================================");
        System.out.println("   Tests terminés avec succès !         ");
        System.out.println("========================================");
    }
}
