$('#inner-button').on('click', function(){
	
	var province = $('#province').val();
	var messageContent = $('#messageContent').val();
	var $spinner = $('#spinner');
	
	$spinner.removeClass('hide');
	
	var message = {
			province: province,
			messageContent: messageContent
	}
	
	function getData(){ $.ajax({
		 		type: "POST",
		 		url: 'sendAlertToUser',
		 		timeout: 10000,
		 		contentType : 'application/json',
		 		data: JSON.stringify(message),
		        success: function () {
		        	console.log('@@@NO NEED HELP - REQUEST EMAIL SEND')
		        	$spinner.addClass('hide');
		        },
		 		fail: function(){
		 			console.log("fail");
		 		},
		 		error: function(e){
		 		    setTimeout(function(){
			 			console.log('@@@@Rerun operation for other records : STARTED : need help for new request');
			 			getData();
		 		    }, 10000);
		 		}
		 	});
	}
	getData();
});