package task2.api;

import java.util.Hashtable;
import org.coos.messaging.Exchange;
import org.coos.messaging.ExchangePattern;
import org.coos.messaging.Message;
import org.coos.messaging.impl.DefaultExchange;
import org.coos.messaging.impl.DefaultMessage;

public class task2MessageFactory {
	public static String EXAMPLE_METHOD= "task2Example";
	
	public static Exchange createExampleMethodExchange(String exampleString) {
		Exchange ex=new DefaultExchange(new ExchangePattern(ExchangePattern.OutOnly));
		Message msg = new DefaultMessage(EXAMPLE_METHOD);
		Hashtable props = new Hashtable();
		props.put(task2MessageProperties.EXAMPLE_PROP_EXAMPLENAME, exampleString);
		msg.setBody(props);
		ex.setOutBoundMessage(msg);
		return ex;
	}

}
