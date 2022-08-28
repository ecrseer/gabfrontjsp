<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="../components/bootstrap.jsp" %>
    <title>Meu Perfil</title>
</head>

<body>
<%@include file="../components/menu.jsp" %>
<h1>Perfil </h1>
<form action="editarPerfil" method="post">

    <div class="mb-3 col-6">
        <label for="login" class="form-label">Login</label>
        <input type="text" name="login" class="form-control" id="login">
    </div>
    <div class="mb-3 col-6">
        <label for="profilePic" class="form-label">Perfil</label>
        <input type="file" name="profilePic" class="form-control" id="profilePic">
    </div>
    <div class="col-12">
        <button class="btn btn-primary" type="submit">Editar</button>
    </div>


</form>
</body>

</html>