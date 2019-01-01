<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="form-group">
    <button onclick="exportData();" value="下载">下载</button>
</div>
<div class="form-group">
    <li>
        <span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;传：</span>
        <span class="input">
    <input type="file" id="upfile" name="upfile" placeholder=""/>
    <input type="hidden" id="a" name="a" placeholder="" value="1"/>
</span>
        <button onclick="importExp();">导入</button>
        <span>格式：.xls</span>
    </li>
</div>
<!--bootstrap-table begin  -->
<div class="form-group">
    <div class="">
        <table class="table" id="grid">

        </table>
    </div>
</div>
<!--bootstrap-table end  -->
<!-- bootstrap 时间选择器开始 -->
<div class="form-group">
    <label class="col-md-5 control-label">开始时间</label>
    <div class="col-md-7">
        <div class="input-group date">
            <input type="text" readonly class="form-control" id="datetimeStart" name="datetimeStart">
            <span class="input-group-btn">
        <button class="btn default date-picker" type="button">
            <i class="fa fa-calendar"></i>
        </button>
    </span>
        </div>
    </div>
</div>
<div class="form-group">
    <label class="col-md-5 control-label">结束时间</label>
    <div class="col-md-7">
        <div  class="input-group date">
            <input  type="text" readonly class="form-control" id="datetimeEnd" name="datetimeEnd">
            <span class="input-group-btn" >
        <button class="btn default date-picker" type="button">
            <i class="fa fa-calendar"></i>
        </button>
    </span>
        </div>
    </div>
