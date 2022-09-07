<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.pimpam.list.label.atitle" path="atitle" width="35%"/>
	<acme:list-column code="chef.pimpam.list.label.itemName" path="itemName" width="35%"/>
	<acme:list-column code="chef.pimpam.list.label.abudget" path="abudget" width="15%"/>
	<acme:list-column code="chef.pimpam.list.label.money" path="money" width="15%"/>
</acme:list>
<acme:button code="chef.itemQuantity.list.button.addItems" action="/chef/pimpam/create"/>
	