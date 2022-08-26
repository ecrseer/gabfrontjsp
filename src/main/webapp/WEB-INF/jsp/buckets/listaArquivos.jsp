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
                            <td>Data modificad</td>
                            <td>Dono</td>
                            <td>Tamanho</td>
                            <td>acoes</td>
                        </thead>
                    </tr>
                    <c:forEach items="${bucketFiles}" var="file">
                        <tr>
                            <td>${file.getKey()}</td>
                            <td>${file.getLastModified()}</td>
                            <td>${file.getOwner()}</td>
                            <td>${file.getSize()}</td>
                        </tr>
                    </c:forEach>
                </table>

        </body>

        </html>