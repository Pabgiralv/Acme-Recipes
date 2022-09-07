package acme.features.chef.pimpam;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.pimpam.Pimpam;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;


@Repository
public interface ChefPimpamRepository extends AbstractRepository {
	
	@Query("select p from Pimpam p where p.id = :id")
	Pimpam findPimpamById(int id);

	@Query("select i from Item i where i.id = :id and i.itemType=1")
	Item findIngredientById(int id);
	
	@Query("select p from Pimpam p where p.item.chef.id = :chefId")
	Collection<Pimpam> findPimpamByChef(Integer chefId);
	
	@Query("select distinct i from Item i where i.chef.userAccount.id = :chefId and i.itemType=1")
	Collection<Item> findIngredientByChef(Integer chefId);
	
	@Query("SELECT c FROM Chef c WHERE c.id = :id")
	Chef findChefById(int id);
	
	@Query("SELECT i FROM Item i WHERE i.code = :code")
	Item findItemByCode(String code);

	@Query("SELECT sc.acceptedCurrencies from SystemConfiguration sc")
	String findAcceptedCurrencies();
	
	@Query("SELECT c FROM SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();

	@Query("select c.defaultCurrency  from SystemConfiguration c")
	String getDefaultCurrency();	
	
	@Query("SELECT c FROM Pimpam c WHERE c.acode =:code")
    Pimpam findPimpamByAcode(String code);

	@Query("select distinct i from Item i where i.chef.userAccount.id = :chefId and i.itemType=0")
	Collection<Item> findKitchenUtensilsByChef(Integer chefId);		
}

