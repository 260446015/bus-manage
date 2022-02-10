function showSelectBusModal(){
    $.get("/api/bus?online=false",function (res){
        let html = '<table class="table bus-table">';
        let arr = res.data.dataList;
        let tdHtml = '';
        for (let i = 0; i < arr.length; i++) {
            for (let k in arr[i]) {
                if (k === 'id') {
                    tdHtml += '<td>' + (i + 1) + '<input type="hidden" value="' + arr[i][k] + '"/></td>';
                } else {
                    tdHtml += '<td><span name="' + k + '">' + arr[i][k] + '</span></td>';
                }
            }
            html += '<tr></tr><td><input value="' + arr[i].id + '" type="checkbox" /></td>' + tdHtml+'</tr>';
        }
        html += '</table>'
        $(".content").html(html);
        $('#selectBus').modal('show');

        /*let html = '';
        let arr = res.data.dataList;
        for (let i = 0; i < arr.length; i++) {
            html += '<li><td>'+arr[i].name+'</td>&nbsp;&nbsp;&nbsp;&nbsp;<td>'+arr[i].busType+'</td></li>'
        }
        $('.dropdown-menu').html(html);*/
    })
}

function showSelectDriverModal(){
    $.get("/api/driver?online=false",function (res){
        let html = '<table class="table driver-table">';
        let arr = res.data.dataList;
        for (let i = 0; i < arr.length; i++) {
            let tdHtml = '';
            for (let k in arr[i]) {
                if (k === 'id') {
                    tdHtml += '<td>' + (i + 1) + '<input type="hidden" value="' + arr[i][k] + '"/></td>';
                } else {
                    tdHtml += '<td><span name="' + k + '">' + arr[i][k] + '</span></td>';
                }
            }
            html += '<tr><td><input value="' + arr[i].id + '" type="checkbox" /></td>' + tdHtml+'</tr>';
        }
        html += '</table>'
        $(".driver-content").html(html);
        $('#selectDriver').modal('show');
    })
}

function selectDriver(){
    let checkBox = $('.driver-table').find("input[type=checkbox]:checked");
    if(checkBox.length > 1){
        alert("请不要选择多条记录")
    }
    let id = checkBox.parents('tr').find("input[type=checkbox]").eq(0).val();
    let name = checkBox.parents('tr').find("span[name=name]").eq(0).html();
    let busNum = checkBox.parents('tr').find("span[name=busNum]").eq(0).html();
    let busType = checkBox.parents('tr').find("span[name=busType]").eq(0).html();
    $('.closeBtn').click();
    $('#add').find('input[name=name]').eq(0).val(name);
    $('#add').find('input[name=driverId]').eq(0).val(id);
}

function selectBus(){
    let checkBox = $('.bus-table').find("input[type=checkbox]:checked");
    if(checkBox.length > 1){
        alert("请不要选择多条记录")
    }
    let id = checkBox.parents('tr').find("input[type=checkbox]").eq(0).val();
    let name = checkBox.parents('tr').find("span[name=name]").eq(0).html();
    let busNum = checkBox.parents('tr').find("span[name=busNum]").eq(0).html();
    let busType = checkBox.parents('tr').find("span[name=busType]").eq(0).html();
    $('.closeBtn').click();
    $('#add').find('input[name=busName]').eq(0).val(name);
    $('#add').find('input[name=busNum]').eq(0).val(busNum);
    $('#add').find('input[name=busType]').eq(0).val(busType);
    $('#add').find('input[name=busId]').eq(0).val(id);
    // $('#name').val(name);
}