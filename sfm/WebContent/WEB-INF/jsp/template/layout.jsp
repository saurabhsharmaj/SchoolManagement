<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.10.4.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" type="text/css">
<script src="${pageContext.request.contextPath}/js/jquery.1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.4.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.autocomplete.min.js"></script>

<script type="text/javascript">
  $(function() {
  	$('.datepicker').datepicker({
  	showOn: "button",
      buttonImage: "${pageContext.request.contextPath}/images/calendar.png",
      buttonImageOnly: true,
      buttonText: "Select date"
      });
    
    $('input:file').change(
    function(e){
    		$('#fileName').val(e.target.files[0].name);       
    });
    
    $('.autoComplte').autocomplete({
			serviceUrl: '${pageContext.request.contextPath}/getUserNames',
			paramName: "userName",
			delimiter: ",",
		    transformResult: function(response) {		    	
		        return {		        	
		            suggestions: $.map($.parseJSON(response), function(item) {		            	
		                return { value: item.firstName +"/"+item.fatherName, data: item.id };
		            })
		            
		        };
		        
		    },
		    click: function (event, ui) {
					    var name = ui.item.value
					   alert(name);
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
