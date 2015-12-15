<%@page import="dominio.Regiao"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Lista de Regiões</h2>
<%
EntityManagerFactory emf = Persistence.createEntityManagerFactory("meujpa");
EntityManager em = emf.createEntityManager();
List<Regiao> lista = em.createQuery("SELECT u FROM Regiao u").getResultList();
%>
<table border="1">
<tr>
<td>Código</td>
<td>Nome</td>
</tr>
<%
for (Regiao x : lista) {
%>
<tr>
<td><%=x.getCodRegiao()%> </td>
<td><%=x.getNomeRegiao()%></td>
</tr>
<%
}
em.close();
emf.close();
%>
</table><br>
<a href="<%=request.getContextPath()%>/cliente/regiaoForm.jsp?cmd=novo">Cadastro de Região</a>
</body>
</html>