//get username (normal grade) session
function getSessionId() {
	return sessionStorage.getItem("session_id")==null?"":sessionStorage.getItem("session_id");
}
//get username (admin grade) session
function getAdminSessionId() {
	return sessionStorage.getItem("admin_session")==null?"":sessionStorage.getItem("admin_session");
}
//get username profile url
function getProfileURL() {
	return sessionStorage.getItem("profileURL")==null?"":sessionStorage.getItem("profileURL");
}
//split params
function getParams(){
	var idx = document.URL.indexOf('?');
	var params = new Array();
	if (idx != -1) {
		var pairs = document.URL.substring(idx+1, document.URL.length).split('&');
		for (var i=0; i<pairs.length; i++){
			nameVal = pairs[i].split('=');
			params[nameVal[0]] = nameVal[1];
		}
	}
	return params;
}
//everytime the windows load, few classes hide if user is logged in and others should display instead
window.onload = function() {
	if(getAdminSessionId() == "")
	{
		for (var i = 0; i < document.getElementsByClassName("showAdmin").length; i++) 
		{
			var x = document.getElementsByClassName("showAdmin");
			x[i].style.display = "none";
		}
		for (var i = 0; i < document.getElementsByClassName("showAdminLogin").length; i++) 
		{
			var x = document.getElementsByClassName("showAdminLogin");
			x[i].style.display = "block";
		}
	}
	else 
	{
		for (var i = 0; i < document.getElementsByClassName("showAdmin").length; i++) 
		{
			var x = document.getElementsByClassName("showAdmin");
			x[i].style.display = "block";
		}
		for (var i = 0; i < document.getElementsByClassName("showAdminLogin").length; i++) 
		{
			var x = document.getElementsByClassName("showAdminLogin");
			x[i].style.display = "none";
		}
	}
	if(getSessionId() != "") {
		for (var i = 0; i < document.getElementsByClassName("register").length; i++) 
		{
			var x = document.getElementsByClassName("register");
			x[i].style.display = "none";
		}
		for (var i = 0; i < document.getElementsByClassName("login").length; i++) 
		{
			var x = document.getElementsByClassName("login");
			x[i].style.display = "none";
		}
		for (var i = 0; i < document.getElementsByClassName("logout").length; i++) 
		{
			var x = document.getElementsByClassName("logout");
			x[i].style.display = "block";
		}
		for (var i = 0; i < document.getElementsByClassName("inventory").length; i++) 
		{
			var x = document.getElementsByClassName("inventory");
			x[i].style.display = "block";
		}
		for (var i = 0; i < document.getElementsByClassName("profile").length; i++) 
		{
			var x = document.getElementsByClassName("profile");
			x[i].style.display = "block";
		}
	}
	else {
		for (var i = 0; i < document.getElementsByClassName("inventory").length; i++) 
		{
			var x = document.getElementsByClassName("inventory");
			x[i].style.display = "none";
		}
		for (var i = 0; i < document.getElementsByClassName("register").length; i++) 
		{
			var x = document.getElementsByClassName("register");
			x[i].style.display = "block";
		}
		for (var i = 0; i < document.getElementsByClassName("logout").length; i++) 
		{
			var x = document.getElementsByClassName("logout");
			x[i].style.display = "none";
		}
		for (var i = 0; i < document.getElementsByClassName("login").length; i++) 
		{
			var x = document.getElementsByClassName("login");
			x[i].style.display = "block";
		}
		for (var i = 0; i < document.getElementsByClassName("profile").length; i++) 
		{
			var x = document.getElementsByClassName("profile");
			x[i].style.display = "none";
		}
	}
}
//remove user servlet 
function removeUser(param00)
{
	var txt;
    var r = confirm("Are you sure you want to remove " + param00 + " From the database?");
    if (r == true) {
        try {
			var xhr = new XMLHttpRequest();

			//remove user from database
			xhr.open("POST", 'http://localhost:8080/BooksForAll/UsersAdminServlet?session_id='+getAdminSessionId(), true);
			xhr.onreadystatechange = function () {
				if (xhr.readyState === 4 && xhr.status === 200) {
					var obj = JSON.parse(xhr.responseText);
					if(obj == "true") location.reload();//if success, then reload page
				}
			};
			var data = JSON.stringify({"target": param00});//target name to remove
			xhr.send(data);
		}
		catch (exception) {
			alert("Request failed");
		}
    } else {
        console.log("NAH");
    }
}
//return all profiles (admin feature)
function getAdminUsers()
{
	try {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", 'http://localhost:8080/BooksForAll/UsersAdminServlet?session_id='+getAdminSessionId(), true);
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				console.log(obj);
				var j = 1;
				var k = 0;
				var l = 0;
				for (var i = 0; i < obj.length; i++) 
				{
					//var id = obj[i].id;
					var target = document.createElement('tr');
					document.getElementById('coolList').appendChild(target);
					
					var target = document.createElement('td');
					target.innerHTML=obj[i].username;
					document.getElementsByTagName('tr')[j].appendChild(target);

					var target = document.createElement('td');
					target.innerHTML=obj[i].email;
					document.getElementsByTagName('tr')[j].appendChild(target);
					
					var target = document.createElement('td');
					target.innerHTML=obj[i].address;
					document.getElementsByTagName('tr')[j].appendChild(target);
					
					var target = document.createElement('td');
					target.innerHTML=obj[i].tel_num;
					document.getElementsByTagName('tr')[j].appendChild(target);
					
					var target = document.createElement('td');
					target.innerHTML=obj[i].nickName;
					document.getElementsByTagName('tr')[j].appendChild(target);
					
					var target = document.createElement('td');
					target.innerHTML=obj[i].desc;
					document.getElementsByTagName('tr')[j].appendChild(target);
					
					var target = document.createElement('td');
					target.setAttribute('class', 'coolIMG');
					document.getElementsByTagName('tr')[j].appendChild(target);
					
					var target = document.createElement('img');
					target.setAttribute('src', obj[i].photoURL);
					target.setAttribute('width', '24');
					target.setAttribute('height', '24');
					document.getElementsByClassName('coolIMG')[k].appendChild(target);
					
					var target = document.createElement('td');
					target.setAttribute('class', 'deleteIMG');
					document.getElementsByTagName('tr')[j].appendChild(target);
					
					var target = document.createElement('input');
					target.onclick = function() { removeUser(param00); };
					target.setAttribute('onClick', "removeUser('"+ obj[i].username+ "')");
					
					target.setAttribute('type', 'image');
					target.setAttribute('src', "images/delete.png");
					target.setAttribute('width', '24');
					target.setAttribute('height', '24');
						
					document.getElementsByClassName('deleteIMG')[l].appendChild(target);
					
					k++;
					l++;
					j++;
				}
			}
		};
		xhr.send();
	}
	catch (exception) {
        alert("Request failed");
    }
}
//verify review (book id, comment)
function verifyReview(param00, param01)
{
	try {
		var xhr = new XMLHttpRequest();
		xhr.open('POST', 'http://localhost:8080/BooksForAll/VerifyAdminServlet?session_id='+getAdminSessionId(), true);
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				if(obj != "true") location.reload();//if success, then reload
				
			}
		};
		var data = JSON.stringify({"target": param00, "comment": param01});
		xhr.send(data);
	}
	catch (exception) {
        alert("Request failed");
    }
}
//get log list (admin feature)
function getLogs()
{	
	try {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", 'http://localhost:8080/BooksForAll/LogAdminServlet?session_id='+getAdminSessionId(), true);
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				console.log(obj);
				var j = 1;
				for (var i = 0; i < obj.length; i++) 
				{
					var target = document.createElement('tr');
					document.getElementById('coolList').appendChild(target);
					
					var target = document.createElement('td');
					target.innerHTML=obj[i].orderID;
					document.getElementsByTagName('tr')[j].appendChild(target);
					
					var target = document.createElement('td');
					target.innerHTML=obj[i].username;
					document.getElementsByTagName('tr')[j].appendChild(target);

					var target = document.createElement('td');
					target.innerHTML=obj[i].bookName;
					document.getElementsByTagName('tr')[j].appendChild(target);
					
					var target = document.createElement('td');
					target.innerHTML=obj[i].price;
					document.getElementsByTagName('tr')[j].appendChild(target);
	
					j++;
				}
			}
		};
		xhr.send();
	}
	catch (exception) {
        alert("Request failed");
    }
}
//get all unverified reviews (admin feature)
function getUnverfiedReviews()
{
	try {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", 'http://localhost:8080/BooksForAll/VerifyAdminServlet?session_id='+getAdminSessionId(), true);
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				//console.log(obj);
				var j = 1;
				var k = 0;
				var l = 0;
				for (var i = 0; i < obj.length; i++) 
				{
					//var id = obj[i].id;
					var target = document.createElement('tr');
					document.getElementById('coolList').appendChild(target);
					
					var target = document.createElement('td');
					target.innerHTML=obj[i].bookName;
					document.getElementsByTagName('tr')[j].appendChild(target);
					
					var target = document.createElement('td');
					target.innerHTML=obj[i].username;
					document.getElementsByTagName('tr')[j].appendChild(target);

					var target = document.createElement('td');
					target.innerHTML=obj[i].review;
					document.getElementsByTagName('tr')[j].appendChild(target);
	
					var target = document.createElement('td');
					target.setAttribute('class', 'deleteIMG');
					document.getElementsByTagName('tr')[j].appendChild(target);
					
					var target = document.createElement('input');
					target.onclick = function() { verifyReview(param00, param01); };
					target.setAttribute('onClick', "verifyReview('"+ obj[i].reviewID+ "' , '" + obj[i].username + " ')");
					
					target.setAttribute('type', 'image');
					target.setAttribute('src', "images/verify.png");
					target.setAttribute('width', '24');
					target.setAttribute('height', '24');
						
					document.getElementsByClassName('deleteIMG')[l].appendChild(target);
					
					k++;
					l++;
					j++;
				}
			}
		};
		xhr.send();
	}
	catch (exception) {
        alert("Request failed");
    }
}
//read a book by clicking on it
function readBook(param00) 
{
	console.log(param00);
	var win = window.open('books/'+param00+'.html', '_blank');
	win.focus();
}
//get user inventory
function getInventory() {
	try {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", 'http://localhost:8080/BooksForAll/InventoryServlet?session_id='+getSessionId(), true);
		
		var isComment = "";
		isComment = "split";
		
		var isComment2 = "";
		isComment2 = "deckCard";
		
		var isComment3 = "";
		isComment3 = "alone";
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				console.log(obj);
				var a = 0;
				var b = 0;
				var c = 0;
				var j = 0;
				var k = 0;
				var l = 0;
				for (var i = 0; i < obj.length; i++) 
				{
					//console.log(obj[i].isLiked);
					var id = obj[i].id;
					var target = document.createElement('div');
					target.setAttribute('class', isComment);
					document.getElementById('inventoryDeck').appendChild(target);
					
					var target = document.createElement('div');
					target.setAttribute('class', 'card '+isComment3);
					document.getElementsByClassName(isComment)[j].appendChild(target);
					j++;
					
					var target = document.createElement('div');
					target.setAttribute('class', 'card-block');
					document.getElementsByClassName('card '+isComment3)[k].appendChild(target);
					k++;
					
					var target = document.createElement('h4');
					target.setAttribute('class', 'card-title');
					target.innerHTML = obj[i].name;
					target.onclick = function() { readBook(param00); };
					target.setAttribute('onClick', "readBook('"+obj[i].targetURL+"')");
					document.getElementsByClassName('card-block')[l].appendChild(target);
					
					var target = document.createElement('img');
					target.setAttribute('class', 'card-img-top');
					target.setAttribute('src', obj[i].imageURL);
					target.onclick = function() { readBook(param00); };
					target.setAttribute('onClick', "readBook('"+obj[i].targetURL+"')");
					document.getElementsByClassName('card-block')[l].appendChild(target);
					
					
					var target = document.createElement('p');
					target.setAttribute('class', 'card-text');
					target.innerHTML = obj[i].desc;
					document.getElementsByClassName('card-block')[l].appendChild(target);					
					
					
					var target = document.createElement('div');
					target.setAttribute('class', 'centerActions');
					document.getElementsByClassName('card-block')[l].appendChild(target);		
					l++;
					
					
					var target = document.createElement('div');
					target.setAttribute('class', 'third');
					
					target.innerHTML = obj[i].price;
					document.getElementsByClassName('centerActions')[a].appendChild(target);	
					
					var target = document.createElement('input');
					target.onclick = function() {buyBook(param00)};
					target.setAttribute('onClick', 'buyBook('+id+')');
					target.setAttribute('type', 'image');
					target.setAttribute('src', 'images/buy.png');
					target.setAttribute('width', '24');
					target.setAttribute('height', '24');
					document.getElementsByClassName('third')[b].appendChild(target);	
					b++;
					
					var target = document.createElement('div');
					target.setAttribute('class', 'third');
					target.innerHTML = obj[i].reviewsAmount;
					document.getElementsByClassName('centerActions')[a].appendChild(target);	
					
					var target = document.createElement('input');
					target.onclick = function() {commentBook(param00)};
					target.setAttribute('onClick', 'commentBook('+id+')');
					target.setAttribute('type', 'image');
					target.setAttribute('src', 'images/comment.png');
					target.setAttribute('width', '24');
					target.setAttribute('height', '24');
					document.getElementsByClassName('third')[b].appendChild(target);	
					b++;
					
					
					var target = document.createElement('div');
					target.setAttribute('class', 'third');
					target.innerHTML = obj[i].likesAmount;
					target.setAttribute('title', 'tooltip');
					document.getElementsByClassName('centerActions')[a].appendChild(target);	
		
					var target = document.createElement('input');
					
					
					if(getSessionId() != "" && obj[i].isLiked == true) {
						target.onclick = function() { unlikeBook(param00); };
						target.setAttribute('onClick', 'unlikeBook('+id+')');
						target.setAttribute('src', 'images/stars_yellow.png');
					} else {
						target.onclick = function() { likeBook(param00); };
						target.setAttribute('onClick', 'likeBook('+id+')');
						target.setAttribute('src', 'images/stars.png');
					}
					
					target.setAttribute('type', 'image');
					target.setAttribute('width', '24');
					target.setAttribute('height', '24');
					

					document.getElementsByClassName('third')[b].appendChild(target);


					var target = document.createElement('span');
					target.setAttribute('class', 'tooltiptext');
					var str = "";
					for(var woah=0; woah<obj[i].list.length; woah++)
					{
						if(woah+1 == obj[i].list.length)
							str += obj[i].list[woah].user;
						else
							str += obj[i].list[woah].user+",";
					}
					target.innerHTML = str;
					document.getElementsByClassName('third')[b].appendChild(target);					
					b++;
					
					a++;
				}
			}
		};
		xhr.send();
	}
	catch (exception) {
        alert("Request failed");
    }
}
//open up user profile
function userProfile(param00)
{
	location.replace("profile_admin.html?target="+param00);
}
//get user profile by target name (admin feature)
function getUserProfile() {
	var params = getParams();
	var target = unescape(params["target"]);
	try {
		var xhr = new XMLHttpRequest();
		xhr.open("POST", 'http://localhost:8080/BooksForAll/ProfileAdminServlet', true);
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				//console.log(obj);
				document.getElementById("profileName").value = obj.username;
				document.getElementById("profileEmail").value = obj.email;
				document.getElementById("profileAddress").value = obj.address;
				document.getElementById("profileTelNum").value = obj.tel_num;
				document.getElementById("profileNickName").value = obj.nickName;
				document.getElementById("profileDesc").value = obj.desc;
				document.getElementById("profilephotoIMG").src = obj.photoURL;
				document.getElementById("profilephotoURL").value = obj.photoURL;
			}
		};
		var data = JSON.stringify({"sessionid": getAdminSessionId(), "target": target});
		xhr.send(data);
	}
	catch (exception) {
        alert("Request failed");
    }
}
//get all books in the database(home page)
function getBooks(param00) {
	try {
		var xhr = new XMLHttpRequest();
		if(param00 == null) {
			xhr.open("GET", 'http://localhost:8080/BooksForAll/browseEbook?session_id='+getSessionId(), true);
		} else {
			xhr.open("GET", 'http://localhost:8080/BooksForAll/browseEbook/'+param00, true);
		}
		var isComment = "";
		if(param00 == null) { isComment = "split"; }
		else				{isComment = "split_class"; }
		
		var isComment2 = "";
		if(param00 == null) { isComment2 = "deckCard"; }
		else				{isComment2 = "commentdeckCard"; }
		
		var isComment3 = "";
		if(param00 == null) { isComment3 = "alone"; }
		else				{isComment3 = "comment_alone"; }
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				console.log(obj);
				var a = 0;
				var b = 0;
				var c = 0;
				var j = 0;
				var k = 0;
				var l = 0;
				var q = 0;
				for (var i = 0; i < obj.length; i++) 
				{
					var id = obj[i].id;
					var target = document.createElement('div');
					target.setAttribute('class', isComment);
					document.getElementById(isComment2).appendChild(target);
					
					var target = document.createElement('div');
					target.setAttribute('class', 'card '+isComment3);
					document.getElementsByClassName(isComment)[j].appendChild(target);
					j++;
					
					var target = document.createElement('div');
					target.setAttribute('class', 'card-block');
					document.getElementsByClassName('card '+isComment3)[k].appendChild(target);
					k++;
					
					var target = document.createElement('h4');
					target.setAttribute('class', 'card-title');
					target.innerHTML = obj[i].name;
					document.getElementsByClassName('card-block')[l].appendChild(target);
					
					var target = document.createElement('img');
					target.setAttribute('class', 'card-img-top');
					target.setAttribute('src', obj[i].imageURL);
					//target.setAttribute('src', "https://images.gr-assets.com/books/1320399351l/1885.jpg");
					document.getElementsByClassName('card-block')[l].appendChild(target);
					
					
					var target = document.createElement('p');
					target.setAttribute('class', 'card-text');
					target.innerHTML = obj[i].desc;
					document.getElementsByClassName('card-block')[l].appendChild(target);					
					
					
					var target = document.createElement('div');
					target.setAttribute('class', 'centerActions');
					document.getElementsByClassName('card-block')[l].appendChild(target);		
					l++;
					
					
					var target = document.createElement('div');
					target.setAttribute('class', 'third');
					
					target.innerHTML = obj[i].price;
					document.getElementsByClassName('centerActions')[a].appendChild(target);	
					
					var target = document.createElement('input');
					target.onclick = function() {buyBook(param00)};
					target.setAttribute('onClick', 'buyBook('+id+')');
					target.setAttribute('type', 'image');
					target.setAttribute('src', 'images/buy.png');
					target.setAttribute('width', '24');
					target.setAttribute('height', '24');
					document.getElementsByClassName('third')[b].appendChild(target);	
					b++;
					
					var target = document.createElement('div');
					target.setAttribute('class', 'third');
					target.innerHTML = obj[i].reviewsAmount;
					document.getElementsByClassName('centerActions')[a].appendChild(target);	
					
					var target = document.createElement('input');
					target.onclick = function() {commentBook(param00)};
					target.setAttribute('onClick', 'commentBook('+id+')');
					target.setAttribute('type', 'image');
					target.setAttribute('src', 'images/comment.png');
					target.setAttribute('width', '24');
					target.setAttribute('height', '24');
					document.getElementsByClassName('third')[b].appendChild(target);	
					b++;
					
					
					var target = document.createElement('div');
					target.setAttribute('class', 'third');
					target.innerHTML = obj[i].likesAmount;
					target.setAttribute('title', 'tooltip');
					document.getElementsByClassName('centerActions')[a].appendChild(target);	
		
					var target = document.createElement('input');
					
					//target.onclick = function() { likeBook(param00); };
					//target.setAttribute('onClick', 'likeBook('+id+')');
					
					target.setAttribute('type', 'image');
					target.setAttribute('src', 'images/stars.png');
					target.setAttribute('width', '24');
					target.setAttribute('height', '24');
					

					document.getElementsByClassName('third')[b].appendChild(target);


					var target = document.createElement('div');
					target.setAttribute('class', 'tooltiptext');
					document.getElementsByClassName('third')[b].appendChild(target);			
					
					var str = "";
					if(getAdminSessionId() == "")
					{//provide a clickable interface for the admin to enter other user profiles
						for(var woah=0; woah<obj[i].list.length; woah++)
						{
							var target = document.createElement('span');
							if(woah+1 == obj[i].list.length)
								target.innerHTML = obj[i].list[woah].user;
							else
								target.innerHTML = obj[i].list[woah].user + ", ";
							document.getElementsByClassName('tooltiptext')[q].appendChild(target);
						}
					} else {	
						for(var woah=0; woah<obj[i].list.length; woah++)
						{
							var target = document.createElement('span');
							target.setAttribute('class', 'clickUser');
							target.innerHTML = obj[i].list[woah].user;
							
							target.onclick = function() {userProfile(param00)};
							target.setAttribute('onClick', "userProfile('"+obj[i].list[woah].user+"')");
							
							document.getElementsByClassName('tooltiptext')[q].appendChild(target);
						}
					}
					q++;
					b++;
					
					a++;
				}
			}
		};
		xhr.send();
	}
	catch (exception) {
        alert("Request failed");
    }
}
//get book by book id
function getBook(param00) {
	try {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", 'http://localhost:8080/BooksForAll/browseEbook/'+param00, true);
		var isComment = "";
		isComment = "split_class";
		
		var isComment2 = "";
		isComment2 = "commentdeckCard";
		
		var isComment3 = "";
		isComment3 = "comment_alone";
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				var a = 0;
				var b = 0;
				var c = 0;
				var j = 0;
				var k = 0;
				var l = 0;
				
				var id = obj.id;
				var target = document.createElement('div');
				target.setAttribute('class', isComment);
				document.getElementById(isComment2).appendChild(target);
				
				var target = document.createElement('div');
				target.setAttribute('class', 'card '+isComment3);
				document.getElementsByClassName(isComment)[j].appendChild(target);
				j++;
				
				var target = document.createElement('div');
				target.setAttribute('class', 'card-block');
				document.getElementsByClassName('card '+isComment3)[k].appendChild(target);
				k++;
				
				var target = document.createElement('h4');
				target.setAttribute('class', 'card-title');
				target.innerHTML = obj.name;
				document.getElementsByClassName('card-block')[l].appendChild(target);
				
				var target = document.createElement('img');
				target.setAttribute('class', 'card-img-top');
				target.setAttribute('src', obj.imageURL);
				document.getElementsByClassName('card-block')[l].appendChild(target);
				
				
				var target = document.createElement('p');
				target.setAttribute('class', 'card-text');
				target.innerHTML = obj.desc;
				document.getElementsByClassName('card-block')[l].appendChild(target);					
				
				
				var target = document.createElement('div');
				target.setAttribute('class', 'centerActions');
				document.getElementsByClassName('card-block')[l].appendChild(target);		
				l++;
				
				
				var target = document.createElement('div');
				target.setAttribute('class', 'third');
				
				target.innerHTML = obj.price;
				document.getElementsByClassName('centerActions')[a].appendChild(target);	
				
				var target = document.createElement('input');
				target.onclick = function() {buyBook(param00)};
				target.setAttribute('onClick', 'buyBook('+id+')');
				target.setAttribute('type', 'image');
				target.setAttribute('src', 'images/buy.png');
				target.setAttribute('width', '24');
				target.setAttribute('height', '24');
				document.getElementsByClassName('third')[b].appendChild(target);	
				b++;
				
				var target = document.createElement('div');
				target.setAttribute('class', 'third');
				target.innerHTML = obj.reviewsAmount;
				document.getElementsByClassName('centerActions')[a].appendChild(target);	
				
				var target = document.createElement('input');
				target.onclick = function() {commentBook(param00)};
				target.setAttribute('onClick', 'commentBook('+id+')');
				target.setAttribute('type', 'image');
				target.setAttribute('src', 'images/comment.png');
				target.setAttribute('width', '24');
				target.setAttribute('height', '24');
				document.getElementsByClassName('third')[b].appendChild(target);	
				b++;
				
				
				var target = document.createElement('div');
				target.setAttribute('class', 'third');
				target.innerHTML = obj.likesAmount;
				target.setAttribute('title', 'tooltip');
				document.getElementsByClassName('centerActions')[a].appendChild(target);	
	
				var target = document.createElement('input');
				
				target.onclick = function() { likeBook(param00); };
				target.setAttribute('onClick', 'likeBook('+id+')');
				
				target.setAttribute('type', 'image');
				target.setAttribute('src', 'images/stars.png');
				target.setAttribute('width', '24');
				target.setAttribute('height', '24');
				

				document.getElementsByClassName('third')[b].appendChild(target);


				var target = document.createElement('span');
				target.setAttribute('class', 'tooltiptext');
				var str = "";
				for(var woah=0; woah<obj.list.length; woah++)
				{
					if(woah+1 == obj.list.length)
						str += obj.list[woah].user;
					else
						str += obj.list[woah].user+",";
				}
				target.innerHTML = str;
				document.getElementsByClassName('third')[b].appendChild(target);					
				b++;
				
				a++;
			}
		};
		xhr.send();
	}
	catch (exception) {
        alert("Request failed");
    }
}
//unlike book by book name
function unlikeBook(param00) {
	console.log('unlike book');
	try {
		var xhr = new XMLHttpRequest();
		xhr.open('POST', 'http://localhost:8080/BooksForAll/UnlikeServlet?session_id='+getSessionId(), true);
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				if(obj == "true") location.reload();
			}
		};
		var data = JSON.stringify({"target": param00});
		xhr.send(data);
	}
	catch (exception) {
        alert("Request failed");
    }
}
//like a book by book id
function likeBook(param00) {
	try {
		var xhr = new XMLHttpRequest();

		xhr.open("POST", 'http://localhost:8080/BooksForAll/LikeServlet?session_id='+getSessionId(), true);
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				if(obj == "true") location.reload();
			}
		};
		var data = JSON.stringify({"target": param00});
		xhr.send(data);
	}
	catch (exception) {
        alert("Request failed");
    }
}
//open up buy GUI
function buyBook(param00) {
	console.log(param00);
	location.replace("buy.html?target="+param00);
}
//get all comments by by book id
function getComment(param00) {
	
	try {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", 'http://localhost:8080/BooksForAll/CommentServlet?target='+param00, true);
		
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				console.log(obj);
				var i = 1;
				var j = 1;
				console.log("SIZE" + obj.length);
				for (var q = 0; i < obj.length+1; q++) 
				{
					var target = document.createElement('div');
					target.setAttribute('class', 'comment-wrap');
					document.getElementById("comments").appendChild(target);
					
					var target = document.createElement('div');
					target.setAttribute('class', 'photo');
					document.getElementsByClassName('comment-wrap')[i].appendChild(target);

					var target = document.createElement('div');
					target.setAttribute('class', 'comment-block');
					document.getElementsByClassName('comment-wrap')[i].appendChild(target);		
					i++;
					
					
					var target = document.createElement('div');
					target.setAttribute('class', 'avatar');
					target.setAttribute('style', "background-image: url('" + obj[q].imageURL + "')");
					document.getElementsByClassName('photo')[j].appendChild(target);
					
					var target = document.createElement('p');
					target.setAttribute('class', 'comment-text');
					target.innerHTML = obj[q].username +" : " + obj[q].reviewContent;
					document.getElementsByClassName('comment-block')[j].appendChild(target);
					
					j++;
				}
			}
		};
		xhr.send();
	}
	catch (exception) {
        alert("Request failed");
    }
	
}
//check if user bought a book
function checkBuy() 
{
	if(getSessionId() == "") {
		document.getElementById('paymentForm').style.display = "none";
		document.getElementById('loginAnnounce').style.display = "block";
	}
	else {
		document.getElementById('paymentForm').style.display = "block";
		document.getElementById('loginAnnounce').style.display = "none";
	}
}
//check if user can comment on a book
function checkMe() 
{
	if(getSessionId() == "") {
		console.log("TEST");
		document.getElementById('checkMe').style.display = "none";
	}
	else {
		document.getElementById('checkMe').style.display = "block";
		document.getElementById('myProfilePhoto').style.backgroundImage = "url('"+ getProfileURL() + "')";
	}
}
//post new comment to a book
function postComment(form) 
{
	console.log("POST COMMENT");
	var params = getParams();
	var target = unescape(params["target"]);
	try {
		var xhr = new XMLHttpRequest();
		xhr.open("POST", 'http://localhost:8080/BooksForAll/CommentServlet?session_id='+getSessionId(), true);
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				console.log(obj);
				if(obj == "true") { 
					document.getElementById("error").innerHTML = "Your comment has been submitted<br>Please wait for verify!";
					document.getElementById("error").style.color = "#2ecc71";
				} else if(obj == "buy") {
					document.getElementById("error").innerHTML = "You can't comment on a book you haven't purchased";
				} else {
					document.getElementById("error").innerHTML = "Please login first";
				}
				document.getElementById("error").style.display = "block";
			}
		};
		var data = JSON.stringify({"target": target, "comment": form.commentText.value});
		xhr.send(data);
	}
	catch (exception) {
        alert("Request failed");
    }
}
//buy a new book
function buyForm(form) 
{	
	var params = getParams();
	var target = unescape(params["target"]);
	try {
		var xhr = new XMLHttpRequest();
		xhr.open("POST", 'http://localhost:8080/BooksForAll/PaymentServlet?session_id='+getSessionId(), true);
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				if(obj == "true") location.replace("inventory.html");//if success, redirect to inventory 
				else			  console.log("The book is found in your inventory");
			}
		};
		var data = JSON.stringify({"owner": form.name.value, "number": form.number.value, 
								   "month": form.month.value, "year": form.year.value,
									"cvv": form.cvv.value, "target" : target});
		xhr.send(data);
	}
	catch (exception) {
        alert("Request failed");
    }
}
//return all comments by book id
function getCommentByID() 
{
	var params = getParams();
	var target = unescape(params["target"]);
	console.log(target);
	window.addEventListener("load", getComment(target), false);
}
//get book info by book id 
function getBookByID()
{
	var params = getParams();
	var target = unescape(params["target"]);
	console.log(target);
	window.addEventListener("load", getBook(target), false);
}
//open up comment on book GUI
function commentBook(param00) {
	console.log(param00);
	location.replace("comment.html?target="+param00);
}
//register form
function postRegister(form) {
	console.log("post register");
	try {
		var xhr = new XMLHttpRequest();
		xhr.open("POST", 'http://localhost:8080/BooksForAll/RegisterServlet', true);
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				console.log(obj);
				if(obj == "true") //if true
				{
					document.getElementById("error").innerHTML = "Your account has been added!";
					document.getElementById("error").style.color = "#2ecc71";
					document.getElementById("error").style.display = "block";
				}
				else
				{
					document.getElementById("error").innerHTML = "User already exists in the database!";
					document.getElementById("error").style.display = "block";
				}
			}
		};
		var data = JSON.stringify({"username": form.name.value, "address" : form.address.value, "email": form.email.value, 
								   "tel_number": form.telephone.value, "password": form.password.value,
								   "nickName": form.nickname.value, "desc": form.desc.value,
								   "photoURL":  form.photoURL.value});//details
		xhr.send(data);
	}
	catch (exception) {
        alert("Request failed");
    }
}

