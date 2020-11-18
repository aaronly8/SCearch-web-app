


function submitFunc(){
	let val = document.getElementById("review").value;
	
	//alert($('#review').val());
	
	//alert(val);
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/201ProjFrontEnd/ReviewServlet?review='+val+'&user=anon', true);
    xhr.send(null);
	xhr.onload = function (e) {
  		if (xhr.readyState === 4) {
    		if (xhr.status === 200) {
      			if(xhr.responseText.trim() === "success"){
					window.location.href = 'Dash.html';
				}
   			 } else {
     		 console.error(xhr.statusText);
    }
  }
};
}

//<button class="SubmitButton" id="Submit" onclick="changeView()" >Submit!</button> -->
    
  