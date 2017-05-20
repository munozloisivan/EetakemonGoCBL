/**
 * Created by Roberto on 20/05/2017.
 */

$(document).ready(function() {

    $.getJSON("http://localhost:8080/myapp/usuario/get_all", function (json) {
        var tr;
        for (var i = 0; i < json.length; i++) {
            tr = $('<tr/>');
            tr.append("<td>" + json[i].nick + "</td>");
            tr.append("<td>" + json[i].email + "</td>");
            tr.append("<td>" + json[i].nombre + "</td>");
            tr.append("<td>" + json[i].id+ "</td>");
            tr.append("<td><button type='delete' id='delete' class='btn btn-danger btn-xs'>X</button></td>");
            $('table').append(tr);
        }
    })
})

function myFunction() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("nameFilter");
    filter = input.value.toUpperCase();
    table = document.getElementById("tableUsuarios");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

$('#registerForm').validator().on('submit', function (e) {
    if (e.isDefaultPrevented()) {
        // handle the invalid form...
    } else {
        // everything looks good!
        var datosRegistro = {
            "nombre": $("#nombre").val(), "nick": $("#usuario").val(),
            "email": $("#email").val(), "contrasena": $("#contrasena").val()
        };

        $.ajax({
            url: "http://localhost:8080/myapp/usuario/new",
            type: "POST",
            data: JSON.stringify(datosRegistro),
            contentType: "application/json"
        })
    }
})

$("#login_button").click(function (e) {

    var datosLogin = {"email": $("#email").val(), "contrasena": $("#contrasena").val()};
    e.preventDefault();

    $.ajax({
        url: "http://localhost:8080/myapp/usuario/login",
        type: "POST",
        data: JSON.stringify(datosLogin),
        contentType: "application/json",
        success: function(result){
            $("#userLogged").html(result.nick);
        }
    })
})
