jQuery(document).ready(function($) {
    $('#search').multiselect({
        search: {
            left: '<input type="text" name="q" class="form-control" placeholder="Search..." />',
            right: '<input type="text" name="q" class="form-control" placeholder="Search..." />',
        },
        fireSearch: function(value) {
            return value.length > 3;
        }
    });
    getSelectList();
    $("#platform").change(function(){
    	getRoleList();
    });
    $("#role").change(function(){
    	getResources();
    });
    
    $("#confirm").click(function(){
    	var allOption = $("#search option")
    	var role_uuid = $("#role").val();
    	var allUuids = "";
    	if(allOption.length){
    		$(allOption).each(function(){
    			allUuids += $(this).val() +",";
    		});
    	}
    	
    	var param = {};
    	param.role_uuid = role_uuid;
    	param.resource_uuids = allUuids;
    	
    	$.ajax({  
 	       url: "/grant/toRole",  
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
 	    		 getResources();
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

function getSelectList(){
	$.ajax({  
	       url: "/grant/platformList",  
	       type: "POST",
	       dataType: "json",  
	       success: function (res) { 
	    	   var data = $.parseJSON(res);
		   		$.each(data, function(key, value){
		   			$("#platform").append("<option value="+value+">" +key + "</option>");
		   		});
		   		getRoleList();
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

function getRoleList(){
	var param = {};
	param.platform_uuid = $("#platform").val();
	$.ajax({  
	       url: "/grant/roleList",  
	       type: "POST",
	       data: param,
	       dataType: "json",  
	       success: function (res) { 
	    	   $("#role").html("");
	    	   var data = $.parseJSON(res);
		   		$.each(data, function(key, value){
		   			$("#role").append("<option value="+value+">" +key + "</option>");
		   		});
		   		getResources();
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

function getResources(){ 
	var param = {};
	param.role_uuid = $("#role").val();
	param.platform_uuid = $("#platform").val();
	
	$.ajax({  
	       url: "/grant/grantResources",  
	       type: "POST",
	       data: param,
	       dataType: "json",  
	       success: function (data) { 
	    	   $("#search_to").html("");
	    	   $("#search").html("");
	    	   $.each(data, function (key, value) {  
	    			  if("grant" == key){
	    				  if(value){
	    					  $.each(value, function(key, value){
	  	    		   			$("#search").append("<option value="+value+">" +key + "</option>");
	  	    		   		});
	    				  }
		    		   	}
		    		   	if("unGrant" == key){
		    		   		if(value){
		    		   			$.each(value, function(key, value){
			    		   			$("#search_to").append("<option value="+value+">" +key + "</option>");
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
