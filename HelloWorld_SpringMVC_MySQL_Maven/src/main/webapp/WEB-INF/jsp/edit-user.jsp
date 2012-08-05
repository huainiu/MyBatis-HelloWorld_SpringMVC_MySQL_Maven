<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head />
<body>
	<h1>Edit User</h1>

	<c:url value="/users/${editUserModel.user.id}/" var="form.action" />
	<form:form action="${form.action}" commandName="editUserModel" method="POST">
		<form:hidden path="user.id" />

		<table>
			<tr>
				<td><form:label path="user.name">Name:</form:label></td>
				<td><form:input path="user.name" autocomplete="${editUserModel.user.name}" /></td>
			</tr>
			<tr>
				<td><form:label path="user.standard">Standard:</form:label></td>
				<td><form:input path="user.standard" autocomplete="${editUserModel.user.standard}" /></td>
			</tr>
			<tr>
				<td><form:label path="user.age">Age:</form:label></td>
				<td><form:input path="user.age" autocomplete="${editUserModel.user.age}" /></td>
			</tr>
			<tr>
				<td><form:label path="user.sex">Sex:</form:label></td>
				<td><form:input path="user.sex" autocomplete="${editUserModel.user.sex}" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Save changes" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>
