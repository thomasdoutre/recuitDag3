/* Une modification est une mutation élémentaire de la situation du problème
 * Un noeud de la route de l'utilisateur est remplacé par un autre, ce qui revient
 * à supprimer 2 arêtes consécutives de sa route et en rajouter 2 autres 
 */
public class Modification {

	public Utilisateur utilisateurModifie;
	// Noeud supprimé de la route
	public Noeud noeudSup;
	// Noeud ajouté à la route
	public Noeud noeudAj;
	
	public Modification(Noeud noeudSup,Noeud noeudAj,Utilisateur utilisateurModifie) {
		
		this.noeudSup = noeudSup;
		this.noeudAj = noeudAj;
		this.utilisateurModifie = utilisateurModifie;
		
	}

}
