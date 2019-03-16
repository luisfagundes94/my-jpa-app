<%@ page import ="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>Lista de Usuários</title>

<link rel="stylesheet" href="../resources/static/css/list.css">
<body>
<center>
    <h1>
        User List
    </h1>

    <table>
    <tr>
          <th>
                Name
            </th>
            <th>
                Email
            </th>
          </tr>
      <c:forEach var="user" items="${userList}">
       <tr>
            <td>
                ${user.name}
            </td>
            <td>
                ${user.email}
            </td>
        </tr>
      </c:forEach>
     </table>

</center>
</body>
</html>