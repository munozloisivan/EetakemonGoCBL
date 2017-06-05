$(document).ready(function() {

    $.getJSON("http://localhost:8080/myapp/eetakemon/get_all", function (json) {

        var actual_id;
        for (var i = 0; i < json.length; i++) {
            actual_id = json[i].id;
            actual_eetakemon = json[i].nombre;
            $("#eetakedex_generated").append("<div class='col-md-2' id='"+actual_id+"'>");
            document.getElementById(actual_id).innerHTML = "<div class='thumbnail' id='"+actual_eetakemon+"' style='align-content: center'>" ;
            document.getElementById(actual_eetakemon).innerHTML = "<img src='"+json[i].imagen+"' alt='"+actual_eetakemon+"' style='width:100%'><div class='caption'><p>"+actual_eetakemon+"</p>";
        }

    })
})



