var $table = $('#user_table'),
    $remove = $('#remove'),
    selections = [],
	validator,
	divPass = 	'<div id="divPass" class="form-group">' +
				    '<label class="col-sm-3 control-label">登录密码：</label>' +
				    '<div class="col-sm-6">' +
				        '<input id="user_login_password" name="user_login_password" class="form-control" type="password"/>' +
				    '</div>' +
				'</div>' +
				'<div id="divrePass" class="form-group">' +
				    '<label class="col-sm-3 control-label">重复密码：</label>' +
				    '<div class="col-sm-6">' +
				        '<input id="re_user_login_password" name="re_user_login_password" class="form-control" type="password"/>' +
				    '</div>' +
				'</div>';

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
                    field: 'user_uuid',
                    title: 'UserUUID',
                    sortable: true,
                    align: 'center'
                }, {
                    field: 'user_login_name',
                    title: '登录名',
                    sortable: true,
                    align: 'center',
                }, {
                	field: 'user_email',
                	title: '邮箱',
                	sortable: true,
                	align: 'center',
                },{
                	field: 'user_phone',
                	title: '电话',
                	sortable: true,
                	align: 'center',
                },{
                	field: 'user_status_name',
                	title: '用户状态',
                	sortable: true,
                	align: 'center'
                },{
                	field: 'user_add_by_name',
                	title: '增加人',
                	sortable: true,
                	align: 'center'
                },{
                	field: 'user_add_time',
                	title: '增加时间',
                	align: 'center'
                },{
                	field: 'user_edit_by_name',
                	title: '编辑人',
                	align: 'center',
                },{
                	field: 'user_edit_time',
                	title: '编辑时间',
                	align: 'center',
                },{
                	field: 'user_type_name',
                	title: '用户类型',
                	align: 'center',
                },{
                	field: 'user_add_type_name',
                	title: '添加用户方式',
                	align: 'center',
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
        return row.user_uuid;
    });
}
        
