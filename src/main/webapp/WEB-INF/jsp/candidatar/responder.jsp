<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="../components/bootstrap.jsp" %>
    <title>lista de vagas</title>
</head>

<body>
<%@include file="../components/menu.jsp" %>
<h1>Respondendo á vaga</h1>
<form action="inserir" method="post">
    cargo:
    <input type="text" class="text" name="cargo" id="cargo">
</form>


</body>

</html>