var $table = $('#user_table'),
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
                    field: 'userUuid',
                    title: 'UserUUID',
                    sortable: true,
                    align: 'center'
                }, {
                    field: 'userLoginName',
                    title: '登录名',
                    sortable: true,
                    align: 'center',
                }, {
                	field: 'userEmail',
                	title: '邮箱',
                	sortable: true,
                	align: 'center',
                },{
                	field: 'userPhone',
                	title: '电话',
                	sortable: true,
                	align: 'center',
                },{
                	field: 'userStatusName',
                	title: '用户状态',
                	sortable: true,
                	align: 'center'
                },{
                	field: 'userAddByName',
                	title: '增加人',
                	sortable: true,
                	align: 'center'
                },{
                	field: 'userAddTime',
                	title: '增加时间',
                	align: 'center'
                },{
                	field: '编辑人',
                	title: 'userEditByName',
                	align: 'center',
                },{
                	field: '编辑时间',
                	title: 'userEditTime',
                	align: 'center',
                },{
                	field: '用户类型',
                	title: 'userTypeName',
                	align: 'center',
                },{
                	field: '添加用户方式',
                	title: 'userAddTypeName',
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
        return row.userUuid;
    });
}
        
//详情
function detailFormatter(index, row) {
    var html = [];
    html.push('<p><b>' + 'UserUUID' + ':</b> ' + row.userUuid + '</p>');
    html.push('<p><b>' + '登录名' + ':</b> ' + row.userLoginName + '</p>');
    html.push('<p><b>' + '邮箱' + ':</b> ' + row.userEmail + '</p>');
    html.push('<p><b>' + '手机' + ':</b> ' + row.userPhone + '</p>');
    html.push('<p><b>' + '用户状态' + ':</b> ' + row.userStatusName + '</p>');
    html.push('<p><b>' + '增加人' + ':</b> ' + row.userAddByName + '</p>');
    html.push('<p><b>' + '增加时间' + ':</b> ' + row.userAddTime+ '</p>');
    html.push('<p><b>' + '编辑人' + ':</b> ' + row.userEditByName+ '</p>');
    html.push('<p><b>' + '编辑时间' + ':</b> ' + row.userEditTime+ '</p>');
    html.push('<p><b>' + '用户类型' + ':</b> ' + row.userTypeName+ '</p>');
    html.push('<p><b>' + '添加用户方式' + ':</b> ' + row.userAddTypeName+ '</p>');
    return html.join('');
}
//操作:删除,编辑
function operateFormatter(value, row, index) {
    return [
        '<a class="edit" href="javascript:void(0)" title="edit">',
        '<i class="glyphicon glyphicon-edit"></i>',
        '</a>  　',
        '<a class="remove" href="javascript:void(0)" title="Remove">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
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
        $("#addPage").modal("show");
        if($("#user_uuid").length <= 0){
        	uuidInput = '<input id="user_uuid" name="userUuid" class="form-control" type="hidden" value="'+row.userUuid+'">';
            $("#addForm").append(uuidInput);
        }
        else{
        	$("#user_uuid").val(row.userUuid);
        }
        $.each(row, function(key, value){
        	if(key == "userEmail"){
        		$("#"+key).val(value);
        		$("#"+key).attr("disabled",true);
        	}
        	else{
        		$("#"+key).val(value);
        	}
        });
    },
    'click .remove': function (e, value, row, index) {
    		var param = {};
    		param.userUuid = row.userUuid;
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
    }
};


