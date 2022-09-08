<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="false"> 
	<acme:input-textbox code="chef.pimpam.list.label.atitle" path="atitle"/>
	<jstl:choose>
		<jstl:when test="${command == 'create'}">	
    		<acme:input-textbox code="chef.pimpam.form.label.acode" path="acode"/>
    	</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:input-textbox readonly="true" code="chef.pimpam.form.label.acode" path="acode"/>
		</jstl:when>
	</jstl:choose>
	<acme:input-moment readonly="true" code="chef.pimpam.form.label.ainstantationDate" path="ainstantationDate"/>
	<acme:input-textarea code="chef.pimpam.form.label.adescription" path="adescription"/>
	<acme:input-moment code="chef.pimpam.form.label.astartDate" path="astartDate" />
	<acme:input-moment code="chef.pimpam.form.label.aendDate" path="aendDate" />
	<acme:input-money code="chef.pimpam.form.label.abudget" path="abudget"/>
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-select code="chef.pimpam.list.label.item" path="itemId">
	   			<jstl:forEach items="${items}" var="i">
					<acme:input-option code="${i.getName()}" value="${i.getId()}" selected="${ i.getId() == itemId }"/>
				</jstl:forEach>
			</acme:input-select>
		</jstl:when>
	</jstl:choose>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:input-textbox readonly="true" code="chef.pimpam.list.label.item" path="itemName"/>
		</jstl:when>
	</jstl:choose>
	<jstl:choose>
		<jstl:when test="${command =='show'}">
			<acme:input-money readonly="true" code="epicure.dish.form.label.money" path="money" />
		</jstl:when>
	</jstl:choose> 
	<acme:input-textbox code="chef.pimpam.form.label.alink" path="alink"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete') && itemPublished==false}">		
			<acme:submit code="chef.pimpam.form.button.update" action="/chef/pimpam/update"/>
			<acme:submit code="chef.pimpam.form.button.delete" action="/chef/pimpam/delete"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="chef.pimpam.form.button.create" action="/chef/pimpam/create"/>
		</jstl:when>	
	</jstl:choose>
		
</acme:form>

