<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <title>Form</title>
</head>
<body>
    <form action="submitDetails" method="post">
        <h2>Personal Details</h2>
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName"/>
        <br/>
        <label for="age">Age:</label>
        <input type="text" id="lastName" name="age"/>
        <br/>

        <input type="submit" value="Submit"/>
    </form>
</body>
</html>
