<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Livros</title>
        <link href="/css/bootstrap.min.css" rel="stylesheet" />
    </head>
    <body>
        <div class="container">
            <h1>Livros</h1>
            <a href="/livros/insert" class="btn btn-primary">Novo Livro</a>
            <table class="table">
                <tr>
                    <th>id</th>
                    <th>titulo</th>
                    <th>genero</th>
                    <th>autores</th>
                    <th>&nbsp;</th>
                </tr>
                <c:forEach var="l" items="${livros}">
                    <tr>
                        <td>${l.id}</td>
                        <td>${l.titulo}</td>
                        <td>${l.genero.nome}</td>
                        <td>
                            <c:forEach var="a" items="${l.autores}">
                                ${a.nome}
                            </c:forEach>
                        </td>
                        <td>
                            <a href="/livros/update/${l.id}" class="btn btn-primary">Editar</a> | 
                            <a href="/livros/delete/${l.id}" class="btn btn-danger">Remover</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>