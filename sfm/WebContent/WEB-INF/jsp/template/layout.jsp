<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.dataTables.css" type="text/css">
<link href='${pageContext.request.contextPath}/css/fullcalendar.css' rel='stylesheet' />
<link href='${pageContext.request.contextPath}/css/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src="${pageContext.request.contextPath}/js/jquery-2.1.4.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
<script src='${pageContext.request.contextPath}/js/moment.min.js'></script>
<script src='${pageContext.request.contextPath}/js/fullcalendar.js'></script>
<script src='${pageContext.request.contextPath}/js/jquery.dataTables.js'></script>

<script type="text/javascript">
 $(function() {   	
  	$('.datepicker').datepicker({
  	showOn: "button",
      buttonImage: "${pageContext.request.contextPath}/images/calendar.png",
      buttonImageOnly: true,
      buttonText: "Select date"
      });
    
    $.datepicker.formatDate('dd/MMM/yy', new Date());
    
    var now = new Date();
	var current;
	if (now.getMonth() == 11) {
	    current = new Date(now.getFullYear() + 1, 0, now.getDate());
	} else {
	    current = new Date(now.getFullYear(), now.getMonth() + 1, now.getDate());
	}

    $('#nextPaymentDueDate').datepicker("setDate", current);
    $('#AddmissionDate').datepicker("setDate", new Date());
    
    $('input:file').change(
    function(e){
    		$('#fileName').val(e.target.files[0].name);       
    });
    
    $('.autocompletesearch').autocomplete({
			serviceUrl: '${pageContext.request.contextPath}/getUserNames',
			paramName: "userName",
			delimiter: ",",
		    transformResult: function(response) {		    	
		        return {		        	
		            suggestions: $.map($.parseJSON(response), function(item) {		            	
		                return { value: item.firstName +"/"+item.fatherName, data: item };
		            })
		            
		        };
		        
		    },
		    onSelect: function (index) {
		    			$('#userId').val(index.data.id);
		    			$('#studentFees').val(index.data.studentFees);
		    			$('#pendingFees').val(index.data.studentFees - $('#totalPaidFees').text());	    
		    			$('#additionCharges').val($('#totalExpenses').text() - $('#totalAdditionCharges').text());
		    			window.location.href = "/sfm/viewFeesByUserId/"+index.data.id;;
					}
		    
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

	$.ajax({
            url : '${pageContext.request.contextPath}/getUserByNextPaymentDate',
            success : function(response) {
               	var linkTemplate = '<li style="padding-bottom: 15px;"><a href="/sfm/viewFeesByUserId/#userId#">#name#</a></li>';
                
                for ( var obj in response) {                
                	var rec = response[obj];                	
                	var link = linkTemplate;
                	link = link.replace('#userId#',rec.user.id);
                	link = link.replace('#name#',rec.user.firstName+""+rec.user.middleName+""+rec.user.lastName +
                	" / ("+rec.user.fatherName+")-"+formatDate(new Date(rec.nextPaymentDueDate)));
                	$('.opinion_hmlist1 ul').append(link);
                }
            }
        });
 });
</script>


</head>
<body>

	<div class="wrapper row1">
		<tiles:insertAttribute name="header" />
	</div>
	<div class="wrapper row2">
		<div id="container" class="clear">
			<aside id="left_column"> <tiles:insertAttribute name="menu" />
			</aside>
			<div id="content">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
	<div class="wrapper row3">
		<tiles:insertAttribute name="footer" />
	</div>

</body>
</html>
