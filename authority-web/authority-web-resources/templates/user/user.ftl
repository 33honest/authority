<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>用户管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

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
                        <div class="btn-group hidden-xs" id="toolbar" role="group">
                        	<button type="button" class="btn btn-outline btn-default"  id="remove" disabled>
                                <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
                            </button>
                            <button type="button" class="btn btn-outline btn-default" data-toggle="modal" id="addButton">
                                <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                            </button>
                        </div>
                        <div class="example">
                        	<table id="user_table"
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
						           data-url="/user/list"
						           data-method="post"
						           data-sort-name="userAddTime"
						           data-sort-order="desc">
                        	</table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal inmodal fade" id="addPage" tabindex="-1" role="dialog"  aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="adddPageTitle">增加用户</h4>
                    </div>
                    <div class="modal-body">
	                        <form class="form-horizontal m-t" id="addForm" action="/platform/add">
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">登录名：</label>
	                                <div class="col-sm-6">
	                                    <input id="userLoginName" name="userLoginName" class="form-control" type="text">
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">登录密码：</label>
	                                <div class="col-sm-6">
	                                    <input id="userLoginPwd" name="userLoginPwd" class="form-control" type="text"/>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">重复密码：</label>
	                                <div class="col-sm-6">
	                                    <input id="reUserLoginPwd" name="reUserLoginPwd" class="form-control" type="text"/>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">邮箱：</label>
	                                <div class="col-sm-6">
	                                   <input class="form-control" id="userEmail" name="userEmail"/><span class="help-block m-b-none">一旦确定不可再修改</span>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">手机：</label>
	                                <div class="col-sm-6">
	                                   <input class="form-control" id="userPhone" name="userPhone"/>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">用户状态：</label>
	                                <div class="col-sm-6">
	                                   <select class="form-control" id="userStatus" name="userStatus"/>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">用户类型：</label>
	                                <div class="col-sm-6">
	                                   <select class="form-control" id="userType" name="userType"/>
	                                </div>
	                            </div>
	                        </form>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" id="save">保存</button>
                    </div>
                </div>
            </div>
        </div>
    <!-- 全局js -->
    <script src="/js/jquery.min.js?v=2.1.4"></script>
    <script src="/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- Bootstrap table -->
    
    <script src="/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    
    <!-- jQuery Validation plugin javascript-->
    <script src="/js/jquery.form.js"></script>
    <script src="/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="/js/plugins/validate/messages_zh.min.js"></script>
    <script src="/js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="/js/user/user.js"></script>

</body>

</html>
