var idSettingsEetakemon;

$(document).ready(function() {

    idSettingsEetakemon = getUrlParameter('id');

    $.get("/myapp/eetakemon/got_id/" + idSettingsEetakemon, function (data) {
        $("#nombre").val(data.nombre);
        $("#habilidad").val(data.habilidad);
        $("#descripcion").val(data.descripcion);
        $("#tipo").val(data.tipo);
        $("#imagen").val(data.imagen);
    })

    $("#settings_button").click(function (e) {

        var datosEdit = {
            "id": idSettingsEetakemon,"nombre": $("#nombre").val(), "habilidad": $("#habilidad").val(),
            "descripcion": $("#descripcion").val(), "imagen": $("#imagen").val(), "tipo": $("#tipo").val()
        };
        e.preventDefault();

        if (validateUpdate()) {
            swal({
                    title: "¿Estas seguro?",
                    text: "Actualizar los datos de un eetakemon puede afectar a la jugabilidad.",
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
                            url: "/myapp/eetakemon/admin_edit",
                            type: "POST",
                            data: JSON.stringify(datosEdit),
                            contentType: "application/json",
                            statusCode: {
                                201: function () {
                                    window.location.href="admin_eetakemon.html"
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
                text: "Eliminar un Eetakemon es una acción irreversible.",
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
                    $.get( "/myapp/eetakemon/delete/"+idSettingsEetakemon , function() {
                        window.location.href="admin_eetakemon.html"
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
}/**
 * Created by Roberto on 04/06/2017.
 */


function validNombreUpdate() {
    var inputNombre = document.getElementById("nombre");

    if(inputNombre.checkValidity()==false){
        $("#nombre").css("border", "2px solid red");
    }else $("#nombre").css("border", "2px solid lime");
}

function validHabilidadUpdate() {
    var inputHab = document.getElementById("habilidad");

    if(inputHab.checkValidity()==false){
        $("#habilidad").css("border", "2px solid red");
    }else $("#habilidad").css("border", "2px solid lime");
}

function validImagenUpdate() {
    var inputImg = document.getElementById("imagen");

    if(inputImg.checkValidity()==false){
        $("#imagen").css("border", "2px solid red");
    }else $("#imagen").css("border", "2px solid lime");
}

function validDescUpdate() {
    var inputDesc = document.getElementById("descripcion");

    if(inputDesc.checkValidity()==false){
        $("#descripcion").css("border", "2px solid red");
    }else $("#descripcion").css("border", "2px solid lime");
}

function validTipoUpdate() {
    var inputTipo = document.getElementById("tipo");

    if(inputTipo.checkValidity()==false){
        $("#tipo").css("border", "2px solid red");
    }else $("#tipo").css("border", "2px solid lime");
}

// Robustez del Update
function validateUpdate() {
    var inputNombre = document.getElementById("nombre");
    var inputHab = document.getElementById("habilidad");
    var inputImagen = document.getElementById("imagen");
    var inputDesc = document.getElementById("descripcion");
    var inputTipo = document.getElementById("tipo");

    if ((inputNombre.checkValidity()==false)||(inputHab.checkValidity()==false)||
        (inputImagen.checkValidity()==false)|| (inputDesc.checkValidity()==false)){

        if (inputNombre.checkValidity() == false) {
            $("#nombre").css("border", "2px solid red");
            $("#nombre").attr("placeholder", inputNombre.validationMessage);
        }   else $("#nombre").css("border", "2px solid lime");

        if (inputHab.checkValidity() == false) {
            $("#habilidad").css("border", "2px solid red");
            $("#habilidadl").attr("placeholder", inputHab.validationMessage);
        }   else $("#habilidad").css("border", "2px solid lime");

        if (inputImagen.checkValidity() == false) {
            $("#imagen").css("border", "2px solid red");
            $("#imagen").attr("placeholder", inputImagen.validationMessage);
        }   else $("#imagen").css("border", "2px solid lime");

        if (inputDesc.checkValidity() == false){
            $("#descripcion").css("border", "2px solid red");
            $("#descripcion").attr("placeholder", inputDesc.validationMessage);
        }   else $("#descripcion").css("border", "2px solid lime");

        if (inputTipo.checkValidity() == false){
            $("#tipo").css("border", "2px solid red");
            $("#tipo").attr("placeholder", inputDesc.validationMessage);
        }   else $("#tipo").css("border", "2px solid lime");
    }
    else {
        return true;
    }
}