var $table = $('#platform_table'),
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
                    field: 'platform_uuid',
                    title: 'PlatformUUID',
                    sortable: true,
                    align: 'center'
                }, {
                    field: 'platform_name',
                    title: '平台名',
                    sortable: true,
                    align: 'center',
                }, {
                	field: 'platform_sign',
                	title: '平台标识',
                	sortable: true,
                	align: 'center',
                },{
                	field: 'platform_domain',
                	title: '平台域名',
                	sortable: true,
                	align: 'center',
                },{
                	field: 'platform_add_time',
                	title: '增加时间',
                	sortable: true,
                	align: 'center'
                },{
                	field: 'platform_edit_time',
                	title: '修改时间',
                	sortable: true,
                	align: 'center'
                },{
                	field: 'platform_add_by_name',
                	title: '增加人',
                	align: 'center'
                },{
                	field: 'platform_status_name',
                	title: '平台状态',
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
        return row.platform_uuid;
    });
}
        
//详情
function detailFormatter(index, row) {
    var html = [];
    html.push('<p><b>' + 'PlatformUuid' + ':</b> ' + row.platform_uuid + '</p>');
    html.push('<p><b>' + '平台名' + ':</b> ' + row.platform_name + '</p>');
    html.push('<p><b>' + '平台标识' + ':</b> ' + row.platform_sign + '</p>');
    html.push('<p><b>' + '平台域名' + ':</b> ' + row.platform_domain + '</p>');
    html.push('<p><b>' + '增加时间' + ':</b> ' + row.platform_add_time + '</p>');
    html.push('<p><b>' + '修改时间' + ':</b> ' + row.platform_edit_time + '</p>');
    html.push('<p><b>' + '增加人' + ':</b> ' + row.platform_add_by_name+ '</p>');
    html.push('<p><b>' + '平台状态' + ':</b> ' + row.platform_status_name+ '</p>');
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
        '</a>'
    ].join('');
}
window.operateEvents = {
    'click .edit': function (e, value, row, index) {
    	//重置校验
    	validator.resetForm();
    	//初始化编辑
        $("#addPageTitle").text("编辑平台");
        $("#addForm").attr("action","/platform/edit");
        $("#save").text("修改");
        $("#addPage").attr("sign","edit");
        $("#addPage").modal("show");
        if($("#platform_uuid").length <= 0){
        	uuidInput = '<input id="platform_uuid" name="platform_uuid" class="form-control" type="hidden" value="'+row.platform_uuid+'">';
            $("#addForm").append(uuidInput);
        }
        else{
        	$("#platform_uuid").val(row.platform_uuid);
        }
        $.each(row, function(key, value){
        	$("#"+key).val(value);
        });
    },
    'click .remove': function (e, value, row, index) {
    		var param = {};
    		param.platform_uuid = row.platform_uuid;
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
			    	  	       url: "/platform/delete",  
			    	  	       dataType: "json", 
			    	  	       data: param,
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
			    	platform_name:{
			    	 	required:true,
			    	 	checkPlatName: true,
			    	 	checkPlatformNameRepeat: true,
			    	 	rangelength:[2,25],
			    	 	
			    	},
			    	platform_sign:{
			    		required: true,
			    		checkPlatSign: true,
			    		rangelength:[2,32],
			    		checkSignRepeat: true
			    	},
			    	platform_domain:{
			    		required: true,
			    		checkDomain: true,
			    		checkDomainRepeat: true
			    	}
			       
			    },
			    messages: {
			    	platform_name:{
			    		required: icon+"平台名是必填项",
			    		rangelength:icon+"长度必须在2-25个字符"
			    	},
			    	platform_sign:{
			    		required: icon+"平台标识是必填项",
			    		rangelength:icon+"长度必须在2-32个字符"
			    	},
			    	platform_domain:{
			    		required: icon+"平台域名是必填项"
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
	
	$.validator.addMethod("checkPlatformNameRepeat",function(value,element,params){  
		var param = {};
		param.platform_name = value;
		if($("#platform_uuid").length > 0){
			param.platform_uuid = $("#platform_uuid").val();
		}
		var result;
		$.ajax({  
	       url: "/platform/isRepeat",  
	       dataType: "json", 
	       data: param,
	       async:false,
	       success: function (data) {  
	    	   var data = $.parseJSON(data);
	    	   if(typeof data == "boolean"){
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
		return this.optional(element) || result;  
    },icon+"已存在该平台名");  

	$.validator.addMethod("checkDomainRepeat",function(value,element,params){  
		var param = {};
		param.platform_domain = value;
		if($("#platform_uuid").length > 0){
			param.platform_uuid = $("#platform_uuid").val();
		}
		var result;
		$.ajax({  
	       url: "/platform/isRepeat",  
	       dataType: "json", 
	       data: param,
	       async:false,
	       success: function (data) {  
	    	   var data = $.parseJSON(data);
	    	   if(typeof data == "boolean"){
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
		return this.optional(element) || result;  
    },icon+"已存在该域名");  
	
	$.validator.addMethod("checkSignRepeat",function(value,element,params){  
		var param = {};
		param.platform_sign = value;
		if($("#platform_uuid").length > 0){
			param.platform_uuid = $("#platform_uuid").val();
		}
		var result;
		$.ajax({  
		       url: "/platform/isRepeat",  
		       dataType: "json", 
		       data: param,
		       async:false,
		       success: function (data) {  
		    	   var data = $.parseJSON(data);
		    	   if(typeof data == "boolean"){
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
		return this.optional(element) || result;  
	    },icon+"已存在该平台标识");  
	    
	$.validator.addMethod("checkPlatName",function(value,element,params){  
        var checkPlatName = /^[\u4e00-\u9fa5]{2,25}$/;  
        return this.optional(element)||(checkPlatName.test(value));  
    },icon+"平台名必须是2-25个汉字"); 
	
	$.validator.addMethod("checkPlatSign",function(value,element,params){  
	    var checkPlatSign = /^(?!_)(?!.*?_$)[a-zA-Z_]+$/;                                                                                 
	    return this.optional(element)||(checkPlatSign.test(value));  
	},icon+"平台标识必须是字母或大小写组成的字符串，下划线不可开头和结尾");

	$.validator.addMethod("checkDomain",function(value,element,params){  
	    var checkDomain = /^((http:\/\/)|(https:\/\/))?([a-zA-Z0-9]([a-zA-Z0-9\-]{0,61}[a-zA-Z0-9])?\.)+[a-zA-Z]{2,6}/;  
	    return this.optional(element)||(checkDomain.test(value));  
	},icon+"平台域名不符合域名格式");
	
}

//初始化下拉列表
function initStatus(){
	$("#platform_status").html("");
	$.ajax({  
	       url: "/platform/getStatusList",  
	       dataType: "json",  
	       success: function (res) {  
	    	  var data = $.parseJSON(res);
	    	   $.each(data, function (key, value) {  
	    	        $("#platform_status").append("<option value="+value+">" +key + "</option>");  
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
	   $("#addPageTitle").text("增加平台");
	    $("#addForm").attr("action","/platform/add");
	    $("#save").text("保存");
	    $("#addPage").attr("sign","add");
	    $("#addPage").modal("show");
	    if($("#platform_uuid").length > 0){
	    	$("#platform_uuid").remove();
        }
	    $("#platform_name").val("");
	    $("#platform_name").attr("disabled",false);
	    $("#platform_sign").val("");
	    $("#platform_sign").attr("disabled",false);
	    $("#platform_domain").val("");
	    $("#platform_domain").attr("disabled",false);
	    $("#platform_status").val(3);
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
					       url: "/platform/deleteBatch",  
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


