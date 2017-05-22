var userRegistredID;

$(document).ready(function() {

    userRegistredID = getUrlParameter('id');
    alert(userRegistredID);

    $.get( "http://localhost:8080/myapp/usuario/got_id/"+userRegistredID , function( data ) {
        alert(JSON.stringify(data));
    });

    $("#usuarioRegistrado").html(userRegistredID);

})

function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};