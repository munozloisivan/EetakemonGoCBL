/**
 * Created by Dani on 06/06/2017.
 */



$(document).ready(function() {


    $("#recuperar_pass").click(function () {
        swal({
            title: "¿Has olvidado tu contraseña?",
            text: "Escribe tu correo para que podamos enviarte la contraseña",
            type: "input",
            animation: "slide-from-top",
            inputPlaceholder: "Dirección de correo electrónico",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "Envíar",
            cancelButtonText: "Atrás"
        }, function(inputValue){
            if (inputValue === false) return false;

            if (inputValue === "") {
                swal.showInputError("Tienes que escribir algo");
                return false
            }

            $.ajax({
                url: "/myapp/usuario/"+inputValue+"/datos",
                type: "POST",
                data: JSON.stringify(inputValue),
                contentType: "application/json",
                statusCode: {

                    201: function () {
                        swal({
                            title: "¡Revisa tu bandeja de entrada!",
                            text: "Has recibido un correo con tu contraseña",
                            type: "success",
                            confirmButtonText: "Vale"
                        });
                    },

                    418: function () {

                        swal({
                            title: "¡Vaya!",
                            text: "¡Parece que el correo no esta registrado!",
                            type: "error",
                            confirmButtonText: "Vale"
                        });
                    }
                }

            });
        });
    });

});












