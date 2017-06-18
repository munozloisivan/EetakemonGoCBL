/**
 * Created by Dani on 18/06/2017.
 */
var id_edit_user;

$(document).ready(function() {

    id_edit_user = getUrlParameter('id');

    $.get("/myapp/usuario/got_id/" + id_edit_user, function (data) {
        $("#usuario").val(data.nick);
        $("#contrasena").val(data.contrasena);
    })



    $("#settings_button").click(function (e) {

        var datosEdit = {
            "id": id_edit_user, "nick": $("#usuario").val(),
            "contrasena": $("#contrasena").val()
        };
        e.preventDefault();

        if (validateUpdate()) {
            swal({
                    title: "¿Estas seguro?",
                    text: "Actualizar los datos de tu cuenta es una accion permanente.",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "Actualizar",
                    cancelButtonText: "Atrás",
                    closeOnConfirm: false,
                    closeOnCancel: false
                },
                function(isConfirm) {
                    if (isConfirm) {
                        $.ajax({
                            url: "/myapp/usuario/edit",
                            type: "POST",
                            data: JSON.stringify(datosEdit),
                            contentType: "application/json",
                            statusCode: {
                                201: function () {
                                    window.location.href="userLoged.html"
                                },
                                418: function () {
                                    swal({
                                        title: "¡Vaya!",
                                        text: "¡Ha ocurrido un error durane la actualización de los datos!",
                                        type: "error",
                                        confirmButtonText: "Vale"
                                    });
                                }
                            }
                        })
                    } else {
                        swal("Cancelado", "¡Se ha cancelado el proceso de actualización!", "error");
                    }
                });
        }
    })

    $("#delete_button").click(function (e) {

        swal({
                title: "¿Estas seguro?",
                text: "Eliminar tu cuenta es una acción irreversible.",
                type: "warning",
                showCancelButton: true,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "Borrar",
                cancelButtonText: "Atrás",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            function(isConfirm) {
                if (isConfirm) {
                    $.get( "/myapp/usuario/delete/"+id_edit_user , function() {
                        swal("¡Hasta pronto!", "Tu cuenta ha sido eliminada correctamente, volverás a la página de inicio", "success");
                        sessionStorage.clear();
                        window.location.href="index.html";
                    })
                } else {
                    swal("Cancelado", "¡Se ha cancelado el proceso de eliminación!", "error");
                }
            });
    })
})

function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
}

//Robustez del UPDATE
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

    var inputUsuario = document.getElementById("usuario");
    var inputPass = document.getElementById("contrasena");

    if ((inputUsuario.checkValidity()==false)|| (inputPass.checkValidity()==false)){

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
