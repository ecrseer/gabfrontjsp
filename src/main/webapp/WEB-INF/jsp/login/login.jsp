<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="../components/bootstrap.jsp" %>
    <title>Login de vagas</title>
</head>

<body>
<%@include file="../components/menu.jsp" %>
<h1>Logue-se </h1>
<form action="logar" method="post">

    <div class="mb-3 col-6">
        <label for="login" class="form-label">Login</label>
        <input type="text" name="login" class="form-control" id="login">
    </div>
    <div class="mb-3 col-6">
        <label for="password" class="form-label">Senha</label>
        <input type="password" name="password" class="form-control" id="password">
    </div>
    <div class="col-12">
        <button class="btn btn-primary" type="submit">Entrar</button>
    </div>
</form>
</body>

</html>