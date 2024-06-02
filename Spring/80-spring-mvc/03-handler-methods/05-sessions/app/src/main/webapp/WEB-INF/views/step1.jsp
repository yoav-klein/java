
<!DOCTYPE html>
<html>
<head>
    <title>Step 1</title>
</head>
<body>
    <form action="step1" method="post">
        <h2>Step 1: Personal Information</h2>
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" value="${user.firstName}"/>
        <br/>
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" value="${user.lastName}"/>
        <br/>
        <input type="submit" value="Next"/>
    </form>
</body>
</html>
