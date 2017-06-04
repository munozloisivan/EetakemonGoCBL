/**
 * Created by Roberto on 20/05/2017.
 */
$(document).ready(function() {

    $("#registrar_loader_div").hide();
    $("#login_loader_div").hide();

    $("#registrar_button").click(function (e) {

        var datosRegistro = {
            "nombre": $("#nombre").val(), "nick": $("#usuario").val(),
            "email": $("#email").val(), "contrasena": $("#contrasena").val()
        };
        e.preventDefault();

        if (validateRegister()) {
            $("#registrar_button_div").hide();
            $("#registrar_loader_div").show();
            $.ajax({
                url: "http://localhost:8080/myapp/usuario/new",
                type: "POST",
                data: JSON.stringify(datosRegistro),
                contentType: "application/json",
                statusCode: {
                    201: function (result) {
                        window.location.href="registrado.html?email="+result.email;
                        $("#registrar_button_div").show();
                        $("#registrar_loader_div").hide();
                    },
                    400: function () {
                        swal({
                            title: "¡Vaya!",
                            text: "¡Parece ser el correo electrónico ya se ha utilizado!",
                            type: "error",
                            confirmButtonText: "Vale"
                        });
                        $("#registrar_button_div").show();
                        $("#registrar_loader_div").hide();
                    }
                }
            })
        }
    })

    $("#login_button").click(function (e) {

        var datosLogin = {"email": $("#email_login").val(), "contrasena": $("#contrasena_login").val()};
        e.preventDefault();

        if (validateLogin()) {

            $("#login_loader_div").show();
            $("#login_button").hide();

            $.ajax({
                url: "http://localhost:8080/myapp/usuario/login",
                type: "POST",
                data: JSON.stringify(datosLogin),
                contentType: "application/json",
                statusCode: {
                    201: function (result) {
                        window.location.href="userLoged.html?email="+result.email;
                        $("#login_loader_div").hide();
                        $("#login_button").show();
                    },
                    404: function () {
                        swal({
                            title: "¡Vaya!",
                            text: "¡Parece ser que ha habido un error al iniciar sesión!",
                            type: "error",
                            confirmButtonText: "Pruebo de nuevo"
                        });
                        document.getElementById("contrasena_login").value = "";
                        $("#contrasena_login").css("border", "2px solid red");
                        $("#login_loader_div").hide();
                        $("#login_button").show();
                    }
                }
            })
        }
    })
})


// Robustez del LOGIN
function validEmail() {
    var inputEmail = document.getElementById("email_login");

    if(inputEmail.checkValidity()==false){
        $("#email_login").css("border", "2px solid red");
    }else $("#email_login").css("border", "2px solid lime");

}

function validPass() {
    var inputPass = document.getElementById("contrasena_login");

    if(inputPass.checkValidity()==false){
        $("#contrasena_login").css("border", "2px solid red");
    }else $("#contrasena_login").css("border", "2px solid lime");

}

function validateLogin() {
    var inputEmail = document.getElementById("email_login");
    var inputPass = document.getElementById("contrasena_login");
    if ((inputEmail.checkValidity() == false)||(inputPass.checkValidity() == false)) {

        if(inputEmail.checkValidity()==false) {
            $("#email_login").css("border", "2px solid red");
            $("#email_login").attr("placeholder", inputEmail.validationMessage);
        }else $("#email_login").css("border", "2px solid lime");

        if(inputPass.checkValidity()==false){
            $("#contrasena_login").css("border", "2px solid red");
            $("#contrasena_login").attr("placeholder", inputPass.validationMessage);
        }else $("#contrasena_login").css("border", "2px solid lime");
    }
    else {
        return true;
    }
}

// Robustez del REGISTRO
function validNombreRegistro() {
    var inputNombre = document.getElementById("nombre");

    if(inputNombre.checkValidity()==false){
        $("#nombre").css("border", "2px solid red");
    }else $("#nombre").css("border", "2px solid lime");
}

function validEmailRegistro() {
    var inputEmail = document.getElementById("email");

    if(inputEmail.checkValidity()==false){
        $("#email").css("border", "2px solid red");
    }else $("#email").css("border", "2px solid lime");
}

function validEmailConfirmRegistro() {
    var inputEmailConfirm = document.getElementById("emailConfirm");
    var inputEmail = document.getElementById("email");

    if((inputEmailConfirm.checkValidity()==false)||(inputEmail.value!=inputEmailConfirm.value)){
        $("#emailConfirm").css("border", "2px solid red");
    }else $("#emailConfirm").css("border", "2px solid lime");
}

function validUsuarioRegistro() {
    var inputNick = document.getElementById("usuario");

    if(inputNick.checkValidity()==false){
        $("#usuario").css("border", "2px solid red");
    }else $("#usuario").css("border", "2px solid lime");
}

function validPassRegistro() {
    var inputPass = document.getElementById("contrasena");

    if(inputPass.checkValidity()==false){
        $("#contrasena").css("border", "2px solid red");
    }else $("#contrasena").css("border", "2px solid lime");
}

function validPassConfirmRegistro() {
    var inputPassConfirm = document.getElementById("contrasenaConfirm");
    var inputPass = document.getElementById("contrasena");

    if((inputPassConfirm.checkValidity()==false)||(inputPass.value!=inputPassConfirm.value)){
        $("#contrasenaConfirm").css("border", "2px solid red");
    }else $("#contrasenaConfirm").css("border", "2px solid lime");
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

        if (inputNombre.checkValidity() == false) {
            $("#nombre").css("border", "2px solid red");
            $("#nombre").attr("placeholder", inputNombre.validationMessage);
        }   else $("#nombre").css("border", "2px solid lime");

        if (inputEmail.checkValidity() == false) {
            $("#email").css("border", "2px solid red");
            $("#email").attr("placeholder", inputEmail.validationMessage);
        }   else $("#email").css("border", "2px solid lime");

        if (inputEmailConfirm.checkValidity() == false) {
            $("#emailconfirm").css("border", "2px solid red");
            $("#emailconfirm").attr("placeholder", inputEmailConfirm.validationMessage);
        }   else $("#emailconfirm").css("border", "2px solid lime");

        if (inputEmail.value!=inputEmailConfirm.value) {
            $("#emailconfirm").css("border", "2px solid red");
            $("#emailconfirm").attr("placeholder", "Las direcciones de correo electrónico no coinciden");
        }

        if (inputUsuario.checkValidity() == false) {
            $("#usuario").css("border", "2px solid red");
            $("#usuario").attr("placeholder", inputUsuario.validationMessage);
        }   else $("#usuario").css("border", "2px solid lime");

        if (inputPass.checkValidity() == false){
            $("#contrasena").css("border", "2px solid red");
            $("#contrasena").attr("placeholder", inputPass.validationMessage);
        }   else $("#contrasena").css("border", "2px solid lime");

        if (inputPassConfirm.checkValidity() == false){
            $("#contrasenaconfirm").css("border", "2px solid red");
            $("#contrasenaconfirm").attr("placeholder", inputPass.validationMessage);
        }   else $("#contrasena").css("border", "2px solid lime");

        if (inputPass.value!=inputPassConfirm.value) {
            $("#contrasenaConfirm").css("border", "2px solid red");
            $("#contrasenaConfirm").attr("placeholder", "Las contraseñas no coinciden");
        }
    }
    else {
        return true;
    }
}

