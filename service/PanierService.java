package service;

import model.LignePanier;
import model.Panier;
import model.Produit;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe PanierService - gère les opérations métier du panier
 * Conforme au diagramme UML du projet ChriOnline
 *
 * Utilisé côté SERVEUR : reçoit les requêtes du client et manipule les paniers.
 */
public class PanierService {

    // Stockage en mémoire : userId -> Panier
    // (sera remplacé par une base de données via JDBC)
    private Map<Integer, Panier> paniers;

    // Constructeur
    public PanierService() {
        this.paniers = new HashMap<>();
    }

    // =====================
    //   Méthodes publiques (+)
    // =====================

    /**
     * Obtenir le panier d'un utilisateur.
     * Si le panier n'existe pas encore, en crée un nouveau.
     * @param userId  l'identifiant de l'utilisateur
     * @return le panier de l'utilisateur
     */
    public Panier obtenirPanier(int userId) {
        if (!paniers.containsKey(userId)) {
            paniers.put(userId, new Panier(userId));
        }
        return paniers.get(userId);
    }

    /**
     * Ajouter un produit dans le panier d'un utilisateur.
     * Vérifie la disponibilité du stock avant d'ajouter.
     * @param userId   l'identifiant de l'utilisateur
     * @param produit  le produit à ajouter
     * @param quantite la quantité souhaitée
     * @return message de résultat (SUCCESS ou ERROR)
     */
    public String ajouterProduitPanier(int userId, Produit produit, int quantite) {
        if (produit == null) {
            return "ERROR : Produit introuvable.";
        }
        if (!produit.estDisponible(quantite)) {
            return "ERROR : Stock insuffisant. Stock disponible : " + produit.getStock();
        }
        Panier panier = obtenirPanier(userId);
        panier.ajouterProduit(produit, quantite);
        return "SUCCESS : " + produit.getNom() + " x" + quantite + " ajouté au panier.";
    }

    /**
     * Retirer un produit du panier d'un utilisateur.
     * @param userId    l'identifiant de l'utilisateur
     * @param produitId l'identifiant du produit à retirer
     * @return message de résultat
     */
    public String retirerProduitPanier(int userId, int produitId) {
        Panier panier = obtenirPanier(userId);
        panier.supprimerProduit(produitId);
        return "SUCCESS : Produit retiré du panier.";
    }

    /**
     * Calculer le total du panier d'un utilisateur.
     * @param userId l'identifiant de l'utilisateur
     * @return le total calculé
     */
    public double calculerTotalPanier(int userId) {
        Panier panier = obtenirPanier(userId);
        return panier.calculerTotal();
    }

    /**
     * Vider le panier d'un utilisateur (après commande validée, par exemple).
     * @param userId l'identifiant de l'utilisateur
     */
    public void viderPanier(int userId) {
        Panier panier = obtenirPanier(userId);
        panier.viderPanier();
    }

    /**
     * Afficher le contenu du panier d'un utilisateur (pour debug/console).
     * @param userId l'identifiant de l'utilisateur
     * @return représentation textuelle du panier
     */
    public String afficherPanier(int userId) {
        return obtenirPanier(userId).toString();
    }
}
