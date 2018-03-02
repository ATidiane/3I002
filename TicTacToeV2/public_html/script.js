var w_canvas = h_canvas = 600;
var w_tuile = h_tuile = 170;
var padding = 30;
var grid, canvas, context;
var last_move = 0;

var emptyTile = buildEmptyTile(w_tuile, h_tuile);
var circleTile = buildCircleTile(w_tuile, h_tuile, "white");
var crossTile = buildCrossTile(w_tuile, h_tuile, "white");
var winCircleTile = buildCircleTile(w_tuile, h_tuile, "red");
var winCrossTile = buildCrossTile(w_tuile, h_tuile, "red");

var currentWelcomeMessage = "Tentative de connexion au serveur"
var endWelcomeMessage = "."

var connected = 0;
var player_id = -1;
var game_started = 0;
var animate = 1;


window.onload = function() {
    canvas = document.createElement("canvas");

    if (!canvas) {
        alert("Impossible to recover the canvas");
        return;
    }

    context = canvas.getContext('2d');
    if (!context) {
        alert("Impossible to recover the canvas context");
        return;
    }
    document.body.appendChild(canvas);
    canvas.id = "mycanvas";

    canvas.height = h_canvas;
    canvas.width = w_canvas;


    init();
	addResetButton();

    canvas.addEventListener("mousedown", doMouseDown, false);

	sendConnectionRequest();

	var connectServerInterval = setInterval(sendUpdateRequest, 1000);
	var welcomeMessageInterval = setInterval(animateWelcomeMessage, 1000);

}

function addResetButton() {
    var canv = document.getElementById("mycanvas");
    var button = document.createElement("input");
    button.type = "button";
    button.value = "Rejouer";
    button.setAttribute("id", "button");
    button.style.visibility = "hidden";
    button.onclick = sendResetRequest;
    var buttonWrapper = document.createElement("div");
    buttonWrapper.className = "button-wrapper";
	document.body.appendChild(buttonWrapper)
    //canv.parentNode.insertBefore(buttonWrapper, canv.nextSibling);
    buttonWrapper.appendChild(button);
}

function animateWelcomeMessage() {
	var welcomeH1 = document.getElementById("welcome");
	if (animate == 0){
		welcomeH1.innerHTML = currentWelcomeMessage;
		return;
	}
	
	welcomeH1.innerHTML = currentWelcomeMessage + endWelcomeMessage;
	
	if (endWelcomeMessage == "...") {
		endWelcomeMessage = ".";
	} else {
		endWelcomeMessage = endWelcomeMessage + ".";
	}
	
}

/* Return the tile indexes (i, j) with coordinates (x, y) or "null" */
function getTileIndexes(x, y) {
    
    var l = Math.floor(x/(w_tuile + padding));
    var c = Math.floor(y/(h_tuile + padding));
    var pad_x = x - l*(w_tuile + padding);
    var pad_y = y - c*(h_tuile + padding);
    
    console.log(pad_x +" , " + pad_y);
    
    if(pad_x < padding/2 || pad_x > w_tuile+padding/2) {
    	return null;
    }
    
    if(pad_y < padding/2 || pad_y > h_tuile+padding/2) {
    	return null;
    }
    
    console.log("C = " + c + ", L = " + l);
    return {i: c, j: l};
}

function init() {
	grid = new Array();
    
    for (var i = 0; i < 3; i++) {
    	grid[i] = new Array();
    	for (var j = 0; j < 3; j++) {
        	grid[i][j] = new Tile(j * w_canvas /3 + padding/2, i * h_canvas /3 + padding/2, emptyTile);
            grid[i][j].draw(context);
        }
    }
}

function doMouseDown(event) {
	var element = event.target;
    
    
	var x = event.pageX - element.offsetLeft;
    var y = event.pageY - element.offsetTop;
    
    console.log("X = " + x + ", Y = " + y);
    
    var indexes = getTileIndexes(x, y);
    
    console.log("i = " + indexes.i +", j = " + indexes.j);
    if (indexes == null || grid[indexes.i][indexes.j].currentTile != emptyTile) {
    	return;
    }

    sendMoveRequest(indexes.i, indexes.j);
	
    //tile.changeTile(last_move == 0 ? circleTile : crossTile);
    //last_move = (last_move + 1) % 2;
    //tile.draw(context);

}

function sendConnectionRequest() {
	var url = "";
	
	xmlRequest = new XMLHttpRequest();
	xmlRequest.open("GET", url + "connection?", true);
	
	xmlRequest.onreadystatechange = function(){
		if(xmlRequest.readyState == 4 && xmlRequest.status == 200) {
			console.log(xmlRequest.responseText);
			var messageTag = xmlRequest.responseXML.getElementsByTagName("message")[0];
			currentWelcomeMessage = messageTag.childNodes[0].nodeValue;
			//animateWelcomeMessage();
			
			if(player_id == -1) {
				console.log("Player id: " + player_id);
				player_id = parseInt(xmlRequest.responseXML.getElementsByTagName("playerId")[0].childNodes[0].nodeValue);
			}
			connected = 1;
		}
	}
	xmlRequest.send(null);
}

function sendUpdateRequest() {
	var url = "";
	params = "playerId=" + player_id;
	
	xmlRequest = new XMLHttpRequest();
	xmlRequest.open("GET", url + "update?" + params, true);
	
	xmlRequest.onreadystatechange = function(){
		update(xmlRequest);
	}
	xmlRequest.send(null);
}

function sendResetRequest() {
    var url = "";
    params = "playerId=" + player_id;

    xmlRequest = new XMLHttpRequest();
    xmlRequest.open("GET", url + "reset?"+params, true);

    xmlRequest.onreadystatechange = function(){
        update(xmlRequest);
    }
    xmlRequest.send(null);
}

