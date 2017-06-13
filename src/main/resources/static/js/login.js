$(document).ready(function() {
	$("#password").change(function() {
		var pwd = $("#password").val();
		if (pwd.length < 8) {
			$("#button").css("background-color", "gray");
			$("#button").attr("disabled", true);
		} else {
			$("#button").css("background-color", "#4a77d4");
			$("#button").removeAttr("disabled");
		}
	});

	$("#button").click(function(){

		$.ajax({
			type : "POST",
			url : "/paySys/userLogin",
			data : $('#loginForm').serialize(),
			success : function(data){
			    console.log("!!!!");
			    if(data.status==200){
			        $("#error").html(data.message);
			        location.href="index";
			    } else{
			        $("#error").html(data.message);
			    }
			},
			error : function(e){
			    alert(e);
			}
		});
	});
});
