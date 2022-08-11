<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Dropdowns within a Navbar</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
<div class="m-4">
    <nav class="navbar navbar-expand-sm navbar-light bg-light">
        <div class="container-fluid">
            <a href="/" class="navbar-brand">Library</a>
            <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div id="navbarCollapse" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="nav-item">
                        <a href="/" class="nav-link">Home</a>
                    </li>
                    <li class="nav-item">
                        <a href="/update" class="nav-link">Profile</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Messages</a>
                        <div class="dropdown-menu">
                            <a href="/pending" class="dropdown-item">Inbox</a>
                            <a href="/" class="dropdown-item">all books</a>
                            <div class="dropdown-divider"></div>
                            <a href="/trash"class="dropdown-item">Trash</a>
                        </div>
                    </li>
                </ul>
                <ul>
                    <form class="form-inline my-2 my-lg-0" action="/" method="get">
                        <input class="form-control mr-sm-2" name="search" type="search" c:value="${search}"
                               placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </ul>



                <ul class="nav navbar-nav ms-auto">
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Admin</a>
                        <div class="dropdown-menu dropdown-menu-end">
                            <a href="/" class="dropdown-item">Reports</a>
                            <a href="/" class="dropdown-item">Settings</a>
                            <div class="dropdown-divider"></div>
                            <a href="/logout" class="dropdown-item">Logout</a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
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
                </div>
            </div>
        </div>
    </c:forEach>
</div>


</body>
</html>