

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
     
     if (value == 5) {  // 5 == TIPUS_AVIS_BACK
    	 document.getElementById("avis_entitatID_rowid").style.display = 'none';
    	 document.getElementById("avis_entitatID").value='';
     } else {
    	 document.getElementById("avis_entitatID_rowid").style.display = '';
     }

 }
 
</script>