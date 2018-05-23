/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zambou.test.a;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author AZAMBOU
 */
public class BDD {
	Connection connection;
	Statement statement;
	String SQL;

	String url;
	String username;
	String password;
	// Socket client;
	int Port;
	String Host;

	public BDD(String url, String username, String password, String Host, int Port) {

		this.url = url;
		this.username = username;
		this.password = password;
		this.Host = Host;

		this.Port = Port;
	}

	// fonction pour se connecter a la base de données

	public Connection connexionDatabase() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.err.println(e.getMessage());// afficher l´erreur de connexion
		}
		return connection;
	}

	// fonction pour se déconnecter de la base de données
	public Connection closeconnexion() {

		try {
			connection.close();
		} catch (Exception e) {
			System.err.println(e);//
		}
		return connection;
	}

	// fonction pour éxécuter une requete
	public ResultSet executionQuery(String sql) {
		connexionDatabase();
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			System.out.println(sql);
		} catch (SQLException ex) {
			System.err.println(ex);//
		}
		return resultSet;
	}

	// fonction pour éxécuter une requete d´actualisation (insertion)
	public String executionUpdate(String sql) {
		connexionDatabase();
		String result = "";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			result = sql;

		} catch (SQLException ex) {
			result = ex.toString();
		}
		return result;

	}
	// fonction pour afficher tout les éléments d´une table

	public ResultSet querySelectAll(String nomTable) {

		connexionDatabase();
		SQL = "SELECT * FROM " + nomTable;
		System.out.println(SQL);
		return this.executionQuery(SQL);

	}

	// fonction pour afficher tout les éléments d´une table avec restriction
	public ResultSet querySelectAll(String nomTable, String Etat) {

		connexionDatabase();
		SQL = "SELECT * FROM " + nomTable + " WHERE " + Etat;
		return this.executionQuery(SQL);

	}

	/*
	 * fonction pour sélectionner une ou plusieurs colonnes d´une table par exemple
	 * select nom,prenom from table
	 */

	public ResultSet querySelect(String[] nomColonne, String nomTable) {

		connexionDatabase();
		int i;
		SQL = "SELECT ";

		for (i = 0; i <= nomColonne.length - 1; i++) {
			SQL += nomColonne[i];
			if (i < nomColonne.length - 1) {
				SQL += ",";
			}
		}

		SQL += " FROM " + nomTable;
		return this.executionQuery(SQL);

	}

	/*
	 * fonction pour sélectionner une ou plusieurs colonnes d´une table avc
	 * restriction par exemple select nom,prenom from table where nom=arthur
	 */

	public ResultSet fcSelectCommand(String[] nomColonne, String nomTable, String Etat) {

		connexionDatabase();
		int i;
		SQL = "SELECT ";

		for (i = 0; i <= nomColonne.length - 1; i++) {
			SQL += nomColonne[i];
			if (i < nomColonne.length - 1) {
				SQL += ",";
			}
		}

		SQL += " FROM " + nomTable + " WHERE " + Etat;
		return this.executionQuery(SQL);

	}

	/*
	 * fonction pour insertion des éléments dans une table de la base de données par
	 * exemple insert To table values ("'arthur','flerrentwiete'")
	 */

	public String queryInsert(String nomTable, String[] contenuTableau) {

		connexionDatabase();
		int i;
		SQL = "INSERT INTO " + nomTable + " VALUES(";

		for (i = 0; i <= contenuTableau.length - 1; i++) {
			SQL += "'" + contenuTableau[i] + "'";
			if (i < contenuTableau.length - 1) {
				SQL += ",";
			}
		}

		SQL += ")";
		return this.executionUpdate(SQL);

	}

	/*
	 * fonction pour insertion des éléments dans la base de données en précisant les
	 * colonnes par exemple insert To table values ("'arthur','flerrentwiete'")
	 */

	public String queryInsert(String nomTable, String[] nomColonne, String[] contenuTableau) {

		connexionDatabase();
		int i;
		SQL = "INSERT INTO " + nomTable + "(";
		for (i = 0; i <= nomColonne.length - 1; i++) {
			SQL += nomColonne[i];
			if (i < nomColonne.length - 1) {
				SQL += ",";
			}
		}
		SQL += ") VALUES(";
		for (i = 0; i <= contenuTableau.length - 1; i++) {
			SQL += "'" + contenuTableau[i] + "'";
			if (i < contenuTableau.length - 1) {
				SQL += ",";
			}
		}

		SQL += ")";
		return this.executionUpdate(SQL);

	}

	/*
	 * fonction pour modification du contenu d´une table de base de données en
	 * précisant les colonnes par exemple insert To table values
	 * ("'arthur','flerrentwiete'")
	 */

	public String queryUpdate(String nomTable, String[] nomColonne, String[] contenuTableau, String Etat) {

		connexionDatabase();
		int i;
		SQL = "UPDATE " + nomTable + " SET ";

		for (i = 0; i <= nomColonne.length - 1; i++) {
			SQL += nomColonne[i] + "='" + contenuTableau[i] + "'";
			if (i < nomColonne.length - 1) {
				SQL += ",";
			}
		}

		SQL += " WHERE " + Etat;
		return this.executionUpdate(SQL);

	}

	// fonction pour supprimer les éléments d´une table sans restriction

	public String queryDelete(String nomtable) {

		connexionDatabase();
		SQL = "DELETE FROM " + nomtable;
		return this.executionUpdate(SQL);

	}

	// fonction pour supprimer les élements d´une table avec restriction

	public String queryDelete(String nomTable, String Etat) {

		connexionDatabase();
		SQL = "DELETE FROM " + nomTable + " WHERE " + Etat;
		return this.executionUpdate(SQL);

	}

}

