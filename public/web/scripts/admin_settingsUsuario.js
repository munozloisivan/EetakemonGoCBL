var idSettingsUsuario;

$(document).ready(function() {

    idSettingsUsuario = getUrlParameter('id');

    $.get("/myapp/usuario/got_id/" + idSettingsUsuario, function (data) {
        $("#nombre").val(data.nombre);
        $("#usuario").val(data.nick);
        $("#email").val(data.email);
        $("#contrasena").val(data.contrasena);
    })

    $.getJSON("http://localhost:8080/myapp/usuario/"+ idSettingsUsuario+"/get_capturas", function (json) {
        var tr;
        for (var i = 0; i < json.length; i++) {
            tr = $('<tr/>');
            tr.append("<td>" + json[i].nombreetakemon + "</td>");
            tr.append("<td><img class='img-responsive' src='"+json[i].imagen+"' alt='"+json[i].nombreetakemon+"' style='width: 50px;height: 50px;' align='center'></td>");
            tr.append("<td>" + json[i].habilidadetakemon + "</td>");
            tr.append("<td>" + json[i].nivel + "</td>");
            tr.append("<td>" + json[i].fecha+ "</td>");
            tr.append("<td><button type='delete' id='delete' class='btn btn-danger btn-xs'>X</button></td>");
            $('table').append(tr);
        }
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
                    text: "Actualizar los datos de un usuario puede afectar a su experiencia de juego.",
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
                            url: "/myapp/usuario/admin_edit",
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

    $("#delete_button").click(function (e) {

        swal({
                title: "¿Estas seguro?",
                text: "Eliminar un usuario es una acción irreversible.",
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
                    $.get( "/myapp/usuario/delete/"+idSettingsUsuario , function() {
                        window.location.href="admin_usuarios.html"
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
