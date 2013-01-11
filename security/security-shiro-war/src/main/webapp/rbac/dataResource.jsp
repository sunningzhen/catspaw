<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<f:view>
	<h:form id="dataResourceForm">
	<a4j:keepAlive beanName="dataResource"></a4j:keepAlive>
	<rich:dataTable id="itemList" rows="#{dataResource.dataModel.pageSize}" value="#{dataResource.dataModel}" var="item"
	 	width="100%" columnClasses="nowrap-css" rowClasses="odd-row,even-row">
		<f:facet name="header">
			<rich:columnGroup>
				<h:column>id</h:column>
				<h:column>code</h:column>
				<h:column>dataCode</h:column>
				<h:column>edit</h:column>
				<h:column>delete</h:column>
			</rich:columnGroup>
		</f:facet>
		<rich:column><h:outputText value="#{item.id}"/></rich:column>
		<rich:column><h:outputText value="#{item.code}"/></rich:column>
		<rich:column><h:outputText value="#{item.dataCode}"/></rich:column>
		<rich:column>
			<a4j:commandLink value="edit" actionListener="#{dataResource.openEdit}" reRender="editForm" oncomplete="Richfaces.showModalPanel('editPanel')">
				<f:attribute name="id" value="#{item.id}"/>
				<f:attribute name="code" value="#{item.code}"/>
				<f:attribute name="dataCode" value="#{item.dataCode}"/>
			</a4j:commandLink>
		</rich:column>
		<rich:column>
			<h:commandLink value="delete" actionListener="#{dataResource.remove}" onclick="return window.confirm('?')">
				<f:attribute name="id" value="#{item.id}"/>
			</h:commandLink>
		</rich:column>
		<f:facet name="footer">
			<h:panelGrid columns="2" width="100%">
				<h:outputText value="共#{dataResource.dataModel.rowCount}条,#{dataResource.dataModel.pageCount}页,#{dataResource.dataModel.pageSize}条/页"></h:outputText>
				<rich:datascroller  for="itemList" page="#{dataResource.dataModel.currentPage}" maxPages="20" renderIfSinglePage="false"/>
			</h:panelGrid>
		</f:facet>
	</rich:dataTable>
	
	<a4j:commandLink value="search" actionListener="#{dataResource.search}" reRender="itemList">
	</a4j:commandLink>
	&nbsp;
	<a4j:commandLink value="add" actionListener="#{dataResource.openAdd}" reRender="addForm" oncomplete="Richfaces.showModalPanel('addPanel')">
	</a4j:commandLink>
	</h:form>
	
	<rich:modalPanel id="editPanel" height="400" autosized="true" width="400">
		<f:facet name="header">
			<h:outputLabel value="edit"></h:outputLabel>
		</f:facet>
		<f:facet name="controls"> 
			<a4j:commandLink value="关闭"
				onclick="javascript:Richfaces.hideModalPanel('editPanel');return false;"></a4j:commandLink>
		</f:facet>
		<h:form id="editForm">
			id:<h:outputText value="#{dataResource.id}"></h:outputText><br>
			code:<h:inputText value="#{dataResource.code}"/><br>
			dataCode:<h:inputText value="#{dataResource.dataCode}"/><br>
			<h:commandButton value="edit" action="#{dataResource.edit}"></h:commandButton>
		</h:form>
	</rich:modalPanel>
	
	<rich:modalPanel id="addPanel" height="400" autosized="true" width="400">
		<f:facet name="header">
			<h:outputLabel value="add"></h:outputLabel>
		</f:facet>
		<f:facet name="controls"> 
			<a4j:commandLink value="关闭"
				onclick="javascript:Richfaces.hideModalPanel('addPanel');return false;"></a4j:commandLink>
		</f:facet>
		<h:form id="addForm">
			code:<h:inputText value="#{dataResource.code}"/><br>
			dataCode:<h:inputText value="#{dataResource.dataCode}"/><br>
			<h:commandButton value="add" action="#{dataResource.add}"></h:commandButton>
		</h:form>
	</rich:modalPanel>
	
	
	</f:view>
</body>
</html>