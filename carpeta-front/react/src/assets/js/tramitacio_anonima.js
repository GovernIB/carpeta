jQuery("#iniciarTramitacioBtn").on("click", function(){
        jQuery.ajax({
            url: window.location.href.split('/').slice(0, 3).join('/')+"/carpetafront/tramitacio?id=" + jQuery('#clauAnonima').val(),
            method: "GET"
        }).done(function(data){
	    if (data.url != "")
	            window.open(data.url, "_blank");
	    else if (data.error)
            jQuery('#tramitacioModal .alert-danger').css('display','block');
	
	    jQuery('#tramitacioModal').modal('hide');
        })
    });