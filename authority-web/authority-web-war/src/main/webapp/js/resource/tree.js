updateTree($("#plat_uuid").val());

function updateTree(platformUuid){
	var param = {};
    param.platform_uuid = platformUuid;
	$.ajax({  
	       url: "/resource/tree",  
	       type: "POST",
	       data: param,
	       dataType: "json",  
	       async: false,
	       success: function (data) {  
	    	 if(!data){
	    		 swal({
						title: "",
						text: "发生错误",
						type: "error"
					});
	    		 return;
	    	 }
	    	 var res = $.parseJSON(data)
	    	 start(res);
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
