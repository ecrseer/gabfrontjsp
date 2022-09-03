<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="../components/bootstrap.jsp" %>
    <title>lista de vagas</title>
</head>

<body>
<%@include file="../components/menu.jsp" %>
<h1>Publique sua vaga${vagaCadastro}</h1>
<form action="inserir" method="post">

    <div class="mb-3 col-6">
        <label for="cargo" class="form-label">Cargo</label>
        <input type="text" name="cargo"
               id="cargo" class="form-control" id="cargo">
    </div>
    <div class="col-6 mb-3">
        <label for="criterio" class="form-label">Criterio</label>
        <input type="text" name="criterio"
               id="criterio" class="form-control" id="criterio">

        <label for="peso" class="form-label">peso</label>
        <input type="text" name="peso"
               id="peso" class="form-control" id="peso">

        <label for="perfilMinimo" class="form-label">perfilMinimo</label>
        <input type="text" name="perfilMinimo"
               id="perfilMinimo" class="form-control" id="perfilMinimo">

        <button class="btn btn-primary" type="submit"
                formaction="/addCriterio" formmethod="post">Adicionar
        </button>
    </div>
    <div class="col-12">
        <button class="btn btn-primary" type="submit">Publicar</button>
    </div>
</form>
</body>

</html>