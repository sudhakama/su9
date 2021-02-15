package task2.impl;

import org.coos.actorframe.ActorSM;

public class task2SM extends ActorSM {

    public task2SM() {
        setBehaviorClass(new task2CS("task2"));
    }

    protected void initInstance() {
    	super.initInstance();
    	setVisible(true);
    }
}

