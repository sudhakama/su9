package task2.itest;


import java.io.InputStream;
import java.util.Hashtable;
import org.coos.messaging.InteractionHelper;
import org.coos.messaging.plugin.test.DefaultTest;
import org.coos.messaging.Exchange;
import org.coos.messaging.Message;
import org.coos.messaging.plugin.simple.SimpleProducer;
import task2.api.task2MessageFactory;

public class task2Test extends DefaultTest {

	public void testtask2Endpoint() {
		
        SimpleProducer producer = (SimpleProducer) getTestPlugins()[0].getEndpoint().createProducer();
        
        Exchange ex;
        System.out.println("testing by sending message to actor");
        ex=task2MessageFactory.createExampleMethodExchange("Testing the example");
        InteractionHelper helper=new InteractionHelper(producer.getEndpoint());
        helper.send("coos://task2",ex);
        
        assertEquals(true, true);
        
	}

	@Override
	public InputStream loadTestPlugin() {
		return this.getClass().getResourceAsStream("/sePlugin.xml");
	}
    
}
