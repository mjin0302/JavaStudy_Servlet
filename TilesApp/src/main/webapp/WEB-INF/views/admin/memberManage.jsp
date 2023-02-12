<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript" src="js2/manage.js"></script>
    <h3>회원관리페이지</h3>
    <table class="table">
    	<tr>
    		<th>ID</th>
    		<td>
                <input type="text" name="mid" id="mid">
            </td>
    	</tr>
        <tr>
            <th>Name</th>
            <td>
                <input type="text" name="mname" id="mname">
            </td>
        </tr>
        <tr>
            <th>phone Number</th>
            <td>
                <input type="text" name="mphone" id="mphone">
            </td>
        </tr>
        <tr>
            <th>Address</th>
            <td>
                <input type="text" name="maddr" id="maddr">
            </td>
        </tr>
        <tr>
            <th>Image</th>
            <td>
                <input type="file" name="mimage" id="mimage">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="button" id="addBtn" name="addBtn" value="등록" >
            </td>
        </tr>
    </table>
    <table class="table">
    	<thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Phone Number</th>
                <th>Address</th>
                <th>Authority</th>
            </tr>
        </thead>
    	<tbody id="list">
            
        </tbody>
    </table>
    <table id="template" style="display: none;">
        <tr>
            <td>admin</td>
            <td><input type="text" class="name"></td>
            <td><input type="text" class="phone"></td>
            <td><input type="text" class="phone"></td>
            <td><input type="text" class="phone"></td>
            <td><input type="text" class="phone"></td>
            <td><button mid="admin">삭제</button></td>
        </tr>
    </table>