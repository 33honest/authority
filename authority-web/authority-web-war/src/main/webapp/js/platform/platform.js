var $table = $('#platform_table'),
    $remove = $('#remove'),
    selections = [];
        
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
                	field: 'platformAddBy',
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
        return row.id
    });
}
        
function detailFormatter(index, row) {
    var html = [];
    $.each(row, function (key, value) {
        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
    });
    return html.join('');
}
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
        alert('You click like action, row: ' + JSON.stringify(row));
    },
    'click .remove': function (e, value, row, index) {
        $table.bootstrapTable('remove', {
            field: 'id',
            values: [row.id]
        });
    }
};

function initStatus(){
	$("#status").html("");
	$.ajax({  
	       url: "/platform/getStatusList",  
	       dataType: "json",  
	       success: function (data) {  
	           $.each(data, function (key, value) {  
	               $("#status").append("<option value="+value+">" +key + "</option>");  
	           });  
	       },  
	       error: function (XMLHttpRequest, textStatus, errorThrown) {  
	           alert("error");  
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
});


function validate(){
	// validate signup form on keyup and submit
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#addForm").validate({
	    rules: {
	    	platformName:{
	    	 	required:true,
	    	 	checkPlatName: true,
	    	 	rangelength:[2,25]
	    	},
	    	platformSign:{
	    		required: true,
	    		checkPlatSign: true,
	    		rangelength:[2,32]
	    	},
	    	platformDomainName:{
	    		required: true,
	    		checkDomain: true
	    	}
	       
	    },
	    messages: {
	    	platformName:{
	    		required: icon+"平台名是必填项"
	    	},
	    	platformSign:{
	    		required: icon+"平台标识是必填项"
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
	    				swal(data.message);
	    			}
	    		}
	    	});
	    }
	});

	//自定义正则表达示验证方法  
	$.validator.addMethod("checkPlatName",function(value,element,params){  
	        var checkPlatName = /^[\u4e00-\u9fa5]{2,25}$/;  
	        return this.optional(element)||(checkPlatName.test(value));  
	    },"平台名必须是2-25个汉字");  
	    
	$.validator.addMethod("checkPlatSign",function(value,element,params){  
	    var checkPlatSign = /(^[A-Za-z]+_?[A-Za-z]{2,32})+$/;                                                                                 
	    return this.optional(element)||(checkPlatSign.test(value));  
	},"平台标识必须是字母或大小写组成的字符串，下划线不可开头");

	$.validator.addMethod("checkDomain",function(value,element,params){  
	    var checkDomain = /^((http:\/\/)|(https:\/\/))?([a-zA-Z0-9]([a-zA-Z0-9\-]{0,61}[a-zA-Z0-9])?\.)+[a-zA-Z]{2,6}/;  
	    return this.optional(element)||(checkDomain.test(value));  
	},"平台域名不符合域名格式");
}


