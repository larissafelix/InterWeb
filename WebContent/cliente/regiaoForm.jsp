<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="dominio.Regiao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Teste CRUD</title>
</head>
<body>
<h1>CADASTRO DE REGIÃO</h1>
<h2>Entre com os dados da região</h2>
 <form method="post" action="<%=request.getContextPath()%>cliente/regiaoServlet" name="formReg">
  <br />

 Nome : <input type="text" name="nome" value="${reg.nome}" /> <br />
 Valor : <input type="text" name="valor" value="${reg.valor}" /> <br />
 <input type="submit" value="Enviar" />
 </form>
</body>
</html>