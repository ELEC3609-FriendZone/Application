package friendzone.elec3609.service;


import java.util.List;

import friendzone.elec3609.model.Person;

public interface PersonService {
    
    public void addPerson(Person person);
    public List<Person> listPeople();
    public void removePerson(Integer id);
}
