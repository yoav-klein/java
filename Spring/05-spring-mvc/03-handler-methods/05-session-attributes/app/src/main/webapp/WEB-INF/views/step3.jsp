<!DOCTYPE html>
<html>
<head>
    <title>Step 3</title>
</head>
<body>
    <form action="step3" method="post">
        <h2>Step 3: Address Information</h2>
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="${user.address}"/>
        <br/>
        <label for="city">City:</label>
        <input type="text" id="city" name="city" value="${user.city}"/>
        <br/>
        <input type="submit" value="Finish"/>
    </form>
</body>
</html>
