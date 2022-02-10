<script type="text/javascript">

    $(document).ready(function() {

        if ($('input[id="dataAccesDesde"]').val() === "") {

            var d = new Date();
            var day = d.getDate();
            var month = d.getMonth() + 1;
            var year = d.getFullYear();
            var hour = d.getHours();
            var minute = d.getMinutes();
            var second = d.getSeconds();
            if (day < 10) {
                day = "0" + day;
            }
            if (month < 10) {
                month = "0" + month;
            }
            if (hour < 10) {
                hour = "0" + hour;
            }
            if (minute < 10) {
                minute = "0" + minute;
            }
            if (second < 10) {
                second = "0" + second;
            }
            var dataFi = day.toString() + "/" + month.toString() + "/" + year.toString() + " " + hour.toString() + ":" + minute.toString() + ":" + second.toString();

            var monthInici = d.getMonth();
            if (monthInici === 0) {
                monthInici = "12";
            }
            if (monthInici < 10) {
                monthInici = "0" + monthInici;
            }
            var dataInici = day.toString() + "/" + monthInici.toString() + "/" + year.toString() + " " + hour.toString() + ":" + minute.toString() + ":" + second.toString();

            $('input[id="dataAccesDesde"]').attr('placeholder', 'dd/MM/aaaa hh:mm:ss');
            $('input[id="dataAccesDesde"]').attr('value', dataInici);

            $('input[id="dataAccesFins"]').attr('placeholder', 'dd/MM/aaaa hh:mm:ss');
            $('input[id="dataAccesFins"]').attr('value', dataFi);

        }

    });

</script>