<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <%@include file="../components/bootstrap.jsp" %>
                <title>lista de buckets</title>
        </head>

        <body>
            <%@include file="../components/menu.jsp" %>

                <table class="table">
                    <tr>
                        <thead>
                            <td>
                                NOME
                            </td>
                            <td>
                                Data
                            </td>
                            <td>Dono</td>
                            <td>acoes</td>
                        </thead>
                    </tr>
                    <c:forEach items="${bucketsList}" var="bucket">
                        <tr>
                            <td>${bucket.getName()}</td>
                            <td>${bucket.getCreationDate()}</td>
                            <td>${bucket.getOwner().getDisplayName()}</td>

                            <td>
                                <form>
                                    <button class="btn btn-negative" formmethod="GET"
                                        formaction="/buckets/lista/arquivos/${bucket.getName()}">
                                        Ver arquivos</button>
                                </form>


                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <form action="/buckets/upload" method="post" enctype="multipart/form-data">
                    nomebucket<input type="text" name="bucketName" id="bucketName">
                    arquivo <input type="file" name="file" id="file">
                    <button type="submit" class="btn btn-primary">enviar</button>

                </form>
        </body>

        </html>