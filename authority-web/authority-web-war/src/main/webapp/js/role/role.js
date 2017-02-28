var $table = $('#role_table'),
    $remove = $('#remove'),
    selections = [],
	validator;
//初始化表格       
function initTable() {
    $table.bootstrapTable({
    	  icons: {
				  paginationSwitchDown: 'glyphicon-collapse-down icon-chevron-down',
				  paginationSwitchUp: 'glyphicon-collapse-up icon-chevron-up',
				  refresh: 'glyphicon-refresh icon-refresh',
				  toggle: 'glyphicon-list-alt icon-list-alt',
				  columns: 'glyphicon-th icon-th',
				  detailOpen: 'glyphicon-chevron-down icon-plus',
				  detailClose: 'glyphicon-chevron-up icon-minus'
				},
        columns: [{
	                field: 'state',
	                checkbox: true,
	                align: 'center',
	                valign: 'middle'
	            },
                {
                    field: 'roleUuid',
                    title: 'RoleUUID',
                    sortable: true,
                    align: 'center'
                }, {
                    field: 'roleName',
                    title: '角色名',
                    sortable: true,
                    align: 'center',
                }, {
                    field: 'rolePlatformName',
                    title: '所属平台',
                    sortable: true,
                    align: 'center',
                }, {
                	field: 'roleStatusName',
                	title: '角色状态',
                	sortable: true,
                	align: 'center',
                },{
                	field: 'roleAddTime',
                	title: '增加时间',
                	sortable: true,
                	align: 'center'
                },{
                	field: 'roleEditTime',
                	title: '修改时间',
                	sortable: true,
                	align: 'center'
                },{
                	field: 'roleAddByName',
                	title: '增加人',
                	align: 'center'
                },{
                	field: 'roleEditByName',
                	title: '修改人',
                	align: 'center'
                }, {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    events: operateEvents,
                    formatter: operateFormatter
                }
            ]
    });
    
    $table.on('check.bs.table uncheck.bs.table ' +
        'check-all.bs.table uncheck-all.bs.table', function () {
        $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);
        // save your data, here just save the current page
        selections = getIdSelections();
        // push or splice the selections if you want to save all data selections
    });
}
        
function getIdSelections() {
    return $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.roleUuid;
    });
}
        
