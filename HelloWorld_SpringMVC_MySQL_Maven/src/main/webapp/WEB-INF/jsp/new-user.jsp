<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head />
<body>
	<h1>New User</h1>

	<c:url value="/users/new/" var="form.action" />
	<form:form action="${form.action}" commandName="newUserModel" method="POST">
		<table>
			<tr>
				<td><form:label path="user.name">Name:</form:label></td>
				<td><form:input path="user.name" autocomplete="${newUserModel.user.name}" /></td>
			</tr>
			<tr>
				<td><form:label path="user.standard">Standard:</form:label></td>
				<td><form:input path="user.standard" autocomplete="${newUserModel.user.standard}" /></td>
			</tr>
			<tr>
				<td><form:label path="user.age">Age:</form:label></td>
				<td><form:input path="user.age" autocomplete="${newUserModel.user.age}" /></td>
			</tr>
			<tr>
				<td><form:label path="user.sex">Sex:</form:label></td>
				<td><form:input path="user.sex" autocomplete="${newUserModel.user.sex}" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Create user" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>
