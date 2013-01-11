<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
<head>

</head>
<body>
<f:view>
	<h:form>
		<rich:panel>
			<h:panelGrid columns="1">
				<h:outputText value="How many tabs would you like?" />
				<h:panelGroup>
					<h:inputText value="#{tabsBean.numOfTabs}" size="2" />
					<a4j:commandButton value="Create"
						actionListener="#{tabsBean.createTabs}" reRender="tabs" />
				</h:panelGroup>
			</h:panelGrid>
			<h:panelGrid id="tabs" binding="#{tabsBean.panelGrid}" >
				
			</h:panelGrid>
			<rich:tabPanel id="tabs2" >
				<rich:tab id="tab1" label="aaaaaaaa">
				
					<rich:contextMenu>
						<rich:menuItem id="menu1" onclick="javascript:alert(1)">
							aaaaaaaaaaa
						</rich:menuItem>
					</rich:contextMenu>
					fffffffffffffffffff
				</rich:tab>
			</rich:tabPanel>
		</rich:panel>
	</h:form>
</f:view>
</body>
</html>