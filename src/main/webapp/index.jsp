<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome</title>
</head>
<body>
<form method="POST" action="/login">
    <h2>Log in</h2>
    <div>
        <input name="username" type="text" placeholder="Username"
               autofocus="true" maxlength="15" required/>
        <input name="password" type="password" minlength="8" maxlength="20" placeholder="Password" required/>
        <button type="submit">Log In</button>
    </div>
</form>
<h4><a href="/create_acc">Create an account</a></h4>
</body>
</html>