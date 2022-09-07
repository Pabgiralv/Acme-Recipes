package acme.features.chef.pimpam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.pimpam.Pimpam;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefPimpamShowService implements AbstractShowService<Chef, Pimpam> {
	
	@Autowired
	protected ChefPimpamRepository repo;
	
	@Autowired
	protected ChefPimpamMoneyExchange chefPimpamMoneyExchange;
	
	@Override
	public boolean authorise(final Request<Pimpam> request) {
		 assert request != null;

	        boolean result;
	        int id;
	        id = request.getModel().getInteger("id");

	        Pimpam pimpam;
	        pimpam = this.repo.findPimpamById(id);

	        result = request.isPrincipal(pimpam.getItem().getChef());

	        return result;
	}

	@Override
	public Pimpam findOne(final Request<Pimpam> request) {
		assert request != null;
		
		int id;
		Pimpam pimpam;
		
		id = request.getModel().getInteger("id");
		pimpam = this.repo.findPimpamById(id);
		
		return pimpam;
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;
		
		final String systemCurrency= this.repo.getDefaultCurrency();
		 MoneyExchange priceExchanged = null;
	     Integer i=0;
	        while (priceExchanged == null && i<=50) {
	        	priceExchanged=this.chefPimpamMoneyExchange.computeMoneyExchange(entity.getAbudget(), systemCurrency);
				i++;
			}
	        try {
				model.setAttribute("money", priceExchanged.getTarget());
			} catch (final Exception e) {
				model.setAttribute("money", "API unavailable at the moment");
			}
	        
			model.setAttribute("itemName", entity.getItem().getName());
			model.setAttribute("items", this.repo.findIngredientByChef(request.getPrincipal().getActiveRoleId()));
			model.setAttribute("itemId", entity.getItem().getId());
			model.setAttribute("itemPublished", entity.getItem().getPublished());
	        
        request.unbind(entity, model, "acode", "atitle", "adescription", "ainstantationMoment", "astartDate", "aendDate", "abudget", "alink");
		
	}

}
