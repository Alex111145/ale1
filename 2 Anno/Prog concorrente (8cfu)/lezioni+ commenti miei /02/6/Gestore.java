public class Gestore {
	public static void main(String[] args)
			throws InterruptedException {
		InsiemePosti iPosti= new InsiemePosti();
		Richiedente client1 = new Richiedente("cliente 1", 3,
				iPosti);
		Richiedente client2 = new Richiedente("cliente 2", 5,
				iPosti);
		Richiedente client3 = new Richiedente("cliente 3", 3,
				iPosti);
		Richiedente client4 = new Richiedente("cliente 4", 10,
				iPosti);
		client1.start(); client2.start();
		client3.start(); client4.start();
		client1.join(); client2.join();
		client3.join(); client4.join();
		System.out.println("Al termine restano disponibili "+
				iPosti.getPostiDisponibili() +" posti");
	}
}
