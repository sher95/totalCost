<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: Frank
  Date: 3/1/2021
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>User management</title>

</head>
<body>
    <header>
        <li><a href="<%=request.getContextPath()%>/list">Users</a></li>
    </header>
    <br>

    <h3 class="text-center">List of Users</h3>
    <hr>
    <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add new user</a>

    <br>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Amount</th>
            <th>Action</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${listUser}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.amount}"/></td>
                <td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
                <a href="delete?id=<c:out value='${iser.id}' />">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        con = DriverManager.getConnection("jdbc:postgresql://localhost/totalcost", "root", "");
        String query = "select count(amount) from totalcost";
        Statement st = con.createStatement();
        rs=st.executeQuery(query);
    %>
<tr>
    <td><%=rs.getString(query)%></td>
</tr>
</body>
</html>