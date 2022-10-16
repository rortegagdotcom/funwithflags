function deleteFlag(id) {
	console.log(id);
	swal({
		  title: "Are you sure?",
		  text: "WARNING: The games and their rankings will also be deleted by deleting their selected flag!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
	})
	.then((OK) => {
		  if (OK) {
			  $.ajax({
				 url:"/home/delete/flag/"+id,
				 success: function(res) {
					console.log(res);
				},			
			  });
			swal("Flag eliminated", {
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