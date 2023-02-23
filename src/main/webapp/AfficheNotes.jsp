<%@ page import="java.lang.annotation.ElementType" %>
<%@ page import="Beans.Etudiant" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: salim
  Date: 18/01/2023
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% HashMap<String,HashMap<Etudiant,Double>> modulesEtudiantstmp = (HashMap<String, HashMap<Etudiant, Double>>) session.getAttribute("modulesEtudiantstmp");
        ArrayList<String> listModule = (ArrayList<String>) session.getAttribute("modules");%>

    <title>Affiche notes</title>
    <link rel="stylesheet" href="style.css">
    <link href="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js">
    <link href="https://code.jquery.com/jquery-3.5.1.js">
    <script src="https://code.jquery.com/jquery-3.5.1.js">
    </script>
    <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js">
    </script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#example').DataTable({searching: false});
        } );
    </script>

</head>

<h2><%= "Liste des notes des étudiants du module" %></h2>
<body>
<jsp:useBean id="etu" class="Beans.Etudiant" scope="page"/>

<jsp:setProperty property="*" name="etu"/>

<form method="post" action="ScolariteManager">
    <select name="module" required>
            <%
            for (String s : listModule) {
        %>
        <option value="<%= s %>"><%= s %></option>
            <%
            }
        %>
    </select>
    <button type="submit" name="action" value="retour">Envoyer</button>
</form>

<table id="example" class="table table-striped table-bordered" style="width:100%">
    <thead>
    <tr>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Modules</th>
        <th>Notes</th>
    </tr>
    </thead>
    <tbody>


    <%
        System.out.print("");

        for(String modules : modulesEtudiantstmp.keySet()){
            for(Etudiant etudiant : modulesEtudiantstmp.get(modules).keySet()){

    %>
    <tr>
        <td><%=etudiant.getNom()%> </td>
        <td><%=etudiant.getPrenom()%> </td>
        <td><%=modules%></td>
        <td><%=modulesEtudiantstmp.get(modules).get(etudiant)%></td>

    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
</body>
</html>
