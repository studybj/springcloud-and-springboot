<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="body_wrap">
    <div class="container">
        <div class="alert alert-success text-center" role="alert">Sptring Boot学习</div>
        <table class="table table-striped table-bordered">
            <tr>
                <td>作者</td>
                <td>教程名称</td>
                <td>地址</td>
            </tr>
            <c:forEach var="learn" items="${learnList}">
                <tr class="text-info">
                    <td >${learn.author}</td>
                    <td >${learn.bookName}</td>
                    <td><a href="${learn.bookurl}" class="btn btn-search btn-green" target="_blank"><span>点我</span></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
