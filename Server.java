import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;

public class Server{

	int count = 1;	
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	TheServer server;
	int port_number;
	private Consumer<Serializable> callback;


	public void closeServer(){
		for(int i=0; i < clients.size(); i++){
			clients.get(i).closeClient();
		}
		try {
			server.mysocket.close();
		} catch (IOException e) {
			System.out.println("Error closing server socket");
			e.printStackTrace();
		}
	}

	Server(Consumer<Serializable> call, int port){
		callback = call;
		server = new TheServer();
		this.port_number = port;
		server.start();
	}

	public class TheServer extends Thread{
		ServerSocket mysocket;
		public void run() {
			try{
				mysocket = new ServerSocket(port_number);
				System.out.println("Port Number: " + port_number);
		    	System.out.println("Server is waiting for a client!");
				callback.accept("Server is on");

		    	while(true) {
					ClientThread c = new ClientThread(mysocket.accept(), count);
					callback.accept("client has connected to server: " + "client #" + count);
					clients.add(c);
					c.start();
					count++;
			    }
			}//end of try
			catch(Exception e) {
				callback.accept("Server socket did not launch");
			}
		}//end of while
	}

	class ClientThread extends Thread{
			Socket connection;
			int count;
			ObjectInputStream in;
			ObjectOutputStream out;
			Deck gameDeck;
			Random random;

			ClientThread(Socket s, int count){
				this.gameDeck = new Deck();
				this.connection = s;
				this.count = count;
				random = new Random();
			}

			public void closeClient(){
				try {
					this.connection.close();
				} catch (IOException e) {
					System.out.println("Error closing client");
					e.printStackTrace();
				}
			}
			
			public void updateClients(PokerInfo message) {
				for(int i = 0; i < clients.size(); i++) {
					ClientThread t = clients.get(i);
					try {
						t.out.reset();
					 	t.out.writeObject(message);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			public void run(){
					
				try {
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
					connection.setTcpNoDelay(true);	
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}

				PokerInfo senderClassMessage = new PokerInfo();
				senderClassMessage.setMessage("new client on server: client #"+count);
				senderClassMessage.setCounter(-1);
				updateClients(senderClassMessage);

				 while(true) {
					    try {
							PokerInfo data = (PokerInfo) in.readObject();

							System.out.println("System received message, client is on step: " + data.getCounter());
							//send cards
							if(data.getCounter() == 0){
								System.out.println("Server is going to deal cards and send them");
								out.reset();
								gameDeck.shuffle();

								for(int i =0; i < 3; i++){
									data.clientCards.add(gameDeck.drawCard());
									data.dealerCards.add(gameDeck.drawCard());
								}

								data.setClientHand(pokerGame.checkHand(data.clientCards));
								data.setDealerHand(pokerGame.checkHand(data.dealerCards));

								System.out.println(data.clientCards.size() + " " + data.dealerCards.size());
								data.setCounter(1);
								out.writeObject(data);
							}
							else if(data.getCounter() == 1){// send hand
								System.out.println("Server got the clients response after getting their cards");
								data.setCounter(2);
								out.writeObject(data);
								System.out.println("Server Server send the info	");
							}
							else if(data.getCounter() == 2){
								System.out.println("Sending final winnings information");
								data.win_or_loose_message = pokerGame.compareHands(data.dealerCards,data.clientCards,data.dealerHand, data.clientHand);
								data.winning = pokerGame.computeWinnings(data.win_or_loose_message, data.getAnte_wager(), data.getPair_wager(), data.getPlay_wager());
								data.setTotal_winnings(data.winning);
								data.setCounter(3);
								out.writeObject(data);
							}
							else if(data.getCounter() == -2){
								System.out.println("Client send information to restart");
								data.reset();
								System.out.println(data.getCounter());
								out.writeObject(data);
							}


							senderClassMessage = data;
					    	callback.accept("Client " + count + ": " + data.getMessage());
							senderClassMessage.setMessage("Client " + count + ": " + data.getMessage());
							senderClassMessage.setCounter(-1);
							updateClients(senderClassMessage);
//					    	updateClients(data);

						}
					    catch(Exception e) {
							e.printStackTrace();
					    	callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");


							senderClassMessage.setMessage("Client #"+count+" has left the server!");
							senderClassMessage.setCounter(-1);
							updateClients(senderClassMessage);
							this.count--;
					    	clients.remove(this);
					    	break;
					    }
				 }
			}//end of run
			
			
	}
	//end of client thread
}


	
	

	
