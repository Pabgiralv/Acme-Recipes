<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.pimpam.form.label.atitle" path="atitle" />
	<jstl:choose>
    	<jstl:when test="${command == 'create'}">	
    		<acme:input-textbox code="chef.pimpam.form.label.acode" path="acode"/>
    	</jstl:when>
    	<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish')}">
    		<acme:input-textbox code="chef.pimpam.form.label.acode" path="acode" readonly="${true}"/>
    		<acme:input-textbox code="chef.pimpam.form.label.money" path="money" readonly="true"/>
    		<acme:input-textbox code="chef.pimpam.form.label.abudget" path="abudget" readonly="true"/>
    	</jstl:when>
    </jstl:choose>
	<acme:input-textbox code="chef.pimpam.form.label.adescription" path="adescription" />
	<acme:input-textbox code="chef.pimpam.form.label.alink" path="alink" />
		<jstl:choose>	 
		<jstl:when test="${command == 'show' && item.published == false}">
			<acme:button code="chef.pimpam.form.button.items" action="/chef/item-quantity/list?masterId=${id}"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && item.published == false}">
			<acme:button code="chef.pimpam.form.button.items" action="/chef/item-quantity/list?masterId=${id}&?published=${ item.published == false}"/>
			<acme:submit code="chef.pimpam.form.button.update" action="/chef/pimpam/update"/>
			<acme:submit code="chef.pimpam.form.button.delete" action="/chef/pimpam/delete"/>
			<acme:submit code="chef.pimpam.form.button.publish" action="/chef/pimpam/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="chef.pimpam.form.button.create" action="/chef/pimpam/create"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>
