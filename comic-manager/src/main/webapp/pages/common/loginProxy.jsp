<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
                      "http://www.w3.org/TR/html4/loose.dtd">

<body onload="document.forms[0].submit()">
    <form method="post" action="j_security_check">
        <input type="hidden" name="j_username" value="${j_username}"/>
        <input type="hidden" name="j_password" value="${j_password}"/>
    </form>
</body>
