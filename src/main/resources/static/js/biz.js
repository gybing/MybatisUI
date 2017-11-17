function addTable() {
    var table = 
`<div class="column">
	<div class="col-md-4" >
		<label>表名称</label>
		<input type="text" class="form-control" name="tablenames" placeholder="Enter test_table">
	</div>
	<div class="col-md-4" >
		<label>模型名称</label>
		<input type="text" class="form-control"  name="tablemodels" placeholder="Enter TestTable">
	</div>
	<div class="col-md-4" >
		<button type="button" class="btn btn-primary" onclick="addTable()" style="margin-top: 25px">添加表</button>
		<button type="button" class="btn btn-danger" onclick="rmTable(this)" style="margin-top: 25px">删除</button>
	</div>
</div>`;
    $('#tables').append(table);
}

//动态创建表格
function rmTable(table) {
    var length = $('#tables').children().length;
    if (length > 1) {
        $(table).parent().parent().remove();
    } else {
        $('#alertModal').modal({backdrop:"static"}).find(".modal-body").text("table不得少于一个!");
    }
}

//代码生成器
function generator() {
    $('input').each(function (i) {
        var text = $(this).val();
        if (text == "") {
            $('#alertModal').modal({backdrop:"static"}).find(".modal-body").text("信息输入不全!");
            return false;
        }
    });
    $.ajax({
        type: 'post',
        url: '/mybatis/index/generator',
        dataType: 'json',
        timeout: 3000, // 超时时间设置，单位毫秒
        data: $('#mbg').serialize(),
        success: function (data) {
            if (data.code == "00") {
                window.open('result/mybatisUI.zip')
            } else if (data.code == "01") {
                $('#alertModal').modal({backdrop:"static"}).find(".modal-body").text("数据库连接异常!");
            }
        },
        error: function (data) {
            $('#alertModal').modal({backdrop:"static"}).find(".modal-body").text("请检查配置信息是否有误!");
        }

    });
}

//保存配置
function saveConf() {
    var data = JSON.stringify($('#mbg').serialize());
    var key = $('#confname').val();
    localStorage.setItem(key, data);
    location.reload();
}

//读取配置
function readConf() {
    var storage = window.localStorage;
    var menu = '';
    for (var i = 0, len = storage.length; i < len; i++) {
        var key = storage.key(i);
        menu += '<li><a href="#" onclick="writeconf(this)"><i class="fa fa-circle-o"></i>' + key + '</a></li>';
    }
    $('#menu').append(menu);
}

//初始化填写配置
function writeconf(ob) {
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

//清除配置
function clearconf() {
    localStorage.clear();
    $('#alertModal').modal({backdrop:"static"}).find(".modal-body").text("清除成功!");
    location.reload();
}
