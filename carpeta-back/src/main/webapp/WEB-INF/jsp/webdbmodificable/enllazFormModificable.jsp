
<script>
 
 onChangeTipus(document.getElementById("enllaz_tipus"));

 function onChangeTipus(combo) {

     var value = combo.options[combo.selectedIndex].value;
     if (value == 4) {  // TIPUS_ENLLAZ_FRONT_PSEUDOPLUGIN = 4
       document.getElementById("enllaz_tipus_rowid").style.display = '';
       document.getElementById("enllaz_descripcioid_rowid").style.display = '';
     } else {
       document.getElementById("enllaz_descripcioid_rowid").style.display = 'none';    	 
       document.getElementById("enllaz_tipus_rowid").style.display = 'none';
       document.getElementById("enllaz_seccioID").value='';
     }

 }
 
</script>