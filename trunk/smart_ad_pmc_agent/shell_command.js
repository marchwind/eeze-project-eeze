/**
 * New node file
 */
function shell_cmd(cmd, args, callBack) {
	var spawn = require('child_process').spawn;
	var child = spawn(cmd, args);
	var resp = "";
	
	child.stdout.on('data', function (buffer) { resp += buffer.toString(); });
	child.stdout.on('end', function() {callBack (resp); });
	child.stderr.on('data', function (data) { console.log(data); });
}

shell_cmd('dir ', [], function(text) { console.log (text); });
