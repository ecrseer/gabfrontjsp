<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="../components/bootstrap.jsp" %>
    <title>lista de vagas</title>
</head>

<body>
<%@include file="../components/menu.jsp" %>

<c:set value="${RespondeVagaDto.getRespostaVaga().getVagaFk()}" var="vaga"/>
<h1>Respondendo a vaga ${vaga.getIdVaga()} - ${vaga.getCargo()}</h1>

<hr/>
<form action="responder" method="post">
    <section>
        <c:set value="${RespondeVagaDto.nextCriterio()}" var="resposta"/>
        <div>
            <bold>${resposta.getCriterio().getDescricao()}</bold>
            conhecimento:
            <input type="text" class="text" name="conhecimento">
        </div>

        <input type="submit" value="Responder proximo criterio" formmethod="post"
               formaction="respondeCriterio"/>

    </section>
    <input type="submit" value="Save"/>
</form>

</body>

</html>