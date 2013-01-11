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
<title>Insert title here</title>
</head>
<body>
<f:view>
	<h:form>
		<h:panelGrid columns="2" width="100%" columnClasses="col1,col2">

			<rich:tree style="width:300px"
				nodeSelectListener="#{simpleTreeBean.processSelection}"
				reRender="selectedNode" onclick="javascript:alert(1)"
				binding="#{simpleTreeBean.sampleTreeBinding}"
				value="#{simpleTreeBean.treeNode}" var="item" ajaxKeys="#{null}">
			</rich:tree>

			<h:outputText escape="false"
				value="Selected Node: #{simpleTreeBean.nodeTitle}" id="selectedNode" />
				
				
		</h:panelGrid>

	</h:form>
</f:view>
</body>
</html>