</div>
<!-- bootstrap 时间选择器结束 -->
<script>
    $(document).ready(function () {
        testDataTable();

        $("#datetimeStart").datetimepicker({
            format         : 'yyyy-mm-dd',
            minView        : 2,
            language       : 'zh-CN',
            autoclose      : true,
            startDate      : new Date(),
            pickTime       : true,
            pickerPosition : "bottom-right"

        }).on("click",function () {
            $("#datetimeStart").datetimepicker("setEndDate",$("#datetimeEnd").val());
        }).on("changeDate",function () {
            $("#datetimeStart").datetimepicker("setEndDate",minusDate($("#datetimeEnd").val()));
            $("#datetimeEnd").datetimepicker("setStartDate",addDate($("#datetimeStart").val()));
        });

        $("#datetimeEnd").datetimepicker({
            format         : 'yyyy-mm-dd',
            minView        : 0,
            language       : 'zh-CN',
            autoclose      : true,
            startDate      : new Date(),
            pickTime       : true,
            pickerPosition : "bottom-right"
        }).on("click",function () {
            $("#datetimeEnd").datetimepicker("setStartDate",$("#datetimeStart").val());
        }).on("changeDate",function () {
            $("#datetimeStart").datetimepicker("setEndDate",minusDate($("#datetimeEnd").val()));
            $("#datetimeEnd").datetimepicker("setStartDate",addDate($("#datetimeStart").val()));
        });
    });
   /* function exportData() {
        var url="/exportData";
        Base.ajax("POST",url,null,null,function(result) {
            alert("success");
            return ;
        });
        window.location.href = url;
    }*/



    function exportData() {
        debugger;
        /*$.ajax({
            type: "get",
            url: "/exportData",
            async: true,
            success: function (msg) {

            },
            error: function (msg) {

            },
            complete: function (XMLHttpRequest, textStatus) {
                //session超时
                /!*   var sessionStatus = XMLHttpRequest.getResponseHeader("sessionStatus");*!/

            }, beforeSend:function(xhr){
                console.log("正在进行，请稍候");
            },
        });
        window.location.href = "/exportData";*/
        Base.downloadFile("/exportData",null);
    }


    function importExp() {
        var formData = new FormData();
        var name = $("#upfile").val();
        formData.append("file",$("#upfile")[0].files[0]);
        formData.append("name",name);
        $.ajax({
            url : '/fileUpLoad',
            type : 'POST',
            async : false,
            data : formData,
            // 告诉jQuery不要去处理发送的数据
            processData : false,
            // 告诉jQuery不要去设置Content-Type请求头
            contentType : false,
            beforeSend:function(xhr){
                xhr.setRequestHeader("Accept-Encoding", "");
                console.log("正在进行，请稍候");
            },
            success : function(responseStr) {
                if(responseStr=="01"){
                    alert("导入成功");
                }else{
                    alert("导入失败");
                }
            }
        });
    }


    function testDataTable() {
        $("#grid").bootstrapTable("destroy");
        var columns = [];
        columns.push([{
            checkbox:true,
            field   : 'resId',
            title   : '',
            valign  : 'middle',
            halign  : 'center',
            width   : 100,
            colspan : 1,
            rowspan : 2
        },{
            field     : '',
            title     : '序号',
            align     : 'center',
            valign    : 'middle',
            halign    : 'center',
            width     : 200,
            colspan   : 1,
            rowspan   : 2,
            formatter : function (value,row,index) {
                return index + 1;
            }
        }, {
            field: 'name',
            title: 'name',
            align: 'center',
            valign: 'middle',
            halign: 'center',
            width: 200,
            colspan: 1,
            rowspan: 2
        }, {
            field: 'sex',
            title: 'sex',
            align: 'center',
            valign: 'middle',
            halign: 'center',
            width: 200,
            colspan: 1,
            rowspan: 2,
            formatter : function (value,row,index) {
                return '<a class="btn blue" onclick="delete(\''+ row.index+'\')" href="javascript:">删除&nbsp;<i class="fa fa-trash"></i></a>';
            }
        }, {
            field: '',
            title: 'time',
            align: 'center',
            valign: 'middle',
            halign: 'center',
            width: 600,
            colspan: 2,
            rowspan: 1,
            formatter : function (value,row,index) {
                return '<img style="height: 15px;width: 30px; src="'+row.showRealPath + '">';
            }
        }],[
            {
                field: 'day',
                title: 'day',
                align: 'center',
                valign: 'middle',
                halign: 'center',
                width: 200
            },{
                field: 'month',
                title: 'month',
                align: 'center',
                valign: 'middle',
                halign: 'center',
                width: 200
            }
        ]);
        var _config = {
            data : [{"name" : "xiao ming","sex" : "man" , "day" : "18" , "month" : "12"},{"name" : "xiao hont","sex" : "woman" , "day" : "18" , "month" : "12"}],
            uniqueId:'resId',
            columns:columns,
            onClickCell:function(field,value,row,$el){
                if(value == null || value == ""){
                    value = 0;
                }
                //当前行号
                var rowIndex = $el.parent()[0].getAttribute("data-index");
                //td 对象
                var thisId = $el[0];
                if(!checkNotNumberic(value) && value != "" && field != ""){
                    if(!$(thisId).is('input')){
                        $(thisId)
                            .addClass("input")
                            .html(
                                '<input type="text" value="'
                                + value
                                + '"class="form-control"'
                                + 'onkeyup="if(isNaN(value))execCommand(\'undo\')" onafterpaste="if(NaN(value))execCommand(\'undo\')"'
                                + '/>'
                            )

                            .find('input')
                            .val("")
                            .focus()
                            .val(value)
                            .blur(function () {
                                var param = {
                                    "newValue" : $(this).val(), //输入的值
                                    "month" : field
                                }

                                if(isMoney($(this).val())){
                                    //使用ajax请求更新
                                    //Base.ajax()
                                    $(this).parent().removeClass('input').html($(this).val() || '');

                                    $('#grid').bootstrapTable("updateCell",{
                                        index : rowIndex,
                                        field : field,
                                        value : param.newValue
                                    });
                                }else{
                                    if ($(this).val() != null || $(this).val() != '0'){
                                        alert("输入有误");
                                    }
                                    $(this).parent().removeClass('input').html(value);
                                }
                            });
                    }
                }
            }
        }

        dataTable('#grid',_config);
        //$("#grid").bootstrapTable("hideColumn","name");
    }

    function dataTable(target,_config) {
        var baseCoffig = {
            classes:"table table-hover",
            striped:true, //是否显示行间隔色
            cache:false // 是否使用缓存 默认为true，所以一般情况下需要设置一下这个属性
        }
        $.extend(baseCoffig,_config);
        $(target).bootstrapTable(baseCoffig);
        $(target).find("thead").addClass("headcss");
    }

    function checkNotNumberic(strObj) {
        return isNaN(strObj);
    }

    function isMoney(s) {
        var regu =  /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
        var re = new RegExp(regu);
        if(re.test(s)){
            return true;
        } else {
            return false;
        }
    }

    /*,
            minView        : 'month',// 0表示分钟(默认),1表示小时,2表示天,3表示月,4表示年
            autoclose      : true,
            startDate      : new Date(),
            pickTime       : true,
            pickerPosition : "bottom-right"*/






    function minusDate(time) {
        if (time !=null && time != undefined && time != ""){
            var date = new Date();
            date.setDate(date.getDate()-1);
            return date;
        } else {
            return time;
        }
    }

    function addDate(time) {
        if (time !=null && time != undefined && time != ""){
            var date = new Date();
            date.setDate(date.getDate()+ 1);
            return date;
        } else {
            return time;
        }
    }
</script>

