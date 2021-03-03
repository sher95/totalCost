<%--
  Created by IntelliJ IDEA.
  User: Frank
  Date: 3/1/2021
  Time: 2:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>User management</title>
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Users</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="$(user!=null">
                <form action="update" method="post">
                </form>
            </c:if>
            <c:if test="$(user==null">
            <form action="insert" method="post">
                </c:if>

                <caption>
                    <h2>
                        <c:if test="$(user != null">
                            Edit user
                        </c:if>
                        <c:if test="$(user==null">
                            Add new user
                        </c:if>
                    </h2>
                </caption>

                <c:if test="$(user!=null">
                    <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                </c:if>

                <fieldset class="form-group">
                    <label>User name</label> <input type="text"
                                                    value="<c:out value='${user.amount}' />" class="form-control" name="amount">
                </fieldset>

                <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>