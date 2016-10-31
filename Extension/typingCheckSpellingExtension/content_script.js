var targ;

var host = "http://localhost:8080/demo/typing";

window.addEventListener ("load", myMain, false);

// set caret at the end of editable element
function placeCaretAtEnd(el) {
    el.focus();
    if (typeof window.getSelection != "undefined"
            && typeof document.createRange != "undefined") {
        var range = document.createRange();
        range.selectNodeContents(el);
        range.collapse(false);
        var sel = window.getSelection();
        sel.removeAllRanges();
        sel.addRange(range);
    } else if (typeof document.body.createTextRange != "undefined") {
        var textRange = document.body.createTextRange();
        textRange.moveToElementText(el);
        textRange.collapse(false);
        textRange.select();
    }
}

function myMain () {
    // DO YOUR STUFF HERE.
    document.body.onmousedown = function (e) {
    	if (!e) {
        var e = window.event;
	    }
	    if (e.target) {
	        targ=e.target;
	    } else if (e.srcElement) {
	        targ=e.srcElement;
	    }

	    var tname;
	    tname = targ.innerText || targ.textContent;
	    // console.log(tname);

	    targ.onkeyup = function(e){
		    if(e.keyCode == 32){
		    	console.log("space press");
		        //your code
		        if (targ.value == null) {
		        	tname = targ.innerText || targ.textContent;
		        	var arrContent = tname.split(" ");
	    			console.log(tname);
					console.log(targ.innerHTML);
					var xhttp = new XMLHttpRequest();
				    xhttp.onreadystatechange = function() {
				          if (xhttp.readyState == 4 && xhttp.status == 200) {
				            var result = xhttp.responseText;
				            console.log(JSON.parse(result));
				            var arrResult = JSON.parse(result);
				            // var arrResult = result.split(",");
				            // alert(result);
				              // sendResponse({data: result});
				              for (var i = arrResult.length - 1; i >= 0; i--) {
				                console.log(arrResult[i]);
				                // var arrMistake = arrResult[i].split("-");
				                tname = tname.replace(arrContent[arrResult[i]], "<span style='color:red;'>" + arrContent[arrResult[i]] +"</span>");
				                targ.innerHTML = tname;
				                // targ.focus(); //sets focus to element
								placeCaretAtEnd(targ);
				                
				                // if (targ.innerHTML.indexOf(arrMistake[0].trim()) !== -1) {
				                //   targ.innerHTML = targ.innerHTML.replace(arrMistake[0].trim(), arrMistake[1].trim());
				                  
				                // }
				              }
				              
				          }
				      };
				    xhttp.open("POST", host, false);
				    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				    xhttp.send("content=" + tname);
				} else {
					tname = targ.innerText || targ.textContent || targ.value;
					var arrContent = tname.split(" ");
	    			console.log(tname);
	    			console.log(targ.value);
					var xhttp = new XMLHttpRequest();
				    xhttp.onreadystatechange = function() {
				          if (xhttp.readyState == 4 && xhttp.status == 200) {
				            var result = xhttp.responseText;
				            console.log(JSON.parse(result));
				            var arrResult = JSON.parse(result);
				            // var arrResult = result.split(",");
				            // alert(result);
				              // sendResponse({data: result});
				      //         for (var i = arrResult.length - 1; i >= 0; i--) {
				      //           console.log(arrResult[i]);
				      //           var arrMistake = arrResult[i].split("-");
				      //           if (targ.value.indexOf(arrMistake[0].trim()) !== -1) {
				      //             targ.value = targ.value.replace(arrMistake[0].trim(), arrMistake[1].trim());
				      //             targ.focus(); //sets focus to element
								  // placeCaretAtEnd(targ);
				      //           }
				      //         }
				      			for (var i = arrResult.length - 1; i >= 0; i--) {
					                console.log(arrResult[i]);
					                // var arrMistake = arrResult[i].split("-");
					                tname = tname.replace(arrContent[arrResult[i]], "<span style='color:red;'>" + arrContent[arrResult[i]] +"</span>");
					                targ.innerHTML = tname;
					                // targ.focus(); //sets focus to element
									placeCaretAtEnd(targ);
					                
					                // if (targ.innerHTML.indexOf(arrMistake[0].trim()) !== -1) {
					                //   targ.innerHTML = targ.innerHTML.replace(arrMistake[0].trim(), arrMistake[1].trim());
					                  
					                // }
					              }
				              
				          }
				      };
				    xhttp.open("POST", host, false);
				    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				    xhttp.send("content=" + tname);
				}
		    }
		}

	    // targ.addEventListener('keyup',sum,false);
	    // var focusedElement = document.activeElement;
	}
    // console.log("haha");
}