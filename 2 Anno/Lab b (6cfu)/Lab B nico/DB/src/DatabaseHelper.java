import java.sql.*;
import java.io.*;
import java.util.*;
import java.math.*;

public class DatabaseHelper {

	static BufferedReader br;
	static Connection conn;
	static Statement statement;
	static String username;
	static String password;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("A, B, C, D, E?");

		String c = br.readLine();

		switch (c) {
		case "a":
			dbOnline();
			break;
		case "b":
			utentiIscritti();
			break;
		case "c":
			creaDb();
			break;
		case "d":
			cancellaDb();
			break;
		case "e":
			backup();
			break;
		}

	}

	// inutile ai fini del progetto finale
	public static void dbOnline() throws IOException {
		System.out
				.println("Inserire il nome utente per accedere al database 'postgres', username di default: postgres ");
		username = br.readLine();
		System.out.println("Inserire la password per accedere al database 'postgres', password di default: 1234");
		password = br.readLine();
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", username,
				password)) {
			if (conn != null) {
				System.out.println("Connesso al database");
			} else {
				System.out.println("Errore di connessione");
			}

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void utentiIscritti() throws IOException {
		System.out
				.println("Inserire il nome utente per accedere al database 'postgres', username di default: postgres ");
		username = br.readLine();
		System.out.println("Inserire la password per accedere al database 'postgres', password di default: 1234");
		password = br.readLine();
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", username,
				password)) {
			Statement statement = conn.createStatement();
			System.out.println("Informazioni sui giocatori connessi: ");
			System.out.println();
			ResultSet resultSet = statement
					.executeQuery("SELECT \"Nome\", \"Cognome\", \"Email\", \"NickName\" FROM public.\"GIOCATORE\"");
			System.out.printf("%-30s%-30s%-30s%-30s%n", "Nickname", "Nome", "Cognome", "Email");
			System.out.println();
			while (resultSet.next()) {
				System.out.printf("%-30s%-30s%-30s%-30s%n", resultSet.getString("Nickname"),
						resultSet.getString("Nome"), resultSet.getString("Cognome"), resultSet.getString("Email"));
			}
		} catch (SQLException e) {
			System.out.println("Errore di connessione");
			e.printStackTrace();
		}
	}

	public static void creaDb() throws IOException {
		System.out
				.println("Inserire il nome utente per accedere al database 'postgres', username di default: postgres ");
		username = br.readLine();
		System.out.println("Inserire la password per accedere al database 'postgres', password di default: 1234");
		password = br.readLine();
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", username, password);
			statement = conn.createStatement();
			String create_table_Giocatore = "CREATE TABLE giocatore " + "(Nome TEXT NOT NULL, "
					+ " Cognome TEXT NOT NULL, " + "Email TEXT NOT NULL UNIQUE, " + "Nickname TEXT NOT NULL UNIQUE, "
					+ "Password TEXT NOT NULL, " + "Id_Partita SMALLINT, " + "PRIMARY KEY (Email, Nickname))";
			statement.executeUpdate(create_table_Giocatore);
			System.out.println("Tabella Test creata");

			String create_table_Partita = "CREATE TABLE partita " + "(Nome_Partita TEXT NOT NULL UNIQUE, "
					+ "Id_Partita SMALLINT NOT NULL, " + "Parole_Giocate TEXT[] NOT NULL, " + "Vincitore TEXT UNIQUE, "
					+ "Data TIMESTAMP WITHOUT TIME ZONE NOT NULL, " + "Lettere_Estratte TEXT NOT NULL, "
					+ "PRIMARY KEY(Id_Partita))";
			statement.executeUpdate(create_table_Partita);
			System.out.println("Tabella Test2 creata");

			String create_table_StoricoPartite = "CREATE TABLE storico_partite " + "(Nome_Partita TEXT NOT NULL, "
					+ "Nickname TEXT NOT NULL, " + "Risultato BOOLEAN, " + "Punteggio_Partita NUMERIC NOT NULL, "
					+ "Id_Storico BIGINT NOT NULL, " + "Vincitore TEXT NOT NULL, " + "PRIMARY KEY(Id_Storico))";
			statement.executeUpdate(create_table_StoricoPartite);
			System.out.println("Tabella Test3 creata");

			String FKGiocatorePartita = "ALTER TABLE giocatore ADD FOREIGN KEY(Id_Partita) REFERENCES partita(Id_Partita) ON UPDATE CASCADE ON DELETE SET NULL";
			statement.executeUpdate(FKGiocatorePartita);
			System.out.println("Chiave esterna giocatore aggiunta");

			String FKStoricopartiteGiocatore = "ALTER TABLE storico_partite ADD FOREIGN KEY(Nickname) REFERENCES giocatore(Nickname) ON UPDATE CASCADE ON DELETE CASCADE";
			statement.executeUpdate(FKStoricopartiteGiocatore);
			System.out.println("Chiave esterne storico1 aggiunte");
			String FKStoricopartitePartita = "ALTER TABLE storico_partite ADD FOREIGN KEY(Nome_Partita) REFERENCES partita(Nome_Partita) ON UPDATE CASCADE ON DELETE NO ACTION";
			statement.executeUpdate(FKStoricopartitePartita);
			System.out.println("Chiave esterne storico2 aggiunte");
			String FKStoricopartitePartitaVincitore = "ALTER TABLE storico_partite ADD FOREIGN KEY(Vincitore) REFERENCES partita(Vincitore) ON UPDATE CASCADE ON DELETE NO ACTION";
			statement.executeUpdate(FKStoricopartitePartitaVincitore);
			System.out.println("Chiave esterne storico3 aggiunte");

		} catch (SQLException e) {
			System.out.println("Errore di connessione");
			e.printStackTrace();
		}

	}

	public static void cancellaDb() throws IOException {
		System.out
				.println("Inserire il nome utente per accedere al database 'postgres', username di default: postgres ");
		username = br.readLine();
		System.out.println("Inserire la password per accedere al database 'postgres', password di default: 1234");
		password = br.readLine();
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", username, password);
			statement = conn.createStatement();
			String sql = "DROP TABLE giocatore CASCADE ";
			statement.executeUpdate(sql);
			System.out.println("Tabella cancellata");
			String sql2 = "DROP TABLE partita CASCADE ";
			statement.executeUpdate(sql2);
			System.out.println("Tabella cancellata");
			String sql3 = "DROP TABLE storico_partite CASCADE ";
			statement.executeUpdate(sql3);
			System.out.println("Tabella cancellata");

		} catch (SQLException e) {
			System.out.println("Errore di connessione");
			e.printStackTrace();
		}
	}

	public static void backup() throws IOException {
		System.out
				.println("Inserire il nome utente per accedere al database 'postgres', username di default: postgres ");
		username = br.readLine();
		System.out.println("Inserire la password per accedere al database 'postgres', password di default: 1234");
		password = br.readLine();
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", username, password);
			statement = conn.createStatement();
			String executeBackup = ("BACKUP DATABASE postgres TO DISK = 'C:\\Users\\ferra\\Desktop\\Backup db'");
			statement.executeQuery(executeBackup);
		} catch (SQLException e) {
			System.out.println("Errore di connessione");
			e.printStackTrace();
		}
	}

}