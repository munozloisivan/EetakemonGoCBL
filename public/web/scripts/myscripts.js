/**
 * Created by Roberto on 20/05/2017.
 */
$(document).ready(function() {

    $("#registrar_button").click(function (e) {

        var datosRegistro = {
            "nombre": $("#nombre").val(), "nick": $("#usuario").val(),
            "email": $("#email").val(), "contrasena": $("#contrasena").val()
        };
        e.preventDefault();

        if (validateRegister()) {
            $.ajax({
                url: "http://localhost:8080/myapp/usuario/new",
                type: "POST",
                data: JSON.stringify(datosRegistro),
                contentType: "application/json"
            })
        }
    })

    $("#login_button").click(function (e) {

        var datosLogin = {"email": $("#email_login").val(), "contrasena": $("#contrasena_login").val()};
        e.preventDefault();

        if (validateLogin()) {

            $.ajax({
                url: "http://localhost:8080/myapp/usuario/login",
                type: "POST",
                data: JSON.stringify(datosLogin),
                contentType: "application/json",
                statusCode: {
                    201: function (result) {
                        $("#userLogged").html(result.nick);
                    },
                    202: function () {
                        alert("Error al iniciar sesión");
                    }
                }
            })
        }
    })
})

function validateLogin() {
    var inputEmail = document.getElementById("email_login");
    var inputPass = document.getElementById("contrasena_login");
    if ((inputEmail.checkValidity() == false)||(inputPass.checkValidity() == false)) {

        if(inputEmail.checkValidity()==false)
            document.getElementById("email_error").innerHTML = inputEmail.validationMessage;
        else document.getElementById("email_error").innerHTML = " ";

        if(inputPass.checkValidity()==false)
            document.getElementById("contrasena_error").innerHTML = inputPass.validationMessage;
        else document.getElementById("contrasena_error").innerHTML = " ";
    }
    else {
        document.getElementById("email_error").innerHTML = " ";
        document.getElementById("contrasena_error").innerHTML = " ";
        return true;
    }
}

function validateRegister() {
    var inputNombre = document.getElementById("nombre");
    var inputEmail = document.getElementById("email");
    var inputUsuario = document.getElementById("usuario");
    var inputPass = document.getElementById("contrasena");
    var inputEmailConfirm = document.getElementById("emailConfirm");
    var inputPassConfirm = document.getElementById("contrasenaConfirm");

    if ((inputNombre.checkValidity()==false)||(inputEmail.checkValidity()==false)||
        (inputUsuario.checkValidity()==false)|| (inputPass.checkValidity()==false)||
        (inputEmailConfirm.checkValidity()==false)||(inputPassConfirm.checkValidity()==false)) {

        if (inputNombre.checkValidity() == false)
            document.getElementById("nombre_error").innerHTML = inputNombre.validationMessage;
        else document.getElementById("nombre_error").innerHTML = " ";

        if (inputEmail.checkValidity() == false)
            document.getElementById("email_error").innerHTML = inputEmail.validationMessage;
        else document.getElementById("email_error").innerHTML = " ";

        if (inputEmailConfirm.checkValidity() == false)
            document.getElementById("emailConfirm_error").innerHTML = inputEmailConfirm.validationMessage;
        else document.getElementById("emailConfirm_error").innerHTML = " ";

        if (inputUsuario.checkValidity() == false)
            document.getElementById("usuario_error").innerHTML = inputUsuario.validationMessage;
        else document.getElementById("usuario_error").innerHTML = " ";

        if (inputPass.checkValidity() == false)
            document.getElementById("contrasena_error").innerHTML = inputPass.validationMessage;
        else document.getElementById("contrasena_error").innerHTML = " ";

        if (inputPassConfirm.checkValidity() == false)
            document.getElementById("contrasenaConfirm_error").innerHTML = inputPassConfirm.validationMessage;
        else document.getElementById("contrasenaConfirm_error").innerHTML = " ";
    }
    else {
        document.getElementById("nombre_error").innerHTML = " ";
        document.getElementById("email_error").innerHTML = " ";
        document.getElementById("emailConfirm_error").innerHTML = " ";
        document.getElementById("usuario_error").innerHTML = " ";
        document.getElementById("contrasena_error").innerHTML = " ";
        document.getElementById("contrasenaConfirm_error").innerHTML = " ";
        return true;
    }

}


// ADMINISTRACION

// etakemons

$('#añadir_etakemon_button').click(function (e) {

        var datosAñadirEtakemon = {
            "nombre": $("#nombre").val(), "habilidad": $("#habilidad").val(),
            "tipo": $("#tipo").val()
        };

        e.preventDefault();

        $.ajax({
            url: "http://localhost:8080/myapp/etakemon/new",
            type: "POST",
            data: JSON.stringify(datosAñadirEtakemon),
            contentType: "application/json"
        })
})

// logros
$('#añadir_logro_button').click(function (e) {

    var datosAñadirLogro = {
        "nombre": $("#nombrelogro").val(), "descripcion": $("#descripcionlogro").val(),
        "experiencia": $("#experiencialogro").val()
    };

    e.preventDefault();

    $.ajax({
        url: "http://localhost:8080/myapp/logros/new",
        type: "POST",
        data: JSON.stringify(datosAñadirLogro),
        contentType: "application/json"
    })
})

