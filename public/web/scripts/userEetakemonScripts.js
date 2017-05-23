$(document).ready(function() {

    $("#userLogedNick").html(" "+sessionStorage.nickLoged);

    $.getJSON("http://localhost:8080/myapp/usuario/"+sessionStorage.idLoged+"/get_capturas", function (json) {
        var tr;
        for (var i = 0; i < json.length; i++) {
            tr = $('<tr/>');
            tr.append("<td>" + json[i].idetakemon + "</td>");
            tr.append("<td>" + json[i].idusuariosss + "</td>");
            tr.append("<td>" + json[i].nivel + "</td>");
            tr.append("<td>" + json[i].fecha+ "</td>");
            tr.append("<td><button type='delete' id='delete' class='btn btn-danger btn-xs'>X</button></td>");
            $('table').append(tr);
        }
    })
})