
<!DOCTYPE html>
<html>
<body>
<center>
    <h1>
        User List
    </h1>


    <table>
      <c:forEach var="user" items="${userList}">
       <tr>
            <td>

            </td>
            <td>

            </td>
            <td>
                ${user.id}
            </td>
            <td>
                ${user.name}
            </td>
            <td>
                ${user.age}
            </td>
            <td>

            </td>
        </tr>
      </c:forEach>
     </table>

      <br><br>



       <br><br>


</center>
</body>
</html>