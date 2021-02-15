package task2.impl;



import org.coos.messaging.util.Log;
import org.coos.messaging.util.LogFactory;

import org.coos.actorframe.ActorCS;
import org.coos.javaframe.ActorAddress;
import org.coos.javaframe.State;
import org.coos.javaframe.StateMachine;
import org.coos.javaframe.messages.AFPropertyMsg;
import org.coos.javaframe.messages.ActorMsg;
import task2.api.*;

public class task2CS extends ActorCS {

    private static final Log logger = LogFactory.getLog(task2CS.class);
    private ActorAddress ac;

    public task2CS(String s) {
        super(s);
    }

    public void execTrans(ActorMsg sig, State st, StateMachine curfsm) {

        super.execTrans(sig, st, curfsm);
        task2SM sm = (task2SM) curfsm;

        if (st == idle) {
            if (sig.equals(task2MessageFactory.EXAMPLE_METHOD)) {
                logger.info("task2SM: received message");
                sameState();
                return;
            }
        } 
    }
}

