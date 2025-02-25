import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailHelper {

	static String accountSystem;
	static String passwordSystem;
	static String destinatario;
	static String mail;
	static String oggetto;
	static String testo;
	//static Timer timer;
	//static TimerTask task;

	public static void main(String[] args) throws IOException {
		RegistrationMail();
		//getPassword();
	}

	private static boolean checkMail(String mail) throws IOException {

		mail.toLowerCase();

		if (mail.matches("[a-z0-9\\._%+!$&*=^|~#%'`?{}/\\-]+@([a-z0-9\\-]+\\.){1,}([a-z]{2,16})")) {
			System.out.println("Mail valida");
			return false;
		} else {
			System.out.println("Mail non valida");
			return true;
		}
	}

	private static boolean sendEmail(String destinatario, String oggetto, String testo) {

		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.office365.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", 587);

		Session session = Session.getInstance(properties);
		Message message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(accountSystem));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			message.setSubject(oggetto);
			message.setText(testo);
		} catch (MessagingException e) {
			return false;
		}

		try {
			Transport.send(message, accountSystem, passwordSystem);
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static void RegistrationMail() throws IOException {
		// TODO
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String OTP = getOTP();

		do {
			System.out.println("Inserisci Mail Admin: ");
			accountSystem = br.readLine();

		} while (checkMail(accountSystem));

		System.out.println("Inserisci Password Admin: ");
		passwordSystem = br.readLine();

		System.out.println("Inserisci la mail per iscriverti: ");
		String destinatario = br.readLine();

		String oggetto = "Conferma dell'avvenuta registrazione ";

		String testo = "Inserisci il codice seguente per confermare l'iscrizione: \n" + OTP;

		sendEmail(destinatario, oggetto, testo);
		System.out.println("Mail inviata correttamente! ");

		System.out.println("Inserisci il codice OTP ricevuto all'indirizzo " + destinatario + " :");
		if (OTP.equals(br.readLine())) {
			System.out.println("Account verificato!");
		} else {
			System.out.println("Codice sbagliato!");
		}
		
		
		/*
		 * Aggiungere funzione timer da 10 minuti, se OTP non viene inserito, chiamata
		 * al DB per eliminare account
		 */
	}
	

	private static String getOTP() {
		Random r = new Random();
		String s;
		String val1, val2, val3, val4, val5;

		val1 = String.valueOf(r.nextInt(9)).toString();
		val2 = String.valueOf(r.nextInt(9)).toString();
		val3 = String.valueOf(r.nextInt(9)).toString();
		val4 = String.valueOf(r.nextInt(9)).toString();
		val5 = String.valueOf(r.nextInt(9)).toString();

		s = val1 + val2 + val3 + val4 + val5;

		return (s);
	}

	private static String getPassword() throws IOException {
		// TODO
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Random r = new Random();
		String pw;

		do {
			System.out.println("Inserisci la mail con la quale ti sei registrato: ");
			mail = br.readLine();

		} while (checkMail(mail));

		// CHECK MAIL ON DB

		char[] txt = new char[r.nextInt(10) + 4];
		for (int i = 0; i < txt.length; i++) {
			txt[i] = (char) ('a' + r.nextInt(26));
		}
		pw = new String(txt);
		return(pw);

		/*
		 * 1) Inserire la mail con la quale ci si è registrati 2) Check sul DB per
		 * verificare l'esistenza della mail, se inesistente chiedere nuova mail 3)
		 * Generazione di una pw min. 4 char inviata tramite mail con chiamata al DB per
		 * impostarla come pw attuale
		 */
	}
}
