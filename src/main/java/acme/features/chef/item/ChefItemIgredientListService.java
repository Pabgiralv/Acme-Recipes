/*
 * AuthenticatedConsumerCreateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.chef.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefItemIgredientListService implements AbstractListService<Chef, Item> {
	
	@Autowired
	protected ChefItemRepository repository;
	@Autowired
	protected ChefItemMoneyExchange chefItemMoneyExchange;


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;
		
		final Collection<Item> result;
		
		result = this.repository.findIngredientByChef(request.getPrincipal().getAccountId());

		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

        final String systemCurrency= this.repository.getDefaultCurrency();
		final Money priceExchanged=this.chefItemMoneyExchange.computeMoneyExchange(entity.getRetailPrice(), systemCurrency).getTarget();
		model.setAttribute("money", priceExchanged);
		
		request.unbind(entity, model, "name", "retailPrice");

	}

}
