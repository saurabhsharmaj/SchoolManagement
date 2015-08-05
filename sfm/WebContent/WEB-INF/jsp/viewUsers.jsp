<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(function() {
  	$('#userView').dataTable({
		"bServerSide": true,
		"lengthMenu": [[5,10,20, -1], [5,10,20, "All"]],
		"sAjaxSource": '${pageContext.request.contextPath}/listUserDATA',		
		"columns": [
			{ "data": "id" },
			{ "data": "firstName" },
			{ "data": "batch" },		
			{ "data": "session" }
		],
		"sDom" : "<'row'<'spanPag'l><'span6'p><'spanSer'f>r>t<'row'<'spanPage'i><'span6'p>>",
         "oLanguage" : {"sLengthMenu" : "_MENU_ records per page"},          
         "bProcessing": true,
         "columnDefs": [
            {
               
                "render": function ( data, type, row ) {
                    return '<a href=edituser/'+row.id+'>'+row.firstName+ ' '+ row.middleName +' '+ row.lastName+ ' / '+ row.fatherName +'</a>';
                },
                "targets": 1
            },{
               
                "render": function ( data, type, row ) {                
                   	return getStreamName(row.userProfile.stream);
                 },
                "targets": 2
            },{
               
                "render": function ( data, type, row ) {                
                    return getSessionName(row.session) +' / '+  getBatchName(row.batch);
                 },
                "targets": 3
            },{
               
                "render": function ( data, type, row ) {              
                    return '<a href=deleteuser/'+row.id+'><img src="images/vcard_delete.png" title="Delete User"/></a>&nbsp;<a href=edituser/'+row.id+'><img src="images/vcard_add.png" title="Edit User"/></a>';
                },
                "targets": 4
            }]         
	});	
	
	function getBatchName(id){
		switch(id){
		 	case '0': return 'FIRST';
		 	case '1': return 'SECOND';
		 	case '2': return 'THIRD';
		 	case '3': return 'FOURTH';
		 	case '4': return 'FIFTH';
		 	case '5': return 'SIXTH';
		}
	}
	
	function getSessionName(id){
		switch(id){
		 	case '0': return '2014-15';
		 	case '1': return '2015-16';		 	
		}
	}
	
	function getStreamName(id){
		switch(id){
		 	case 0: return 'Arts';
		 	case 1: return 'Commerce';
		 	case 2: return 'Science';
		 	case 3: return 'Other';				 	
		}
	}
});
</script>
<fieldset>
	<legend>List of Users</legend>
	<p style="color: green; font-weight: bold;">
		To add a New Student please click <a
			href="<c:url value='/addUser' />"> <img
			src="<c:url value='/images/vcard_add.png' />"
			title="Add a New Student" />
		</a>
	</p>
	<c:url var="action" value="/user/add.html"></c:url>
	<table id="userView" class="display">
		<thead>
			<tr>
				<th width="10">UserID</th>
				<th>firstName</th>
				<th>stream</th>
				<th>Session</th>
				<th style="width:90px">Action</th>
			</tr>
		</thead>
	</table>
</fieldset>