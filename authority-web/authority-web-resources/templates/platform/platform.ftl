<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - Bootstrap Table</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">
    

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>基本</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-wrench"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#">选项1</a>
                        </li>
                        <li><a href="#">选项2</a>
                        </li>
                    </ul>
                    <a class="close-link">
                        <i class="fa fa-times"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <h4 class="example-title">从URL加载</h4>
                        <div id="toolbar">
					        <button id="remove" class="btn btn-danger" disabled>
					            <i class="glyphicon glyphicon-remove"></i> Delete
					        </button>
					    </div>
                        <div class="example">
                        	<table id="platform_table"
                        		   data-toolbar="#toolbar"
						           data-search="true"
						           data-show-refresh="true"
						           data-show-toggle="true"
						           data-show-columns="true"
						           data-show-export="true"
						           data-detail-view="true"
						           data-detail-formatter="detailFormatter"
						           data-minimum-count-columns="2"
						           data-show-pagination-switch="true"
						           data-pagination="true"
						           data-id-field="id"
						           data-page-list="[10, 25, 50, 100, ALL]"
						           data-show-footer="false"
						           data-side-pagination="server"
						           data-url="/platform/list"
						         
						           data-method="post">
                        	</table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
   
    <!-- 全局js -->
    <script src="/js/jquery.min.js?v=2.1.4"></script>
    <script src="/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="/js/content.js?v=1.0.0"></script>


    <!-- Bootstrap table -->
    <script src="/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

    <!-- Peity -->
    <script src="/js/demo/bootstrap-table-demo.js"></script>

     <script>
    	var $table = $('#platform_table'),
        $remove = $('#remove'),
        selections = [];
        initTable();
        
        function initTable() {
	        $table.bootstrapTable({
	            columns: [
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
	                        editable: {
	                            type: 'text',
	                            title: '平台名'
	                        }
	                    }, {
	                    	field: 'platformSign',
	                    	title: '平台标识',
	                    	sortable: true,
	                    	align: 'center',
	                    	editable: {
	                    		type: 'text',
	                    		title: '平台标识',
	                    		validate: validateFun(this.value)
	                    	}
	                    },{
	                    	field: 'platformDomainName',
	                    	title: '平台域名',
	                    	sortable: true,
	                    	align: 'center',
	                    	editable: {
	                    		type: 'text',
	                    		title: '平台域名'
	                    	}
	                    },{
	                    	field: 'platformAddTime',
	                    	title: '增加时间',
	                    	sortable: true,
	                    	align: 'center'
	                    },{
	                    	field: 'platformAddBy',
	                    	title: '增加人',
	                    	sortable: true,
	                    	align: 'center'
	                    },{
	                    	field: 'platformStatus',
	                    	title: '平台状态',
	                    	sortable: true,
	                    	align: 'center',
	                    	editable: {
	                    		type: 'text',
	                    		title: '平台状态'
	                    	}
	                    }
	                ]
	        });
        }
        
        function detailFormatter(index, row) {
	        var html = [];
	        $.each(row, function (key, value) {
	            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
	        });
	        return html.join('');
	    }
        
    	function validateFun(value){
            value = $.trim(value);
            if (!value) {
                return '该字段必须不可为空';
            }
            var data = $table.bootstrapTable('getData'),
                index = $(this).parents('tr').data('index');
            console.log(data[index]);
            return '';
    	}
    	
    </script>
    
    

</body>

</html>
