$(document).ready(function() {

    $.getJSON("http://localhost:8080/myapp/eetakemon/get_all", function (json) {
        var eetakedex_item;
        for (var i = 0; i < json.length; i++) {
            eetakedex_item = $(".thumbnail");
            eetakedex_item.attr('id',json[i].id);
            eetakedex_item.html("<img src='Eetakemon_images/"+json[i].nombre+".png' alt='"+json[i].nombre+"' style='width:100%'>");
            $(".thumbnail").append(eetakedex_item);
        }
    })
})

