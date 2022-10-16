function deleteTrueFalseGame(id) {
	console.log(id);
	swal({
		  title: "Are you sure?",
		  text: "This T/F game and you questions will be permanently erased!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
	})
	.then((OK) => {
		  if (OK) {
			  $.ajax({
				 url:"/home/delete/tfgame/"+id,
				 success: function(res) {
					console.log(res);
				},			
			  });
			swal("T/F game eliminated", {
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