//详情
function detailFormatter(index, row) {
    var html = [];
    html.push('<p><b>' + 'roleUUID' + ':</b> ' + row.roleUuid + '</p>');
    html.push('<p><b>' + '角色名' + ':</b> ' + row.roleName + '</p>');
    html.push('<p><b>' + '角色状态' + ':</b> ' + row.roleStatusName + '</p>');
    html.push('<p><b>' + '所属平台' + ':</b> ' + row.rolePlatformName + '</p>');
    html.push('<p><b>' + '增加时间' + ':</b> ' + row.roleAddTime + '</p>');
    html.push('<p><b>' + '修改时间' + ':</b> ' + row.roleEditTime + '</p>');
    html.push('<p><b>' + '增加人' + ':</b> ' + row.roleAddByName + '</p>');
    html.push('<p><b>' + '修改人' + ':</b> ' + row.roleEditByName+ '</p>');
    return html.join('');
}
//操作:删除,编辑
function operateFormatter(value, row, index) {
    return [
        '<a class="edit" href="javascript:void(0)" title="编辑">',
        '<i class="fa fa-edit"></i>',
        '</a>  　',
        '<a class="remove" href="javascript:void(0)" title="删除">',
        '<i class="fa fa-remove"></i>',
        '</a>'  ,
        '<a class="grant" href="javascript:void(0)" title="分配资源">',
        '<i class="fa fa-cogs"></i>',
        '</a>'
    ].join('');
}
window.operateEvents = {
    'click .edit': function (e, value, row, index) {
    	//重置校验
    	validator.resetForm();
    	//初始化编辑
        $("#addPageTitle").text("编辑角色");
        $("#addForm").attr("action","/role/edit");
        $("#save").text("修改");
        $("#addPage").attr("sign","edit");
        $("#addPage").modal("show");
        if($("#roleuuid").length <= 0){
        	uuidInput = '<input id="roleuuid" name="roleUuid" class="form-control" type="hidden" value="'+row.roleUuid+'">';
            $("#addForm").append(uuidInput);
        }
        else{
        	$("#roleuuid").val(row.roleUuid);
        }
        $.each(row, function(key, value){
        		$("#"+key).val(value);
        });
    },
    'click .remove': function (e, value, row, index) {
    		var param = {};
    		param.roleUuid = row.roleUuid;
    	    swal({
    	        title: "您确定要删除这条信息吗",
    	        text: "删除后将无法恢复，请谨慎操作！",
    	        type: "warning",
    	        showCancelButton: true,
    	        confirmButtonColor: "#DD6B55",
    	        confirmButtonText: "删除",
    	        cancelButtonText: "取消",
    	        closeOnConfirm: false
    	    }, function () {
			    	    	$.ajax({  
			    	  	       url: "/role/delete",  
			    	  	       dataType: "json", 
			    	  	       data: param,
			    	  	       success: function (data) {  
			    	  	    	  if(data.code == 0 && data.resObject == 1){
			    	  	    		  	$table.bootstrapTable('refresh');
			    	  					swal({
			    	 	    					title: "",
			    	 	    					text: "删除成功",
			    	 	    					type: "success"
			    	 	    				});
			    	  	    	  }
			    	  	    	  else{
			    	 	 	    		 swal({
			    	 	 					title: "",
			    	 	 					text: "发生错误",
			    	 	 					type: "error"
			    	 	 				});
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
    },
    
    'click .grant': function (e, value, row, index) {
    	var uuid = '<input id="grand_role_uuid" name="roleUuid" class="form-control" type="hidden" value="'+row.roleUuid+'">';
    	if($("#grand_role_uuid").length <= 0){
    		$("#grandForm").append(uuid);
    	}
    	else{
    		$("#grand_role_uuid").val(row.roleUuid);
    	}
    	
    	var param = {};
    	param.resourcePlatformUuid = row.rolePlatformUuid;
    	$.ajax({  
    	       url: "/resource/getListByPlatform",  
    	       dataType: "json",  
    	       data: param,
    	       success: function (data) {  
    	    	   $("#selectAll").attr("checked",false);
    	    	   $("#resourceList").html("");
    	    	   $.each(data, function (key, value) {  
    	    		   $("#resourceList").append('<label><input class="resour" name="resourceList" type="checkbox" value="'+value['resourceUuid']+'"/> <i>'+value['resourceName']+'</i></label>');
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
    	$("#grantResource").modal("show");
    }
};




//验证
function validate(){
	// validate signup form on keyup and submit
	var icon = "<i class='fa fa-times-circle'></i> ";
	validator = $("#addForm").validate({
			    rules: {
			    	rolePlatformUuid:{
			    	 	required:true
			    	},
			    	roleName:{
			    		required: true,
			    		checkRoleName: true,
			    		rangelength:[2,16],
			    		checkRoleNameRepeat: true
			    	},
			    	roleStatusName:{
			    		required: true
			    	}
			       
			    },
			    messages: {
			    	rolePlatformUuid:{
			    		required: icon+"平台是必填项"
			    	},
			    	roleName:{
			    		required: icon+"角色名是必填项",
			    		rangelength:icon+"长度必须在2-16个字符"
			    	},
			    	roleStatusName:{
			    		required: icon+"平台域名是必填项"
			    	}
			    },
			    submitHandler: function(form){
			    	$(form).ajaxSubmit({
			    		type: "POST",
			    		dataType: "json",
			    		success: function(data){
			    			if(data.code == 0 && data.resObject == 1){
			    				$("#addPage").modal('hide');
			    				$table.bootstrapTable('refresh');
			    				if($("#addPage").attr("sign")  == 'add')
			    				{
			    					swal({
				    					title: "",
				    					text: "增加成功",
				    					type: "success"
				    				});
			    				}
			    				else if($("#addPage").attr("sign")  == 'edit'){
			    					swal({
				    					title: "",
				    					text: "修改成功",
				    					type: "success"
				    				});
			    				}
			    				else{
			    					swal({
				    					title: "",
				    					text: "发生错误",
				    					type: "error"
				    				});
			    				}
			    			}
			    			else{
			    				swal({
			    					title: "",
			    					text: "发生错误",
			    					type: "error"
			    				});
			    			}
			    		}
			    	});
			    }
			});

	$.validator.addMethod("checkRoleNameRepeat",function(value,element,params){  
		var param = {};
		param.roleName = value;
		param.rolePlatformUuid = $("#rolePlatformUuid").val();
		if($("#roleuuid").length > 0){
			param.roleUuid = $("#roleuuid").val();
		}
		var result;
		$.ajax({  
	       url: "/role/isRepeat",  
	       dataType: "json", 
	       data: param,
	       async:false,
	       success: function (data) {  
	    	   if(typeof data == 'boolean'){
	    		  result=data;
	    	   }
	    	   else{
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
		return this.optional(element)||!result;  
    },icon+"该平台已存在该角色名");  
	
	$.validator.addMethod("checkRoleName",function(value,element,params){  
        var checkPlatName = /^[\u4e00-\u9fa5]{2,16}$/;  
        return this.optional(element)||(checkPlatName.test(value));  
    },icon+"平台名必须是2-16个汉字"); 
}

//初始化下拉列表
function initStatus(){
	$("#rolePlatformUuid").html("");
	$("#roleStatus").html("");
	$.ajax({  
	       url: "/role/getStatusList",  
	       dataType: "json",  
	       success: function (data) {  
	    	   $.each(data, function (key, value) {  
	    			if("roleStatus" == key){
	    		   		$.each(value, function(key, value){
	    		   			$("#roleStatus").append("<option value="+value+">" +key + "</option>");
	    		   		});
	    		   	}
	    		   	if("platform" == key){
	    		   		$.each(value, function(key, value){
	    		   			$("#rolePlatformUuid").append("<option value="+value+">" +key + "</option>");
	    		   		});
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

//验证授权表单
function validateGrandAuth(){
	var grandvalidator = $("#grandForm").validate({
	    submitHandler: function(form){
	    	$(form).ajaxSubmit({
	    		type: "POST",
	    		dataType: "json",
	    		success: function(data){
	    			if(data == "success"){
	    				swal({
	    					title: "",
	    					text: "成功",
	    					type: "success"
	    				});
	    			}
	    			else{
	    				swal({
	    					title: "",
	    					text: "发生错误",
	    					type: "error"
	    				});
	    			}
	    			$("#grantResource").modal("hide");
	    		}
	    	});
	    }
	});
}


        
$(function () {
	
   initTable();
   initStatus();
   validate();
   validateGrandAuth();
   
   $("#save").on("click",function(){
	   $("#addForm").submit();
   });
   $("#grantResourceSave").on("click",function(){
	   $("#grandForm").submit();
   })
   
   $("#addButton").on("click",function(){
	   validator.resetForm();
	   $("#addPageTitle").text("增加角色");
	    $("#addForm").attr("action","/role/add");
	    $("#save").text("保存");
	    $("#addPage").attr("sign","add");
	    $("#addPage").modal("show");
	    if($("#roleuuid").length > 0){
	    	$("#roleuuid").remove();
        }
	    $("#roleName").val("");
	    initStatus();
   });
   $remove.on("click", function(){
	   swal({
	        title: "您确定要删除这"+selections.length+"条信息吗",
	        text: "删除后将无法恢复，请谨慎操作！",
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "删除",
	        cancelButtonText: "取消",
	        closeOnConfirm: false
	    }, function () {
				    $.ajax({  
					       url: "/role/deleteBatch",  
					       type: "POST",
					       dataType: "json",  
					       contentType:"application/json",
					       data: JSON.stringify(selections), 
					       success: function (data) {  
					    	  if(data.code == 0 && data.resObject == selections.length){
					    		  $table.bootstrapTable('refresh');
					    		  swal({
					    			  title: "",
					    			  text: "删除成功",
					    			  type: "success"
					    		  });
					    	  }
					    	  else{
					    		  swal({
					    			  title: "",
					    			  text: "删除失败",
					    			  type: "error"
					    		  })
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
   
  
});


