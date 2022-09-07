package acme.features.chef.pimpam;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.pimpam.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefPimpamCreateService implements AbstractCreateService<Chef, Pimpam>{

	@Autowired
	protected ChefPimpamRepository repo;
	
	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;
		return true;	
	}

	@Override
	public void bind(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "acode", "adescription", "atitle");
		
		int itemId;
		itemId = request.getModel().getInteger("items");
        
        Item item;
        item = this.repo.findIngredientById(itemId);
        entity.setItem(item);

	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final Chef chef = this.repo.findChefById(request.getPrincipal().getActiveRoleId());
		final Collection<Item> items = this.repo.findIngredientByChef(chef.getId());
		model.setAttribute("items", items);
		model.setAttribute("itemSize", items.size());

		request.unbind(entity, model,  "acode", "atitle", "adescription", "ainstantationMoment", "astartDate", "aendDate", "abudget", "alink");	
	}

	@Override
	public Pimpam instantiate(final Request<Pimpam> request) {
		assert request != null;

		final Pimpam result;
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		
		Date initialD;
		Date finalD;

		initialD = DateUtils.addMonths( new Date(System.currentTimeMillis() + 300000),1);
		finalD = DateUtils.addWeeks( initialD,1);
		finalD = DateUtils.addMinutes(finalD, 1);

	
		result = new Pimpam();
		result.setAcode("");
		result.setAinstantationMoment(moment);
		result.setAtitle("");
		result.setAdescription("");
		result.setAstartDate(initialD);
		result.setAendDate(finalD);
		result.setAlink("");

		return result;

	
	}

	@Override
	public void validate(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if(!errors.hasErrors("Acode")) {
			Pimpam existing;
			
			existing = this.repo.findPimpamByAcode(entity.getAcode());
			errors.state(request, existing == null|| existing.getId() == entity.getId(), "code", "chef.pimpam.form.error.duplicated");
		}
		
		if(!errors.hasErrors("alink")) {
			final boolean isInfo;
			if(!entity.getAlink().isEmpty()) {
				isInfo = (entity.getAlink().startsWith("http") || entity.getAlink().startsWith("www")) && entity.getAlink().contains(".");
				errors.state(request, isInfo, "alink", "chef.pimpam.form.error.info");
			}
		}


	}

	@Override
	public void create(final Request<Pimpam> request, final Pimpam entity) {
		assert request != null;
		assert entity != null;

		this.repo.save(entity);
	}

}
