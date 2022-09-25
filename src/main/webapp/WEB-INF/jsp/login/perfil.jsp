<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="../components/bootstrap.jsp" %>
    <title>Meu Perfil</title>
</head>

<body>
<%@include file="../components/menu.jsp" %>
<h1>Perfil do usuario ${loggedUser.getLogin()}</h1>
<h1>Vagas respondidas: </h1>
<div class="ma-3 col-6">

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#id</th>
            <th scope="col">Cargo da vaga</th>
            <th scope="col">Criterios - perfil Minimo</th>
            <th scope="col">Voce respondeu</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${respostas}" var="resposta">
            <tr>
                <th scope="row">${resposta.getIdRespostaVaga()}</th>
                <td>${resposta.getVagaFk().getCargo()}</td>
                <td>
                    <c:forEach items="${resposta.getVagaFk().getCriterios()}" var="criterio">
                        ${criterio.getDescricao()} - ${criterio.getPerfilMinimo()}
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${resposta.getRespostas()}" var="respondeu">
                        ${respondeu.getConhecimento()},
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <hr/>
    <h1>Foto perfil</h1>
    <div class="ma-3 col-6">
        <form action="editarPerfil" method="post" enctype="multipart/form-data">
            <div class="mb-3 col-6">
                <img src="../images/${loggedUser.getLogin()}/profilePic.png"/>

            </div>
            <div class="mb-3 co l-6">
                <caption>somente png</caption>
            </div>
            <div class="mb-3 col-6">
                <label for="profilePic" class="form-label">Perfil</label>
                <input type="file" name="profilePic" class="form-control" id="profilePic" accept="image/png">
            </div>
            <div class="col-12">
                <button class="btn btn-primary" type="submit">Editar Foto</button>
            </div>
        </form>
    </div>
    <%--<form action="editSenha">

        <div class="mb-3 col-6">
            <label for="password" class="form-label">Senha</label>
            <input type="text" name="password" class="form-control" id="password">
        </div>
        <div class="mb-3 col-6">
            <label for="newPassword" class="form-label">Nova Senha</label>
            <input type="text" name="newPassword" class="form-control" id="newPassword">
        </div>
        <div class="col-12">
            <button class="btn btn-primary" type="submit">Mudar senha</button>
        </div>


    </form>--%>

</div>
</body>

</html>