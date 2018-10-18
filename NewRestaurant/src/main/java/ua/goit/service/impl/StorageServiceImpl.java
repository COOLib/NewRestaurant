package ua.goit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Ingredient;
import ua.goit.domain.Storage;
import ua.goit.DAO.StorageDao;
import ua.goit.repository.StorageRepository;
import ua.goit.service.IngredientService;
import ua.goit.service.StorageService;
import ua.goit.service.impl.IngredientServiceImpl;

import java.util.List;

/**
 * Created by user on 24.11.2016.
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageDao storageDao;

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private IngredientService ingredientService;

    @Transactional
    public Ingredient addIngredientToStorage(String name, int quantity) {

        Ingredient exist = ingredientService.getByName(name);

        if (exist == null) {
            ingredientService.addIngredient(name);
            Ingredient ingredient = ingredientService.getByName(name);
            Storage storage = new Storage();
            storage.setIngredient(ingredient);
            storage.setQuantity(quantity);
            storageRepository.save(storage);
        }

        return exist;
    }

    @Transactional
    public void removeIngredientFromStorage(String name) {

        storageRepository.deleteByIngredient_Name(name);
    }

    @Transactional
    public void updateQuantity(String ingredientName, int quantity) {

        Storage storage = storageRepository.findByIngredient_Name(ingredientName);
        int realQuantity = storage.getQuantity();

        if (realQuantity + quantity >= 0) {
            storage.setQuantity(realQuantity + quantity);
        } else {

            throw new RuntimeException("Cannot update quantity at storage, because it will be less than zero.");
        }
    }

    @Transactional
    public List<Storage> getAllIngredients() {

        return storageRepository.findAll();
    }

    @Transactional
    public List<Storage> getEndingIngredients() {

        return storageRepository.findAllByQuantityIsLessThan(10);
    }

    @Transactional
    public Storage getByIngredientName(String name) {

        return storageRepository.findByIngredient_Name(name);
    }
}
