FOR messageservice:
example grocery services:

package Lab.Service;

import Lab.Model.Grocery;
import Lab.Repository.GroceryRepository;

import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryService {
    GroceryRepository groceryRepository;
    @Autowired
    public GroceryService(GroceryRepository groceryRepository){
        this.groceryRepository = groceryRepository;
    }

    /**
     * Given a brand new transient grocery (meaning, no such grocery exists yet in the database),
     * persist the grocery to the database (create a new database record for the grocery entity.)
     */
    public Grocery persistGrocery(Grocery grocery){
        return groceryRepository.save(grocery);
    }
    /**
     * finaAll() will return all grocery entities stored in the DB table.
     * @return all Grocery entities.
     */
    public List<Grocery> getAllGroceries(){
        return groceryRepository.findAll();
    }

    /**
     * Because fingById() returns a type of Optional<Grocery>, we also need to apply .get() to convert the
     * Optional<Grocery> to Grocery. Optionals help the developer to avoid null pointer exceptions.
     * @param id the id of the Grocery entity.
     * @return Grocery entity
     */
    public Grocery getGroceryById(long id){
        //findById returns a type Optional<Grocery>. This helps the developer avoid null pointer
        //exceptions. We can use the method .get() to convert an Optional<Grocery> to Grocery.
        Optional<Grocery> optionalGrocery = groceryRepository.findById(id);
        if(optionalGrocery.isPresent()){
            return optionalGrocery.get();
        }else{
            return null;
        }
    }
    /**
     * Delete a grocery entity of a certain id.
     * @param id
     * @return
     */
    public void deleteGrocery(long id){
        groceryRepository.deleteById(id);
    }
    /**
     * Given a groceru ID, overwrite the contents of the grocery entity.
     * @param id
     * @return
     */
    public void updateGrocery(long id, Grocery replacement){
        //findById returns a type Optional<Grocery>. This helps the developer avoid null pointer
        //exceptions. We can use the method .get() to convert an Optional<Grocery> to Grocery.
        Optional<Grocery> optionalGrocery = groceryRepository.findById(id);
        if(optionalGrocery.isPresent()){
            Grocery grocery = optionalGrocery.get();
            grocery.setName(replacement.getName());
            groceryRepository.save(grocery);
        }

    }
}
package Lab.Service;

import Lab.Model.Store;
import Lab.Repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class StoreService {
    StoreRepository storeRepository;
    @Autowired
    public StoreService(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }
    /**
     * TODO: given a transient store, persist the store and return it.
     * @param store a transient store
     * @return the persisted store
     */
    public Store persistStore(Store store) {
        return storeRepository.save(store);
    }    
    /**
     * TODO: get all store entities
     * @return all store entities
     */
    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }
    /**
     * TODO: given an id of a store, return the store.
     *
     * @param id id of store entity
     * @return a store entity
     */
    public Store getStoreById(long id) {
        Optional<Store> storeOptional = storeRepository.findById(id);
        return storeOptional.orElse(null);
    }    
    /**
     * TODO: given an id of an existing store, delete the store
     */
    public void deleteStore(long id) {
        storeRepository.deleteById(id);
    }    
    /**
     * TODO: given an id and some replacement data for a store, overwrite the data of an existing store,
     * and return the updated store.
     * @return the updated store entity
     */
    public Store updateStore(long id, Store replacement) {    
        Optional<Store> optionalStore = storeRepository.findById(id);
        if(optionalStore.isPresent()){
            Store store = optionalStore.get();
            store.setName(replacement.getName());
            storeRepository.save(store);
        }
        return replacement;
    }
}