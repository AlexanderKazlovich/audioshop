<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/registration" method="post">
    <table style="with: 80%">
        <tr>
            <td>Username</td>
            <td><input type="text" name="username" maxlength="15" required/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" minlength="8" maxlength="20" required/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="email" name="email" pattern="^\S+@\S+\.\S+$" title="example@mail.com" required/></td>
        </tr>
    </table>
    <input type="submit" value="Submit" />
</form>

</body>
</html>
