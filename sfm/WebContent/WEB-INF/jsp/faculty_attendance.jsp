<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>

		

$(document).ready(function() {


$('#facultyName').empty();
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
        }
    });
    
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
            $('#eventStart').datepicker("setDate", new Date(start));
            $('#eventEnd').datepicker("setDate", new Date(end));
            $('#calEventDialog').dialog('open');
        },
        eventClick: function(calEvent, jsEvent, view) {
            $('#eventStart').datepicker("setDate", new Date(calEvent.start));
            $('#eventEnd').datepicker("setDate", new Date(calEvent.end));
            $('#calEventDialog #eventTitle').val(calEvent.title);
            $('#calEventDialog #allday').val([calEvent.className == "gbcs-halfday-event" ? "1" : "2"]).prop('checked', true);
            $("#calEventDialog").dialog("option", "buttons", [
                {
                text: "Save",
                click: function() {
                    $(this).dialog("close");
                }},
            {
                text: "Delete",
                click: function() {
                    $(this).dialog("close");
                }},
            {
                text: "Cancel",
                click: function() {
                    $(this).dialog("close");
                }}
            ]);
            $("#calEventDialog").dialog("option", "title", "Edit Event");
            $('#calEventDialog').dialog('open');
        },
        editable: true
    });

    var title = $('#eventTitle');
    var start = $('#eventStart');
    var end = $('#eventEnd');
    var eventClass, color;
    $('#calEventDialog').dialog({
        resizable: false,
        autoOpen: false,
        title: 'Add Event',
        width: 400,
        buttons: {
            Save: function() {
                if ($('input:radio[name=allday]:checked').val() == "1") {
                    eventClass = "gbcs-halfday-event";
                    color = "#9E6320";
                    end.val(start.val());
                }
                else {
                    eventClass = "gbcs-allday-event";
                    color = "#875DA8";
                }
                if (title.val() !== '') {
                    $myCalendar.fullCalendar('renderEvent', {
                        title: title.val(),
                        start: start.val(),
                        end: end.val(),
                        allDay: true,
                        className: eventClass,
                        color: color
                    }, true // make the event "stick"
                    );
                }
                $myCalendar.fullCalendar('unselect');
                $(this).dialog('close');
            },
            Cancel: function() {
                $(this).dialog('close');
            }
        }
    });
});
</script>
<style>

	body {
		margin: 40px 10px;
		padding: 0;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		font-size: 14px;
	}

</style>

	


<fieldset>
  	<legend>Attendance: </legend>
  	Select Faculty Name:<select id="facultyName"></select>
  	
	<div id="calEventDialog">
	    <form>
	        <fieldset>
	        <label for="eventTitle">Title</label>
	        <input type="text" name="eventTitle" id="eventTitle" /><br>
	        <label for="eventStart">Start Date</label>
	        <input type="text" name="eventStart" id="eventStart" /><br>
	        <label for="eventEnd">End Date</label>
	        <input type="text" name="eventEnd" id="eventEnd" /><br>
	        <input type="radio" id="allday" name="allday" value="1">
	        Half Day
	        <input type="radio" id="allday" name="allday" value="2">
	        All Day
	        </fieldset>
	    </form>
	</div>
	<div style="border:solid 2px red;">
	        <div id='myCalendar'></div>
	</div>

</fieldset>
