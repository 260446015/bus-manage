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
$(function (){
    pageSize = 10;
    pageNum = 1;
    HttpUtil.getAll(url,pageSize,pageNum);
})
function initPage() {
    $(".delete").on('click', function () {
        let id = $(this).parents("tr").find('input[type=checkbox]').eq(0).val();
        HttpUtil.delete(url,id);
    })
    $(".add").on('click',function (){
        $('.attr').each(function (i,item){
            item.value = null;
        })
        $('#add').modal('show');
    })
    $('.search').on('click',function () {
        var value = $(this).parent().prev().val();
        let key = $('#query').val();
        HttpUtil.getAll(url+"?"+key+"="+value,pageSize,pageNum);
    })
    $(".am-btn-xs").on('click', function () {
        let val = $(this).text();
        if (val == "提交保存") {
            save();
        } else {

        }
    })
}

const HttpUtil = {
    get: function (url){
        $.get(url,function (res){
            $('.attr').each(function (i, item) {
                for(let k in res.data ){
                    let name = item.name;
                    console.log(name)
                    //遍历packJson 对象的每个key/value对,k为key
                    if (k !== item.name) {
                        continue;
                    }
                    if (item.type === 'radio') {
                        if (item.value === res.data[k].toString()) {
                            item.checked = true;
                        }
                    } else if (item.type === 'select-one') {
                        $(item).children().each(function (i, optionEle) {
                            if (optionEle.innerHTML === res.data[k].toString()) {
                                optionEle.selected = true;
                            }
                        })
                    } else {
                        item.value = res.data[k];
                    }
                }
            })
        })
    },
    getAll: function (url,_pageSize,_pageNum){
        let req = {"ps": _pageSize, "pn": _pageNum};
        $.ajax({
            type: 'get',
            data: req,
            url: url,
            success: function (res) {
                if (res.code === 0) {
                    let html = '';
                    let arr = res.data.dataList;
                    for (let i = 0; i < arr.length; i++) {
                        let tdHtml = '';
                        for(let k in arr[i]){
                            if(k === 'id'){
                                tdHtml += '<td>'+(i+1)+'<input type="hidden" value="'+arr[i][k]+'"/></td>';
                            }else{
                                tdHtml += '<td><span name="'+k+'">'+arr[i][k]+'</span></td>';
                            }
                        }
                        html += '<tr><td><input value="'+arr[i].id+'" type="checkbox" /></td>' + tdHtml +
                            '<td><div class="am-btn-toolbar"><div class="am-btn-group am-btn-group-xs"><button type="button" class="am-btn am-btn-default edit" onclick="HttpUtil.showEditTable(this)"><span class="am-icon-pencil-square-o"></span>编辑</button>' +
                            '<button class="am-btn am-btn-default am-btn-xs am-text-danger delete"><span class="am-icon-trash-o"></span> 删除</button></div></div></td></tr>';
                    }
                    $("#data").html(html);
                    if (res.data.totalPage > 1) {
                        page.init(res.data.totalNumber, res.data.number, options);
                        $("#" + page.pageId + ">li[class='pageItem']").on("click", function () {
                            this.getAll(url,$(this).attr("page-data"), pageSize);
                        });
                    } else {
                        $('#page').html("");
                    }
                    initPage();
                }
            }
        })
    },
    post: function (url,successUrl){
        let req = {};
        $(".attr").each(function (i, item) {
            console.log($(this).val())
            if (item.type === "radio") {
                if(item.checked){
                    req[item.name] = true;
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
            url: url,
            success: function (res) {
                alert(res.message);
                if(res.code === 0){
                    window.location.href = successUrl;
                }
            }
        })
    },
    delete: function (url,id){
        $.ajax({
            url: url + '/' + id,
            type: 'delete',
            success: function (res) {
                if (res.code === 0) {
                    window.location.href = successUrl;
                }
            }
        })
    },
    showEditTable: function (e){
        const id = $(e).parents('tr').find('input[type=checkbox]').eq(0).val();
        $('#id').val(id);
        this.get(url+"/"+id);
        $("#add").modal('show');
    }

}