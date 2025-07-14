
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sistema de Cadastro de Livros UniCesumar</title>
    <style>
        body {
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 40px auto;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 12px rgba(0,0,0,0.1);
        }

        h1 {
            text-align: center;
            color: #003366;
            margin-bottom: 30px;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 12px;
        }

        label {
            font-weight: bold;
            margin-top: 10px;
            color: #333;
        }

        input[type="text"], input[type="number"] {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        button {
            padding: 10px;
            background-color: #0055aa;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #003f7f;
        }

        .lista {
            background-color: #f9f9f9;
            padding: 15px;
            margin-top: 20px;
            border-radius: 6px;
            border: 1px solid #ddd;
        }

        .delete-form {
            margin-top: 10px;
        }

        .mensagem-erro {
            color: red;
            font-weight: bold;
            margin-bottom: 15px;
        }

        hr {
            margin: 20px 0;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Cadastro de Livros UniCesumar</h1>

    <% if (request.getAttribute("mensagemErro") != null) { %>
    <div class="mensagem-erro"><%= request.getAttribute("mensagemErro") %></div>
    <% } %>

    <form method="post" action="<%= request.getContextPath() %>/livros">
        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo" required>

        <label for="autor">Autor:</label>
        <input type="text" id="autor" name="autor" required>

        <label for="ano">Ano:</label>
        <input type="number" id="ano" name="ano" required>

        <button type="submit">Cadastrar Livro</button>
    </form>

    <hr>

    <%= request.getAttribute("htmlLivros") %>
</div>
</body>
</html>
