var host = "http://localhost:8080/SpellingCheckWebService/webresources/service/paragraph/check";

chrome.extension.onMessage.addListener(function(request, sender, sendResponse) {
  if (request.method == "getSelection"){
  	var xhttp = new XMLHttpRequest();
	 xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	    	var result = xhttp.responseText;
	    	// alert(result);
	      	sendResponse({data: result});
	    }
	};
	xhttp.open("POST", host, false);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("string=" + window.getSelection().toString());
  }else {
  	sendResponse({}); // snub them.
  }
});