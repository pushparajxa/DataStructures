<!DOCTYPE>
<html>
<head>
    <title>My first web page</title>
</head>
<body>
    <p>
    <?php echo date("F jS, Y"); echo "\n Great!!! \n";

//$output = shell_exec("/usr/bin/python hello.py");
//echo $output;

  ?>
  </p>

Status:
 <label id = "status">
        NOT_RUNNING
    </label>

<script type="text/javascript">

	function poll() {
		var ajax = new XMLHttpRequest();
		ajax.onreadystatechange = function() {
			if (this.readyState === 4 && this.status === 200) {
				if (this.status === 200) {
					try {
						var json = JSON.parse(this.responseText);
					} catch {
						poll();return;
					}


					if (json.status !== true) {
						alert(json.error);return;
					}
					/*
					var div = document.createElement("DIV");
					document.body.appendChild(div);
					div.innerHTML = 'time: ' +  json.time +  ', content: ' +  json.content;
					*/

					document.getElementById('status').innerHTML = 'time: ' +  json.time +  ', content: ' +  json.content;

					poll();
				} else {
					poll();
				}
			}
		}
		ajax.open('GET', 'long-polling.php', true);
		ajax.send();
	}
	poll();
</script>




</body>
</html>