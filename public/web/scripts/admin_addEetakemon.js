$(document).ready(function() {

    $("#add_button").click(function (e) {

        var datosAdd = {
            "nombre": $("#nombre").val(), "habilidad": $("#habilidad").val(),
            "descripcion": $("#descripcion").val(), "imagen": $("#imagen").val(), "tipo": $("#tipo").val()
        };
        e.preventDefault();

        if (validateUpdate()) {
            swal({
                    title: "¿Estas seguro?",
                    text: "Añadir un eetakemon puede afectar a la jugabilidad.",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "Añadir",
                    cancelButtonText: "Atrás",
                    closeOnConfirm: false,
                    closeOnCancel: false
                },
                function(isConfirm) {
                    if (isConfirm) {
                        $.ajax({
                            url: "http://localhost:8080/myapp/eetakemon/new",
                            type: "POST",
                            data: JSON.stringify(datosAdd),
                            contentType: "application/json",
                            statusCode: {
                                201: function () {
                                    window.location.href="admin_eetakemon.html"
                                },
                                400: function () {
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

// Robustez del ADD
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

function previewImagen() {

    var imgpreview = $("#imagen").val();
    $("#preview").attr('src', imgpreview);
}
