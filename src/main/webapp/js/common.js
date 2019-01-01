var Base = function () {
    return {
        /**
         * @param type 请求类型 “GET" ,"POST"
         * @param URI  请求地址
         * @param param 请求处理参数
         * @param successCallBack 请求处理成功的回调函数
         * @param errorCallBack 请求处理失败的回调函数
         * @param async 是否异步
         * @param dataType 告诉服务器，我要想什么类型的数据，如果没有指定，那么会自动推断是返回 XML，还是JSON，还是script，还是String。
         * @param obj 当前请求的页面对象
         * contentType: 告诉服务器，我要发什么类型的数据
         */
        ajax: function (type, url, param,dataType,successCallBack, errorCallBack, async, obj) {
            debugger
            $.ajax({
                type: type,
                url: url,
                data: param,
                dataType:dataType === undefined ? null : dataType,
                contentType: "application/json;charset=utf-8",
                async: async === undefined ? true : async,
                success: function (msg) {
                    if (successCallBack) {
                        successCallBack(msg, obj);
                    }
                },
                error: function (msg) {
                    if (errorCallBack) {
                        errorCallBack(msg, obj);
                    }
                },
                complete: function (XMLHttpRequest, textStatus) {
                    //session超时
                    /*   var sessionStatus = XMLHttpRequest.getResponseHeader("sessionStatus");*/

                }
            });
        },
        /**
         * 下载文件
         * @param url
         * @param jsonParams
         */
        downloadFile : function (url,jsonParams) {
            var formInfo = "<form id='downloadForm'>";
            if(null != jsonParams){
                for(var key in jsonParams) {
                    formInfo += "<input type='hidden' id='" + key + "' name= '" + key + "'value='" + jsonParams[key]
                            +"' />";
                }
            }
            formInfo += "</form>";
            //定义form表单，通过表单发送请求
            var form= $(formInfo);
            //设置不显示
            form.attr("style","display:none");
            //设置请求类型
            form.attr("method","post");
            //设置请求路径
            form.attr("action",url);
            // 添加表单到页面（body）中
            $("body").append(form);
            //提交表单
            form.submit();
            //删除表单
            $("#downloadFile").remove();
        }
    }
}();

