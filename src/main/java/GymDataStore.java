import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GymDataStore
    implements DataStoreReadable, DataStoreWriteable {

    // TODO: Add your collection here to store Person objects...
    private HashMap<Integer,Person> collection = new HashMap<Integer,Person>();

    public GymDataStore() {

    }

    @Override
    public Boolean addPerson(Integer id, Person person) {
        boolean retval = false;
        // TODO: Implement using your collection...
        if(this.collection.put(id,person)==null)retval=true;
        return retval;
    }

    @Override
    public Boolean removePerson(Integer id) {
        boolean retval = false;
        // TODO: Implement using your collection...
        if(this.collection.remove(id)!=null)retval=true;
        return retval;
    }

    @Override
    public Person getPerson(Integer id) {
        // TODO: Implement using your collection...
        return this.collection.get(id);
    }

    @Override
    public ArrayList<Person> getAllPeople() {
        ArrayList<Person> retval = new ArrayList<Person>();
        // TODO: Implement using your collection...
        retval.addAll(collection.values());
        return retval;
    }
}
