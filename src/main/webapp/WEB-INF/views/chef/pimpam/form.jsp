<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="false"> 
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<jstl:if test="${items.isEmpty()==false}">
				<acme:input-textbox code="chef.pimpam.list.label.atitle" path="atitle"/>
				<acme:input-textbox code="chef.pimpam.form.label.acode" path="acode"/>
				<acme:input-moment readonly="true" code="chef.pimpam.form.label.ainstantiationDate" path="ainstantiationDate"/>
				<acme:input-textarea code="chef.pimpam.form.label.adescription" path="adescription"/>
				<acme:input-moment code="chef.pimpam.form.label.astartDate" path="astartDate" />
				<acme:input-moment code="chef.pimpam.form.label.aendDate" path="aendDate" />
				<acme:input-money code="chef.pimpam.form.label.abudget" path="abudget"/>
				<acme:input-select code="chef.pimpam.list.label.item" path="itemId">
	   				<jstl:forEach items="${items}" var="i">
						<acme:input-option code="${i.getName()}" value="${i.getId()}" selected="${ i.getId() == itemId }"/>
					</jstl:forEach>
				</acme:input-select>
				<acme:input-textbox code="chef.pimpam.form.label.alink" path="alink"/>
				<acme:submit code="chef.pimpam.form.button.create" action="/chef/pimpam/create"/>
			</jstl:if>
			<jstl:if test="${items.isEmpty()==true}">
				<h3 style="color: red"><acme:message code="chef.item.no-item"/></h3>
				<acme:button code="chef.item.create-item-no-item" action="/chef/item/create"/>
			</jstl:if>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:input-textbox code="chef.pimpam.list.label.atitle" path="atitle"/>
			<acme:input-textbox readonly="true" code="chef.pimpam.form.label.acode" path="acode"/>
			<acme:input-moment readonly="true" code="chef.pimpam.form.label.ainstantationDate" path="ainstantationDate"/>
			<acme:input-textarea code="chef.pimpam.form.label.adescription" path="adescription"/>
			<acme:input-moment code="chef.pimpam.form.label.astartDate" path="astartDate" />
			<acme:input-moment code="chef.pimpam.form.label.aendDate" path="aendDate" />
			<acme:input-money code="chef.pimpam.form.label.abudget" path="abudget"/>
			<jstl:choose>
				<jstl:when test="${command =='show'}">
					<acme:input-money readonly="true" code="epicure.dish.form.label.money" path="money" />
				</jstl:when>
			</jstl:choose>
			<acme:input-textbox readonly="true" code="chef.pimpam.list.label.item" path="itemName"/>
			<acme:input-textbox code="chef.pimpam.form.label.alink" path="alink"/>
			<jstl:if test="${itemPublished==false}">
				<acme:submit code="chef.pimpam.form.button.update" action="/chef/pimpam/update"/>
				<acme:submit code="chef.pimpam.form.button.delete" action="/chef/pimpam/delete"/>
			</jstl:if>
		</jstl:when>
	</jstl:choose>	
</acme:form>
