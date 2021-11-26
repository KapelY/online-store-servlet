<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CodePen - Sign up / Login Form</title>
    <style>
        <#include "css/style.css">
    </style>

</head>
<body>
<!DOCTYPE html>
<html>
<head>
    <title>Slide Navbar</title>
    <link rel="stylesheet" type="text/css" href="slide navbar style.css">
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
</head>
<body>
<div class="main">
    <#if message?has_content>
        <h1 align="center" style="color: #FFA500;">${message}</h1>
    </#if>
    <input type="checkbox" id="chk" aria-hidden="true">

    <div class="signup">
        <form action="/login" method="post">
            <label for="chk" aria-hidden="true">Sign up</label>
            <input type="hidden" name="login" value="false">
            <input type="text" name="name" placeholder="User name" required="">
            <input type="email" name="email" placeholder="Email" required="">
            <input type="password" name="password" placeholder="Password" required="">
            <input type="submit" class="button" value="SIGN UP">
        </form>
    </div>

    <div class="login">
        <form action="/login" method="post">
            <label for="chk" aria-hidden="true">Login</label>
            <input type="hidden" name="login" value="true">
            <input type="email" name="email" placeholder="Email" required="">
            <input type="password" name="password" placeholder="Password" required="">
            <input type="submit" class="button" value="Login">
        </form>
    </div>
</div>
</body>
</html>
<!-- partial -->

</body>
</html>
