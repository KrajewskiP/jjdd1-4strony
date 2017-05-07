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
    <link href="/css/styles.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen"
          href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="http://localhost:8080/4analysis/notowania/${investFund.id}">Notowania</a></li>
                <li><a href="http://localhost:8080/4analysis/wyniki/${investFund.id}">Wyniki</a></li>
                <li class="active"><a href=http://localhost:8080/4analysis/analiza/${investFund.id}">Analiza
                    techniczna<span
                            class="sr-only">(current)</span></a></li>
            </ul>
        </div>
        <div class="col-md-8 col-md-offset-2 main">
            <div class="row">
                <h3 class="page-header"><a
                        href="http://localhost:8080/4analysis/notowania/${investFund.id}">${investFund.name}</a></h3>
                <span class="text-muted">${investFund.id} </span><br>
                <div class="col-md-4">
                    <span class="lead"><strong>${investFund.close} PLN </strong></span>
                    <c:choose>
                        <c:when test="${investFund.diff > 0}">
                            <span class="glyphicon glyphicon-arrow-up green"></span>
                            <span class="green"> +${investFund.diff}% </span>
                        </c:when>
                        <c:when test="${investFund.diff < 0}">
                            <span class="glyphicon glyphicon-arrow-down red"></span>
                            <span class="red"> ${investFund.diff}% </span>
                        </c:when>
                        <c:otherwise>
                            <span class="glyphicon glyphicon-arrow-right grey"></span>
                            <span class="grey"> 0.00% </span>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="row">
                <span class="text-info">wycena na dzień ${investFund.date}</span>
            </div>

            <div class="row main">
                <h4 class="page-header">Analiza techniczna</h4>
                <form method="get" action="/4analysis/analiza/${investFund.id}">
                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-3 box">
                                <label>od</label>
                                <div class="input-group">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                                    <input type="date" id="date-picker-start" name="from" value="${from}"
                                           class="form-control date-picker">
                                </div>
                            </div>
                            <div class="col-xs-3 col-md-offset-1 box">
                                <label>do</label>
                                <div class="input-group">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                                    <input type="date" id="date-picker-end" name="to" value="${to}"
                                           class="form-control date-picker">
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="col-xs-6">
                            <div>
                                <h5 class="sub-header"><strong>Wskaźnik ZigZag</strong></h5>
                                <span class="text-info">parametr filtrujący w [%]:</span><br>
                                <input type="number" name="zigZag" value="${zigZag}"><br>
                                <button type="submit" class="btn btn-info" value="extrema">Pokaż ekstrema</button>
                            </div>
                            <br>
                            <%--<div>--%>
                            <%--<h5 class="sub-header"><strong>Średnia krocząca</strong></h5>--%>
                            <%--<div class="radio">--%>
                            <%--<label><input type="radio" name="ma" value="SMA">prosta (SMA)</label>--%>
                            <%--</div>--%>
                            <%--<div class="radio">--%>
                            <%--<label><input type="radio" name="ma" value="WMA">ważona (WMA)</label>--%>
                            <%--</div>--%>
                            <%--<div class="radio">--%>
                            <%--<label><input type="radio" name="ma" value="EMA">wykładnicza (EMA)</label>--%>
                            <%--</div>--%>
                            <%--<span class="text-info">okres w [dniach]:</span><br>--%>
                            <%--<input type="number" name="period" value="${period}"><br>--%>
                            <%--<button type="submit" class="btn btn-info" value="average">Pokaż średnią</button>--%>
                            <%--</div>--%>
                        </div>
                    </div>
                </form>
                <div id="Chart" style="float: right; width: 370px;">
                    <canvas id="myChart" width="100" height="100"></canvas>
                </div>
                <div class="table-responsive col-md-4">
                    <table class="table table-striped table-condensed table-hover-other">
                        <thead>
                        <tr>
                            <th>data</th>
                            <th>wycena</th>
                        </tr>
                        </thead>
                        <tbody class="table-other">
                        <c:forEach items="${ratings}" var="rating">
                            <tr>
                                <td>${rating.date}</td>
                                <td>${rating.close}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous"></script>
<script src="js/bootstrap.min.js"></script>
<script>

    var ctx = document.getElementById("myChart");
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [<c:forEach items="${ratings}" var="rating">
                ${rating.date},
                </c:forEach>],
            datasets: [{
                label: 'Wycena',
                data: [<c:forEach items="${ratings}" var="rating">
                    ${rating.close},
                    </c:forEach>],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
</script>
<%--<script src="/js/ratings-ajax.js"></script>--%>
</body>
</html>