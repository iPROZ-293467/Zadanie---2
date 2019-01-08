package rows.messages;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.JMSConsumer ; 
import javax.jms.Queue ;
import javax.jms.TextMessage;
import javax.jms.Message ; 
import javax.jms.QueueBrowser ; 
import java.util.Enumeration ;

public class Consumer {
	private JMSContext jmsContext ; 
	private JMSConsumer jmsConsumer; 
	private Queue queue ; 
	
	public Consumer ( String url, String queueName ) throws JMSException {
		com.sun.messaging.ConnectionFactory connectionFactory = 
				new com.sun.messaging.ConnectionFactory() ; 
		jmsContext = connectionFactory.createContext() ; 
		connectionFactory.setProperty
		(com.sun.messaging.ConnectionConfiguration.imqAddressList, url) ; 
		queue = new com.sun.messaging.Queue(queueName) ; 
		jmsConsumer = jmsContext.createConsumer(queue);  
		
	}
	public String receiveQueueMessage () throws JMSException {
		Message msg = jmsConsumer.receiveNoWait();
		String result ;
		if (msg instanceof TextMessage) {
			result = ((TextMessage) msg).getText() ; 
			return result ; 
		}
		return null ; 
	}
	public void browseQueue() throws JMSException {
		QueueBrowser browser = jmsContext.createBrowser(queue) ; 
		@SuppressWarnings ("rawtypes")
		Enumeration messageEnumeration = browser.getEnumeration() ; 
		while (messageEnumeration.hasMoreElements()) {
			TextMessage msg = (TextMessage) messageEnumeration.nextElement() ; 
			System.out.println(msg.getText()) ; 
		}
	}
	protected void finalize()
	{
		if (jmsConsumer != null) jmsConsumer.close(); 
		if ( jmsContext != null ) jmsContext.close();
	}
	public void setMessageListener(AsynchConsumer a)
	{
		jmsConsumer.setMessageListener(a);
	}
	public void receiveAsynch()
	{
		
	}

}