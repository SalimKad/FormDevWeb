<%@ page import="java.util.ArrayList" %>
<%@ page import="Beans.Etudiant" %>
<%@ page import="java.io.PrintWriter" %>
<%--
  Created by IntelliJ IDEA.
  User: salim
  Date: 18/01/2023
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Saisie</title>
</head>
<h2><%= "Saisie de note" %> </h2>
<body>

<%
    ArrayList<String> modules = (ArrayList<String>) session.getAttribute("modules");
    System.out.println("modules = " + modules);
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
        <label>Module </label>
        <select name="module" required>
            <%
                for (String s : modules) {
            %>
            <option value="<%= s %>"><%= s %></option>
            <%
                }
            %>
        </select>
        <br/>
        <br/>
        <label for="note">Note </label>
        <input type="number" name="note" id="note" required/>
        <br/>
        <br/>
        <input type="submit" value="Envoyer"/>
    </form>
</body>
</head>
</html>
