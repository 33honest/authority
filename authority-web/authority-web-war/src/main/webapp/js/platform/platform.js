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
                    field: 'platformUuid',
                    title: 'PlatformUUID',
                    sortable: true,
                    align: 'center'
                }, {
                    field: 'platformName',
                    title: '平台名',
                    sortable: true,
                    align: 'center',
                }, {
                	field: 'platformSign',
                	title: '平台标识',
                	sortable: true,
                	align: 'center',
                },{
                	field: 'platformDomainName',
                	title: '平台域名',
                	sortable: true,
                	align: 'center',
                },{
                	field: 'platformAddTime',
                	title: '增加时间',
                	sortable: true,
                	align: 'center'
                },{
                	field: 'platformEditTime',
                	title: '修改时间',
                	sortable: true,
                	align: 'center'
                },{
                	field: 'platformAddByName',
                	title: '增加人',
                	align: 'center'
                },{
                	field: 'platformStatusName',
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
        return row.platformUuid;
    });
}
        
//详情
function detailFormatter(index, row) {
    var html = [];
    html.push('<p><b>' + 'PlatformUuid' + ':</b> ' + row.platformUuid + '</p>');
    html.push('<p><b>' + '平台名' + ':</b> ' + row.platformName + '</p>');
    html.push('<p><b>' + '平台标识' + ':</b> ' + row.platformSign + '</p>');
    html.push('<p><b>' + '平台域名' + ':</b> ' + row.platformDomainName + '</p>');
    html.push('<p><b>' + '增加时间' + ':</b> ' + row.platformAddTime + '</p>');
    html.push('<p><b>' + '修改时间' + ':</b> ' + row.platformEditTime + '</p>');
    html.push('<p><b>' + '增加人' + ':</b> ' + row.platformAddByName+ '</p>');
    html.push('<p><b>' + '平台状态' + ':</b> ' + row.platformStatusName+ '</p>');
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
        $("#adddPageTitle").text("编辑平台");
        $("#addForm").attr("action","/platform/edit");
        $("#save").text("修改");
        $("#addPage").attr("sign","edit");
        $("#addPage").modal("show");
        if($("#platformuuid").length <= 0){
        	uuidInput = '<input id="platformuuid" name="platformUuid" class="form-control" type="hidden" value="'+row.platformUuid+'">';
            $("#addForm").append(uuidInput);
        }
        else{
        	$("#platformuuid").val(row.platformUuid);
        }
        $.each(row, function(key, value){
        	if(key == "platformSign" || key == "platformDomainName"){
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
    		param.platformUuid = row.platformUuid;
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
			    	platformName:{
			    	 	required:true,
			    	 	checkPlatName: true,
			    	 	rangelength:[2,25],
			    	 	
			    	},
			    	platformSign:{
			    		required: true,
			    		checkPlatSign: true,
			    		rangelength:[2,32],
			    		checkSignRepeat: true
			    	},
			    	platformDomainName:{
			    		required: true,
			    		checkDomain: true,
			    		checkDomainRepeat: true
			    	}
			       
			    },
			    messages: {
			    	platformName:{
			    		required: icon+"平台名是必填项",
			    		rangelength:icon+"长度必须在2-25个字符"
			    	},
			    	platformSign:{
			    		required: icon+"平台标识是必填项",
			    		rangelength:icon+"长度必须在2-32个字符"
			    	},
			    	platformDomainName:{
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

	$.validator.addMethod("checkDomainRepeat",function(value,element,params){  
		var param = {};
		param.platformDomainName = value;
		var result;
		$.ajax({  
	       url: "/platform/isRepeat",  
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
	
	$.validator.addMethod("checkSignRepeat",function(value,element,params){  
		var param = {};
		param.platformSign = value;
		var result;
		$.ajax({  
		       url: "/platform/isRepeat",  
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
	    },icon+"已存在该平台标识");  
	    
	$.validator.addMethod("checkPlatName",function(value,element,params){  
        var checkPlatName = /^[\u4e00-\u9fa5]{2,25}$/;  
        return this.optional(element)||(checkPlatName.test(value));  
    },icon+"平台名必须是2-25个汉字"); 
	
	$.validator.addMethod("checkPlatSign",function(value,element,params){  
	    var checkPlatSign = /(^[A-Za-z]+_?[A-Za-z]+)+$/;                                                                                 
	    return this.optional(element)||(checkPlatSign.test(value));  
	},icon+"平台标识必须是字母或大小写组成的字符串，下划线不可开头和结尾");

	$.validator.addMethod("checkDomain",function(value,element,params){  
	    var checkDomain = /^((http:\/\/)|(https:\/\/))?([a-zA-Z0-9]([a-zA-Z0-9\-]{0,61}[a-zA-Z0-9])?\.)+[a-zA-Z]{2,6}/;  
	    return this.optional(element)||(checkDomain.test(value));  
	},icon+"平台域名不符合域名格式");
	
}

//初始化下拉列表
function initStatus(){
	$("#platformStatus").html("");
	$.ajax({  
	       url: "/platform/getStatusList",  
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
	   $("#adddPageTitle").text("增加平台");
	    $("#addForm").attr("action","/platform/add");
	    $("#save").text("保存");
	    $("#addPage").attr("sign","add");
	    $("#addPage").modal("show");
	    if($("#platformuuid").length > 0){
	    	$("#platformuuid").remove();
        }
	    $("#platformName").val("");
	    $("#platformSign").val("");
	    $("#platformSign").attr("disabled",false);
	    $("#platformDomainName").val("");
	    $("#platformDomainName").attr("disabled",false);
	    $("#platformStatus").val(3);
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


