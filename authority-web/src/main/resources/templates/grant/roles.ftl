<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>授权角色</title>
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
                <h5>授权角色</h5>
            </div>
            <div class="ibox-content">
            		<div class="row" style="margin-bottom:20px">
            			<div class="col-md-3">
		                	<select id="platform" name="platform" class="form-control"></select>
		                </div>
		            	<div class= "col-md-3">
		                   <select id="role" name="role" class="form-control"></select> 
		                </div>
		            </div>
					 <div class="row">
					    <div class="col-xs-5">
					        <select name="from[]" id="search" class="form-control" size="8" multiple="multiple">
					        </select>
					    </div>
					    
					    <div class="col-xs-2">
					        <button type="button" id="search_rightAll" class="btn btn-block"><i class="glyphicon glyphicon-forward"></i></button>
					        <button type="button" id="search_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
					        <button type="button" id="search_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
					        <button type="button" id="search_leftAll" class="btn btn-block"><i class="glyphicon glyphicon-backward"></i></button>
					    </div>
					    
					    <div class="col-xs-5">
					        <select name="to[]" id="search_to" class="form-control" size="8" multiple="multiple">
					        </select>
					    </div>
               	 	</div>
               	 	<div  class="col-md-offset-5">
						<button class="btn" id="confirm">确定</button>
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
    <script src="/js/plugins/multiselect/multiselect.js"></script>
    <script src="/js/grant/role.js"></script>

</body>

</html>
