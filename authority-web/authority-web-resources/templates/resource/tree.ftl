<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>资源管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<meta charset="utf-8">
    <style>
	body {
	  text-align: center;
	}
	#main {
	 
	  height:1000px;
	}
      .node {
        cursor: pointer;
      }

      .node circle {
        fill: #fff;
        stroke: steelblue;
        stroke-width: 1.5px;
      }

      .node text {
        font: 10px sans-serif;
      }

      .link {
        fill: none;
        stroke: #ccc;
        stroke-width: 1.5px;
      }

      #example {
		  display: flex;
		  max-width: 1000px;
		  margin: 0 auto;
		}
		
		#d3view {
		  overflow: auto;
		  height:800px;
		  width:1000px
		}
		
		#editme {
		  float: left;
		  height: 800px;
		  overflow: auto;
		}
		
		#editme{
		  text-align: left;
		  width: 600px;
		  border-right: 1px solid #ccc;
		  padding-right: 10px;
		}
		
		
    </style>
    <link rel="stylesheet" href="/js/plugins/treed/index.css"/>
    <link rel="shortcut icon" href="favicon.ico"> <link href="/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

</head>

<body class="gray-bg">
<div id = "main">
<input type="hidden" id="plat_uuid" value = "${platform_uuid}"/>
<div id="example">
        <div id="editme"></div>
        <div id="d3view"></div>
    </div>
</div>
    <!-- crud js -->
    <script src="/js/resource/crud.js"></script>
    <!-- treed js -->
    <script src="/js/plugins/treed/d3.v3.min.js"></script>
    <script src="/js/plugins/treed/default-node.js"></script>

    <script src="/js/plugins/treed/commands.js"></script>
    <script src="/js/plugins/treed/dom-vl.js"></script>
    <script src="/js/plugins/treed/controller.js"></script>
    <script src="/js/plugins/treed/model.js"></script>
    <script src="/js/plugins/treed/view.js"></script>
    <script src="/js/plugins/treed/util.js"></script>

    <script src="/js/plugins/treed/index.js"></script>
    <script src="/js/plugins/treed/flare-data.js"></script>
    <script src="/js/plugins/treed/d3tree.js"> </script>
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
    <script src="/js/resource/tree.js"></script>

</body>

</html>
