$(function(){
	$("#resource_platform_uuid").change(function(){
		var url = "toTree?platform_uuid=" + $("#resource_platform_uuid").val();
		$("#tree_iframe").attr('src',url);
	    return false;
	});
	getPlatforms();
	var url = "toTree?platform_uuid=" + $("#resource_platform_uuid").val();
	$("#tree_iframe").attr('src',url);
});

function getPlatforms(){
	$.ajax({  
	       url: "/resource/platforms",  
	       type: "POST",
	       dataType: "json",  
	       contentType:"application/json",
	       async: false,
	       success: function (data) {  
	    	   var res = $.parseJSON(data);
	    	   $.each(res, function(key, value){
		   			$("#resource_platform_uuid").append("<option value="+value+">" +key + "</option>");
		   		});
	       },  
	       error: function (XMLHttpRequest, textStatus, errorThrown) {  
	    	   swal({
					title: "",
					text: "发生错误",
					type: "error"
				});
	       }  
	   }); 
}

