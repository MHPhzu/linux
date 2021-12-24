$(function(){
    $("#register").click(function() {
        var para={
            stunum:$("#stunum").val(),
            stuphone:$("#stuphone").val(),
            stupwd:$("#stupwd").val(),
            stuemail:$("#stuemail").val()
        }
        $.ajax({
            url:"/ayUser/doregister",//加网页的路径
            type:"Post",
            data:para,
            datatype:"html",
            success:function(data){

                eval(data);
            },
            error: function (data) {

            }
        });
    });


})