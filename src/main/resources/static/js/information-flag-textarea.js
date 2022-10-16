$(document).ready(function() {
	$('#information').summernote( {
		placeholder: 'Text here the information of the flag',
		height: 350,
		toolbar: [
			['style', ['style']],
		    ['font', ['bold', 'underline', 'clear']],
		    ['color', ['color']],
		    ['para', ['ul', 'ol', 'paragraph']],
		    ['table', ['table']],
		    ['insert', ['link']],
		    ['view', ['fullscreen', 'codeview', 'help']]
		]
	});
});