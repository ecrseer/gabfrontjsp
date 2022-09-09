<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <%@include file ="../components/bootstrap.jsp" %>
        <title>lista de vagas</title>
    </head>

    <body>
        <%@include file ="../components/menu.jsp" %>
        <h2>suas vagas publicadas:</h2>
        <h1>${vagasListSize}</h1>
        <a href="vagas/inserir">publique uma vaga</a>
        <table class="table">
            <tr>
                <thead>
                    <td>
                        id
                    </td>
                    <td>
                        Cargo
                    </td>
                    <td>Criterios</td>
                    <td>acoes</td>
                </thead>
            </tr>
            <c:forEach items="${vagasList}" var="vaga">
                <tr>
                    <td>${vaga.getIdVaga()}</td>
                    <td>${vaga.getCargo()}</td>
                    <td>

                    </td>
                    <td>
                    <button type="button" class="btn btn-danger"
                    formmethod="get" formaction="/vagas/${vaga.getIdVaga()}/deletar">
                    Excluir</button>
                        
                    </td>
                </tr>
            </c:forEach>
        </table> 

    </body>

    </html>