function addBook(form) {
	console.log("post add book");
	try {
		var xhr = new XMLHttpRequest();
		xhr.open("POST", 'http://localhost:8080/BooksForAll/AddBookServlet', true);
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				if(obj == "true") 
				{
					location.reload();
				}
			}
		};
		var data = JSON.stringify({"id": 1, "name": form.name.value, 
								"price": form.price.value, "description": form.desc.value,
								"imgURL": form.imgURL.value});
		xhr.send(data);
	}
	catch (exception) {
        alert("Request failed");
    }
}
//admin logout (wipe session)
function adminLogout()
{
	try {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", 'http://localhost:8080/BooksForAll/LogoutAdminServlet?session_id='+getAdminSessionId(), true);
		
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				if(obj == true) {
					sessionStorage.setItem("admin_session",""); 
					window.location.href = "login_admin.html"; 
				}				
			}
		};
		xhr.send();
	}
	catch (exception) {
        alert("Request failed");
    }
}
//user logout (wipe session)
function logout()
{
	try {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", 'http://localhost:8080/BooksForAll/LogoutServlet?session_id='+getSessionId(), true);
		
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				if(obj == true) {
					sessionStorage.setItem("session_id","");
					localStorage.setItem('show', 'true'); 
					window.location.href = "index.html"; 
				}				
			}
		};
		xhr.send();
	}
	catch (exception) {
        alert("Request failed");
    }
}
//request user profile details
function profile()
{
	try {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", 'http://localhost:8080/BooksForAll/ProfileServlet?session_id='+getSessionId(), true);
		
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				if(obj == "false") {
					window.location.href = "login.html";
				} else {
					console.log(obj);
					document.getElementById("profileName").value = obj.username;
					document.getElementById("profileEmail").value = obj.email;
					document.getElementById("profileAddress").value = obj.address;
					document.getElementById("profileTelNum").value = obj.tel_num;
					document.getElementById("profileNickName").value = obj.nickName;
					document.getElementById("profileDesc").value = obj.desc;
					document.getElementById("profilephotoIMG").src = obj.photoURL;
					document.getElementById("profilephotoURL").value = obj.photoURL;
					
					sessionStorage.setItem("profileURL",obj.photoURL);
				}
			}
		};
		xhr.send();
	}
	catch (exception) {
        alert("Request failed");
    }
}
//update profile
function updateProfile(form)
{
	try {
		var xhr = new XMLHttpRequest();
		xhr.open("POST", 'http://localhost:8080/BooksForAll/ProfileServlet?session_id='+getSessionId(), true);
		
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				if(obj == "true") window.location.href = "profile.html";
			}
		};
		var data = JSON.stringify({"username": form.username.value, "email": form.email.value
									, "address": form.address.value, "tel_num": form.telNum.value
									, "nickName": form.nickName.value, "desc": form.desc.value
									, "photoURL": form.photoURL.value});//new details
		xhr.send(data);
	}
	catch (exception) {
        alert("Request failed");
    }
}

function postAdminLogin(form) {
	try {
		var xhr = new XMLHttpRequest();
		xhr.open("POST", 'http://localhost:8080/BooksForAll/LoginAdminServlet', true);
		
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				sessionStorage.setItem("admin_session",obj);
				if(obj == "false") {
					window.location.href = "login_admin.html";
				}
				else {
					window.location.href = "users_admin.html";
				}				
			}
		};
		var data = JSON.stringify({"username": form.name.value, "password": form.password.value});
		xhr.send(data);
	}
	catch (exception) {
        alert("Request failed");
    }
}

function postLogin(form) {
	try {
		var xhr = new XMLHttpRequest();
		xhr.open("POST", 'http://localhost:8080/BooksForAll/LoginServlet', true);
		
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var obj = JSON.parse(xhr.responseText);
				sessionStorage.setItem("session_id",obj);
				
				if(obj == "false") {
					document.getElementById("error").innerHTML = "User not found!";
					document.getElementById("error").style.display = "block";
					//window.location.href = "login.html";
				}
				else {
					window.location.href = "profile.html";
				}
							
			}
		};
		var data = JSON.stringify({"username": form.name.value, "password": form.password.value});
		xhr.send(data);
	}
	catch (exception) {
        alert("Request failed");
    }
}