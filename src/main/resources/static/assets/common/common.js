$(function () {
    $(".add").on('click', function () {
        window.location.href = '/apis/eduShow/addClass.html';
    })
    $(".am-btn-xs").on('click', function () {
        var val = $(this).text();
        if (val == "提交保存") {
            saveClass();
        } else {

        }
    })
})