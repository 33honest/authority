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
   
});

// validate signup form on keyup and submit
var icon = "<i class='fa fa-times-circle'></i> ";
$("#addForm").validate({
    rules: {
    	platformName:{
    	 	required:true,
    	 	
    	}
       
    },
    messages: {
        firstname: icon + "请输入你的姓",
        lastname: icon + "请输入您的名字",
        username: {
            required: icon + "请输入您的用户名",
            minlength: icon + "用户名必须两个字符以上"
        },
        password: {
            required: icon + "请输入您的密码",
            minlength: icon + "密码必须5个字符以上"
        },
        confirm_password: {
            required: icon + "请再次输入密码",
            minlength: icon + "密码必须5个字符以上",
            equalTo: icon + "两次输入的密码不一致"
        },
        email: icon + "请输入您的E-mail",
        agree: {
            required: icon + "必须同意协议后才能注册",
            element: '#agree-error'
        }
    }
});
