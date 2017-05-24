var emailJustLoged;

$(document).ready(function() {

    emailJustLoged = getUrlParameter('email');

    $.get( "http://localhost:8080/myapp/usuario/got_email/"+emailJustLoged , function( data ) {
        $("#userLogedNick").html(" "+data.nick);
        $("#nombreLoged").append(data.nombre);
        $("#nickLoged").append(data.nick);
        $("#correoLoged").append(data.email);
        $("#nivel").append(data.nivel);
        $("#experiencia").append(data.experiencia);
        sessionStorage.nickLoged = data.nick;
        sessionStorage.idLoged = data.id;
        sessionStorage.emailLoged = data.email;

        if (data.admin==1){
            $("#admin_button").show();
        }
        else{
            $("#admin_button").hide();
        };
    });

    $("#close_button").click(function () {
        window.location.href="index.html";
        sessionStorage.clear();
    })

    $("#admin_button").click(function () {
        window.location.href="admin_usuarios.html";
    })

})

function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
}

function reloadUserLoged(){
   window.location.href="userLoged.html?email="+sessionStorage.emailLoged;
}
