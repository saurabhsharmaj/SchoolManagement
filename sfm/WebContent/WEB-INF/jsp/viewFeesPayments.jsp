<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(function() {
  	$('#viewFeesPayments').dataTable({
		"bServerSide": true,
		"lengthMenu": [[5,10,20, -1], [5,10,20, "All"]],
		"sAjaxSource": '${pageContext.request.contextPath}/listFeesDATA',			
		"columns": [
			{ "data": "id" },
			{ "data": "fullName" },
			{ "data": "totalFees" },		
			{ "data": "totalExpenses" },
			{ "data": "totalPaidFees" },
			{ "data": "totalAdditionCharges" },
			{ "data": "totalPendingFees" },
			{ "data": "nextDueDate" }
		],
		"sDom" : "<'row'<'spanPag'l><'span6'p><'spanSer'f>r>t<'row'<'spanPage'i><'span6'p>>",
         "oLanguage" : {"sLengthMenu" : "_MENU_ records per page"},          
         "bProcessing": true,
         "columnDefs": [
            {
               
                "render": function ( data, type, row ) {
                    return '<a href=viewFeesByUserId/'+row.id+'>'+row.fullName+ ' / '+ row.fatherName +'</a>';
                },
                "targets": 1
            },{
               
                "render": function ( data, type, row ) {
                    return formatDate(new Date(row.nextDueDate));
                },
                "targets": 7
            },{
               
                "render": function ( data, type, row ) {              
                    return '<a href=deleteuser/'+row.id+'><img src="images/vcard_delete.png" title="Delete User"/></a>&nbsp;<a href=viewFeesByUserId/'+row.id+'><img src="images/vcard_add.png" title="Edit User"/></a>';
                },
                "targets": 8
            }]         
	});
	
	function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [day, month, year].join('-');
}		
});
</script>
<fieldset>
	<legend>View Fees Payment</legend>
	<p style="color: green; font-weight: bold;">
		Add Fees <a
			href="<c:url value='/addFees' />"> <img
			src="<c:url value='/images/vcard_add.png' />"
			title="Add fees" />
		</a>
	</p>

<table id="viewFeesPayments" class="display">
		<thead>
			<tr>
				<th width="5">UserID</th>
				<th width="20">UserName</th>
				<th width="12">Total Fees</th>
				<th width="12.5">Total Expenses</th>
				<th width="12.5">Paid Fees</th>
				<th width="12.5">Additional Fees</th>
				<th width="12.5">Pending Fees</th>
				<th width="12.5">Next Due</th>
				<th width="12.5">Action</th>
			</tr>
		</thead>
</table>
</fieldset>
<a href="${pageContext.request.contextPath}/pdf/fees_report/1" css="button">Fees Report</a>

