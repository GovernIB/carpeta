<iframe src="${urlToShowPluginPage}" class="" style="min-height:300px;width:100%;height:400px;" frameborder='0' id="myiframe" scrolling="auto" title="Frame del plugin">
<p>NO IFRAME</p>
</iframe>

<script type="text/javascript" language="javascript"> 

    var lastSize = 0;

    function checkIframeSize() {
        
        setTimeout(checkIframeSize, 1000);
                
        var iframe = document.getElementById('myiframe');
        
        var iframeDocument = iframe.contentDocument || iframe.contentWindow.document;

        var h1 = $(iframeDocument.body).height();
        var h2 = iframeDocument.body.scrollHeight;
        //var h3 = $("#tablefull").height();

        var h = Math.max(h1,h2);

        var log = false;

        var d = new Date();
        if (log) {
            console.log("================ " + d + " (H = " + h +" | H1= " + h1 + " | H2= " + h2 + ") ===================");
        }

        if (h != lastSize) {
            h = h + 100;
            lastSize = h;
            if (log) {
              console.log(" checkIframeSize()::iframeDocument.body.scrollHeight = " + iframeDocument.body.scrollHeight);
              console.log(" checkIframeSize()::$(iframeDocument.body).height() = " + $(iframeDocument.body).height());
              console.log(" checkIframeSize()::$(TABLE).height() = " + $("#tablefull").height());
              console.log(" checkIframeSize():: SET " + h);
            }
            document.getElementById('myiframe').style.height=h + "px";
            lastSize =  Math.max($(iframeDocument.body).height(),iframeDocument.body.scrollHeight); <%--  $("#tablefull").height() --%>
            if (log) {
              console.log(" checkIframeSize():: GET " + lastSize);
            }
        }
    }
    
    $(document).ready(function ()  {
        setTimeout(checkIframeSize, 2000);
      });

</script>
