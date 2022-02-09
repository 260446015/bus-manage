function saveStudent() {
    var school = $("#school").val();
    var gradeLevel = $("#gradeLevel").val();
    var nomalClass = $("#nomalClass").val();
    var name = $("#name").val();
    var phone = $("#phone").val();
    var address = $("#address").val();
    var description = $("#description").val();
    var age = $("#age").val();
    var sex = $("#sex").val();
    var id = $("#id").val();
    var course = new Array();
    $("select").each(function (i) {
        if (i > 0) {
            if ($(this).val() != '' && $(this).val() != null && $(this).val() != '---')
                course.push($(this).val());
        }
    })
    var req = {
        'id':id,
        'school': school,
        'age': age,
        'sex': sex,
        'gradeLevel': gradeLevel,
        'nomalClass': nomalClass,
        'name': name,
        'phone': phone,
        'address': address,
        'description': description,
        'course': course
    }
    $.ajax({
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(req),
        url: '/apis/student/addStudent.json',
        success: function (res) {
            alert(res.data);
            window.location.href = '/apis/eduShow/showStudent.html';
        }
    })
}

var pageSize = 10;
var pageNum = 0;
var options={
    "id":"page",//显示页码的元素
    "data":null,//显示数据
    "maxshowpageitem":5,//最多显示的页码个数
    "pagelistcount":10,//每页显示数据个数
    "callBack":function(){}
};
function showStudent(_pageNum,_pageSize) {
    var req = {"pageSize":_pageSize,"pageNum":_pageNum};
    $.ajax({
        type:'post',
        contentType:'application/json',
        data:JSON.stringify(req),
        async:false,
        url:'/apis/student/showStudent.json',
        success:function (res) {
            if(res.success){
                var html = '';
                var arr = res.data.content;
                for(var i= 0;i<arr.length;i++){
                    html += '<tr><td><input type="checkbox" /></td><td>'+arr[i].id+'</td><td><a href="/apis/eduShow/showStudentDetail.html?id='+arr[i].id+'">'+arr[i].name+'</a></td>' +
                            '<td>'+arr[i].school+'</td><td>'+arr[i].gradeLevel+'</td><td>'+arr[i].nomalClass+'</td><td>'+arr[i].phone+'</td>' +
                            '<td><div class="am-btn-toolbar"><div class="am-btn-group am-btn-group-xs"><button class="am-btn am-btn-default am-btn-xs am-text-secondary edit"><span class="am-icon-pencil-square-o"></span><a href="/apis/eduShow/editStudent.html?id='+arr[i].id+'">编辑</a></button>' +
                            '<button class="am-btn am-btn-default am-btn-xs am-text-danger delete"><span class="am-icon-trash-o"></span> 删除</button></div></div></td></tr>';
                }
                $("#student").html(html);
                if(res.data.totalPages>1){
                    page.init(res.data.totalElements,res.data.number+1,options);
                    $("#"+page.pageId +">li[class='pageItem']").on("click",function(){
                        showStudent($(this).attr("page-data")-1,pageSize);
                    });
                }else{
                    $('#page').html("");
                }
                initPage();
            }
        }
    })
}

function deleteStudent(_id) {
    $.ajax({
        url:'/apis/student/deleteStudent.json?id='+_id,
        success:function (res) {
            if(res.success){
                showStudent(pageNum,pageSize);
            }
        }
    })
}

$(function () {
    showStudent(pageNum,pageSize);
})


function initPage() {
    $(".delete").on('click',function () {
        var id = $(this).parents("tr").children().eq(1).html();
        deleteStudent(id);
    })
    $(".add").on('click', function () {
        window.location.href = '/apis/eduShow/addStudent.html';
    })
    $(".am-btn-xs").on('click', function () {
        var val = $(this).text();
        if (val == "提交保存") {
            saveStudent();
        } else {

        }
    })
}