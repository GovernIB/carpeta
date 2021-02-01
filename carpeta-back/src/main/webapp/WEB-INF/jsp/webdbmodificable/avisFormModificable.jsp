

<script>
 
 onChangeTipus(document.getElementById("avis_tipus"));

 function onChangeTipus(combo) {

     var value = combo.options[combo.selectedIndex].value;
     if (value == 3) {  // 3 == TIPUS PLUGIN
       document.getElementById("avis_pluginFrontID_rowid").style.display = '';
     } else {
       document.getElementById("avis_pluginFrontID_rowid").style.display = 'none';
       document.getElementById("avis_pluginFrontID").value='';
     }

 }
 
</script>