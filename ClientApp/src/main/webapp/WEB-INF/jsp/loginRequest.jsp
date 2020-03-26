<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
    <h3 style="color: red;">Login</h3>

    <div id="login">
       <%--  <form:form action="http://localhost:8080/resourceapp/oauth/authorize" --%>
        
         <form:form action="https://p2002038304trial.authentication.eu10.hana.ondemand.com/oauth/authorize"
            method="get">
            <p>
                <label>Click On Login Button</label>
                
                <!--  Commented code for Local Authorization Server flow -->
                <!--  <input type="text" name="response_type" value="code" hidden="true"/> 
                 <input type="text" name="client_id" value="loginclient" hidden="true" />
                 <input type="text" name="redirect_uri" value="http://localhost:8090/clientapp/api/v1/loginSuccess" hidden="true" />
                 <input type="text" name="scope" value="read" hidden="true" /> -->
                 
                 <input type="text" name="response_type" value="code" hidden="true"/> 
                 <input type="text" name="client_id" value="sb-na-016638a2-874b-4217-9019-4dd48b755af2!t40200" hidden="true" />
                 <input type="text" name="redirect_uri" value="http://localhost:8090/clientapp/api/v1/loginSuccess" hidden="true" />
                 <input type="SUBMIT" value="Login" />
                 
        </form:form>
    </div>
</body>
</html>