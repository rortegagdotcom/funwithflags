function deleteQuizGame(id) {
	console.log(id);
	swal({
		  title: "Are you sure?",
		  text: "This quiz game and your questions will be permanently erased!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
	})
	.then((OK) => {
		  if (OK) {
			  $.ajax({
				 url:"/home/delete/quizgame/"+id,
				 success: function(res) {
					console.log(res);
				},			
			  });
			swal("Quiz game eliminated", {
				icon: "success",
		    })
			.then((ok) => {
		    	if(ok){
		    		location.href="/home";
		    	}
		    });
		  } 
	});
}