<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
<div class="m-4">
    <button type="button" class="btn btn-success mb-4 text-white" data-toggle="modal" data-target="#exampleModal">
        ➕ Add
    </button>
<%--   "https://cdn0.iconfinder.com/data/icons/avatars-3/512/avatar_hipster_guy-512.png" --%>


    <nav class="navbar navbar-dark bg-dark navbar-expand-sm">
        <a class="navbar-brand" href="/">
            <img src="https://s3.eu-central-1.amazonaws.com/bootstrapbaymisc/blog/24_days_bootstrap/logo_white.png" width="30" height="30" alt="logo">
            ${username}

        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-list-4" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbar-list-4">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <img src="https://cdn0.iconfinder.com/data/icons/avatars-3/512/avatar_hipster_guy-512.png" width="40" height="40" class="rounded-circle">
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item"  href="/">Home</a>
                        <a class="dropdown-item" href="/update">Edit Profile</a>
                        <a class="dropdown-item" href="/logout">Log Out</a>
                    </div>
                </li>
            </ul>
        </div>
        <form class="form-inline my-2 my-lg-0" action="/" method="get">
            <input class="form-control mr-sm-2" name="search" type="search" c:value="${search}"
                   placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </nav>

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

    <div class="modal fade bd-example-modal-lg" id="exampleModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Save book</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form method="post" action="/uploads/add" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group mb-3">
                                    <label>Name</label>
                                    <label>
                                        <input type="text" name="name" class="form-control" placeholder="Book name"/>
                                    </label>
                                </div>

                                <div class="form-group mb-3">
                                    <label>Author</label>
                                    <label>
                                        <input type="text" name="author" class="form-control"
                                               placeholder="Book author"/>
                                    </label>
                                </div>
                                <div class="form-group mb-3">
                                    <label>Description</label>
                                    <label>
                                        <input type="text" name="description" class="form-control"
                                               placeholder="Description"/>
                                    </label>
                                </div>

                                <div class="form-group mb-3">
                                    <label>Genre</label>
                                    <label for="genre"></label>
                                    <select class="form-control" name="genre" id="genre">
                                        <c:forEach items="${genres}" var="genre">
                                            <option value="${genre.name()}">${genre.getKey()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group mb-3">
                                    <label>Language</label>
                                    <label for="language"></label>
                                    <select class="form-control" name="language" id="language">
                                        <c:forEach items="${languages}" var="language">
                                            <option value="${language.name()}">${language.getKey()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group mb-3">
                                    <label>Page Count</label>
                                    <label>
                                        <input type="number" name="pageCount" class="form-control"
                                               placeholder="Book pageCount"/>
                                    </label>
                                </div>

                                <div class="form-group mb-3">
                                    <label>Book</label>
                                    <label>
                                        <input type="file" name="file" class="form-control" placeholder="Book"/>
                                    </label>
                                </div>
                                <button type="submit" class="btn btn-primary">save</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
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
