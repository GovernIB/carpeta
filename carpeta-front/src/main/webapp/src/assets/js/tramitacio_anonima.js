jQuery("#iniciarTramitacioBtn").on("click", function(){
        jQuery.ajax({
            url: window.location.href.split('/').slice(0, 3).join('/')+"/carpetafront/tramitacio?id=" + jQuery('#clauAnonima').val(),
            method: "GET"
        }).done(function(data){
    	    if (data.url !== undefined){
    	            window.open(data.url, "_blank");
                    jQuery('#tramitacioModal').modal('hide');
    	    }else if (data.error == 'true')
                jQuery('#tramitacioModal .alert-danger').css('display','block');
        })
    });