//详情
function detailFormatter(index, row) {
    var html = [];
    html.push('<p><b>' + 'UserUUID' + ':</b> ' + row.user_uuid + '</p>');
    html.push('<p><b>' + '登录名' + ':</b> ' + row.user_login_name + '</p>');
    html.push('<p><b>' + '邮箱' + ':</b> ' + row.user_email + '</p>');
    html.push('<p><b>' + '手机' + ':</b> ' + row.user_phone + '</p>');
    html.push('<p><b>' + '用户状态' + ':</b> ' + row.user_status_name + '</p>');
    html.push('<p><b>' + '增加人' + ':</b> ' + row.user_add_by_name + '</p>');
    html.push('<p><b>' + '增加时间' + ':</b> ' + row.user_add_time+ '</p>');
    html.push('<p><b>' + '编辑人' + ':</b> ' + row.user_edit_by_name+ '</p>');
    html.push('<p><b>' + '编辑时间' + ':</b> ' + row.user_edit_time+ '</p>');
    html.push('<p><b>' + '用户类型' + ':</b> ' + row.user_type_name+ '</p>');
    html.push('<p><b>' + '添加用户方式' + ':</b> ' + row.user_add_type_name+ '</p>');
    return html.join('');
}
//操作:删除,编辑
function operateFormatter(value, row, index) {
    return [
        '<a class="edit" href="javascript:void(0)" title="编辑">',
        '<i class="fa fa-edit"></i>',
        '</a>  ',
        '<a class="remove" href="javascript:void(0)" title="删除">',
        '<i class="fa fa-remove"></i>',
        '</a>  '
    ].join('');
}
window.operateEvents = {
    'click .edit': function (e, value, row, index) {
    	//重置校验
    	validator.resetForm();
    	//初始化编辑
        $("#addPageTitle").text("编辑用户");
        $("#addForm").attr("action","/user/edit");
        $("#save").text("修改");
        $("#addPage").attr("sign","edit");
        if($("#user_uuid").length <= 0){
        	uuidInput = '<input id="user_uuid" name="user_uuid" class="form-control" type="hidden" value="'+row.user_uuid+'">';
            $("#addForm").append(uuidInput);
        }
        else{
        	$("#user_uuid").val(row.userUuid);
        }
        
       if($("#divPass").length > 0){
        	$("#divPass").remove();
        	$("#divrePass").remove();
        }
        $.each(row, function(key, value){
        	if(key == "user_email"){
        		$("#"+key).val(value);
        		$("#"+key).attr("disabled",true);
        	}
        	else{
        		$("#"+key).val(value);
        	}
        });
        $("#addPage").modal("show");
    },
    'click .remove': function (e, value, row, index) {
    		var param = {};
    		param.user_uuid = row.user_uuid;
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
			    	  	       url: "/user/delete",  
			    	  	       dataType: "json", 
			    	  	       data: param,
			    	  	       success: function (res) {  
			    	  	    	   var data = $.parseJSON(res)
			    	  	    	  if(data.success){
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
    	var uuid = '<input id="grand_user_uuid" name="urUserUuid" class="form-control" type="hidden" value="'+row.userUuid+'">';
    	if($("#grand_user_uuid").length <= 0){
    		$("#grandForm").append(uuid);
    	}else{
    		$("#grand_user_uuid").val(row.userUuid);
    	}
    	$.ajax({  
	  	       url: "/platform/getList",  
	  	       dataType: "json", 
	  	       data: null,
	  	       success: function (data) {  
	  	    	 $("#platform").html("");
	  	    	 $.each(data, function (key, value) {  
	  	    		$("#platform").append("<option value="+value['platformUuid']+">" +value['platformName'] + "</option>");
	  	    	 });
	  	    	changByPlatform();
	  	       },  
	  	       error: function (XMLHttpRequest, textStatus, errorThrown) {  
	  	    	  swal({
	 					title: "",
	 					text: "发生错误",
	 					type: "error"
	 				});
	  	       }  
	  	   });  
    	$("#grantRole").modal("show");
    }
};


//验证
function validate(){
	// validate signup form on keyup and submit
	var icon = "<i class='fa fa-times-circle'></i> ";
	validator = $("#addForm").validate({
			    rules: {
			    	user_login_name:{
			    	 	checkLoginName: true,
			    	 	rangelength:[2,64],
			    	 	checkLoginRepeat: true
			    	},
			    	user_login_password:{
			    		required: true,
			    		checkPwd: true,
			    		rangelength:[6,20]
			    	},
			    	re_user_login_password:{
			    		required: true,
			    		checkPwd: true,
			    		rangelength:[6,20],
			    		equalTo:"#user_login_password"
			    	},
			    	user_email:{
			    		required: true,
			    		checkEmail: true,
			    		checkEmailRepeat: true
			    	},
			    	user_phone:{
			    		checkPhone: true,
			    		checkPhoneRepeat: true
			    	},
			    	user_status:{
			    		required: true,
			    		range: [1,3]
			    	},
			    	user_type:{
			    		required: true,
			    		range: [1,2]
			    	}
			       
			    },
			    messages: {
			    	user_login_name:{
			    		rangelength:icon+"长度必须在2-64个字符"
			    	},
			    	user_login_password:{
			    		required: icon+"登录密码是必填项",
			    		rangelength:icon+"长度必须在6-20个字符"
			    	},
			    	re_user_login_password:{
			    		required: icon+"重复密码是必填项",
			    		rangelength: icon+"长度必须在6-20个字符",
			    		equalTo: icon+"两次输入密码不符"
			    	},
			    	user_email:{
			    		required: icon+"邮箱是必填项"
			    	},
			    	user_status:{
			    		required: icon+"用户状态是必填项",
			    		range: icon+"输入项非法"
			    	},
			    	user_type:{
			    		required: icon+"用户类型是必填项",
			    		range: icon+"输入项非法"
			    	}
			    },
			    submitHandler: function(form){
			    	$(form).ajaxSubmit({
			    		type: "POST",
			    		dataType: "json",
			    		success: function(res){
			    			var data = $.parseJSON(res);
			    			if(data.success){
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

			$.validator.addMethod("checkPhoneRepeat",function(value,element,params){  
				var param = {};
				param.user_phone = value;
				if($("#user_uuid").length > 0){
					param.user_uuid = $("#user_uuid").val();
				}
				var result;
				$.ajax({  
			       url: "/user/isRepeat",  
			       dataType: "json", 
			       data: param,
			       async:false,
			       success: function (res) {  
			    	   var data = $.parseJSON(res);
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
				return this.optional(element)||result;  
		    },icon+"已存在该手机号码");  
			
	$.validator.addMethod("checkLoginRepeat",function(value,element,params){  
		var param = {};
		param.user_login_name = value;
		if($("#user_uuid").length > 0){
			param.user_uuid = $("#user_uuid").val();
		}
		var result;
		$.ajax({  
	       url: "/user/isRepeat",  
	       dataType: "json", 
	       data: param,
	       async:false,
	       success: function (res) {  
	    	   var data = $.parseJSON(res);
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
		return this.optional(element)||result;  
    },icon+"已存在该登录名");  
	
	$.validator.addMethod("checkEmailRepeat",function(value,element,params){  
		var param = {};
		param.user_email = value;
		if($("#user_uuid").length > 0){
			param.user_uuid = $("#user_uuid").val();
		}
		var result;
		$.ajax({  
		       url: "/user/isRepeat",  
		       dataType: "json", 
		       data: param,
		       async:false,
		       success: function (res) {  
		    	   var data = $.parseJSON(res);
		    	   if(typeof data == 'boolean'){
		    		   result=data;
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
		return this.optional(element)||result;  
	    },icon+"已存在该邮箱账号");  
	    
	$.validator.addMethod("checkLoginName",function(value,element,params){  
        var loginNameRegex = /^(?!_)(?!.*?_$)[a-zA-Z0-9_]+$/;  
        return this.optional(element)||(loginNameRegex.test(value));  
    },icon+"登录名必须由字母数字下划线组合，下划线不可开头与结尾"); 
	
	$.validator.addMethod("checkPwd",function(value,element,params){  
	    var pwdRegex = /^\w+$/;                                                                                 
	    return this.optional(element)||(pwdRegex.test(value));  
	},icon+"必须由字母数字下划线自由组合");

	$.validator.addMethod("checkEmail",function(value,element,params){  
	    var emailRegex = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;  
	    return this.optional(element)||(emailRegex.test(value));  
	},icon+"不符合邮箱格式");
	
	$.validator.addMethod("checkPhone",function(value,element,params){  
	    var phoneRegex = /(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}/;  
	    return this.optional(element)||(phoneRegex.test(value));  
	},icon+"不符合手机号码格式");
}

//初始化下拉列表
function initStatus(){
	$("#user_status").html("");
	$("#user_type").html("");
	$.ajax({  
	       url: "/user/getSelectList",  
	       dataType: "json",  
	       success: function (res) {  
	    	   var data = $.parseJSON(res);
	    	   $.each(data, function (key, value) {  
	    		   	if("user_status" == key){
	    		   		$.each(value, function(key, value){
	    		   			$("#user_status").append("<option value="+value+">" +key + "</option>");
	    		   		});
	    		   	}
	    		   	if("user_type" == key){
	    		   		$.each(value, function(key, value){
	    		   			$("#user_type").append("<option value="+value+">" +key + "</option>");
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

function changByPlatform(){
	var param = {};
	param.rolePlatformUuid = $("#platform").val();
	$.ajax({  
	       url: "/role/getListByPlatform",  
	       dataType: "json",  
	       data: param,
	       success: function (data) {  
	    	   $("#roleList").html("");
	    	   $.each(data, function (key, value) {  
	    		   $("#roleList").append('<label><input name="roleList" type="checkbox" value="'+value['roleUuid']+'"/> <i>'+value['roleName']+'</i></label>');
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
	    			$("#grantRole").modal("hide");
	    		}
	    	});
	    }
	});
}


        
$(function () {
	
   initTable();
   initStatus();
   validate();
//   validateGrandAuth();
//   $("#platform").on("change",function(){
//	   changByPlatform();
//   });
   $("#save").on("click",function(){
	   $("#addForm").submit();
   })
//   $("#grantRoleSave").on("click",function(){
//	   $("#grandForm").submit();
//   })
   $("#addButton").on("click",function(){
	   validator.resetForm();
	   $("#addPageTitle").text("增加用户");
	    $("#addForm").attr("action","/user/add");
	    $("#save").text("保存");
	    $("#addPage").attr("sign","add");
	    if($("#user_uuid").length > 0){
	    	$("#user_uuid").remove();
        }
	    if($("#divPass").length <= 0){
	    	 $(divPass).insertAfter($("#divEmail"));
        }
	    $("#user_login_name").val("");
	    $("#user_login_password").val("");
	    $("#re_user_login_password").val("");
	    $("#user_email").val("");
	    $("#user_email").attr("disabled",false);
	    $("#user_login_name").attr("disabled",false);
	    $("#user_phone").attr("disabled",false);
	    $("#user_phone").val("");
	    $("#user_status").val(1);
	    $("#user_type").val(1);
	    $("#addPage").modal("show");
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
					       url: "/user/deleteBatch",  
					       type: "POST",
					       dataType: "json",  
					       contentType:"application/json",
					       data: JSON.stringify(selections), 
					       success: function (res) {  
					    	   var data = $.parseJSON(res);
					    	  if(data.success){
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


