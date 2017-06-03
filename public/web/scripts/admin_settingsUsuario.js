var idSettingsUsuario;

$(document).ready(function() {

    idSettingsUsuario = getUrlParameter('id');

    $.get("http://localhost:8080/myapp/usuario/got_id/" + idSettingsUsuario, function (data) {
        $("#nombreSettings").append(data.nombre);
        $("#nickSetting").append(data.nick);
        $("#correoSettings").append(data.email);
        $("#contrasenaSettings").append(data.contrasena);
    })

    $("#settings_button").click(function (e) {

        var datosEdit = {
            "id": idSettingsUsuario,"nombre": $("#nombre").val(), "nick": $("#usuario").val(),
            "email": $("#email").val(), "contrasena": $("#contrasena").val()
        };
        e.preventDefault();

        if (validateUpdate()) {
            $.ajax({
                url: "http://localhost:8080/myapp/usuario/admin_edit",
                type: "POST",
                data: JSON.stringify(datosEdit),
                contentType: "application/json",
                statusCode: {
                    201: function () {
                        window.location.href="admin_usuarios.html"
                    },
                    418: function () {
                        alert("Ha ocurrido un error durante la actualización de los datos.");
                    }
                }
            })
        }
    })
})

function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
}

$.get( "http://localhost:8080/myapp/usuario/delete/"+id , function() {
    alert("Usuario eliminado");
    location.reload();
})

function validateUpdate() {
    var inputNombre = document.getElementById("nombre");
    var inputEmail = document.getElementById("email");
    var inputUsuario = document.getElementById("usuario");
    var inputPass = document.getElementById("contrasena");

    if ((inputNombre.checkValidity()==false)||(inputEmail.checkValidity()==false)||
        (inputUsuario.checkValidity()==false)|| (inputPass.checkValidity()==false)) {

        if (inputNombre.checkValidity() == false)
            document.getElementById("nombre_error").innerHTML = inputNombre.validationMessage;
        else document.getElementById("nombre_error").innerHTML = " ";

        if (inputEmail.checkValidity() == false)
            document.getElementById("email_error").innerHTML = inputEmail.validationMessage;
        else document.getElementById("email_error").innerHTML = " ";

        if (inputUsuario.checkValidity() == false)
            document.getElementById("usuario_error").innerHTML = inputUsuario.validationMessage;
        else document.getElementById("usuario_error").innerHTML = " ";

        if (inputPass.checkValidity() == false)
            document.getElementById("contrasena_error").innerHTML = inputPass.validationMessage;
        else document.getElementById("contrasena_error").innerHTML = " ";
    }
    else {
        document.getElementById("nombre_error").innerHTML = " ";
        document.getElementById("email_error").innerHTML = " ";
        document.getElementById("usuario_error").innerHTML = " ";
        document.getElementById("contrasena_error").innerHTML = " ";
        return true;
    }
}
