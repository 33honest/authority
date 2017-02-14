var $table = $('#resource_table'),
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
                    field: 'resourceUuid',
                    title: 'ResourceUUID',
                    sortable: true,
                    align: 'center'
                }, {
                    field: 'resourceName',
                    title: '资源名',
                    sortable: true,
                    align: 'center',
                }, {
                	field: 'resourcePlatformName',
                	title: '平台',
                	sortable: true,
                	align: 'center',
                },{
                	field: 'resourceLevel',
                	title: '层级',
                	sortable: true,
                	align: 'center',
                },{
                	field: 'resourceOrder',
                	title: '编码',
                	sortable: true,
                	align: 'center'
                },{
                	field: 'resourceParentName',
                	title: '父级',
                	sortable: true,
                	align: 'center'
                },{
                	field: 'resourceStatusName',
                	title: '状态',
                	align: 'center'
                },{
                	field: 'resourceUrl',
                	title: '链接',
                	align: 'center',
                },{
                	field: 'resourceAddByName',
                	title: '增加人',
                	align: 'center',
                },{
                	field: 'resourceAddTime',
                	title: '增加时间',
                	align: 'center',
                },{
                	field: 'resourceEditByName',
                	title: '编辑人',
                	align: 'center',
                },{
                	field: 'resourceEditTime',
                	title: '编辑时间',
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
        return row.resourceUuid;
    });
}
        
//详情
function detailFormatter(index, row) {
    var html = [];
    html.push('<p><b>' + 'ResourceUUID' + ':</b> ' + row.resourceUuid + '</p>');
    html.push('<p><b>' + '平台' + ':</b> ' + row.resourcePlatformName + '</p>');
    html.push('<p><b>' + '层级' + ':</b> ' + row.resourceLevel + '</p>');
    html.push('<p><b>' + '编码' + ':</b> ' + row.resourceOrder + '</p>');
    html.push('<p><b>' + '父级' + ':</b> ' + row.resourceParentName + '</p>');
    html.push('<p><b>' + '状态' + ':</b> ' + row.resourceStatusName + '</p>');
    html.push('<p><b>' + '链接' + ':</b> ' + row.resourceUrl+ '</p>');
    html.push('<p><b>' + '增加人' + ':</b> ' + row.resourceAddByName+ '</p>');
    html.push('<p><b>' + '增加时间' + ':</b> ' + row.resourceAddTime+ '</p>');
    html.push('<p><b>' + '编辑人' + ':</b> ' + row.resourceEditByName+ '</p>');
    html.push('<p><b>' + '编辑时间' + ':</b> ' + row.resourceEditTime+ '</p>');
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
        $("#addPageTitle").text("编辑资源");
        $("#addForm").attr("action","/resource/edit");
        $("#save").text("修改");
        $("#addPage").attr("sign","edit");
        $("#addPage").modal("show");
        if($("#resource_uuid").length <= 0){　
        	uuidInput = '<input id="resource_uuid" name="resourceUuid" class="form-control" type="hidden" value="'+row.resourceUuid+'">';
            $("#addForm").append(uuidInput);
        }
        else{
        	$("#resource_uuid").val(row.resourceUuid);
        }
        $.each(row, function(key, value){
        	$("#"+key).val(value);
        });
    },
    'click .remove': function (e, value, row, index) {
    		var param = {};
    		param.resourceUuid = row.resourceUuid;
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
			    	  	       url: "/resource/delete",  
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
			    	resourceName:{
			    	 	required:true,
			    	 	checkResourceName: true,
			    	 	rangelength:[2,25],
			    	 	
			    	},
			    	resourceUrl:{
			    		required: true,
			    		checkURL: true,
			    		rangelength:[1,128]
			    	}
			    },
			    messages: {
			    	platformName:{
			    		required: icon+"资源名是必填项",
			    		rangelength:icon+"长度必须在2-25个字符"
			    	},
			    	resourceUrl:{
			    		required: icon+"资源URL是必填项",
			    		rangelength:icon+"长度必须在1-128个字符"
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

	
	    
	$.validator.addMethod("checkResourceName",function(value,element,params){  
        var checkPlatName = /^[\u4e00-\u9fa5]{2,25}$/;  
        return this.optional(element)||(checkPlatName.test(value));  
    },icon+"平台名必须是2-25个汉字"); 
	
	$.validator.addMethod("checkURL",function(value,element,params){  
	    var checkURL = /(^\/)?([\w\/]*)(\/)?$/;                                                                                 
	    return this.optional(element)||(checkURL.test(value));  
	},icon+"不符合资源URL格式");
}

//初始化下拉列表
function initStatus(){
	$("#resourcePlatformUuid").html("");
	$("#resourceParentUuid").html("");
	$("#resourceStatus").html("");
	$("#resourceLevel").html("");
	$("#resourceOrder").html("");
	for(var i=1; i<=100 ; i++){ 
		$("#resourceLevel").append("<option value="+i+">" +i + "</option>");  
		$("#resourceOrder").append("<option value="+i+">" +i + "</option>");  
	}
	var param = {};
	//资源层级
	param.resourceLevel = $("#resourceLevel").val();
	$.ajax({  
	       url: "/resource/initSelect",  
	       dataType: "json",  
	       data: param,
	       success: function (data) {  
	    	   $.each(data, function (key, value) {  
	    		   var keyval = key != undefined;
	    		   if(keyval && "parent" == key){
	    		   		$.each(value, function(key, value){
	    		   			$("#resourceParentUuid").append("<option value="+value+">" +key + "</option>");
	    		   		});
	    		   	}
	    		   	if(keyval && "platform" == key){
	    		   		$.each(value, function(key, value){
	    		   			$("#resourcePlatformUuid").append("<option value="+value+">" +key + "</option>");
	    		   		});
	    		   	}
	    		   	if(keyval && "resourceStatus" == key){
	    		   		$.each(value, function(key, value){
	    		   			$("#resourceStatus").append("<option value="+value+">" +key + "</option>");
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

function changeByChange(){
	var param = {};
	param.resourcePlatformUuid = $("#resourcePlatformUuid").val(); 
	param.resourceLevel = $("#resourceLevel").val();
	$.ajax({
		url: "/resource/changeBychange",  
	       dataType: "json",  
	       data: param,
	       success: function (data) { 
	    	   $.each(data, function (key, value) {  
	    		   if("parent" == key){
		   		   		$.each(value, function(key, value){
		   		   			$("#resourceParentUuid").append("<option value="+value+">" +key + "</option>");
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
        
$(function () {
	
   initTable();
   initStatus();
   validate();
   
   $("#resourcePlatformUuid").on("change", function(){
	   changeByChange();
   }); 
   
   $("#resourceLevel").on("change", function(){
	   changeByChange();
   });
   
   $("#save").on("click",function(){
	   $("#addForm").submit();
   })
   $("#addButton").on("click",function(){
	   validator.resetForm();
	   $("#addPageTitle").text("增加资源");
	    $("#addForm").attr("action","/resource/add");
	    $("#save").text("保存");
	    $("#addPage").attr("sign","add");
	    $("#addPage").modal("show");
	    if($("#resource_uuid").length > 0){
	    	$("#resource_uuid").remove();
        }
	    initStatus();
	    $("#resourceName").val("");
	    $("#resourceUrl").val("");
	    $("#resourceCssCode").val("");
	    
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
					       url: "/resource/deleteBatch",  
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


