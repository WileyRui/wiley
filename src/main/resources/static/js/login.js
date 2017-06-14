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
			dataType : "json",
			url : "/paySys/userLogin",
			data : $('#loginForm').serialize(),
			success : function(data){
			    if(data.status==200){
			        location.href="index?userName=" + data.userName;
			    } else{
			        $("#password").val("");
			        $("#vertificationCode").attr("src","getGifCode?date=" + new Date().getTime());
			        $("#error").html(data.message);
			    }
			},
			error : function(e){
			    console.log("error");
			}
		});
	});
	
	// 点击更换验证码
	$("#vertificationCode").click(function(){
	    $("#vertificationCode").attr("src","getGifCode?date=" + new Date().getTime());
	})
});
