package afrique.cm.dla.zambou.snack.bean;

public class VenteData {

	private String id;
	private String Prix_Vente;
	private String Stock_Sortie;
	private String Num_Facture;
	private String Sub_Total;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrix_Vente() {
		return Prix_Vente;
	}

	public void setPrix_Vente(String Prix_Vente) {
		this.Prix_Vente = Prix_Vente;
	}

	public String getStock_Sortie() {
		return Stock_Sortie;
	}

	public void setStock_Sortie(String Stock_Sortie) {
		this.Stock_Sortie = Stock_Sortie;
	}

	public String getNum_Facture() {
		return  Num_Facture;
	}

	public void setNum_Facture(String  num_facture) {
		this.Num_Facture =  num_facture;
	}

	public String getSub_Total() {
		return Sub_Total;
	}

	public void setSub_Total(String Sub_Total) {
		this.Sub_Total = Sub_Total;
	}
    
}
