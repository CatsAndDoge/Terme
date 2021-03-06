
//验证码
var checkCode = "";

layui.use("form",function () {
    var form = layui.form;

    var $ = layui.$;

    $("#sendCheckCode").click(function () {
        var email = $("#email").val();
        if (email == null || email == ""){
            layer.msg("请输入邮箱！！！");
            return;
        }
        var index = layer.open({
            type:3,
            content:"邮件发送中..."
        });

        $.ajax({
            url:"/getCheckCode?email="+email,
            type:"get",
            data:{key:"a"},
            success:function (text) {
                if (text != null && text != ""){
                    layer.close(index);
                    layer.msg("已发送");
                    // checkCode = text;
                    countDown();
                } else{
                    layer.alert("获取失败，请重新获取")
                }
            }
        });
    });

    var maxTime = 60;
    function countDown(){
        if (maxTime == 0){
            checkCode = "";
            $("#sendCheckCode").removeClass("layui-btn-disabled");
            $("#sendCheckCode").removeAttr("disabled")
            $("#sendCheckCode").html("获取验证码");
            maxTime = 60;
        }else{
            $("#sendCheckCode").attr("disabled","disabled");
            $("#sendCheckCode").addClass("layui-btn-disabled");
            form.render();
            $("#sendCheckCode").html(maxTime+"秒后重新获取");
            maxTime--;
            setTimeout(countDown,1000);
        }
    }

});



$("form :input").blur(function(){
    var $parent = $(this).parent();
    $parent.find(".msg").remove();
    //验证密码
    if($(this).is("#con_new_pwd")) {
        var pass1 = $("#new_pwd").val();
        var pass2 = $("#con_new_pwd").val();
        if (pass1 != pass2) {
            var errorMsg = "确认密码不相同！";
            $parent.append("<span class='msg onError'>" + errorMsg + "</span>");
        }
    }
}).keyup(function(){
    //triggerHandler 防止事件执行完后，浏览器自动为标签获得焦点
    $(this).triggerHandler("blur");
}).focus(function(){
    $(this).triggerHandler("blur");
});



$(function(){
    $("#btnSub").on("click",function(){
        var userEmail = $("#email").val();
        var pass2 = $("#con_new_pwd").val();
        var checkCode = $("#checkCode").val();
        $("#btnSub").addClass("submiting");
        $("#btnSub").val("提交中");

        $.ajax({
            url:"/doReset",
            type:"POST",
            dataType:"json",
            data:{
                "userEmail":userEmail,
                "userPassword":pass2,
                "inputCheckCode":checkCode
            },
            success:function (result) {
                if(result == 1)
                    alert("修改密码成功")
                else
                    alert(result.meg)
            },
            error:function(result){
                alert(result.meg)
            }
        })
    })

})
