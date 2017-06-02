jQuery(document).ready(function($) {
	$('.multiselect').multiselect();
    
    getSelect();
    
    $("#user").change(function(){
    	getRoles();
    });
    
    $("#platform").change(function(){
   	 	getRoles();
   });
    
    $("#confirm").click(function(){
    	var allOption = $("#multiselect_left option")
    	var user_uuid = $("#user").val();
    	var platform_uuid = $("#platform").val();
    	var allUuids = "";
    	if(allOption.length){
    		$(allOption).each(function(){
    			allUuids += $(this).val() +",";
    		});
    	}
    	
    	var param = {};
    	param.user_uuid = user_uuid;
    	param.role_uuids = allUuids;
    	param.platform_uuid = platform_uuid;
    	
    	$.ajax({  
 	       url: "/grant/toUser",  
 	       type: "POST",
 	       data: param,
 	       dataType: "json",  
 	       success: function (res){ 
 	    	   var data = $.parseJSON(res);
 	    	   if(data.success){
 	    		  swal({
 	 					title: "",
 	 					text: "授权成功",
 	 					type: "success"
 	 				});
 	    		 getRoles();
 	    		 return;
 	    	   }else{
 	    		  swal({
 	 					title: "",
 	 					text: "发生错误",
 	 					type: "error"
 	    		  });
 	    		  return;
 	    	   }
 	    	   	
 	       },  
 	       error: function (XMLHttpRequest, textStatus, errorThrown) {  
 	    	   swal({
 					title: "",
 					text: "发生错误",
 					type: "error"
 				});
 	       }  
 	   });
    	
    });
});


function getRoles(param){
	var platform_uuid = $("#platform").val();
    var user_uuid = $("#user").val();
    var param = {};
    param.platform_uuid = platform_uuid;
    param.user_uuid = user_uuid;
	$.ajax({  
	       url: "/grant/grantRoles",  
	       type: "POST",
	       data: param,
	       dataType: "json",  
	       success: function (data) { 
	    	  $("#multiselect_left").html("");
	    	  $("#multiselect_to_1").html("");
	    	  $.each(data, function (key, value) {  
    			  if("grantRoles" == key){
    				  if(value){
    					  $.each(value, function(key, value){
  	    		   			$("#multiselect_left").append("<option value="+value+">" +key + "</option>");
  	    		   		});
    				  }
	    		   	}
	    		   	if("unGrantRoles" == key){
	    		   		if(value){
	    		   			$.each(value, function(key, value){
		    		   			$("#multiselect_to_1").append("<option value="+value+">" +key + "</option>");
		    		   		});
	    		   		}
	    		   	}
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

function getSelect(){
	$.ajax({  
	       url: "/grant/selectList",  
	       type: "POST",
	       dataType: "json",  
	       success: function (res) { 
	    	  $.each(res, function (key, value) {  
	    			if("user" == key){
	    		   		$.each(value, function(key, value){
	    		   			$("#user").append("<option value="+value+">" +key + "</option>");
	    		   		});
	    		   	}
	    		   	if("plat" == key){
	    		   		$.each(value, function(key, value){
	    		   			$("#platform").append("<option value="+value+">" +key + "</option>");
	    		   		});
	    		   	}
	    		   	getRoles();
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


