<%@ page language="java" contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="windows-1256">
  <title>Modifier un Produit</title>
</head>
<body>
<form action="updateProduit" method="post">
<pre>
id : <input type="text" name="idProduit" value="${produit.idProduit}">
nom :<input type="text" name="nomProduit" value="${produit.nomProduit}">
prix :<input type="text" name="prixProduit" value="${produit.prixProduit}">
Date création :
<fmt:formatDate pattern="yyyy-MM-dd" value="${produit.dateCreation}" var="formatDate"
/>
<input type="date" name="date" value="${formatDate}"></input>
<input type="submit" value="Modifier">
</pre>
</form>
<br/>
<br/>
<a href="ListeProduits">Liste Produits</a>
</body>
</html>