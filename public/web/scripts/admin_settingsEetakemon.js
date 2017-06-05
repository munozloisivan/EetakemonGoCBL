var idSettingsEetakemon;

$(document).ready(function() {

    idSettingsEetakemon = getUrlParameter('id');

    $.get("http://localhost:8080/myapp/eetakemon/got_id/" + idSettingsEetakemon, function (data) {
        $("#nombre").val(data.nombre);
        $("#habilidad").val(data.habilidad);
        $("#descripcion").val(data.descripcion);
        $("#tipo").val(data.tipo);
    })

    $("#settings_button").click(function (e) {

        var datosEdit = {
            "id": idSettingsUsuario,"nombre": $("#nombre").val(), "nick": $("#usuario").val(),
            "email": $("#email").val(), "contrasena": $("#contrasena").val()
        };
        e.preventDefault();

        if (validateUpdate()) {
            swal({
                    title: "¿Estas seguro?",
                    text: "Actualizar los datos de un usuario puede afectar a su jugabilidad.",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "¡Actualízalo!",
                    cancelButtonText: "Atrás",
                    closeOnConfirm: false,
                    closeOnCancel: false
                },
                function(isConfirm) {
                    if (isConfirm) {
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
})

function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
}/**
 * Created by Roberto on 04/06/2017.
 */
