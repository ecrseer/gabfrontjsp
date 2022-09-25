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

<c:if test="${RespondeVagaDto!=null}">

    <form action="responder" method="post">
        <c:if test="${RespondeVagaDto.nextCriterio()!=null}">
            <section>
                <c:set value="${RespondeVagaDto.nextCriterio()}" var="resposta"/>
                <div>
                    <bold>${RespondeVagaDto.findCriterioById(resposta.getCriterioFk()).getDescricao()}</bold>
                    conhecimento:
                    <input type="text" class="text" name="conhecimento">
                </div>

                <c:if test="${RespondeVagaDto.getIsLast()==false}">
                    <input type="submit" value="Responder proximo criterio" formmethod="post"
                           formaction="respondeCriterio"/>
                </c:if>

            </section>
        </c:if>
        <c:if test="${RespondeVagaDto.getIsLast()==true}">
            <input type="submit" value="Se candidatar a vaga"/>
        </c:if>

    </form>
</c:if>
<c:if test="${result!=null}">
    <h4>${result}</h4>
</c:if>
<c:if test="${RespondeVagaDto==null}">
    <h4>${result}</h4>
</c:if>
</body>

</html>