package rows.messages;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.JMSConsumer ; 
import javax.jms.Queue ;

public class Producer {
	private javax.jms.JMSContext jmsContext;
	private javax.jms.JMSProducer jmsProducer;
	private javax.jms.Queue queue;
	
	public Producer (String url, String queueName) throws javax.jms.JMSException{
		com.sun.messaging.ConnectionFactory connectionFactory = new com.sun.messaging.ConnectionFactory() ; 
		jmsContext = connectionFactory.createContext() ; 
		connectionFactory.setProperty
		(com.sun.messaging.ConnectionConfiguration.imqAddressList, url) ;
		jmsProducer = jmsContext.createProducer() ; 
		queue = new com.sun.messaging.Queue(queueName) ; 	
	}
	
	public void sendQueueMessage ( String msg )
	{
		jmsProducer.send(queue, msg) ; 
	}

	protected void finalize() {
		if ( jmsContext != null )
			jmsContext.close();
	}
	 
	
}
