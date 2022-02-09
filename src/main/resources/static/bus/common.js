function initPage() {
    $(".delete").on('click', function () {
        var id = $(this).parents("tr").children().eq(1).html();
        deleteStudent(id);
    })
    $(".add").on('click', function () {
        window.location.href = '/page/';
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