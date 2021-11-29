<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CodePen - Sign up / Login Form</title>
    <style>
        <#include "css/style.css">
    </style>
    <title>Slide Navbar</title>
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
</head>
<body>
<div class="main">
    <#if message?has_content>
    <H3 align="center" style="color: #FFA500; margin: 0; padding: 0">${message}</H3>
    </#if>
    <input type="checkbox" id="chk" aria-hidden="true">
    <div class="signup">

        <form action="/register" method="post">
            <label for="chk" aria-hidden="true" style="margin: 30px">Sign up</label>
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
