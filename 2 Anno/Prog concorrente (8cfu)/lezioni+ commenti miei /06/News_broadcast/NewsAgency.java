class NewsAgency extends Thread {
	Broadcast<String> broadcast;
	NewsAgency(Broadcast<String> b) {
		this.broadcast=b;
		this.start();
	}
	public void run(){	
		String msg;
		for(int i=0; i<10; i++){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {	}
			msg=i%2==0?"Aumentano le immatricolazioni":"Diminuiscono le immatricolazioni";
			msg="Msg_"+i+"_"+msg;
			System.out.println("News from agency: "+ msg);
			broadcast.send(msg);
		}

	}
}
