$(document).ready(function(){
	$("#password").change(function() {
		var pwd = $("#password").val();
		if (pwd.length < 8) {
			$("#button").css("background-color", "gray");
			$("#button").attr("disabled",true);
		} else {
			$("#button").css("background-color", "#4a77d4");
			$("#button").removeAttr("disabled");
		}
	});
});