var userRegistred;

$(document).ready(function() {

    userRegistred = getUrlParameter('email');

    $.get( "/myapp/usuario/got_email/"+userRegistred , function( data ) {
        $("#nombreRegistrado").append(data.nombre);
        $("#usuarioRegistrado").append(data.nick);
        $("#correoRegistrado").append(data.email);
    });
})

function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};