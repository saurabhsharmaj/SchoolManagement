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
					}
		    
		});
		
    $('.autocompletesearch-fees').autocomplete({
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
               	var linkTemplate = '<li class="#class#"><img src="#src#"></span><a style="padding-left: 15px;" href="/sfm/viewFeesByUserId/#userId#">#name#</a></li>';
                
                for ( var obj in response) {                
                	var rec = response[obj];                	
                	var link = linkTemplate;
                	var due= DateDiff.inDays(new Date(),new Date(rec.nextDueDate));
                	if (due == 0){
                		due =' is Due today';
                		link = link.replace('#class#','yellow').replace('#src#','./images/yellowalertcircle.png');
                	} else if (due > 0) {
                		due =' will be Due ' + Math.abs(due) + 'day(s) ';
                		link = link.replace('#class#','green').replace('#src#','./images/greenalertcircle.png');
					} else {
                		due =' Has been Due ' + Math.abs(due) + 'day(s)';
                		link = link.replace('#class#','red').replace('#src#','./images/redalertcircle.png');
					}
                	link = link.replace('#userId#',rec.id);
                	link = link.replace('#name#',rec.fullName +
                	" / ("+rec.fatherName+")- Due on"+formatDate(new Date(rec.nextDueDate))+'- ('+due+')');
                	
					
					
                	$('.opinion_hmlist1 ul').append(link);
                }
            }
        });
        
		$('.deleteButton').on('click',function(ref){
		 	$('#deleteId').val($(this).attr('id')); 
		 	$('#deleteURL').val($(this).attr('url')); 	
			$('#dialog-confirm').dialog('open');
		});

var DateDiff = {

    inDays: function(d1, d2) {
        var t2 = d2.getTime();
        var t1 = d1.getTime();

        return parseInt((t2-t1)/(24*3600*1000));
    },

    inWeeks: function(d1, d2) {
        var t2 = d2.getTime();
        var t1 = d1.getTime();

        return parseInt((t2-t1)/(24*3600*1000*7));
    },

    inMonths: function(d1, d2) {
        var d1Y = d1.getFullYear();
        var d2Y = d2.getFullYear();
        var d1M = d1.getMonth();
        var d2M = d2.getMonth();

        return (d2M+12*d2Y)-(d1M+12*d1Y);
    },

    inYears: function(d1, d2) {
        return d2.getFullYear()-d1.getFullYear();
    }
}
    
$( "#dialog-confirm" ).dialog({
		resizable: false,
        autoOpen: false,
        buttons: {
        "Delete": function() {
          	$("#ajaxform").submit();
        },
        Cancel: function() {
          $( this ).dialog( "close" );
        }
      }
    });
 
 $("#ajaxform").submit(function(e)
{
	var postData = $(this).serializeArray();
    $.ajax(
    {
        url : $('#deleteURL').val(),
        type: "get",
        data : postData,
        success:function(response, textStatus, jqXHR) 
        {   
        	$('#dialog-confirm').dialog('close');
            window.location.href=response;
        },
        error: function(jqXHR, textStatus, errorThrown) 
        {        
          
           $('#dialog-confirm').dialog('close'); 
        }
    });    
     e.preventDefault();    
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
<div id="dialog-confirm" title="Delete">
<form name="ajaxform" id="ajaxform">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>These item will be permanently deleted and cannot be recovered. Are you sure?</p>
  <input type="hidden" id="deleteId">
  <input type="hidden" id="deleteURL">
</form>
</div>
</body>
</html>
