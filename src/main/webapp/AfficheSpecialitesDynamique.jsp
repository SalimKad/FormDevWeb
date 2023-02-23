<%@ page import="java.lang.annotation.ElementType" %>
<%@ page import="Beans.Etudiant" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: salim
  Date: 18/01/2023
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% ArrayList<Etudiant> listEtudiant=(ArrayList<Etudiant>) session.getAttribute("etudiants"); %>
    <title>Liste étudiants en fonction des spé</title>
    <link rel="stylesheet" href="style.css">
    <link href="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js">
    <link href="https://code.jquery.com/jquery-3.5.1.js">
    <script src="https://code.jquery.com/jquery-3.5.1.js">
    </script>
    <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js">
    </script>
    <script type="text/javascript">
        //fait moi un datatable qui recherche uniquement dans la colonne Spécialité

        $(document).ready(function() {
            $('#example').DataTable({columns: [
                    { "data": "Nom",
                        "searchable": false},
                    { "data": "Prénom",
                        "searchable": false},
                    { "data": "Spécialité",
                        "searchable": true,
                    },
                ],
            });
        } );
    </script>

</head>

<h2><%= "Rechercher dynamiquement les notes des étudiants du module" %></h2>
<body>
<jsp:useBean id="etu" class="Beans.Etudiant" scope="page"/>

<jsp:setProperty property="*" name="etu"/>



<table id="example" class="table table-striped table-bordered" style="width:100%">
    <thead>
    <tr>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Spécialité</th>
    </tr>
    </thead>
    <tbody>


    <%
        System.out.print("");
        for(Etudiant etudiant:listEtudiant){
    %>
    <tr>
        <td><%=etudiant.getNom()%> </td>
        <td><%=etudiant.getPrenom()%> </td>
        <td><%=etudiant.getSpecialite()%> </td>

    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
