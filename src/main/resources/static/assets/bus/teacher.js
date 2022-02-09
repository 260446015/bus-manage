function saveTeacher() {
    var name = $("#name").val();
    var phone = $("#phone").val();
    var job = $("#job").val();
    var salary = $("#salary").val();
    var score = $("#score").val();
    var age = $("#age").val();
    var sex = $("#sex option:selected").val();
    var address = $("#address").val();
    var id = $("#id").val();
    var req = {
        'id': id,
        'name': name,
        'phone': phone,
        'job': job,
        'salary': salary,
        'score': score,
        'age': age,
        'sex': sex,
        'address': address
    };
    $.ajax({
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(req),
        url: '/apis/teacher/addTeacher.json',
        success: function (res) {
            alert(res.data);
            window.location.href = '/apis/eduShow/showTeacher.html';
        }
    })
}

var pageSize = 10;
var pageNum = 0;
var options = {
    "id": "page",//显示页码的元素
    "data": null,//显示数据
    "maxshowpageitem": 5,//最多显示的页码个数
    "pagelistcount": 10,//每页显示数据个数
    "callBack": function () {
    }
};

function showTeacher(_pageNum, _pageSize,_searchName) {
    var req = {"pageSize": _pageSize, "pageNum": _pageNum,"searchName":_searchName};
    $.ajax({
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(req),
        async: false,
        url: '/apis/teacher/showTeacher.json',
        success: function (res) {
            if (res.success) {
                var html = '';
                var arr = res.data.content;
                for (var i = 0; i < arr.length; i++) {
                    html += '<tr><td><input type="checkbox" /></td><td>' + arr[i].id + '</td><td><a href="/apis/eduShow/showTeacherDetail.html?id='+arr[i].id+'">' + arr[i].name + '</a></td>' +
                        '<td>' + arr[i].sex + '</td><td>' + arr[i].address + '</td><td>' + arr[i].salary + '</td><td>' + arr[i].score + '</td>' +
                        '<td>' + arr[i].phone + '</td><td>' + arr[i].job + '</td>' +
                        '<td><div class="am-btn-toolbar"><div class="am-btn-group am-btn-group-xs"><button class="am-btn am-btn-default am-btn-xs am-text-secondary edit"><span class="am-icon-pencil-square-o"></span><a href="/apis/eduShow/editTeacher.html?id=' + arr[i].id + '">编辑</a></button>' +
                        '<button class="am-btn am-btn-default am-btn-xs"><span class="am-icon-copy"></span> 复制</button><button class="am-btn am-btn-default am-btn-xs am-text-danger delete"><span class="am-icon-trash-o"></span> 删除</button></div></div></td></tr>';
                }
                $("#teacher").html(html);
                if (res.data.totalPages > 1) {
                    page.init(res.data.totalElements, res.data.number + 1, options);
                    $("#" + page.pageId + ">li[class='pageItem']").on("click", function () {
                        showTeacher($(this).attr("page-data") - 1, pageSize);
                    });
                } else {
                    $('#page').html("");
                }
                initPage();
            }
        }
    })
}

$(function () {
    showTeacher(pageNum, pageSize);
})

function initPage() {
    $(".delete").on('click', function () {
        var id = $(this).parents("tr").children().eq(1).html();
        deleteStudent(id);
    })
    $(".add").on('click', function () {
        window.location.href = '/apis/eduShow/addTeacher.html';
    })
    $(".select").change(function () {
        showTeacher(pageNum,pageSize,$(this).val());
    })
    $(".am-btn-xs").on('click', function () {
        var val = $(this).text();
        if (val == "提交保存") {
            saveTeacher();
        } else {

        }
    })
}