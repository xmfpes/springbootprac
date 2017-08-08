$(document).ready(function() {
	/* initialize the calendar
	-----------------------------------------------------------------*/
	$('#calendar').fullCalendar({
		header : {
			left : 'prev,next today',
			center : 'title',
			right : 'month,agendaWeek,agendaDay'
		},
		buttonText : {
			today : '오늘',
			month : '월간 계획',
			week : '주간 계획',
			day : '일간 계획'
		},
		navLinks : true,
		editable : true,
		eventSources: [
	        // your event source
	        {
	            url: '/schedule/load',
	            type: 'POST',
	            editable : true
	        }
	    ],
		timeFormat : "HH:mm",
		monthNames: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
		monthNamesShort: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
		dayNames: ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"],
		dayNamesShort: ["일", "월", "화", "수", "목", "금", "토"],
		droppable : true, // this allows things to be dropped onto the calendar
		drop : function(date, allDay) {
            // is the "remove after drop" checkbox checked?
            if ($('#drop-remove').is(':checked')) {
                // if so, remove the element from the "Draggable Events" list
                $(this).remove();
            }
		},
		eventDrop: function(event, delta, revertFunc) {
	        alert(event.id + " 일정을 " + event.start.format() + "날짜로 옮깁니다.");
	        if (!confirm("일정을 변경하시겠어요?")) {
	            revertFunc();
	        }
	        
	        updateEvent(event);

	    },
	    eventAfterAllRender : function(event, element, view){
	    		
	    },
	    eventReceive:function(event){

	    	var new_event = {};
	    		$.ajax({
	    		  dataType: "json",
	    		  url: 'schedule/getcurrent',
	    		  success:function(result){
					event.id = result + 1;
					alert(new_event.id);
				},
				error:function(result){
					alert(result);
				}
	    		});
	    		
			new_event.borderColor = event.borderColor;
			new_event.title = event.title;
			new_event.start = event.start;
			new_event.backgroundColor = event.backgroundColor;
			new_event.end = event.end;
			new_event.borderColor = event.borderColor;
			new_event.editable = event.editable;
			new_event.durationEditable = event.durationEditable;
			new_event.allDay = event.allDay;
			
			console.log(new_event.title);
			console.log(new_event.start);
			console.log(new_event.end);
			console.log(new_event.backgroundColor);
			console.log(new_event.borderColor);
			console.log(new_event.editable);
			console.log(new_event.durationEditable);
			//new_event.owner_id = $_SESS['user_id']		
			console.log(event);
			console.log(new_event);
			insertEvent(new_event);
	    },
	    eventResize :function(event , delta , revertFunc , jsEvent , ui , view) { 
	    		updateEvent(event);
	    },
		eventDragStop: function(event, jsEvent, ui, view) {
            if(isEventOverDiv(jsEvent.clientX, jsEvent.clientY)) {
                $('#calendar').fullCalendar('removeEvents', event._id);
            }
        },
        eventAfterRender: function(event, element) {
	
	    }
	});
	
	/* initialize the external events
	-----------------------------------------------------------------*/
	var currColor = "#f6504d";
	$('#external-events .fc-event').each(function() {

		// store data so the calendar knows to render an event upon drop
		$(this).data('event', {
			title : $.trim($(this).text()), // use the element's text as the event title
			stick : true,
			backgroundColor : $(this).css('background-color'),
			borerColor : $(this).css('border-color')
		// maintain when user navigates (see docs on the renderEvent method)
		});

		// make the event draggable using jQuery UI
		$(this).draggable({
			zIndex : 999,
			revert : true, // will cause the event to go back to its
			revertDuration : 0
		//  original position after the drag
		});
	});

	//external event form
	var externalEvents = $('#external-events');
	// Color switchers
	var eventColorSelector = $('.external-event-color-selector .point');
	
	/* ColorSelector click event*/
	eventColorSelector.click(function(e) {
		e.preventDefault();
		var $this = $(this);
		// Save color
		currColor = $this.css('background-color');
		// De-select all and select the current one
		eventColorSelector.removeClass('selected');
		$this.addClass('selected');
	});
	/* colorPicker Button Event*/
	var eventNameInput = $('.external-event-name');
	var eventAddButton = $('.external-event-add-btn');
	eventAddButton.click(function(e){
		e.preventDefault();
		// Get event name from input
		var val = eventNameInput.val();
		// Dont allow empty values
		if ($.trim(val) === '')
			return;
		
		// Create new event element
		var newEvent = $('<div/>').css({
			'background-color' : currColor,
			'border-color' : currColor,
			'color' : '#fff'
		}).addClass("fc-event")
		newEvent.html(val);
		
		addDraggable(newEvent);
		// Prepends to the external events list
		externalEvents.prepend(newEvent);
		// Initialize the new event element
		
		// Clear input
		eventNameInput.val('');
	});
	
	function addDraggable(event){
		event.data('event', {
			title : $.trim(event.text()), // use the element's text as the event title
			stick : true,
			backgroundColor : event.css('background-color'),
			borderColor : event.css('border-color')
		// maintain when user navigates (see docs on the renderEvent method)
		
		});
		
		event.draggable({
			zIndex : 999,
			revert : true, // will cause the event to go back to its
			revertDuration : 0
		//  original position after the drag
		});
	}
	function updateEvent(event){
		alert('/' + event.id);
		alert(event.end);
		$.ajax({
			type:'post',
			url:'/schedule/' + event.id,
			headers: { 
			      "Content-Type": "application/json",
			      "X-HTTP-Method-Override": "POST" },
			dataType:'text',
			data: JSON.stringify({ 
				start:event.start, 
				end:event.end
			}),
			success:function(result){
				console.log("result: " + result);
				if(result == 'SUCCEUSS'){
					alert("업데이트 되었습니다.");
				}
			},
			error:function(result){
				console.log(result);
			}
	      });
	}
	function insertEvent(event){
		$.ajax({
				type:'post',
				url:'/schedule',
				headers: { 
				      "Content-Type": "application/json",
				      "X-HTTP-Method-Override": "POST" },
				dataType:'text',
				data: JSON.stringify({
					title:event.title, 
					allDay:event.allDay, 
					editable:true, 
					durationEditable:true, 
					start:event.start, 
					end:event.end, 
					borderColor:String(event.borderColor), 
					backgroundColor:String(event.backgroundColor)}),
				success:function(result){
					console.log("result: " + result);
					if(result == 'SUCCESS'){
						alert("전 송 되었습니다.");
					}
				},
				error:function(result){
					console.log(result);
				}
		      });
	}
    var isEventOverDiv = function(x, y) {

        var external_events = $( '#external-events' );
        var offset = external_events.offset();
        offset.right = external_events.width() + offset.left;
        offset.bottom = external_events.height() + offset.top;

        // Compare
        if (x >= offset.left
            && y >= offset.top
            && x <= offset.right
            && y <= offset .bottom) { return true; }
        return false;

  	}
});