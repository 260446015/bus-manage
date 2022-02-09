function saveSystem() {
    checkPassword();
    var req = {};
    $(".form-group").find("input").each(function (i, item) {
        console.log($(this).val())
        if (item.type === "radio") {
            if(item.checked){
                req[item.name] = item.value;
            }
        } else {
            req[item.name] = item.value;
        }
    })
    $("#id").val('');
    $.ajax({
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(req),
        url: '/api/user',
        success: function (res) {
            alert(res.message);
            window.location.href = '/page/bus/showSystem.html';
        }
    })
}

var pageSize = 10;
var pageNum = 1;
var options = {
    "id": "page",//显示页码的元素
    "data": null,//显示数据
    "maxshowpageitem": 5,//最多显示的页码个数
    "pagelistcount": 10,//每页显示数据个数
    "callBack": function () {
    }
};

function showSystem(_pageNum, _pageSize) {
    var req = {"ps": _pageSize, "pn": _pageNum};
    $("#query").children().each(function (i, item) {
        if (item.selected) {
            req[item.value] = $('#search').val();
        }
    })
    $.ajax({
        type: 'get',
        data: req,
        url: '/api/user',
        success: function (res) {
            if (res.code === 0) {
                var html = '';
                var arr = res.data.dataList;
                for (var i = 0; i < arr.length; i++) {
                    var id = arr[i].id.toString();
                    html += '<tr><td><input type="checkbox" /></td><td>' + id + '</td><td>' + arr[i].name + '</a></td>' + '<td>' + arr[i].username + '</td>' +
                        '<td><div class="am-btn-toolbar"><div class="am-btn-group am-btn-group-xs"><button type="button" class="am-btn am-btn-default edit" onclick="showEditTable(this)"><span class="am-icon-pencil-square-o"></span>编辑</button>' +
                        '<button class="am-btn am-btn-default am-btn-xs am-text-danger delete"><span class="am-icon-trash-o"></span> 删除</button></div></div></td></tr>';
                }
                $("#system").html(html);
                if (res.data.totalPage > 1) {
                    page.init(res.data.totalNumber, res.data.number, options);
                    $("#" + page.pageId + ">li[class='pageItem']").on("click", function () {
                        showSystem($(this).attr("page-data"), pageSize);
                    });
                } else {
                    $('#page').html("");
                }
                initPage();
            }
        }
    })
}

function deleteSystem(_id) {
    $.ajax({
        url: '/api/user/' + _id,
        type: 'delete',
        success: function (res) {
            if (res.success) {
                showSystem(pageNum, pageSize);
            }
        }
    })
}

$(function () {
    showSystem(pageNum, pageSize);
})


function initPage() {
    $(".delete").on('click', function () {
        var id = $(this).parents("tr").children().eq(1).html();
        deleteSystem(id);
    })
    $(".am-btn-xs").on('click', function () {
        var val = $(this).text();
        if (val == "提交保存") {
            saveSystem();
        } else {

        }
    })
}

function checkPassword() {
    var checkFlag = $("#password").val() === $("#password-check").val();
    if (checkFlag) {
        return true;
    } else {
        alert("两次密码不一致。");
        return false;
    }
}

function showEditTable(e) {
    var id = $(e).parents('tr').children().eq(1).html();
    $('#id').val(id);
    $.get("/api/user/"+id,function (res) {
        $('#addSystem').find('input').each(function (i, item) {
            for(var k in res.data ){
                //遍历packJson 对象的每个key/value对,k为key
                if(k === item.name){
                    if(item.type === 'radio'){
                        if(item.value === res.data[k].toString()){
                            item.checked = true;
                        }
                    }else if(item.type === 'password'){

                    }else{
                        item.value = res.data[k];
                    }
                }
            }
        })
    })
    $("#addSystem").modal('show');
}