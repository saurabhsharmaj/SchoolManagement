<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>

		

$(document).ready(function() {

function selcteFaculty(ref){
   		
}

$('#facultyName').on('change', function (e) {
    var optionSelected = $("option:selected", this);
    var valueSelected = this.value;
 	$('#action').val('getAttendanceByFacultyId/'+valueSelected+"/"+ (new Date($myCalendar.fullCalendar('getDate')).getMonth()+1));               		
  	$("#ajaxform").submit();
});  

$('#facultyName').empty();
$('#facultyName').append($('<option></option>').val(0).html('Select Faculty'));
	    $.getJSON('getFacultyName',{},function(data)
	    {
	    	
	        $.each(data, function(i,obj)
	        {
	             $('#facultyName').append(
	             	$('<option></option>')
	                        .val(obj.id)	            
	                        .html(obj.facultyName)
	             );
	         });
	       
	    });


	    
    var dates = $("#eventStart, #eventEnd").datepicker({
        defaultDate: "+1w",
        changeMonth: true,
        onSelect: function(selectedDate) {
            var option = this.id == "eventStart" ? "minDate" : "maxDate",
                instance = $(this).data("datepicker"),
                date = $.datepicker.parseDate(
                instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
            dates.not(this).datepicker("option", option, date);
            $('#facultyId').val($('#facultyName').val());
        }
    });
   $('#facultyId').val(1); 
    $myCalendar = $('#myCalendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: ''
        },
        theme: true,
        selectable: true,
        selectHelper: true,
        height: 500,
        select: function(start, end, allDay) {
       	 	$('#attendanceDate').val(new Date(start));            
            $('#calEventDialog').dialog('open');
        },
        eventClick: function(calEvent, jsEvent, view) {
        	
           
            $('#calEventDialog #allday').val([calEvent.className == "gbcs-halfday-event" ? "1" : "2"]).prop('checked', true);
            $("#calEventDialog").dialog("option", "buttons", {
            Save: function() {
               
               	if($('#facultyName').val()!=0){
               		$('#action').val('saveAttendance/'+$('#facultyName').val());               		
               		$("#ajaxform").submit();
               	}else{
               		alert('Please select, Any single faculty.');
               	}
                
            },
            Cancel: function() {
                $(this).dialog('close');
            }
        });
            $("#calEventDialog").dialog("option", "title", "Edit Event");
            $('#calEventDialog').dialog('open');
        },
        editable: true
    });

$("#ajaxform").submit(function(e)
{
    var postData = $(this).serializeArray();
    var formURL = $('#action').val();
    $.ajax(
    {
        url : formURL,
        type: "POST",
        data : postData,
        success:function(item, textStatus, jqXHR) 
        { 
        console.log(item);
        $myCalendar.fullCalendar( 'removeEvents', function(event) {    
        	return true;
		});       
       	for(var i in item)
			{			     
				var newEvent = new Object();
				newEvent.title = item[i].faculty.subject + " - " +item[i].noOfHours;
				newEvent.start = new Date(item[i].attendanceDate);
				newEvent.allDay = false;
				$myCalendar.fullCalendar( 'renderEvent', newEvent );
			}
       
		            
            $myCalendar.fullCalendar('unselect');
            $('#calEventDialog').dialog('close');
        },
        error: function(jqXHR, textStatus, errorThrown) 
        {        
           $myCalendar.fullCalendar('unselect');
           $('#calEventDialog').dialog('close'); 
        }
    });    
     e.preventDefault();    
});

    $('#calEventDialog').dialog({
        resizable: false,
        autoOpen: false,
        title: 'Add Event',
        width: 400,
        buttons: {
            Save: function() {
               
               	if($('#facultyName').val()!=0){
               		$('#action').val('saveAttendance/'+$('#facultyName').val());               		
               		$("#ajaxform").submit();
               	}else{
               		alert('Please select, Any single faculty.');
               	}
                
            },
            Cancel: function() {
                $(this).dialog('close');
            }
        }
    });    
});
</script>
<fieldset>
	<legend>Attendance: </legend>
	<p style="color: green; font-weight: bold;">
		To add a New Faculty please click <a
			href="<c:url value='/addFacultyPage' />"> <img
			src="<c:url value='/images/vcard_add.png' />"
			title="Add a New Faculty" />
		</a>
	</p>
	<p>
	Select Faculty Name:<select id="facultyName"></select> <a href="getFacultiesListView">go to download attendance</a>
	</p>
	<div id="calEventDialog">
		<form name="ajaxform" id="ajaxform">
			<fieldset>
				<table>
					<tr>
						<th>Enter Hours:</th>
						<td>
							<input type="hidden" id="action" name="action" />
							<input type="hidden" id="id" name="id" />
							<input type="hidden" id="facultyId" name="facultyId" />
							<input	type="hidden" id="attendanceDate" name="attendanceDate" />
							<input	type="text" id="noOfHours" name="noOfHours" value="1" /></td>
					</tr>
				</table>

			</fieldset>
		</form>
	</div>
	<div>
		<div id='myCalendar'></div>
	</div>

</fieldset>
