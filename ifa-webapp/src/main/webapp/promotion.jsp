<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>4analysis</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<form action="/4analysis/promotion" method="post">
    <div>Identyfikator funduszu:
        <select name="id" id="fund-id">
            <c:forEach items="${investFunds}" var="investFund">
                <option value="${investFund.id}">${investFund.id}</option>
            </c:forEach>
        </select>
    </div>
    <div>Nazwa:</div>
    <div>
        <input id="fund-name" type=text" name="name" required>
    </div>
    <div>Ustaw priorytet funduszu:</div>
    <div>
        <input id="fund-priority" type="number" name="priority" min="0" max="100" required>
    </div>
    <div>
        <input type="submit" value="Uaktualnij bazę danych"/>
    </div>
</form>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous"></script>
<script src="/js/promotion-ajax.js"></script>
</body>
</html>
