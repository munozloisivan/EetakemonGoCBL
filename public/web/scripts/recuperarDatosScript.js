/**
 * Created by Dani on 06/06/2017.
 */

$(document).ready(function() {


    $('#enviar_button').click(function (evento){
        evento.preventDefault();

        var email = $('#correo').val();
        var url = 'http://localhost:8080/myapp/datos';
        // http://localhost:8080/myapp/usuario/login
        var datos = {
            "email": email
        }

        $.ajax({
            url: url,
            type: "POST",
            data: datos
        }).done(function( response ) {

            console.log(response);

            if(response.status === 418) {
                swal({
                    title: "¡Vaya!",
                    text: "¡Parece que no se ha encontrado el correo!",
                    type: "error",
                    confirmButtonText: "Pruebo de nuevo"
                });
            }
            if(response.status === 201){
                swal({
                    title: "¡Revisa tu bandeja de entrada!",
                    text: "¡Ya le hemos enviado su contraseña con el correo con el que se registro!",
                    type: "success",
                    confirmButtonText: "Aceptar"
                });

                // redirigimos a otra página
                window.location.href = 'index.html'
            }
        });

    });


});

