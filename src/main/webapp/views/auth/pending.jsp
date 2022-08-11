<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>

<div style="margin-top: 10px">
    <nav aria-label="Page navigation example">
        <ul class="pagination">

            <c:if test="${currentPage != 1}">
                <td><a href="book?search=${search}&page=${currentPage - 1}">Previous</a></td>
            </c:if>
            <table border="1" cellpadding="5" cellspacing="5">
                <tr>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <td>${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td><a href="book?search=${search}&page=${i}">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </table>
            <%--For displaying Next link --%>
            <c:if test="${currentPage lt noOfPages}">
                <td><a href="book?search=${search}&page=${currentPage + 1}">Next</a></td>
            </c:if>

        </ul>
    </nav>
</div>

<div class="row m-4">
    <c:forEach items="${books}" var="book">
        <div class="col-2">
            <div class="card p-2" style="width: 100%">
                <img class="card-img-top" src="/display?img=${book.cover.path}" width="140" height="250"
                     alt="Image cover">
                <div class="card-body">
                    <h5 class="card-title"> ${book.name}</h5>
                    <i style="display:block;">author : ${book.author} </i>
                    <i style="display:block;">description : ${book.description}</i>
                    <i style="display:block;">genre : ${book.genre}"</i>
                    <i style="display:block;">language : ${book.language}"</i>
                    <i style="display:block;">pageCount : ${book.pageCount}"</i>
                    <i style="display:block;">downloadCount : ${book.downloadCount}"</i>
                    <a class="btn btn-success mb-4 text-white"
                       href="${pageContext.request.contextPath}/download?path=${book.cover.path}">Download Cover</a>
                    <br/>
                    <a class="btn btn-success mb-4 text-white"
                       href="${pageContext.request.contextPath}/download?path=${book.file.path}">Download File</a>
                    <br>
                    <a href="/confirmBook?bookId=${book.id}">Confirm</a>
                    <br/>
                    <a href="/cancelBook?bookId=${book.id}">Cancel</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
