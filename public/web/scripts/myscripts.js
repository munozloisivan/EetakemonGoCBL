/**
 * Created by Roberto on 20/05/2017.
 */
$(document).ready(function() {

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
