package afrique.cm.dla.zambou.snack.bean;

public class ProduitData {

	private String id;
	private String code_produit;
	private String reference;
	private String designation;
	private String rangement;
	private String fournisseur;
	private String remise;
	private String prix;
	private String stock;
	
	public String  getId() {
		return id;
	}
	public void setId(String id) {
		this.id=id;
	}
	
	public String  getCode_produit() {
		return code_produit;
	}
	public void setCode_produit(String code_produit) {
		this.code_produit=code_produit;
	}
	
	public String  getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference=reference;
	}
	
	public String  getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation=designation;
	}
	
	public String  getRangement() {
		return rangement;
	}
	public void setRangement(String rangement) {
		this.rangement=rangement;
	}
	
	public String  getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(String fournisseur) {
		this.fournisseur=fournisseur;
	}
	
	public String  getRemise() {
		return remise;
	}
	public void setRemise(String remise) {
		this.remise=remise;
	}
	
	public String  getPrix() {
		return prix;
	}
	public void setPrix(String prix) {
		this.prix=prix;
	}
	
	public String  getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock=stock;
	}
}
