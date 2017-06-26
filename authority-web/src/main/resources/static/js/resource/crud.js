function node_changed(id , name ,model){
	var returnJson = {};
	var value = name;
	var self = model.ids[id];
	var parent = model.ids[self.parent];
	var index = $.inArray(id , parent.children) + 1;
	var parent_uuid = parent.data.uuid;
	var level = parent.data.level + 1;
	var uuid = self.data.uuid;
	var plat_uuid = $("#plat_uuid").val();
	var param = {};
	param.order = index;
	param.level = level;
	param.parent_uuid = parent_uuid;
	param.url = value;
	param.platform_uuid = plat_uuid;
	
	if(uuid){
		param.uuid = uuid;
		url = "/resource/update";
	}else{
		url = "/resource/add";
	}
	var validateParam = {};
	validateParam.value = value;
	validateParam.uuid = uuid;
	validateParam.platform_uuid = plat_uuid;
	//验证
	$.ajax({  
	       url: "/resource/isRepeat",  
	       type: "POST",
	       dataType: "json",  
	       data: validateParam,
	       async: false,
	       success: function (data) {  
	    	 var res =  $.parseJSON(data);
	    	 if(!res.is_pass){
	    		 swal({
						title: "",
						text: "该url已存在",
						type: "error"
					});
	    		 return;
	    	 }else{
	    		 //添加或更新
	    		 $.ajax({  
	    		       url: url,  
	    		       type: "POST",
	    		       dataType: "json",  
	    		       data: param,
	    		       async: false,
	    		       success: function (data) {  
	    		    	 var res =  $.parseJSON(data);
	    		    	 if(res.success){
	    		    		console.log("操作成功");
	    		    		returnJson.uuid = res.uuid ? res.uuid : uuid;
	    		    		returnJson.level = level;
	    		    		returnJson.order = index;
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
	    		    	   return;
	    		       }  
	    		   }); 
	    	 }
	       },  
	       error: function (XMLHttpRequest, textStatus, errorThrown) {  
	    	   swal({
					title: "",
					text: "发生错误",
					type: "error"
				});
	    	   return;
	       }  
	   }); 
	
	return returnJson;
}

//获取要删除的节点uuid(子节点)
var resUuids = new Array();
function getChildrenUuids(id , modal){
	var self = modal.ids[id];
	if(self.children.length > 0){
		var children = self.children;
		for(var index in children){
		    var childId = children[index];
			resUuids.push(modal.ids[childId].data.uuid);
			getChildrenUuids(children[index],modal);
		}
	}
}
//删除
function delete_resource(id , modal){
	resUuids = new Array();
	getChildrenUuids(id,modal);
	resUuids.push(modal.ids[id].data.uuid);
	$.ajax({  
	       url: "/resource/delete",  
	       type: "POST",
	       dataType: "json",  
	       data: {uuids:resUuids},
	       async: false,
	       success: function (data) {  
	    	 var res =  $.parseJSON(data);
	    	 if(res.success){
	    		console.log("操作成功");
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
	    	   return;
	       }  
	   }); 
}