//验证
function validate(){
	// validate signup form on keyup and submit
	var icon = "<i class='fa fa-times-circle'></i> ";
	validator = $("#addForm").validate({
			    rules: {
			    	userLoginName:{
			    	 	checkLoginName: true,
			    	 	rangelength:[2,64],
			    	 	checkLoginRepeat: true
			    	},
			    	userLoginPwd:{
			    		required: true,
			    		checkPwd: true,
			    		rangelength:[6,20]
			    	},
			    	reUserLoginPwd:{
			    		required: true,
			    		checkPwd: true,
			    		rangelength:[6,20],
			    		equalTo:"#userLoginPwd"
			    	},
			    	userEmail:{
			    		required: true,
			    		checkEmail: true,
			    		checkEmailRepeat: true
			    	},
			    	userPhone:{
			    		checkPhone: true,
			    		checkPhoneRepeat: true
			    	},
			    	userStatus:{
			    		required: true,
			    		range: [1,3]
			    	},
			    	userType:{
			    		required: true,
			    		range: [1,2]
			    	}
			       
			    },
			    messages: {
			    	userLoginName:{
			    		rangelength:icon+"长度必须在2-64个字符"
			    	},
			    	userLoginPwd:{
			    		required: icon+"登录密码是必填项",
			    		rangelength:icon+"长度必须在6-20个字符"
			    	},
			    	reUserLoginPwd:{
			    		required: icon+"重复密码是必填项",
			    		rangelength: icon+"长度必须在6-20个字符",
			    		equalTo: icon+"两次输入密码不符"
			    	},
			    	userEmail:{
			    		required: icon+"邮箱是必填项"
			    	},
			    	userStatus:{
			    		required: icon+"用户状态是必填项",
			    		range: icon+"输入项非法"
			    	},
			    	userType:{
			    		required: icon+"用户类型是必填项",
			    		range: icon+"输入项非法"
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

			$.validator.addMethod("checkPhoneRepeat",function(value,element,params){  
				var param = {};
				param.userPhone = value;
				var result;
				$.ajax({  
			       url: "/user/isRepeat",  
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
		    },icon+"已存在该手机号码");  
			
	$.validator.addMethod("checkLoginRepeat",function(value,element,params){  
		var param = {};
		param.userLoginName = value;
		var result;
		$.ajax({  
	       url: "/user/isRepeat",  
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
    },icon+"已存在该域名");  
	
	$.validator.addMethod("checkEmailRepeat",function(value,element,params){  
		var param = {};
		param.userEmail = value;
		var result;
		$.ajax({  
		       url: "/user/isRepeat",  
		       dataType: "json", 
		       data: param,
		       async:false,
		       success: function (data) {  
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
		return this.optional(element)||!result;  
	    },icon+"已存在该邮箱账号");  
	    
	$.validator.addMethod("checkLoginName",function(value,element,params){  
        var loginNameRegex = /^(?!_)(?!.*?_$)[a-zA-Z0-9_]+$/;  
        return this.optional(element)||(loginNameRegex.test(value));  
    },icon+"登录名必须由字母数字下划线组合，下划线不可开头与结尾"); 
	
	$.validator.addMethod("checkPwd",function(value,element,params){  
	    var pwdRegex = /^\\w+$/;                                                                                 
	    return this.optional(element)||(pwdRegex.test(value));  
	},icon+"必须由字母数字下划线自由组合");

	$.validator.addMethod("checkEmail",function(value,element,params){  
	    var emailRegex = /\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*/;  
	    return this.optional(element)||(emailRegex.test(value));  
	},icon+"不符合邮箱格式");
	
	$.validator.addMethod("checkPhone",function(value,element,params){  
	    var phoneRegex = /(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{7,14}/;  
	    return this.optional(element)||(phoneRegex.test(value));  
	},icon+"不符合手机号码格式");
}

//初始化下拉列表
function initStatus(){
	$("#userStatus").html("");
	$("#userType").html("");
	$.ajax({  
	       url: "/user/getSelectList",  
	       dataType: "json",  
	       success: function (data) {  
	    	   $.each(data, function (key, value) {  
	    		   
	    	        $("#platformStatus").append("<option value="+value+">" +key + "</option>");  
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

        
$(function () {
	
   initTable();
   initStatus();
   validate();
   
   $("#save").on("click",function(){
	   $("#addForm").submit();
   })
   $("#addButton").on("click",function(){
	   validator.resetForm();
	   $("#addPageTitle").text("增加增加用户");
	    $("#addForm").attr("action","/user/add");
	    $("#save").text("保存");
	    $("#addPage").attr("sign","add");
	    $("#addPage").modal("show");
	    if($("#user_uuid").length > 0){
	    	$("#user_uuid").remove();
        }
	    $("#userLoginName").val("");
	    $("#userLoginPwd").val("");
	    $("#reUserLoginPwd").val("");
	    $("#userEmail").val("");
	    $("#userEmail").attr("disabled",false);
	    $("#userPhone").val("");
	    $("#userStatus").val(1);
	    $("#userType").val(1);
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


