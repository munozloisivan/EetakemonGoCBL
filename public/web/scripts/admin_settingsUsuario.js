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

    $("#delete_button").click(function (e) {

        $.get( "http://localhost:8080/myapp/usuario/delete/"+idSettingsUsuario , function() {
            alert("Usuario eliminado");
            window.location.href="admin_usuarios.html"
        })

    })
})

function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
}

//Robustez del UPDATE
function validNombreUpdate() {
    var inputNombre = document.getElementById("nombre");

    if(inputNombre.checkValidity()==false){
        $("#nombre").css("border", "2px solid red");
    }else $("#nombre").css("border", "2px solid lime");
}

function validEmailUpdate() {
    var inputEmail = document.getElementById("email");

    if(inputEmail.checkValidity()==false){
        $("#email").css("border", "2px solid red");
    }else $("#email").css("border", "2px solid lime");
}

function validUsuarioUpdate() {
    var inputNick = document.getElementById("usuario");

    if(inputNick.checkValidity()==false){
        $("#usuario").css("border", "2px solid red");
    }else $("#usuario").css("border", "2px solid lime");
}

function validPassUpdate() {
    var inputPass = document.getElementById("contrasena");

    if(inputPass.checkValidity()==false){
        $("#contrasena").css("border", "2px solid red");
    }else $("#contrasena").css("border", "2px solid lime");
}

function validateUpdate() {
    var inputNombre = document.getElementById("nombre");
    var inputEmail = document.getElementById("email");
    var inputUsuario = document.getElementById("usuario");
    var inputPass = document.getElementById("contrasena");

    if ((inputNombre.checkValidity()==false)||(inputEmail.checkValidity()==false)||
        (inputUsuario.checkValidity()==false)|| (inputPass.checkValidity()==false)){

        if (inputNombre.checkValidity() == false) {
            $("#nombre").css("border", "2px solid red");
            $("#nombre").attr("placeholder", inputNombre.validationMessage);
        }   else $("#nombre").css("border", "2px solid lime");

        if (inputEmail.checkValidity() == false) {
            $("#email").css("border", "2px solid red");
            $("#email").attr("placeholder", inputEmail.validationMessage);
        }   else $("#email").css("border", "2px solid lime");

        if (inputUsuario.checkValidity() == false) {
            $("#usuario").css("border", "2px solid red");
            $("#usuario").attr("placeholder", inputUsuario.validationMessage);
        }   else $("#usuario").css("border", "2px solid lime");

        if (inputPass.checkValidity() == false){
            $("#contrasena").css("border", "2px solid red");
            $("#contrasena").attr("placeholder", inputPass.validationMessage);
        }   else $("#contrasena").css("border", "2px solid lime");
    }
    else {
        return true;
    }
}
