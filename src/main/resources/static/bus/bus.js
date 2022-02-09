function saveBus() {
    var req = {};
    $(".attr").each(function (i, item) {
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
        url: '/api/bus',
        success: function (res) {
            alert(res.message);
            window.location.href = '/page/bus/showBus.html';
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

function showBus(_pageNum, _pageSize) {
    var req = {"ps": _pageSize, "pn": _pageNum};
    $("#query").children().each(function (i, item) {
        if (item.selected) {
            req[item.value] = $('#search').val();
        }
    })
    $.ajax({
        type: 'get',
        data: req,
        url: '/api/bus',
        success: function (res) {
            if (res.code === 0) {
                var html = '';
                var arr = res.data.dataList;
                for (var i = 0; i < arr.length; i++) {
                    var id = arr[i].id.toString();
                    html += '<tr><td><input type="checkbox" /></td><td>' + id + '</td><td>' + arr[i].name + '</td><td>'+arr[i].busNum+'</td><td>'+arr[i].busType+'</td><td>'+arr[i].colorEnum+'</td><td>'+arr[i].busStatus+'</td><td>'+arr[i].online+'</td>' +
                        '<td><div class="am-btn-toolbar"><div class="am-btn-group am-btn-group-xs"><button type="button" class="am-btn am-btn-default edit" onclick="showEditTable(this)"><span class="am-icon-pencil-square-o"></span>编辑</button>' +
                        '<button class="am-btn am-btn-default am-btn-xs am-text-danger delete"><span class="am-icon-trash-o"></span> 删除</button></div></div></td></tr>';
                }
                $("#Bus").html(html);
                if (res.data.totalPage > 1) {
                    page.init(res.data.totalNumber, res.data.number, options);
                    $("#" + page.pageId + ">li[class='pageItem']").on("click", function () {
                        showBus($(this).attr("page-data"), pageSize);
                    });
                } else {
                    $('#page').html("");
                }
                initPage();
            }
        }
    })
}

function deleteBus(_id) {
    $.ajax({
        url: '/api/bus/' + _id,
        type: 'delete',
        success: function (res) {
            if (res.success) {
                showBus(pageNum, pageSize);
            }
        }
    })
}

$(function () {
    showBus(pageNum, pageSize);
})


function initPage() {
    $(".delete").on('click', function () {
        var id = $(this).parents("tr").children().eq(1).html();
        deleteBus(id);
    })
    $(".am-btn-xs").on('click', function () {
        var val = $(this).text();
        if (val == "提交保存") {
            saveBus();
        } else {

        }
    })
}

function showEditTable(e) {
    var id = $(e).parents('tr').children().eq(1).html();
    $('#id').val(id);
    $.get("/api/bus/"+id,function (res) {
        $('.attr').each(function (i, item) {
            for(var k in res.data ){
                //遍历packJson 对象的每个key/value对,k为key
                if(k === item.name){
                    if(item.type === 'radio'){
                        if(item.value === res.data[k].toString()){
                            item.checked = true;
                        }
                    }else if(item.type === 'select-one'){
                        $(item).children().each(function (i, optionEle) {
                            if(optionEle.innerHTML === res.data[k].toString()){
                                optionEle.selected = true;
                            }
                        })
                    }else{
                        item.value = res.data[k];
                    }
                }
            }
        })
    })
    $("#add").modal('show');
}