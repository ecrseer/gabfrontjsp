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
<h1>Respondendo a vaga ${RespondeVagaDto.getVagaFk().getIdVaga()}</h1>
vaga:
<select name="vaga" id="vaga">
    <option value="${RespondeVagaDto}" selected>${RespondeVagaDto.getVagaFk().getCargo()}</option>
</select>

<hr/>
<form:form action="responder" method="post" modelAttribute="RespondeVagaDto">

    <section>
        <c:forEach items="${RespondeVagaDto.getVagaFk().getCriterios()}" var="criterio">
            <div>
                <bold>${criterio.getDescricao()}</bold>
                conhecimento:
                <input type="text" class="text" name="${criterio.getIdCriterio()}">
            </div>
        </c:forEach>
    </section>
    <input type="submit" value="Save"/>
</form:form>

</body>

</html>