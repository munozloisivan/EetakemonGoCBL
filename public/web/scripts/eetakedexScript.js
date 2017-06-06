$(document).ready(function() {

    $.get("http://localhost:8080/myapp/eetakemon/get_all", function (json) {
        $("#eetakedex_generated").hide();
    })
        .done(function(json) {
            var actual_id;
            for (var i = 0; i < json.length; i++) {
                actual_id = json[i].id;
                actual_eetakemon = json[i].nombre;
                $("#eetakedex_generated").append("<div class='col-md-4' id='"+actual_id+"'>");
                document.getElementById(actual_id).innerHTML = "<div class='thumbnail' id='"+actual_eetakemon+"' style='align-content: center;border-radius: 10px'>" ;
                document.getElementById(actual_eetakemon).innerHTML = "<img src='"+json[i].imagen+"' alt='"+actual_eetakemon+"' style='width:100%'>" +
                    "<div class='well well-sm' style='border-radius: 10px; height: 200px'><h4><strong>"+actual_eetakemon+"</strong></h4>" +
                    "<h4>Habilidad: "+json[i].habilidad+"</h4>" +
                    "<p>"+json[i].descripcion+"</p>";
            }
            $("#eetakedex_loader_div").hide();
            $("#eetakedex_generated").show();
        })
})


