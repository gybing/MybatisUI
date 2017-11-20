function addTable() {
    var table =
        `<div class="column">
            <div class="col-md-4" >
                <label>表名称</label>
                <input type="text" class="form-control" name="tableNames" placeholder="请输入表明">
            </div>
            <div class="col-md-4" >
                <label>实体名称</label>
                <input type="text" class="form-control"  name="tableModels" placeholder="表明对应的实体名称">
            </div>
            <div class="col-md-4" >
                <button type="button" class="btn btn-primary" onclick="addTable()" style="margin-top: 25px">添加表</button>
                <button type="button" class="btn btn-danger" onclick="removeTable(this)" style="margin-top: 25px">删除表</button>
            </div>
        </div>`;
    $('#tables').append(table);
}

// 动态创建表格
function removeTable(table) {
    var length = $('#tables').children().length;
    if (length > 1) {
        $(table).parent().parent().remove();
    } else {
        $('#alertModal').modal({backdrop: "static"}).find(".modal-body").text("表名称不得少于一个!");
    }
}

// 代码生成器
function generator() {
	// FIXME 写得有点挫
	var flag = true;
    $('input').each(function (i) {
        var text = $(this).val();
        if (text == "") {
            $('#alertModal').modal({backdrop: "static"}).find(".modal-body").text("信息输入不全!");
            flag = false;
        }
    });
    if(flag){
	    $.ajax({
	        type: 'post',
	        url: '/mybatis/generator/code',
	        dataType: 'json',
	        data: $('#mbg').serialize(),
	        timeout: 3000,
	        success: function (data) {
	            if (data.code == "00") {
	                window.location.href = 'result/mybatisUI.zip';
	            } else if (data.code == "01") {
	                $('#alertModal').modal({backdrop: "static"}).find(".modal-body").text("数据库连接异常!");
	            }
	        },
	        error: function (data) {
	            $('#alertModal')
		            .modal({backdrop: "static"})
		            .find(".modal-body")
		            .html("请检查配置信息是否有误!<br/>1、数据库地址是否有误！<br/>2、数据库类型是否有误！<br/>3、数据库端口号是否有误！<br/>3、用户名密码是否有误！");
	        }
	    });
    }
}

// 保存配置
function saveConf() {
    var data = JSON.stringify($('#mbg').serialize());
    var key = $('#confname').val();
    localStorage.setItem(key, data);
    location.reload();
}

// 读取配置
function readConf() {
    var storage = window.localStorage;
    var menu = '',
    	$menu = $('#menu');
    for (var i = 0, len = storage.length; i < len; i++) {
        var key = storage.key(i);
        menu += '<li><a href="#" onclick="writeConf(this)"><i class="fa fa-circle-o"></i>' + key + '</a></li>';
    }
    $menu.append(menu);
    // 设置 菜单打开
    if(storage.length){
    		$("li.treeview").addClass("active");
    		$menu.addClass("menu-open");
	}
}

// 初始化填写配置
function writeConf(ob) {
    var index = $(ob).index();
    var key = $(ob).eq(index).text();
    var value = localStorage.getItem(key);
    value = value.replace('"', '');
    var arrys = value.split('&');
    for (var i = 0, l = arrys.length; i < l; i++) {
        var tmp = arrys[i].split('=');
        if (tmp[0] == "tablemodels" || tmp[0] == "tablenames") {
            continue;
        }
        $('#' + tmp[0]).val(tmp[1]);
    }
}

// 清除配置 提示框
function clearConfirm() {
    $('#confirmModal').modal({backdrop: "static"});
}

// 清除消息
function clearConf() {
    localStorage.clear();
    location.reload();
}

// 数据库链接测试
function linkTest() {
	// FIXME 写得有点挫
	var flag = true;
	$('#ip,#db,#port,#userName,#passWord').each(function (i) {
        var text = $(this).val();
        if (text == "") {
            $('#alertModal').modal({backdrop: "static"}).find(".modal-body").text("数据库链接基本信息不全！");
            flag = false;
        }
    });
	if(flag){
	    $.ajax({
	        type: 'post',
	        url: '/mybatis/generator/linkTest',
	        dataType: 'json',
	        data: $('#mbg').serialize(),
	        timeout: 3000,
	        success: function (data) {
	            if (data.code == 1) {
	            	$('#alertModal').modal({backdrop: "static"}).find(".modal-body").text("数据库连接成功，请继续你的操作");
	            } else {
	                $('#alertModal').modal({backdrop: "static"}).find(".modal-body").text("数据库连接异常，请查看信息是否正确!");
	            }
	        },
	        error: function (data) {
	            $('#alertModal')
		            .modal({backdrop: "static"})
		            .find(".modal-body")
		            .html("请检查配置信息是否有误!<br/>1、数据库地址是否有误！<br/>2、数据库类型是否有误！<br/>3、数据库端口号是否有误！<br/>3、用户名密码是否有误！");
	        }
	    });
	}
}

