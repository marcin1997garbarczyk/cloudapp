$('#inner-button').on('click', function(){
	
	var province = $('#province').val();
	var messageContent = $('#messageContent').val();
	var $spinner = $('#spinner');
	
	$spinner.removeClass('hide');
	
	var message = {
			province: province,
			messageContent: messageContent
	}
	
	$.ajax({
		 		type: "POST",
		 		url: 'sendAlertToUser',
		 		timeout: 5000,
		 		contentType : 'application/json',
		 		data: JSON.stringify(message),
		        success: function () {
		        	$spinner.addClass('hide');
		        },
		 		fail: function(){
		 			console.log("fail");
		 		},
		 		error: function(e){
		 			console.log('@@@@@@@@@@');
		 			$.ajax({
				 		type: "POST",
				 		url: 'sendAlertToUser',
				 		timeout: 5000,
				 		contentType : 'application/json',
				 		data: JSON.stringify(message),
				        success: function () {
				        	$spinner.addClass('hide');
				        },
				 		fail: function(){
				 			console.log("fail");
				 		},
				 		error: function(e){
				 			console.log('@@@@@@@@@@');
				 			console.log(e);
				 		}
				 	});
		 		}
		 	});
});