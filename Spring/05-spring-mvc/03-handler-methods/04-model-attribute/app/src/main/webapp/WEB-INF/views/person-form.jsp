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
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName"/>
        <br/>

        <h2>Address Details</h2>
        <label for="street">Street:</label>
        <input type="text" id="street" name="street"/>
        <br/>
        <label for="city">City:</label>
        <input type="text" id="city" name="city"/>
        <br/>

        <input type="submit" value="Submit"/>
    </form>
</body>
</html>
