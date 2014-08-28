/**
 * New node file
 */
require('shelljs/global');

var varsion = exec('node --version', {silent:true}).output;

exec('netstat.exe -an', function(status, output) {
	console.log('Exit status: ', status);
	console.log('Program output: ', output);
});