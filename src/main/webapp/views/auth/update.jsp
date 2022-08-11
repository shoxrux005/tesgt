<%--
  Created by IntelliJ IDEA.
  User: asliddin
  Date: 12/07/22
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="row">
    <div class="col-6 offset-3">
        <form action="/update" method="post">

            <div class="form-group">
                <label for="firstname">Firstname</label>
                <input type="text" class="form-control" name="firstname" id="firstname" placeholder="Firstname">
            </div>

            <div class="form-group">
                <label for="lastname">Lastname</label>
                <input type="text" class="form-control" name="lastname" id="lastname" placeholder="Lastname">
            </div>

            <div class="form-group">
                <label for="password">old password</label>
                <input type="password" class="form-control" name="old_password" id="old password" placeholder="old password">
            </div>
            <div class="form-group">
                <label for="password">new password</label>
                <input type="password" class="form-control" name="new_password" id="new password" placeholder="new password">
            </div>
            <div class="form-group">
                <label for="password">Password confirm</label>
                <input type="password" class="form-control" name="new_confirm_Password" id="new password confirm" placeholder="new password confirm">
            </div>
            <div class="form-check">
                <input type="checkbox" class="form-check-input" id="exampleCheck1">
                <label class="form-check-label" for="exampleCheck1">Check me out</label>
            </div>
            <button type="submit" class="btn btn-primary">update</button>
        </form>
    </div>
</div>
</body>
</html>
