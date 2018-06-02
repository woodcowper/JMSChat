package zad2;

import java.util.Hashtable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Chat {
	private String name; 
	
	private Context context; 
	private ConnectionFactory factory;
	private Destination destination; 
	private Connection con; 
	private Session ses;
	private MessageConsumer msgConsumer;
	private MessageProducer msgProducer;
	private TextMessage msg; 
	private ViewChat viewChat;
	
	public Chat(String dest) throws NamingException, JMSException {
		
		new ViewName(this);

		 Hashtable properties = new Hashtable();
		    properties.put(Context.INITIAL_CONTEXT_FACTORY, 
		                   "org.exolab.jms.jndi.InitialContextFactory");
		    properties.put(Context.PROVIDER_URL, "tcp://localhost:3035/");

		     context = new InitialContext(properties);
				factory = (ConnectionFactory) context.lookup("ConnectionFactory");
				destination = (Destination) context.lookup(dest);


		con = factory.createConnection();
		ses = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		msgProducer = ses.createProducer(destination);
		msgConsumer = ses.createConsumer(destination);
		
		con.start();
		boolean isRunning = true;
		
	       while(isRunning) {
	    	   
	    	   Message msg = msgConsumer.receive();
	           if (msg instanceof TextMessage) {
	        	   
	               TextMessage text = (TextMessage) msg;
	               viewChat.setText(text.getText());
	           }
	       }
		
	}
	
	
	public void sendMsg(String ms) throws JMSException {
		msg = ses.createTextMessage();
	  msg.setText(" "+name+": "+ms);
   	  msgProducer.send(msg);
	}
	
	public void openChat(String name) {
		this.name =name;
		viewChat =new ViewChat(this);
	}
	
	
	public static void main(String args[]) throws NamingException, JMSException {
		
		new Chat("topic1");
	}

}
