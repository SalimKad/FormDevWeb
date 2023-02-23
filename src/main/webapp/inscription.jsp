<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: salim
  Date: 20/12/2022
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Inscription</title>
</head>

<body> <!-- on fait un petit retour à la ligne -->

</body>
<h1><%= "Inscription" %> </h1>

<body>

<%
    ArrayList<String> specialites = (ArrayList<String>) session.getAttribute("specialites");
%>
<br>
<br>
<form action="ScolariteManager" method="post">
    <label for="nom">Nom</label>
    <input type="text" name="nom" id="nom" required/>
    <br/>
    <br/>
    <label for="prenom">Prénom</label>
    <input type="text" name="prenom" id="prenom" required/>
    <br/>
    <br/>
    <label>Spécialité </label>
    <select name="specialite" required>
        <%
            for (String s : specialites) {
        %>
        <option value="<%= s %>"><%= s %></option>
        <%
            }
        %>
    </select>
    <br/>
    <br/>
    <input type="submit" value="Envoyer"/>
</form>
</body>
</html>
