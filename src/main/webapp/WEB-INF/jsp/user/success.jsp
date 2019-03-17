<%@ page import ="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>Cadastro feito com sucesso</title>

<link rel="stylesheet" href="../resources/static/css/success.css">
<body>
<center>
    <h1>
        Cadastro feito com sucesso!
    </h1>

    <p>${registeredName}</p>
    <p>${registeredEmail}</p>

    <br>
    <a href="<%=request.getContextPath()%>/user/login/doLogin">Login</a>

</center>
</body>
</html>