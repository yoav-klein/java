<!DOCTYPE html>
<html>
<head>
    <title>Step 2</title>
</head>
<body>
    <form action="step2" method="post">
        <h2>Step 2: Contact Information</h2>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" value="${user.email}"/>
        <br/>
        <input type="submit" value="Next"/>
    </form>
</body>
</html>