function update(xmlRequest) {
	if(xmlRequest.readyState != 4 || xmlRequest.status != 200) {
		return;
	}

	var playerIdTag = xmlRequest.responseXML.getElementsByTagName("playerId")[0];
    var playerId = parseInt(playerIdTag.childNodes[0].nodeValue);

	var messageTag = xmlRequest.responseXML.getElementsByTagName("message")[0];
	currentWelcomeMessage = messageTag.childNodes[0].nodeValue;

	var nbPlayerTag = xmlRequest.responseXML.getElementsByTagName("nbPlayer")[0];
	var nbPlayer = parseInt(nbPlayerTag.childNodes[0].nodeValue);

	if (nbPlayer < 2) return;
	
	var nextTag = xmlRequest.responseXML.getElementsByTagName("nextToPlay")[0];
	var nextId = parseInt(nextTag.childNodes[0].nodeValue);

	var winnerTag = xmlRequest.responseXML.getElementsByTagName("winnerId")[0];
	var winnerId = parseInt(winnerTag.childNodes[0].nodeValue);

	if (nextId == player_id || winnerId > -1) {
		animate = 0;
	} else {
		animate = 1;
	}
	//animateWelcomeMessage();
	

	// Color the winner line (if any) 
	for(var i = 0; i < 3; i++) {
		for(var j = 0; j < 3; j++){
			var cellTag = xmlRequest.responseXML.getElementsByTagName("cell")[i*3 + j];
			var cell = parseInt(cellTag.childNodes[0].nodeValue);
			
			var tile = grid[i][j];
			
			if(cell == -1 && tile.currentTile != emptyTile) {
				tile.changeTile(emptyTile);
				tile.draw(context);
			} else if(cell == 0 && tile.currentTile != circleTile && tile.currentTile != winCircleTile) {
				tile.changeTile(circleTile);
				tile.draw(context);
			} else if(cell == 1 && tile.currentTile != crossTile  && tile.currentTile != winCrossTile) {
				tile.changeTile(crossTile);
				tile.draw(context);
			}
		}
	}
	
	var winnerLineTag = xmlRequest.responseXML.getElementsByTagName("winnerLine")[0];
    var winnerLine = winnerLineTag.childNodes[0].nodeValue.split(',');

    

    if(winnerId != -1) {
        if(winnerId != 2) {
            for(var i = 0; i < 3; i++) {
				var r = winnerLine[i*2];
				var c = winnerLine[i*2+1];
				var tile = grid[r][c];
                if(tile.currentTile == circleTile && tile.currentTile != winCircleTile) {
					grid[r][c].changeTile(winCircleTile);
					grid[r][c].draw(context);
                } else if(tile.currentTile == crossTile && tile.currentTile != winCrossTile) {
					grid[r][c].changeTile(winCrossTile);
					grid[r][c].draw(context);
				}
            }
        }
        document.getElementById("button").style.visibility = "visible";
    } else {
		document.getElementById("button").style.visibility = "hidden";
	}
}

/* 
Send an XML request (Ajax)
Ask to the server to execute the move (i, j)
 */
function sendMoveRequest(i, j) {
	var url = "";
	var params = "playerId="  + escape(player_id) + "&i=" + escape(i) + "&j=" + escape(j);
	
	xmlRequest = new XMLHttpRequest();
	xmlRequest.open("GET", url + "move?" + params, true);
	
	xmlRequest.onreadystatechange = function(){
		update(xmlRequest);
	}
	xmlRequest.send(null);
}


function Tile(x, y, tile) {
	this.currentTile = tile;
    this.x = x;
    this.y = y;
    
    this.changeTile = function(newTile) {
    	this.currentTile = newTile;    
    }

    this.draw = function(context) {
        context.drawImage(this.currentTile, this.x, this.y);
    }
}

function buildEmptyTile(h, w) {

    var canv = document.createElement("canvas");
    canv.class = 'smallCanvas';
    canv.height = h;
    canv.width = w;
    
    var ctx = canv.getContext("2d");
    ctx.fillStyle = "skyblue";
    ctx.fillRect(0, 0, canv.height, canv.width);

    var tile = new Image();
    tile.src = canv.toDataURL();
    
    return tile;
}

function buildCircleTile(h, w, color) {

    var canv = document.createElement("canvas");
    canv.class = 'smallCanvas';
    canv.height = h;
    canv.width = w;
    
    var ctx = canv.getContext("2d");
    
    ctx.fillStyle = "skyblue";
    ctx.fillRect(0, 0, canv.height, canv.width);
    
    ctx.beginPath();
    ctx.arc(canv.height / 2, canv.width / 2, (canv.width / 2) - 30, 0, Math.PI * 2);
    ctx.lineWidth = 6;
    ctx.strokeStyle = color;
    ctx.stroke();

    var tile = new Image();
    tile.src = canv.toDataURL();
    
    return tile;
}

function buildCrossTile(h, w, color) {

    var canv = document.createElement("canvas");
    canv.class = 'smallCanvas';
    canv.height = h;
    canv.width = w;
    
    var ctx = canv.getContext("2d");
    
    ctx.fillStyle = "skyblue";
    ctx.fillRect(0, 0, canv.height, canv.width);
    
    ctx.beginPath();
    ctx.moveTo(30, 30);
    ctx.lineTo(w - 30, h -30);
    ctx.moveTo(30, w - 30);
    ctx.lineTo(h - 30, 30);
    ctx.lineWidth = 6;
    ctx.strokeStyle = color;
    ctx.stroke();
    
    var tile = new Image();
    tile.src = canv.toDataURL();
    
    return tile;
}
