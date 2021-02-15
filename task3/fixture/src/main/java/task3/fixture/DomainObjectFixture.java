package task3.fixture;

import org.nakedobjects.applib.fixtures.AbstractFixture;

import task3.dom.DomainObject;


public class DomainObjectFixture extends AbstractFixture {

    @Override
    public void install() {
        DomainObject object = newTransientInstance(DomainObject.class);
        object.setName("My Object");
        persist(object);
    }
}
